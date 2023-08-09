package com.convenient.store.user.controller;

import com.convenient.store.common.utill.CustomJWTException;
import com.convenient.store.common.utill.FileUploader;
import com.convenient.store.common.utill.JWTUtil;
import com.convenient.store.user.dto.PureUserDTO;
import com.convenient.store.user.dto.UserDTO;
import com.nimbusds.jwt.JWT;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
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
