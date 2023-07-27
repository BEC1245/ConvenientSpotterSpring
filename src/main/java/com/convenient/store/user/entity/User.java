package com.convenient.store.user.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    private String email;

    @Column(nullable = false)
    private String pw;

    private String profile;

    private String nickName;

    @Column(columnDefinition = "boolean default false")
    private boolean isAdmin;
}
