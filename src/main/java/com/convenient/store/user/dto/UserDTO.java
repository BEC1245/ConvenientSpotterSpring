package com.convenient.store.user.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Getter
@Setter
@ToString
public class UserDTO extends User implements OAuth2User {

    private Long id;
    private String email;
    private String pw;
    private String profile;
    private String nickName;
    private Boolean isSocial;
    private List<String> roleNames;

    private MultipartFile file;

    // 여기서 authorities는 USER 같은 데이터를 던저야 한다.
    public UserDTO(Long id, String email, String pw, String profile, String nickName, Boolean isSocial, List<String> roleNames) {

        super(email, pw, roleNames.stream().map(ele -> new SimpleGrantedAuthority(ele)).collect(Collectors.toList()));
        this.id = id;
        this.email = email;
        this.pw = pw;
        this.profile = profile;
        this.nickName = nickName;
        this.isSocial = isSocial;
        this.roleNames = roleNames;

    }

    public Map<String, Object> getClaims(){

        Map<String, Object> claims = new HashMap<>();

        claims.put("id", id);
        claims.put("email", email);
        claims.put("pw", pw);
        claims.put("isSocial", isSocial);
        claims.put("profile", profile);
        claims.put("nickName", nickName);
        claims.put("roleNames", roleNames);

        return claims;
    }

    @Override
    public Map<String, Object> getAttributes() { return null; }

    @Override
    public String getName() { return this.email; }

}
