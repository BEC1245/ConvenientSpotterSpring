package com.convenient.store.user.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Getter
@Setter
@ToString
public class UserDTO extends User {

    private String email;
    private String pw;
    private String profile;
    private String nickName;
    private boolean isAdmin;

    // 여기서 authorities는 USER 같은 데이터를 던저야 한다.
    public UserDTO(String email, String pw, String profile, String nickName, boolean isAdmin, Collection<? extends GrantedAuthority> authorities) {

        super(email, pw, authorities);
        this.email = email;
        this.pw = pw;
        this.profile = profile;
        this.nickName = nickName;
        this.isAdmin = isAdmin;

    }

    public Map<String, Object> getClaims(){

        Map<String, Object> claims = new HashMap<>();

        claims.put("email", email);
        claims.put("pw", pw);
        claims.put("profile", profile);
        claims.put("nickName", nickName);
        claims.put("isAdmin", isAdmin);

        return claims;
    }


}
