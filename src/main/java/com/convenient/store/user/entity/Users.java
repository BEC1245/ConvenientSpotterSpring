package com.convenient.store.user.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

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

    @Column(columnDefinition = "boolean default false")
    private boolean isAdmin;

}
