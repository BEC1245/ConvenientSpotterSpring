package com.convenient.store.product.service;

import com.convenient.store.product.dto.ReviewDTO;
import com.convenient.store.product.dto.ReviewListDTO;
import com.convenient.store.product.common.dto.ScrollRequestDTO;
import com.convenient.store.product.common.dto.ScrollResponseDTO;
import com.convenient.store.product.entity.Product;
import com.convenient.store.product.entity.Review;
import com.convenient.store.product.entity.ReviewImg;
import com.convenient.store.product.repository.ReviewRepository;
import com.convenient.store.user.entity.Users;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ScrollResponseDTO<ReviewListDTO> getList(ScrollRequestDTO scrollRequestDTO) {
        return reviewRepository.getReviewList(scrollRequestDTO);
    }

    @Override
    public Long regist(ReviewDTO reviewDTO) {

        Users users = Users.builder().email(reviewDTO.getEmail()).build();

        Product product = Product.builder().id(reviewDTO.getProduct_id()).build();

        Review review = Review.builder()
                .score(reviewDTO.getScore())
                .content(reviewDTO.getContent())
                .users(users)
                .product(product)
                .build();

        List<String> fileNames = reviewDTO.getImgs();

        if(fileNames != null && fileNames.size() > 0) {
            fileNames.forEach(ele -> review.insertImgs(ele));
        }

        Review result = reviewRepository.save(review);

        return result.getId();
    }

    @Override
    public ReviewDTO getReview(Long id) {

        Optional<Review> get = reviewRepository.findById(id);

        Review review = get.orElseThrow();

        ReviewDTO reviewDTO = modelMapper.map(review, ReviewDTO.class);

        reviewDTO.setNickName(review.getUsers().getNickName());

        reviewDTO.setImgs(review.getImgs().stream().map(ele -> ele.getImageName()).collect(Collectors.toList()));

        return reviewDTO;

    }

    @Override
    public void deleteReview(Long id) {

        Review review = Review.builder()
                .id(id)
                .build();

        reviewRepository.delete(review);

    }
}
