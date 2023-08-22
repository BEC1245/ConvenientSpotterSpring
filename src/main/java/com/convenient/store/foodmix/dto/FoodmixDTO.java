package com.convenient.store.foodmix.dto;

import com.convenient.store.foodmix.entity.FoodmixProduct;
import com.convenient.store.product.dto.ProductDTO;
import com.convenient.store.user.entity.Users;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

// 꿀조합 정보를 담고있고 유저의 정보를 담고있는 dto

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class FoodmixDTO {

    private Long id;
    private String title;
    private String content;
    private String imageName;

    private Long users_id;
    private MultipartFile file;

    @Builder.Default
    private List<ProductDTO> products = new ArrayList<>();

    @Builder.Default
    private List<Long> product_id = new ArrayList<>();

}
