package com.convenient.store.product.controller;

import com.convenient.store.common.dto.ScrollRequestDTO;
import com.convenient.store.common.dto.ScrollResponseDTO;
import com.convenient.store.common.utill.FileUploader;
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
    public ReviewDTO readImg(@PathVariable("id") Long id){
        return  reviewService.getReview(id);
    }

    @PostMapping("")
    public Map<String, Long> regist(ReviewDTO reviewDTO){

        log.info(reviewDTO);

        List<String> fileNames = fileUploader.uploadFile("review", reviewDTO.getFiles(), 75, 75, true);
        reviewDTO.setImgs(fileNames);

        Long id = reviewService.regist(reviewDTO);

        return Map.of("result", 1L);

    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Long id){
        reviewService.deleteReview(id);
    }

    @PutMapping("")
    public void modify(ReviewDTO reviewDTO){
        log.info("Put / Review List");
        log.info(reviewDTO);

        if(reviewDTO.getFiles() != null && reviewDTO.getFiles().size() != 0) {
            List<String> files = fileUploader.uploadFile("review", reviewDTO.getFiles(), 75, 75, true);

            files.stream().forEach(ele -> reviewDTO.getImgs().add(ele));
        }

        reviewService.updateReview(reviewDTO);

    }

}
