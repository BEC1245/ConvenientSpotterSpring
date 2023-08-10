package com.convenient.store.product.service;

import com.convenient.store.common.utill.FileUploader;
import com.convenient.store.product.dto.ReviewDTO;
import com.convenient.store.product.dto.ReviewListDTO;
import com.convenient.store.common.dto.ScrollRequestDTO;
import com.convenient.store.common.dto.ScrollResponseDTO;
import com.convenient.store.product.entity.Product;
import com.convenient.store.product.entity.Review;
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

    @Autowired
    private FileUploader fileUploader;

    @Override
    public ScrollResponseDTO<ReviewListDTO> getList(ScrollRequestDTO scrollRequestDTO) {
        return reviewRepository.getReviewList(scrollRequestDTO);
    }

    @Override
    public Long regist(ReviewDTO reviewDTO) {

        Users users = Users.builder().id(reviewDTO.getUser_id()).build();

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

        reviewDTO.setUser_id(review.getUsers().getId());

        reviewDTO.setImgs(review.getImgs().stream().map(ele -> ele.getImageName()).collect(Collectors.toList()));

        return reviewDTO;

    }

    @Override
    public void deleteReview(Long id) {

        Optional<Review> getReview = reviewRepository.findById(id);

        Review review = getReview.orElseThrow();

        List<String> remainFileNames = review.getImgs().stream().map(ele -> ele.getImageName()).collect(Collectors.toList());

        fileUploader.deleteFile("review", remainFileNames);

        review.onDelflag();

        reviewRepository.save(review);

    }

    // 추가한 파일 이름 + 삭제한 파일 이름 + 기존의 이름
    @Override
    public void updateReview(ReviewDTO reviewDTO) {

        // 현 데이터를 가져온다
        Optional<Review> getReview = reviewRepository.findById(reviewDTO.getId());

        Review review = getReview.orElseThrow();

        // 있던 정보를 수정한다.
        review.createScore(reviewDTO.getScore());
        review.createContent(reviewDTO.getContent());

        // 수정전 파일이름들을 가져온다.
        List<String> oldFileNames = review.getImgs().stream().map(ele -> ele.getImageName()).collect(Collectors.toList());
        review.cleanImgs();

        // 현재 있는 이미지를 db에 넣는다.
        reviewDTO.getImgs().stream().forEach(ele -> review.insertImgs(ele));

        reviewRepository.save(review);

        List<String> remainFileNames = oldFileNames.stream().filter(ele -> reviewDTO.getImgs().indexOf(ele) == -1).collect(Collectors.toList());

        fileUploader.deleteFile("review", remainFileNames);

    }
}
