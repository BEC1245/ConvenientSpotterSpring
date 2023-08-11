package com.convenient.store.foodmix.entity;

import com.convenient.store.product.entity.Product;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Builder
@ToString
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class FoodmixProduct {

    @ManyToOne
    private Product product;
    private int ord;

}
