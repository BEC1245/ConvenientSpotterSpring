package com.convenient.store.common.dto;

import lombok.*;

import java.util.List;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ScrollResponseDTO<E> {
    private List<E> list;
    private Long total;
}
