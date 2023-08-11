package com.convenient.store.foodmix.service;

import com.convenient.store.common.dto.ScrollRequestDTO;
import com.convenient.store.common.dto.ScrollResponseDTO;
import com.convenient.store.foodmix.repository.FoodmixRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class FoodmixServiceImpl implements FoodmixService {

    @Autowired
    private FoodmixRepository foodmixRepository;

    @Override
    public ScrollResponseDTO getFoodmixList(ScrollRequestDTO requestDTO) {
        return foodmixRepository.getList(requestDTO);
    }
}
