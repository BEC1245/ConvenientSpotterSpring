package com.convenient.store.product.controller;

import com.convenient.store.product.dto.PageRequestDTO;
import com.convenient.store.product.dto.PageResponseDTO;
import com.convenient.store.product.service.ProductService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
@RequestMapping("/product/")
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping("list")
    public PageResponseDTO getList(PageRequestDTO pageRequestDTO){
        return productService.list(pageRequestDTO);
    }


}
