package com.convenient.store.foodmix.dto;

import lombok.*;

import java.util.List;

@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FoodmixListDTO {

    private Long id;
    private String title;
    private String imageName;
    private String nickName;
    private String profile;

}
