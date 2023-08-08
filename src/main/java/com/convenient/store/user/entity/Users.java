package com.convenient.store.user.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@Table(name = "users")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Users {

    @Id
    private String email;

    @Column(nullable = false)
    private String pw;

    private String profile;

    private String nickName;

    private Boolean isSocial;

    @Builder.Default
    @ElementCollection(fetch = FetchType.LAZY)
    private List<UsersRole> roles = new ArrayList<>();

    public void addUserRole(UsersRole role){
        roles.add(role);
    }

    public void clearRole(){
        roles.clear();
    }

    @PrePersist
    public void setSocialDefault(){
        this.isSocial = this.isSocial == null ? false : this.isSocial;
    }

}
