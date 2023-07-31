package com.convenient.store.user.repository;

import com.convenient.store.user.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users, Long> { }
