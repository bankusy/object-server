package com.object.objectserver.domain.auth.entity;

public enum OauthType {
    KAKAO, GOOGLE;

    public String getServiceBeanName() {
        return this.name().toLowerCase() + "OauthService";
    }
}
