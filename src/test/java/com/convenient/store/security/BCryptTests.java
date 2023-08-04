package com.convenient.store.security;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

@Log4j2
@SpringBootTest
public class BCryptTests {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void makeBCrypt(){
       log.info(passwordEncoder.encode("1111"));
    }

}
