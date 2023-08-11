package com.convenient.store.user.controller;

import com.convenient.store.common.utill.CustomJWTException;
import com.convenient.store.common.utill.FileUploader;
import com.convenient.store.common.utill.JWTUtil;
import com.convenient.store.user.dto.PureUserDTO;
import com.convenient.store.user.dto.UserDTO;
import com.convenient.store.user.service.UserService;
import com.nimbusds.jwt.JWT;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Log4j2
@RestController
@RequestMapping("/api/user/")
@RequiredArgsConstructor
public class APIUserController {

    private final FileUploader fileUploader;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("email")
    public PureUserDTO getUser(String email){ return userService.getUserByEmail(email); }

    @PostMapping("signin")
    public Map<String, String> registUser(PureUserDTO pureUserDTO){

        log.info(pureUserDTO + " POST / check dto in apiUserController");

        if(pureUserDTO.getFile() != null) {
            String fileName = fileUploader.uploadFile("profile", pureUserDTO.getFile(), 90, 90, true);
            pureUserDTO.setProfile(fileName);
        }
        pureUserDTO.setPw(passwordEncoder.encode(pureUserDTO.getPw()));

        userService.registUser(pureUserDTO);

        return Map.of("result", "success");
    }

    @PutMapping("modify")
    public Map<String, String> modifyInfo(PureUserDTO pureUserDTO){

        if(pureUserDTO.getFile() != null) {
            String fileName = fileUploader.uploadFile("profile", pureUserDTO.getFile(), 90, 90, true);
            pureUserDTO.setProfile(fileName);
        }

        log.info(pureUserDTO + " / check dto in apiUserController");

        pureUserDTO.setPw(passwordEncoder.encode(pureUserDTO.getPw()));

        userService.modifyUserInfo(pureUserDTO);

        return Map.of("result", "success");
    }

    @PutMapping("restore/{id}")
    public Map<String, String> map(@PathVariable("id") Long id){
        log.info(id + " PUT / check id in APIUserController");

        userService.restoreUser(id);

        return Map.of("result", "done");
    }

    @DeleteMapping("resign/{id}")
    public Map<String, String> deleteUser(@PathVariable("id") Long id){

        log.info(id + " DELETE / deleteUser in APIUserController ");

        userService.deleteUser(id);

        return Map.of("result", "success");
    }

    @RequestMapping("refresh")
    public Map<String, Object> refreshToken(@RequestHeader("Authorization") String authHeader, String refreshToken){

        if(refreshToken == null){
            throw new CustomJWTException("NO_REFRESH_TOKEN");
        }

        if(authHeader == null || authHeader.length() < 7){
            throw new CustomJWTException("INVALID_STRING");
        }

        String accessToken = authHeader.substring(7);

        if(checkExpired(accessToken) == false){
            return Map.of("accessToken", accessToken, "refreshToken", refreshToken);
        }

        Map<String, Object> claims = null;

        claims = JWTUtil.validateToken(refreshToken);

        String newAccessToken = JWTUtil.generateToken(claims, 1);

        String newRefreshToken = (checkTime((Integer) claims.get("exp"))) ? JWTUtil.generateToken(claims, 60 * 24) : refreshToken;

        return Map.of("accessToken", newAccessToken, "refreshToken", newRefreshToken);

    }

    private boolean checkTime(Integer exp){

        java.util.Date expDate = new java.util.Date((long)exp * (1000));

        long gap = expDate.getTime() - System.currentTimeMillis();

        long leftMin = gap / (1000 * 60);

        return leftMin < 60;
    }


    private boolean checkExpired(String token){

        try{
            JWTUtil.validateToken(token);
        } catch(CustomJWTException ex) {
            if(ex.getMessage().equals("Expired")){
                return true;
            }
        }
        return false;

    }

}
