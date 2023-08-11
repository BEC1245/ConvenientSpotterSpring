package com.convenient.store.foodmix.controller;

import com.convenient.store.common.dto.ScrollRequestDTO;
import com.convenient.store.common.dto.ScrollResponseDTO;
import com.convenient.store.foodmix.dto.FoodmixListDTO;
import com.convenient.store.foodmix.service.FoodmixService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@CrossOrigin
@RestController
@RequestMapping("/foodmix/")
@RequiredArgsConstructor
public class FoodmixController {

    private final FoodmixService foodmixService;

    @GetMapping("list")
    public ScrollResponseDTO<FoodmixListDTO> getList(ScrollRequestDTO requestDTO){
        return foodmixService.getFoodmixList(requestDTO);
    }

}
