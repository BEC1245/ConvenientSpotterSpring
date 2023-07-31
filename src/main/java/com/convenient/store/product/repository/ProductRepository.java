package com.convenient.store.product.repository;

import com.convenient.store.product.entity.Product;
import com.convenient.store.product.repository.search.ProductSearch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductRepository extends JpaRepository<Product, Long>, ProductSearch {

}
