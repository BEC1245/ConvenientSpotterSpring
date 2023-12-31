package com.convenient.store.product.service;

import com.convenient.store.common.dto.PageRequestDTO;
import com.convenient.store.common.dto.PageResponseDTO;
import com.convenient.store.product.dto.ProductDTO;
import com.convenient.store.product.dto.ProductListWithRcntDTO;
import com.convenient.store.product.dto.ProductWithRcntAvgDTO;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface ProductService {

    PageResponseDTO<ProductListWithRcntDTO>
    list(PageRequestDTO pageRequestDTO);

    ProductWithRcntAvgDTO get(Long id);

    String update(ProductDTO productDTO);

    String delete(Long id);

}
