package com.convenient.store.product.repository.search;

import com.convenient.store.product.dto.ReviewListDTO;
import com.convenient.store.product.common.dto.ScrollRequestDTO;
import com.convenient.store.product.common.dto.ScrollResponseDTO;

public interface ReviewSearch {

    ScrollResponseDTO<ReviewListDTO> getReviewList(ScrollRequestDTO scrollRequestDTO);

}
