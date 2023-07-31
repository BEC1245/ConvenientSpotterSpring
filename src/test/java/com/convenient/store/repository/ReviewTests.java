package com.convenient.store.repository;

import com.convenient.store.product.common.dto.ScrollRequestDTO;
import com.convenient.store.product.entity.Product;
import com.convenient.store.product.entity.Review;
import com.convenient.store.product.entity.ReviewImg;
import com.convenient.store.product.repository.ReviewRepository;
import com.convenient.store.user.entity.Users;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@Log4j2
@SpringBootTest
public class ReviewTests {

    @Autowired
    private ReviewRepository reviewRepository;

    @Test
    public void insert_1(){

        // 리뷰 이미지 삽입
        List<ReviewImg> imgs = new ArrayList<>();

        ReviewImg reviewImg = ReviewImg.builder()
                .ord(0)
                .imageName("someimg.jpg")
                .build();

        imgs.add(reviewImg);

        // 유저 선택
        Users users = Users.builder()
                .email("user005@notgmail.com")
                .build();

        // 상품 선택
        Product product = Product.builder()
                .id(1L)
                .build();

        Review review = Review.builder()
                .score(4)
                .content("느낄 수 있습니다. 향긋한 플레이버로 감동을 선사하며, 물과 쉽게 혼합되어 편리하게 섭취할 수 있습니다.\n" +
                        "비타민의 힘을 경험하고 싶다면, 지금 비타 500을 선택하세요! 더 건강하고 활기찬 삶을 즐길 수 있는 비결이 여기에 있습니다. 비타 500과 함께라면, 당신은 최고 버전의 자신을 만날 수 있습니다.\n")
                .imgs(imgs)
                .users(users)
                .product(product)
                .build();

        reviewRepository.save(review);

    }

    @Test
    public void list_1(){

        ScrollRequestDTO scrollRequestDTO = ScrollRequestDTO.builder()
                .cursor(0)
                .id(1L)
                .build();

        Assertions.assertNotNull(reviewRepository);
        log.info(reviewRepository.getReviewList(scrollRequestDTO));
    }

    @Test
    public void select_1(){

        List<Object[]> a = reviewRepository.getCountAvg(1L);

        log.info(a.size());
        log.info((Long) a.get(0)[0]);
        log.info((Double) a.get(0)[1]);
    }

}
