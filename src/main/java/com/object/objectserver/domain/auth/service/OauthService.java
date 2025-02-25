package com.object.objectserver.domain.auth.service;

public interface OauthService {
    /* [리다이렉트 URL] */
    String getRedirectUrl();

    /* [토큰 요청] */
    String process(String code);
}
