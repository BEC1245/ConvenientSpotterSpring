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

    public void createTitle(String title){
        this.title = title;
    }

    public void createContent(String content){
        this.content = content;
    }

    public void createImageName(String imageName){
        this.imageName = imageName;
    }

}
