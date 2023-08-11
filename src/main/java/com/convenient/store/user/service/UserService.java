package com.convenient.store.user.service;

import com.convenient.store.user.dto.PureUserDTO;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface UserService {

    PureUserDTO getUserByEmail(String email);

    void modifyUserInfo(PureUserDTO pureUserDTO);

    void registUser(PureUserDTO pureUserDTO);

    void deleteUser(Long id);

    void restoreUser(Long id);

}
