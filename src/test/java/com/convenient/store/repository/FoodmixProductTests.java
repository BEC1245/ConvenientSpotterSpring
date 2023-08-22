package com.convenient.store.repository;

import com.convenient.store.foodmix.entity.Foodmix;
import com.convenient.store.foodmix.entity.FoodmixProduct;
import com.convenient.store.foodmix.repository.FoodmixProductRepository;
import com.convenient.store.product.entity.Product;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Log4j2
@SpringBootTest
public class FoodmixProductTests {

    @Autowired
    FoodmixProductRepository foodmixProductRepository;

    @Test
    public void insert_1() {

        IntStream.range(0, 5).boxed().forEach(ele -> {

            FoodmixProduct foodmixProduct = FoodmixProduct.builder()
                    .foodmix(Foodmix.builder().id(1L).build())
                    .product(Product.builder().id(ele.longValue() + 1L).build())
                    .ord(ele)
                    .build();

            foodmixProductRepository.save(foodmixProduct);

        });
    }

    @Test
    @Transactional
    public void select_1(){

        log.info(foodmixProductRepository.getOneByMixId(1L));

    }

}
