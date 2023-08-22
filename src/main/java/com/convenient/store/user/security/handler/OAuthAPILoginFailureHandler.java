package com.convenient.store.user.security.handler;

import com.convenient.store.common.utill.JWTUtil;
import com.convenient.store.user.dto.UserDTO;
import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

@Log4j2
public class OAuthAPILoginFailureHandler implements AuthenticationFailureHandler {

    @Value("${com.convenient.frontserver.loc}")
    String serverLoc;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {

        String errorMsg = exception.getMessage();

        Map<String, String> error = new HashMap<>();

        if(errorMsg.startsWith("RESIGNED_USER")){
            String[] splitedMsg = errorMsg.split(" ");
            error.put("error", splitedMsg[0]);
            error.put("id", splitedMsg[1]);
        } else {
            error.put("error", errorMsg);
        }

        Gson gson = new Gson();

        String jsonStr = gson.toJson(error);

        response.sendRedirect("http://localhost:3000/kakaologin?data=" + jsonStr);

    }
}
