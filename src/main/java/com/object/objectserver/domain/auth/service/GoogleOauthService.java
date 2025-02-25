package com.object.objectserver.domain.auth.service;

import org.springframework.stereotype.Service;

@Service
public class GoogleOauthService implements OauthService {
    @Override
    public String getRedirectUrl() {
        return "";
    }

    @Override
    public String process(String code) {
        return "";
    }
}
