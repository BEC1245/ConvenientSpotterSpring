package com.convenient.store.user.entity;

import com.convenient.store.product.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@Table(name = "users")
@Builder
@ToString(exclude = "roles")
@NoArgsConstructor
@AllArgsConstructor
public class Users extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
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

    public void createEmail(String email){
        this.email = email;
    }

    public void createPw(String pw){
        this.pw = pw;
    }

    public void createProfile(String profile){
        this.profile = profile;
    }

    public void createNickName(String nickName){
        this.nickName = nickName;
    }

    public void createIsSocial(Boolean isSocial){
        this.isSocial = isSocial;
    }

    @PrePersist
    public void setSocialDefault(){
        this.isSocial = this.isSocial == null ? false : this.isSocial;
    }

}
