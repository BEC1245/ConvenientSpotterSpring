package com.convenient.store.product.dto;

import lombok.Data;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Data
@Log4j2
@ToString
public class PageResponseDTO<E> {

    // response로 전달 하는 패이지 정보
    // 내용, 전체값, 이전, 다음, 패이지의 숫자

    // DB
    private List<E> list;
    private Long total;

    // calculate
    private boolean next, prev;
    private List<Integer> pageNums;
    private int page, size, limit, start, end;
    private PageRequestDTO pageRequestDTO;

    public PageResponseDTO(List<E> list, Long total, PageRequestDTO requestDTO){
        this.list = list;
        this.total = total;

        this.pageRequestDTO = requestDTO;
        this.page = requestDTO.getPage();
        this.size = requestDTO.getSize();
        this.limit = requestDTO.getLimit();

        int tempEnd = (int) Math.ceil((this.page * this.limit)/(double)total) * this.size;

        log.info(tempEnd + " / tempEnd");

        this.start = tempEnd - (this.size - 1);
        this.prev = this.start != 1;

        int realEnd = (int) Math.ceil(total/(double)this.limit);

        log.info(realEnd + " / tempEnd");

        this.end = realEnd > tempEnd ? tempEnd : realEnd;
        this.next = realEnd > tempEnd;
        this.pageNums = IntStream.rangeClosed(start, end).boxed().collect(Collectors.toList());

    }

}
