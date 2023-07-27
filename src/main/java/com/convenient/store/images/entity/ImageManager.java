package com.convenient.store.images.entity;

import com.convenient.store.product.entity.Review;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@ToString(exclude = "images")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ImageManager {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Review review;

    @ElementCollection(fetch = FetchType.LAZY)
    @Builder.Default
    private List<Images> images = new ArrayList<>();

    public void addImages(String name, int ord){
        Images makeImg = Images.builder()
                .fileName(name)
                .ord(ord)
                .build();

        this.images.add(makeImg);
    }
}
