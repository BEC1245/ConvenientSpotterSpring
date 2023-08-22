package com.convenient.store.user.security.handler;

import com.convenient.store.common.utill.JWTUtil;
import com.convenient.store.user.dto.UserDTO;
import com.google.gson.Gson;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;

import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.Map;

public class OAuthAPILoginSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        UserDTO userDTO = (UserDTO) authentication.getPrincipal();

        Map<String, Object> claims = userDTO.getClaims();

        String accessToken = JWTUtil.generateToken(claims, 1);
        String refreshToken = JWTUtil.generateToken(claims, 60 * 24);

        claims.put("accessToken", accessToken);
        claims.put("refreshToken", refreshToken);

        Gson gson = new Gson();

        String jsonStr = gson.toJson(claims);

        String encodeStr = URLEncoder.encode(jsonStr, "UTF-8");

        response.sendRedirect("http://localhost:3000/kakaologin?data=" + encodeStr);
    }

}
