package com.convenient.store.product.service;

import com.convenient.store.product.common.dto.PageRequestDTO;
import com.convenient.store.product.common.dto.PageResponseDTO;
import com.convenient.store.product.dto.ProductDTO;
import com.convenient.store.product.dto.ProductListWithRcntDTO;
import com.convenient.store.product.dto.ProductWithRcntAvgDTO;
import com.convenient.store.product.entity.Product;
import com.convenient.store.product.repository.ProductRepository;
import com.convenient.store.product.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{

    private final ProductRepository productRepository;
    private final ReviewRepository reviewRepository;
    private final ModelMapper modelMapper;

    @Override
    public PageResponseDTO<ProductListWithRcntDTO> list(PageRequestDTO pageRequestDTO) {
        return productRepository.list(pageRequestDTO);
    }

    @Override
    public ProductWithRcntAvgDTO get(Long id) {

        // 상품 데이터 받기
        Optional<Product> object = productRepository.findById(id);

        Product product = object.orElseThrow();

        // 리뷰 숫자, 평균점수 구하는 코드
        List<Object[]> reviewCntRvg = reviewRepository.getCountAvg(id);

        ProductWithRcntAvgDTO dto = modelMapper.map(product, ProductWithRcntAvgDTO.class);

        if(reviewCntRvg != null && reviewCntRvg.size() != 0) {
            dto.setCount((Long) reviewCntRvg.get(0)[0]);
            dto.setAvg((Double) reviewCntRvg.get(0)[1]);
        }
        return dto;

    }

}
