package com.lyhcoding.myblog.dto.user;


import com.lyhcoding.myblog.model.user.User;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

public class UserRequest {
    @Getter @Setter
    public static class JoinInDTO {
        @NotEmpty
        private String username;
        @NotEmpty
        private String password;
        @NotEmpty
        private String email;

        public User toEntity(){
            return User.builder()
                    .username(username)
                    .password(password)
                    .email(email)
                    .role("USER") // enum 사용해도 됨
                    .status(true)
                    .profile("person.png") // 프로필 사진 관련
                    .build();
        }
    }
}
