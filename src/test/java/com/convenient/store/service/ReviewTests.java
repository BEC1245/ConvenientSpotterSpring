package com.convenient.store.service;

import com.convenient.store.product.dto.ReviewDTO;
import com.convenient.store.product.service.ReviewService;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Log4j2
@SpringBootTest
public class ReviewTests {

    @Autowired
    private ReviewService reviewService;

    @Test
    public void insert_1(){

        List<String> fakeNames = new ArrayList<>();

        fakeNames.add("다비드 마르틴.jpg");
        fakeNames.add("샘페레.jpg");
        fakeNames.add("패드로 비달.jpg");

        ReviewDTO reviewDTO = ReviewDTO.builder()
                .score(1)
                .content("이 음식 구림")
                .imgs(fakeNames)
                .product_id(2L)
                .email("user009@notgmail.com")
                .build();

        log.info(reviewService.regist(reviewDTO));

    }

    @Test
    public void delete_1(){
        reviewService.deleteReview(36L);
    }
}
