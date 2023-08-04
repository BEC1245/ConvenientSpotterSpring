package com.convenient.store.product.controller;

import com.convenient.store.common.dto.PageRequestDTO;
import com.convenient.store.common.dto.PageResponseDTO;
import com.convenient.store.common.utill.FileUploader;
import com.convenient.store.product.dto.ProductDTO;
import com.convenient.store.product.dto.ProductWithRcntAvgDTO;
import com.convenient.store.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Log4j2
@CrossOrigin
@RestController
@RequestMapping("/product/")
@RequiredArgsConstructor
public class ProductController {

    private final FileUploader fileUploader;

    public static class ProductException extends RuntimeException {
        public ProductException(String msg) { super(msg); }
    }

    @Autowired
    ProductService productService;

    @GetMapping("list")
    public PageResponseDTO getList(PageRequestDTO pageRequestDTO){

        PageResponseDTO pageResponseDTO = productService.list(pageRequestDTO);

        return productService.list(pageRequestDTO);
    }

    @GetMapping("{id}")
    public ProductWithRcntAvgDTO getOne(@PathVariable("id") Long id){
        return productService.get(id);
    }

    @PutMapping("")
    public String modify(ProductDTO productDTO){
        log.info(" PUT / modify ");
        log.info(productDTO);

        if(productDTO.getFile() != null && !productDTO.getFile().isEmpty()){
            String fileName = fileUploader.uploadFile("product", productDTO.getFile(), 0, 0, false);
            log.info(fileName + " / the file Name in productController");
            productDTO.setImg(fileName);
        }

        return productService.update(productDTO);
    }

    @DeleteMapping("{id}")
    public String delete(@PathVariable("id") Long id){
        return productService.delete(id);
    }


}
