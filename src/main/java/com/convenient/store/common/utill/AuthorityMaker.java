package com.convenient.store.common.utill;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class AuthorityMaker {

    public List<SimpleGrantedAuthority> make(boolean isAdmin){

        List<String> authority = new ArrayList<>();
        if(isAdmin){
            authority.add("ADMIN");
        } else {
            authority.add("USER");
        }

        return authority.stream().map(str -> new SimpleGrantedAuthority("ROLE_"+str)).collect(Collectors.toList());
    }

}
