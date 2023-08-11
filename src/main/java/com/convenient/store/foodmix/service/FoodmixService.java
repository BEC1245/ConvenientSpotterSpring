package com.convenient.store.foodmix.service;

import com.convenient.store.common.dto.ScrollRequestDTO;
import com.convenient.store.common.dto.ScrollResponseDTO;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface FoodmixService {

    ScrollResponseDTO getFoodmixList(ScrollRequestDTO requestDTO);

}
