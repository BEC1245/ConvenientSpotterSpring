package com.convenient.store.product.service;

import com.convenient.store.product.dto.ReviewDTO;
import com.convenient.store.product.dto.ReviewListDTO;
import com.convenient.store.product.common.dto.ScrollRequestDTO;
import com.convenient.store.product.common.dto.ScrollResponseDTO;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface ReviewService {

    ScrollResponseDTO<ReviewListDTO> getList(ScrollRequestDTO scrollRequestDTO);

    Long regist(ReviewDTO reviewDTO);

    ReviewDTO getReview(Long id);

    void deleteReview(Long id);

}
