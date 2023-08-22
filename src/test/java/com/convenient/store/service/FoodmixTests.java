package com.convenient.store.service;

import com.convenient.store.foodmix.dto.FoodmixDTO;
import com.convenient.store.foodmix.service.FoodmixService;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@Log4j2
@SpringBootTest
public class FoodmixTests {

    @Autowired
    private FoodmixService foodmixService;

    @Test
    public void select_1(){

        log.info(foodmixService.getOneFoodmix(1L));

    }

    @Test
    public void insert_1(){

        List<Long> product_id = List.of(5L, 6L);

        FoodmixDTO foodmixDTO = FoodmixDTO.builder()
                .title("제에ㅔㅔㅔ목")
                .content("이 상품은 어쩌구 저쩌구")
                .users_id(24L)
                .imageName("이쁜 이미지.jpg")
                .product_id(product_id)
                .build();

        foodmixService.registFoodmix(foodmixDTO);

    }

}
