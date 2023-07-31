package com.convenient.store.repository;

import com.convenient.store.user.entity.Users;
import com.convenient.store.user.repository.UserRepository;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.IntStream;

@Log4j2
@SpringBootTest
public class UsersTests {

    @Autowired
    private UserRepository repository;

    @Test
    public void Insert_1(){

        IntStream.range(1, 10).forEach(ele -> {

            Users users = Users.builder()
                .email("user00" + ele + "@notgmail.com")
                .pw("1111")
                .nickName("frontController" + ele)
                .isAdmin(false)
                .profile("someJpg.jpg").build();

            repository.save(users);
        });

    }




}
