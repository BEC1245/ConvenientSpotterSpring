package com.convenient.store.foodmix.repository.search;

import com.convenient.store.common.dto.ScrollRequestDTO;
import com.convenient.store.common.dto.ScrollResponseDTO;
import com.convenient.store.foodmix.dto.FoodmixListDTO;

public interface FoodmixSearch {

    ScrollResponseDTO<FoodmixListDTO> getList(ScrollRequestDTO scrollRequestDTO);

}
