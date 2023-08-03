package com.convenient.store.product.dto;

import lombok.*;
import org.modelmapper.ModelMapper;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {

    private Long id;
    private String pname;
    private int price;
    private String content;
    private String state;
    private String sname;
    private String img;
    private Boolean delflag;
    private LocalDateTime regDate;
    private LocalDateTime modDate;

    private MultipartFile file;

}
