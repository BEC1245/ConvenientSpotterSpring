package com.convenient.store.user.service;

import com.convenient.store.common.utill.FileUploader;
import com.convenient.store.user.dto.PureUserDTO;
import com.convenient.store.user.entity.Users;
import com.convenient.store.user.entity.UsersRole;
import com.convenient.store.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Log4j2
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final FileUploader fileUploader;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public PureUserDTO getUserByEmail(String email) {

        Users users = userRepository.getUser(email);

        if(users == null || users.getDelflag()){
            return null;
        }

        PureUserDTO pureUserDTO = modelMapper.map(users, PureUserDTO.class);
        pureUserDTO.setRoleNames(users.getRoles().stream().map(ele -> ele.name()).collect(Collectors.toList()));
        return pureUserDTO;
    }

    @Override
    public void modifyUserInfo(PureUserDTO pureUserDTO) {

        // 현 유저 정보를 가져온다.
        Optional<Users> getUsers = userRepository.findById(pureUserDTO.getId());

        Users users = getUsers.orElseThrow();

        // 기본적 이메일을 새팅한다
        users.createEmail(pureUserDTO.getEmail());
        users.createPw(pureUserDTO.getPw());
        users.createNickName(pureUserDTO.getNickName());

        // 만약 변경해야 하는 프로필이 있으면
        if(pureUserDTO.getProfile() != null){
            fileUploader.deleteFile("profile", users.getProfile());
            users.createProfile(pureUserDTO.getProfile());
        }

        // 만약 첫 소셜 유저라면
        if(pureUserDTO.getIsSocial() == true && pureUserDTO.getRoleNames().indexOf("GUEST") != -1){
            users.clearRole();
            users.addUserRole(UsersRole.USER);
        }

        userRepository.save(users);

    }

    @Override
    public void registUser(PureUserDTO pureUserDTO) {

        Users users = Users.builder()
                .email(pureUserDTO.getEmail())
                .pw(pureUserDTO.getPw())
                .nickName(pureUserDTO.getNickName())
                .profile(pureUserDTO.getProfile())
                .roles(List.of(UsersRole.USER))
                .isSocial(false)
                .build();

        userRepository.save(users);

    }
}
