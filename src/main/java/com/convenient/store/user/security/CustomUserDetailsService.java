package com.convenient.store.user.security;

import com.convenient.store.common.utill.AuthorityMaker;
import com.convenient.store.user.dto.UserDTO;
import com.convenient.store.user.entity.Users;
import com.convenient.store.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Log4j2
@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    // email을 채킹하는 로직은 다음과 같다
    // 1. 받은 email인 username을 통해 users를 끌어오고
    // 2. 해당 데이터를 DTO의 데이터로 보내준다.

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Users users = userRepository.getUser(username);

        if(users == null){
            throw new UsernameNotFoundException("Not Found");
        }

        UserDTO userDTO = new UserDTO(
                users.getEmail(),
                users.getPw(),
                users.getProfile(),
                users.getNickName(),
                users.getIsSocial(),
                users.getRoles().stream().map(ele -> ele.name()).collect(Collectors.toList())
        );

        return userDTO;
    }
}
