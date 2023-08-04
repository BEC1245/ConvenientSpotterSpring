package com.convenient.store.common.dto;

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
