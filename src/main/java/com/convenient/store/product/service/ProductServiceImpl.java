package com.convenient.store.product.service;

import com.convenient.store.product.dto.PageRequestDTO;
import com.convenient.store.product.dto.PageResponseDTO;
import com.convenient.store.product.dto.ProductListWithRcntDTO;
import com.convenient.store.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    ProductRepository productRepository;

    @Override
    public PageResponseDTO<ProductListWithRcntDTO> list(PageRequestDTO pageRequestDTO) {
        return productRepository.list(pageRequestDTO);
    }

}
