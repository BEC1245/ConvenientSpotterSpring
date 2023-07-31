package com.convenient.store.product.service;

import com.convenient.store.product.dto.ReviewListDTO;
import com.convenient.store.product.common.dto.ScrollRequestDTO;
import com.convenient.store.product.common.dto.ScrollResponseDTO;
import com.convenient.store.product.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Override
    public ScrollResponseDTO<ReviewListDTO> getList(ScrollRequestDTO scrollRequestDTO) {
        return reviewRepository.getReviewList(scrollRequestDTO);
    }
}
