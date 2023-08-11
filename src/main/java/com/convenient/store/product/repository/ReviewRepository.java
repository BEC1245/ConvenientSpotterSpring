package com.convenient.store.product.repository;

import com.convenient.store.product.entity.Review;
import com.convenient.store.product.repository.search.ReviewSearch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long>, ReviewSearch {

    // 원본 select count(r), avg(r.score) from Review r left outer join Product p on r.product = p where p.id = :id and r.delflag = false group by p

    @Query("select count(r), avg(r.score) from Review r left outer join Product p on r.product = p left outer join Users u on r.users = u where p.id = :id and r.delflag = false and u.delflag = false group by p")
    List<Object[]> getCountAvg(@Param("id") Long id);

}
