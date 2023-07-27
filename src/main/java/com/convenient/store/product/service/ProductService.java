package com.convenient.store.product.service;

import com.convenient.store.product.dto.PageRequestDTO;
import com.convenient.store.product.dto.PageResponseDTO;
import com.convenient.store.product.dto.ProductListWithRcntDTO;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface ProductService {

    PageResponseDTO<ProductListWithRcntDTO> list(PageRequestDTO pageRequestDTO);

}
