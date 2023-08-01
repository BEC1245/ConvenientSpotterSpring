package com.convenient.store.product.dto;

import jakarta.persistence.Entity;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

// regist, modify, read에 사용될 ReviewDTO
@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReviewDTO {

    private Long review_id;
    private int score;
    private String content;
    private Long product_id;
    private String users_email;

    private List<String> fileNames;

    private List<MultipartFile> files;

}
