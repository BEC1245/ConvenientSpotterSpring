package com.convenient.store.product.repository.search;

import com.convenient.store.product.dto.PageRequestDTO;
import com.convenient.store.product.dto.PageResponseDTO;
import com.convenient.store.product.dto.ProductListWithRcntDTO;

public interface ProductSearch {

    PageResponseDTO<ProductListWithRcntDTO> list(PageRequestDTO pageRequestDTO);

}
