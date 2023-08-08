package com.convenient.store.user.security;

import com.convenient.store.user.dto.UserDTO;
import com.convenient.store.user.entity.Users;
import com.convenient.store.user.entity.UsersRole;
import com.convenient.store.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Log4j2
@Service
@RequiredArgsConstructor
public class CustomOAuth2UserDetailsService extends DefaultOAuth2UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        log.info("userRequest....");
        log.info(userRequest);

        // 현 oauth 로그인을 처리하는 제삼자의 이름
        ClientRegistration clientRegistration = userRequest.getClientRegistration();
        String clientName = clientRegistration.getClientName();

        // 로그인 데이터를 인가
        log.info("clientName :/ " + clientName);
        OAuth2User oAuth2User = super.loadUser(userRequest);
        Map<String, Object> paramMap = oAuth2User.getAttributes();

        String email = null;

        switch(clientName){
            case "kakao":
                email = getKaKaoEmail(paramMap);
                break;
        }

        UserDTO userDTO = generateDTO(email);

        log.info(userDTO);

        return userDTO;

    }

    private String getKaKaoEmail(Map<String, Object> paramMap) {

        log.info("KAKAO------------------------------");

        Object value = paramMap.get("kakao_account");

        log.info(value);

        LinkedHashMap accountMap = (LinkedHashMap) value;

        String email = (String) accountMap.get("email");

        log.info("email...."+email);

        return email;

    }

    private UserDTO generateDTO(String email){

        Users user = userRepository.getUser(email);

        if(user == null){

            Users socialMember = Users.builder()
                    .email(email)
                    .pw(passwordEncoder.encode("1111"))
                    .profile("NONE")
                    .nickName("Social")
                    .isSocial(true)
                    .build();
            socialMember.addUserRole(UsersRole.GUEST);
            userRepository.save(socialMember);

            UserDTO userDTO =
                    new UserDTO(email, "1111", "NONE", "Social", true, java.util.List.of("GUEST"));

            return userDTO;

        } else {

            UserDTO userDTO = new UserDTO(
                    user.getEmail(),
                    user.getPw(),
                    user.getProfile(),
                    user.getNickName(),
                    user.getIsSocial(),
                    user.getRoles().stream().map(ele -> ele.name()).collect(Collectors.toList())
            );

            return userDTO;

        }


    }
}
