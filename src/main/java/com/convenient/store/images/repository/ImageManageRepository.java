package com.convenient.store.images.repository;

import com.convenient.store.images.entity.ImageManager;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageManageRepository extends JpaRepository<ImageManager, Long> { }
