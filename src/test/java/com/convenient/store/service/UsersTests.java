package com.convenient.store.service;

import com.convenient.store.product.dto.ReviewDTO;
import com.convenient.store.product.service.ReviewService;
import com.convenient.store.user.dto.PureUserDTO;
import com.convenient.store.user.entity.UsersRole;
import com.convenient.store.user.service.UserService;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.parameters.P;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;

@Log4j2
@SpringBootTest
public class UsersTests {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void select_1() {

        String email = "user706@gmail.com";
        log.info(userService.getUserByEmail(email));

    }

    @Test
    public void modify_1(){

        PureUserDTO pureUserDTO = PureUserDTO.builder()
                .id(12L)
                .email("chanymini1@naver.com")
                .pw(passwordEncoder.encode("abcd"))
                .nickName("칼조심")
                .isSocial(true)
                .roleNames(List.of("GUEST"))
                .build();

        userService.modifyUserInfo(pureUserDTO);

    }

    @Test
    public void regist_1(){

        PureUserDTO pureUserDTO = PureUserDTO.builder()
                .email("testingEmail")
                .pw(passwordEncoder.encode("1111"))
                .nickName("칼조심")
                .profile("NONE")
                .roleNames(List.of(UsersRole.USER.name()))
                .isSocial(false)
                .build();

        userService.registUser(pureUserDTO);

    }

}
