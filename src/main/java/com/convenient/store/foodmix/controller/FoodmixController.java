package com.convenient.store.foodmix.controller;

import com.convenient.store.common.dto.ScrollRequestDTO;
import com.convenient.store.common.dto.ScrollResponseDTO;
import com.convenient.store.common.utill.FileUploader;
import com.convenient.store.foodmix.dto.FoodmixDTO;
import com.convenient.store.foodmix.dto.FoodmixListDTO;
import com.convenient.store.foodmix.service.FoodmixService;
import com.convenient.store.product.dto.ProductDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Log4j2
@CrossOrigin
@RestController
@RequestMapping("/foodmix/")
@RequiredArgsConstructor
public class FoodmixController {

    private final FoodmixService foodmixService;
    private final FileUploader fileUploader;

    @GetMapping("list")
    public ScrollResponseDTO<FoodmixListDTO> getList(ScrollRequestDTO requestDTO){

        log.info(" GET / getList in FoodmixController ");
        log.info(requestDTO + " data ");

        return foodmixService.getFoodmixList(requestDTO);

    }

//    @GetMapping("{id}")
//    public FoodmixDTO read(@PathVariable("id") Long id){
//
//        log.info(" GET / read in FoodmixController ");
//        log.info(id + " data ");
//
//        return foodmixService.getOneFoodmix(id);
//
//    }

//    @PostMapping("regist")
//    public Map<String, String> regist(FoodmixDTO foodmixDTO){
//
//        log.info(" POST / regist in FoodmixController ");
//        log.info(foodmixDTO + " data ");
//
//        String fileName = fileUploader.uploadFile("foodmix", foodmixDTO.getFile(), 550, 300, true);
//        foodmixDTO.setImageName(fileName);
//
//        foodmixService.registFoodmix(foodmixDTO);
//
//        return Map.of("result", "success");
//
//    }

//    @DeleteMapping("{id}")
//    public Map<String, String> delete(@PathVariable("id") Long id){
//
//        log.info(" DELETE / delete in FoodmixController ");
//        log.info(id + " data ");
//
//        foodmixService.deleteFoodmix(id);
//
//        return Map.of("result", "success");
//
//    }

}
