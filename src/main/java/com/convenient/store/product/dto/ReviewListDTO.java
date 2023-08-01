package com.convenient.store.product.dto;

import lombok.*;

import java.util.List;

@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReviewListDTO {

    private Long id;

    private int score;

    private String content;

    private String nickName;

    private String profile;

    private String mainImg;

}
