package com.convenient.store.foodmix.service;

import com.convenient.store.common.dto.ScrollRequestDTO;
import com.convenient.store.common.dto.ScrollResponseDTO;
import com.convenient.store.foodmix.dto.FoodmixDTO;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface FoodmixService {

    ScrollResponseDTO getFoodmixList(ScrollRequestDTO requestDTO);

    FoodmixDTO getOneFoodmix(Long id);

    void registFoodmix(FoodmixDTO foodmixDTO);

    void modifyFoodmix(FoodmixDTO foodmixDTO);

    void deleteFoodmix(Long id);
}
