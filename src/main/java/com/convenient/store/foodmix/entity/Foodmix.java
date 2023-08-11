package com.convenient.store.foodmix.entity;


import com.convenient.store.product.entity.BaseEntity;
import com.convenient.store.product.entity.Product;
import com.convenient.store.user.entity.Users;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@Builder
@ToString(exclude = "users")
@NoArgsConstructor
@AllArgsConstructor
public class Foodmix extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Column(length = 500)
    private String content;

    @JoinColumn(nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Users users;

    private String imageName;

    @ElementCollection(fetch = FetchType.LAZY)
    @Builder.Default
    private List<FoodmixProduct> products = new ArrayList<>();

    public void addProduct(Product product){

        FoodmixProduct foodmixProduct = FoodmixProduct.builder()
                .product(product)
                .ord(this.products.size())
                .build();

        this.products.add(foodmixProduct);
    }

    public void clearProduct(){
        this.products.clear();
    }

}
