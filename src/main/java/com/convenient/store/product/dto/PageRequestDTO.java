package com.convenient.store.product.dto;

import lombok.*;

@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PageRequestDTO {

    // request로 받아야할 정보
    // 현 패이지, 패이지 크기, 내용물 표시 개수, 검색 키워드, 검색 타입
    @Builder.Default
    private int page = 1;

    @Builder.Default
    private int size = 10;

    @Builder.Default
    private int limit = 30;
    private String keyword;
    private String searchType;
    private String orderBy;

    public void setPage(int page){
        this.page = page <= 0 ? 1 : page;
    }
    public void setSize(int size){
        this.size = size <= 1 || size > 30 ? 10 : size;
    }
    public void setLimit(int limit){
        this.limit = limit <= 1 || limit > 100 ? 30 : limit;
    }

}
