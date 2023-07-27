package com.convenient.store.repository;

import com.convenient.store.images.entity.Images;
import com.convenient.store.images.entity.ImageManager;
import com.convenient.store.images.repository.ImageManageRepository;
import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Log4j2
@SpringBootTest
public class ImageManagerRepositoryTest {

    @Autowired
    private ImageManageRepository imageManageRepository;

    @Test
    public void a(){

        ImageManager imageManager = ImageManager.builder().build();

        imageManager.addImages("aaaa", 0);
        imageManager.addImages("bbbb", 0);
        imageManager.addImages("cccc", 0);

        imageManageRepository.save(imageManager);
    }

    @Test
    @Transactional
    public void select_1(){

        Optional<ImageManager> imageManager = imageManageRepository.findById(1L);

        ImageManager manager = imageManager.orElseThrow();

        log.info(manager.getImages());

    }

}
