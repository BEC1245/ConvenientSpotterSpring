package com.convenient.store.foodmix.repository;

import com.convenient.store.foodmix.entity.Foodmix;
import com.convenient.store.foodmix.entity.FoodmixProduct;
import com.convenient.store.foodmix.repository.search.FoodmixSearch;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FoodmixProductRepository extends JpaRepository<FoodmixProduct, Long> {

    @EntityGraph(attributePaths = "product")
    @Query(" select fp from FoodmixProduct fp left outer join fp.foodmix f left outer join fp.product where f.id = :id")
    List<FoodmixProduct> getOneByMixId(@Param("id") Long id);


}
