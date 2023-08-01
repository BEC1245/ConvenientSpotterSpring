package com.convenient.store.product.entity;

import com.convenient.store.user.entity.Users;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Review extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int score;

    @Column(length = 1000)
    private String content;

    @JoinColumn(nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;

    @JoinColumn(nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Users users;

    @ElementCollection
    @Builder.Default
    private List<ReviewImg> imgs = new ArrayList<>();

    public void insertImgs(String fileNames){

        ReviewImg reviewImg = ReviewImg.builder()
                .imageName(fileNames)
                .ord(imgs.size())
                .build();

        imgs.add(reviewImg);
    }

}
