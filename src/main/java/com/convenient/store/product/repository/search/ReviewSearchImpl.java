package com.convenient.store.product.repository.search;

import com.convenient.store.product.dto.ReviewListDTO;
import com.convenient.store.product.common.dto.ScrollRequestDTO;
import com.convenient.store.product.common.dto.ScrollResponseDTO;
import com.convenient.store.product.entity.QProduct;
import com.convenient.store.product.entity.QReview;
import com.convenient.store.product.entity.Review;
import com.convenient.store.user.entity.QUsers;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPQLQuery;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

public class ReviewSearchImpl extends QuerydslRepositorySupport implements ReviewSearch {

    public ReviewSearchImpl() {
        super(Review.class);
    }

    @Override
    public ScrollResponseDTO getReviewList(ScrollRequestDTO requestDTO) {

        // Q객체 삽입
        QReview review = QReview.review;
        QProduct product = QProduct.product;
        QUsers users = QUsers.users;

        // 쿼리 만들기
        JPQLQuery<Review> query = from(review);

        query.innerJoin(product).on(review.product.eq(product));
        query.innerJoin(users).on(review.users.eq(users));
        query.where(product.id.eq(requestDTO.getId()));

        Pageable pageable = PageRequest.of(requestDTO.getCursor(), 30, Sort.by("id"));
        this.getQuerydsl().applyPagination(pageable, query);

        // Projections로 뽑아오기
        JPQLQuery<ReviewListDTO> dto = query.select(Projections.bean(ReviewListDTO.class,
                review.id,
                review.score,
                review.content,
                users.nickName,
                users.profile
                ));

        List<ReviewListDTO> list = dto.fetch();

        Long total = dto.fetchCount();

        ScrollResponseDTO<ReviewListDTO> responseDTO = ScrollResponseDTO.<ReviewListDTO>builder()
                .list(list)
                .total(total)
                .build();

        return responseDTO;
    }
}
