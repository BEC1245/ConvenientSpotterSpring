package com.convenient.store.foodmix.repository.search;

import com.convenient.store.common.dto.ScrollRequestDTO;
import com.convenient.store.common.dto.ScrollResponseDTO;
import com.convenient.store.foodmix.dto.FoodmixListDTO;
import com.convenient.store.foodmix.entity.Foodmix;
import com.convenient.store.foodmix.entity.FoodmixProduct;
import com.convenient.store.foodmix.entity.QFoodmix;
import com.convenient.store.foodmix.entity.QFoodmixProduct;
import com.convenient.store.product.entity.QProduct;
import com.convenient.store.user.entity.QUsers;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPQLQuery;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

public class FoodmixSearchImpl extends QuerydslRepositorySupport implements FoodmixSearch  {

    public FoodmixSearchImpl() {
        super(Foodmix.class);
    }

    @Override
    public ScrollResponseDTO<FoodmixListDTO> getList(ScrollRequestDTO scrollRequestDTO) {

        QFoodmix foodmix = QFoodmix.foodmix;
        QUsers users = QUsers.users;

        JPQLQuery<Foodmix> query = from(foodmix);
        query.innerJoin(users).on(foodmix.users.eq(users));

        Pageable pageable = PageRequest.of(scrollRequestDTO.getCursor(), 20, Sort.by("id").descending());
        this.getQuerydsl().applyPagination(pageable, query);

        JPQLQuery<FoodmixListDTO> sql = query.select(Projections.bean(FoodmixListDTO.class,
                foodmix.id,
                foodmix.title,
                foodmix.imageName,
                foodmix.users.nickName,
                foodmix.users.profile
                ));

        List<FoodmixListDTO> list = sql.fetch();

        Long total = sql.fetchCount();

        return new ScrollResponseDTO<>(list, total);
    }

}
