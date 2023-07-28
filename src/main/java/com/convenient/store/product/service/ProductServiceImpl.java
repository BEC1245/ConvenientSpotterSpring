package com.convenient.store.product.service;

import com.convenient.store.product.dto.PageRequestDTO;
import com.convenient.store.product.dto.PageResponseDTO;
import com.convenient.store.product.dto.ProductDTO;
import com.convenient.store.product.dto.ProductListWithRcntDTO;
import com.convenient.store.product.entity.Product;
import com.convenient.store.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{

    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;

    @Override
    public PageResponseDTO<ProductListWithRcntDTO> list(PageRequestDTO pageRequestDTO) {
        return productRepository.list(pageRequestDTO);
    }

    @Override
    public ProductDTO get(Long id) {

        Optional<Product> object = productRepository.findById(id);

        Product product = object.orElseThrow();

        return modelMapper.map(product, ProductDTO.class);
    }

}
