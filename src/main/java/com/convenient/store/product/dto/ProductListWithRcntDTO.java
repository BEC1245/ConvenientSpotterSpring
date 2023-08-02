package com.convenient.store.product.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ProductListWithRcntDTO {

    private Long id;
    private String pname;
    private int price;
    private String state;
    private String sname;
    private String img;
    private double avgScore;
    private Long reviewCount;

}
