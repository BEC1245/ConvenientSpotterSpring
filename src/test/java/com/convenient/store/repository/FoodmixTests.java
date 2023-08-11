package com.convenient.store.repository;

import com.convenient.store.common.dto.ScrollRequestDTO;
import com.convenient.store.common.dto.ScrollResponseDTO;
import com.convenient.store.foodmix.entity.Foodmix;
import com.convenient.store.foodmix.entity.FoodmixProduct;
import com.convenient.store.foodmix.repository.FoodmixRepository;
import com.convenient.store.product.entity.Product;
import com.convenient.store.user.entity.Users;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.parameters.P;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Log4j2
@SpringBootTest
public class FoodmixTests {

    @Autowired
    private FoodmixRepository foodmixRepository;

    @Test
    public void list_1(){

        ScrollRequestDTO requestDTO = ScrollRequestDTO.builder().build();

        log.info(foodmixRepository.getList(requestDTO));
    }

    @Test
    public void insert_1(){

        Users users = Users.builder().id(2L).build();

        Foodmix foodmix = Foodmix.builder()
                .title("리이뷰")
                .content("대충 섞어 먹으면 됩니다")
                .users(users)
                .imageName("someimage.jpg")
                .build();

        Product product1 = Product.builder().id(1L).build();
        Product product2 = Product.builder().id(2L).build();

        foodmix.addProduct(product1);
        foodmix.addProduct(product2);

        foodmixRepository.save(foodmix);

    }

    @Test
    @Transactional
    public void read_1(){

        log.info(foodmixRepository.getOneFoodmix(1L));

    }


}
