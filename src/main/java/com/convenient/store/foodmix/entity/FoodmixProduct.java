package com.convenient.store.foodmix.entity;

import com.convenient.store.product.entity.Product;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Builder
@Entity
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class FoodmixProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Foodmix foodmix;

    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;

    private int ord;

}
