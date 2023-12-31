package com.convenient.store.foodmix.repository;

import com.convenient.store.foodmix.entity.Foodmix;
import com.convenient.store.foodmix.repository.search.FoodmixSearch;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface FoodmixRepository extends JpaRepository<Foodmix, Long>, FoodmixSearch {

}
