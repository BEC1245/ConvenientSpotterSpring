package com.convenient.store.repository;

import com.convenient.store.product.dto.PageRequestDTO;
import com.convenient.store.product.dto.ProductListWithRcntDTO;
import com.convenient.store.product.repository.ProductRepository;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@Log4j2
@SpringBootTest
public class ProductTests {

    @Autowired
    private ProductRepository repository;

    @Test
    public void productListTest(){

        PageRequestDTO pageRequestDTO = PageRequestDTO.builder()
                .orderBy("rv")
                .keyword("2+1")
                .searchType("e")
                .build();

        List<ProductListWithRcntDTO> list = repository.list(pageRequestDTO).getList();

        for(ProductListWithRcntDTO dto : list){

            log.info(dto);

        }


    }

}
