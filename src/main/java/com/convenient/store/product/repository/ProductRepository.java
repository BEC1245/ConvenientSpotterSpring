package com.convenient.store.product.repository;

import com.convenient.store.product.entity.Product;
import com.convenient.store.product.repository.search.ProductSearch;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long>, ProductSearch { }
