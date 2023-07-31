package com.convenient.store.product.dto;

import lombok.*;

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

}
