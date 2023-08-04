package com.convenient.store.product.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product extends BaseEntity {

    /* `pid` bigInt primary key auto_increment,
	`pname`	varchar(100) NOT NULL,
	`price`	int	NOT NULL,
	`desc`	varchar(1000) DEFAULT "해당 상품은 설명이 없습니다",
	`state`	varchar(50)	not null,
    `sname` varchar(100) NOT NULL,
    `img` varchar(300) not null,
    `delflag` boolean default 0,
    `regDate` datetime default now(),
    `modDate` datetime default now() */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false)
    private String pname;

    @Column(nullable = false)
    private int price;

    @Column(columnDefinition = "varchar(1000) DEFAULT '해당 상품은 설명이 없습니다'")
    private String content;

    @Column(length = 50, nullable = false)
    private String state;

    @Column(length = 100, nullable = false)
    private String sname;

    @Column(length = 300, nullable = false)
    private String img;

    public void createPname(String pname){
        this.pname = pname;
    }

    public void createPrice(int price){
        this.price = price;
    }

    public void createContent(String content){
        this.content = content;
    }

    public void createState(String state){
        this.state = state;
    }

    public void createSname(String sname){
        this.sname = sname;
    }

    public void createImg(String img){
        this.img = img;
    }
}
