package com.convenient.store.product.common.dto;

import lombok.*;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ScrollRequestDTO {

    private Long id;
    private int cursor;

}
