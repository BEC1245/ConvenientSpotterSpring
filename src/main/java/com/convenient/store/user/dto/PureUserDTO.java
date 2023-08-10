package com.convenient.store.user.dto;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PureUserDTO {

    private Long id;
    private String email;
    private String pw;
    private String profile;
    private String nickName;
    private Boolean isSocial;
    private List<String> roleNames;

    private MultipartFile file;

}
