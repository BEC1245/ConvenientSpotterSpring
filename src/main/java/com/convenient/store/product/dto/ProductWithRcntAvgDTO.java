package com.convenient.store.product.dto;

import lombok.*;

import java.time.LocalDateTime;

@Data
@ToString
public class ProductWithRcntAvgDTO{

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

    private Double avg;
    private Long count;
}
