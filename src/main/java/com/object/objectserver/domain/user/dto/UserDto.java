package com.object.objectserver.domain.user.dto;

import com.object.objectserver.domain.user.entity.User;
import com.object.objectserver.domain.user.entity.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

public class UserDto {

    @Getter
    public static class Request {
        private String email;
        private String nickname;
        private String phone;
        private String avatar;

        @Builder
        public Request(String email, String nickname, String phone, String avatar) {
            this.email = email;
            this.nickname = nickname;
            this.phone = phone;
            this.avatar = avatar;
        }
    }

    @Getter
    @Setter
    public static class Response {
        private String id;
        private UserRole role;
        private String email;
        private String nickname;
        private String phone;
        private String birth;
        private String avatar;
        private String bio;

        @Builder
        public Response(String id, UserRole role, String email, String nickname, String phone, String birth, String avatar, String bio) {
            this.id = id;
            this.role = role;
            this.email = email;
            this.nickname = nickname;
            this.phone = phone;
            this.birth = birth;
            this.avatar = avatar;
            this.bio = bio;
        }

        public static Response from(User user) {
            return Response.builder()
                    .id(user.getId())
                    .role(user.getRole())
                    .email(user.getEmail())
                    .nickname(user.getNickname())
                    .phone(user.getPhone())
                    .birth(user.getBirth())
                    .avatar(user.getAvatar())
                    .bio(user.getBio())
                    .build();
        }
    }
}
