package com.convenient.store.repository;

import com.convenient.store.user.entity.Users;
import com.convenient.store.user.entity.UsersRole;
import com.convenient.store.user.repository.UserRepository;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
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
                .pw("$2a$10$Vde4LqwB3iB/HRz354ZlGOJPaENFIdDrdake2SjYC3CfiusSPk3WS")
                .nickName("frontController" + ele)
                .roles(List.of(UsersRole.USER))
                .profile("someJpg.jpg").build();

            repository.save(users);
        });

    }

    @Test
    public void Insert_2(){

        Users users = Users.builder()
                .email("guest")
                .pw("$2a$10$Vde4LqwB3iB/HRz354ZlGOJPaENFIdDrdake2SjYC3CfiusSPk3WS")
                .nickName("GUEST")
                .roles(List.of(UsersRole.GUEST))
                .profile("someJpg.jpg").build();

        repository.save(users);

    }

    @Test
    public void Insert_3(){

        Users users = Users.builder()
                .email("user707@gmail.com")
                .pw("$2a$10$Vde4LqwB3iB/HRz354ZlGOJPaENFIdDrdake2SjYC3CfiusSPk3WS")
                .nickName("MANAGER1")
                .roles(List.of(UsersRole.USER, UsersRole.ADMIN))
                .profile("someJpg.jpg").build();

        repository.save(users);

    }




}
