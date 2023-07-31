package com.convenient.store.repository;

import com.convenient.store.product.common.dto.PageRequestDTO;
import com.convenient.store.product.common.dto.PageResponseDTO;
import com.convenient.store.product.dto.ProductListWithRcntDTO;
import com.convenient.store.product.repository.ProductRepository;
import com.convenient.store.product.service.ProductService;
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

    @Autowired
    private ProductService productService;

    @Test
    public void productListTest(){

        PageRequestDTO pageRequestDTO = PageRequestDTO.builder()
                .build();

        PageResponseDTO<ProductListWithRcntDTO> result = repository.list(pageRequestDTO);

        log.info(result);
        log.info("--------------------");

        List<ProductListWithRcntDTO> list = result.getList();

        for(ProductListWithRcntDTO dto : list){
            log.info(dto);
        }
    }

    @Test
    public void getProductOne(){
        log.info(productService.get(1L));
    }

}
