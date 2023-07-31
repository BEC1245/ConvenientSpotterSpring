package com.convenient.store.repository;

import com.convenient.store.product.common.dto.PageRequestDTO;
import com.convenient.store.product.common.dto.PageResponseDTO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Log4j2
@SpringBootTest
public class PageDTOTests {

    @Test
    public void generateTest(){
        PageRequestDTO pageRequestDTO = PageRequestDTO.builder().build();

        List<Integer> exList = new ArrayList<>();
        exList = IntStream.rangeClosed(1, 220).boxed().collect(Collectors.toList());

        log.info(new PageResponseDTO(exList, (long) exList.size() + 1, pageRequestDTO));
    }

}
