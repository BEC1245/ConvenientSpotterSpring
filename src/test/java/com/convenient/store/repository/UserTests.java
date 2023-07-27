package com.convenient.store.repository;

import com.convenient.store.user.entity.User;
import com.convenient.store.user.repository.UserRepository;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Log4j2
@SpringBootTest
public class UserTests {

    @Autowired
    private UserRepository repository;

    @Test
    public void Insert_1(){

        User user = User.builder()
                .email("user00@notgmail.com")
                .pw("1111")
                .nickName("frontController")
                .isAdmin(false)
                .profile("someJpg.jpg")
                .build();

        repository.save(user);

    }




}
