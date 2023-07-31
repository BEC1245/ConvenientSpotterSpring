package com.convenient.store.product.controller;

import com.convenient.store.product.common.dto.ScrollRequestDTO;
import com.convenient.store.product.common.dto.ScrollResponseDTO;
import com.convenient.store.product.service.ReviewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@CrossOrigin
@RestController
@RequestMapping("/review/")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @GetMapping("list")
    public ScrollResponseDTO list(ScrollRequestDTO scrollRequestDTO){

        log.info("GET / reviewList");
        log.info(scrollRequestDTO.getId() + " / " + scrollRequestDTO.getCursor());

        return reviewService.getList(scrollRequestDTO);
    }

}
