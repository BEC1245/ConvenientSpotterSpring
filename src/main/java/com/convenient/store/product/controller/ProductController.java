package com.convenient.store.product.controller;

import com.convenient.store.product.dto.PageRequestDTO;
import com.convenient.store.product.dto.PageResponseDTO;
import com.convenient.store.product.dto.ProductDTO;
import com.convenient.store.product.service.ProductService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Log4j2
@RestController
@RequestMapping("/product/")
@CrossOrigin
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping("list")
    public PageResponseDTO getList(PageRequestDTO pageRequestDTO){

        PageResponseDTO pageResponseDTO = productService.list(pageRequestDTO);

        return productService.list(pageRequestDTO);
    }

    @GetMapping("{id}")
    public ProductDTO getOne(@PathVariable("id") Long id){
        return productService.get(id);
    }


}
