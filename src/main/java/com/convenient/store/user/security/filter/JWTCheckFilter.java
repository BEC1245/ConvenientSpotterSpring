package com.convenient.store.user.security.filter;

import com.convenient.store.common.utill.AuthorityMaker;
import com.convenient.store.common.utill.JWTUtil;
import com.convenient.store.user.dto.UserDTO;
import com.google.gson.Gson;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Log4j2
public class JWTCheckFilter extends OncePerRequestFilter {

    // 걸러질 uri
    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {

        log.info(request.getContentType() + " / current content Type");

        String uri = request.getRequestURI();

        if(uri.startsWith("/api/user")){
            return true;
        }

        return false;
    }

    // 토큰 검사 로직
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        log.info("-------------------- doFilterInternal is working -------------------------");
        log.info("-------------------- doFilterInternal is working -------------------------");

        String authHeaderStr = request.getHeader("Authorization");

        try {

            String accessToken = authHeaderStr.substring(7);
            Map<String, Object> claims = JWTUtil.validateToken(accessToken);

            filterChain.doFilter(request, response);

            Integer intId = (Integer) claims.get("id");

            Long id = Long.valueOf(intId.longValue());
            String email = (String) claims.get("email");
            String pw = (String) claims.get("pw");
            String profile = (String) claims.get("profile");
            String nickName = (String) claims.get("nickName");
            boolean isSocial = (boolean) claims.get("isSocial");
            List<String> roleNames = (List<String>) claims.get("roleNames");

            UserDTO userDTO = new UserDTO(
                    id,
                    email,
                    pw,
                    profile,
                    nickName,
                    isSocial,
                    roleNames
            );

            UsernamePasswordAuthenticationToken authenticationToken
                    = new UsernamePasswordAuthenticationToken(userDTO, pw, userDTO.getAuthorities());

            // memberDTO가 security 추가된 방식이다.
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        } catch (Exception e) {

            log.error("JWT Check Error..............");
            log.error(e.getMessage());

            Gson gson = new Gson();
            String msg = gson.toJson(Map.of("error", "ERROR_ACCESS_TOKEN"));

            if(e.getMessage().equals("Expired")){
                msg = gson.toJson(Map.of("error", "EXPIRED"));
            }

            response.setContentType("application/json");
            PrintWriter printWriter = response.getWriter();
            printWriter.println(msg);
            printWriter.close();

        }


    }

}
