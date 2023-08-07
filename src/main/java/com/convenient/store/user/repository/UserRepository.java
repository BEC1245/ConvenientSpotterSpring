package com.convenient.store.user.repository;

import com.convenient.store.user.entity.Users;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<Users, Long> {

    @EntityGraph(attributePaths = {"roles"})
    @Query("select u from Users u where u.email = :email")
    Users getUser(@Param("email") String email);

}
