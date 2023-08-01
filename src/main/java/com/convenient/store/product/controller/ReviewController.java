package com.convenient.store.product.controller;

import com.convenient.store.product.common.dto.ScrollRequestDTO;
import com.convenient.store.product.common.dto.ScrollResponseDTO;
import com.convenient.store.product.common.utill.FileUploader;
import com.convenient.store.product.dto.ReviewDTO;
import com.convenient.store.product.service.ReviewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Log4j2
@CrossOrigin
@RestController
@RequestMapping("/review/")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;
    private final FileUploader fileUploader;

    @GetMapping("list")
    public ScrollResponseDTO list(ScrollRequestDTO scrollRequestDTO){

        log.info("GET / reviewList");
        log.info(scrollRequestDTO.getId() + " / " + scrollRequestDTO.getCursor());

        return reviewService.getList(scrollRequestDTO);
    }

    @GetMapping("img/{id}")
    public List<String> readImg(@PathVariable("id") Long id){
        return  reviewService.getReviewImg(id);
    }

    @PostMapping("")
    public Map<String, Long> regist(ReviewDTO reviewDTO){

        log.info(reviewDTO);

        List<String> fileNames = fileUploader.uploadFile(reviewDTO.getFiles(), 75, 75);
        reviewDTO.setFileNames(fileNames);

        Long id = reviewService.regist(reviewDTO);

        return Map.of("result", 1L);

    }

}
