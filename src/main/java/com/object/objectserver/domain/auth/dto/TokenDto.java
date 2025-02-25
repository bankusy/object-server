package com.object.objectserver.domain.auth.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

public class TokenDto {

    public static class KakaoRequest {

    }

    @Getter
    @Setter
    @ToString
    public static class KakaoResponse {
        String token_type;
        String access_token;
        String id_token;
        Integer expires_in;
        String refresh_token;
        Integer refresh_token_expires_in;
        String scope;
    }

    @Getter
    @Setter
    @ToString
    public static class KakaoIdToken {
        private String aud;
        private String sub;
        private Integer auth_time;
        private String iss;
        private Integer exp;
        private Integer iat;
        private String nickname;
        private String picture;
        private String email;
    }

}
