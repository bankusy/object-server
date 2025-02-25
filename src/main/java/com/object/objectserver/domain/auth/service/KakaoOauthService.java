package com.object.objectserver.domain.auth.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.object.objectserver.domain.auth.dto.TokenDto;
import com.object.objectserver.domain.auth.entity.OauthAccount;
import com.object.objectserver.domain.auth.entity.OauthType;
import com.object.objectserver.domain.auth.repository.OauthAccountRepository;
import com.object.objectserver.domain.user.dto.UserDto;
import com.object.objectserver.domain.user.entity.User;
import com.object.objectserver.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Base64;

@Slf4j
@Service
@RequiredArgsConstructor
public class KakaoOauthService implements OauthService {

    private final UserService userService;
    private final OauthAccountRepository oauthAccountRepository;

    @Value("${oauth.kakao.client_id}")
    private String clientId;

    @Value("${oauth.kakao.redirect_uri}")
    private String redirectUri;

    @Value("${oauth.kakao.token_uri}")
    private String tokenUri;

    @Value("${oauth.kakao.authorization_code_uri}")
    private String authorizeCodeUri;

    @Value("${oauth.kakao.response_type}")
    private String responseType;

    @Value("${oauth.kakao.grant_type}")
    private String grantType;

    @Override
    public String getRedirectUrl() {
        return String.format("%s?client_id=%s&redirect_uri=%s&response_type=%s",
                authorizeCodeUri,
                clientId,
                redirectUri,
                responseType
        );
    }

    @Override
    public String process(String code) {
        try {
            RestTemplate restTemplate = new RestTemplate();

            // HTTP 헤더 설정
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

            MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
            params.add("grant_type", grantType);
            params.add("client_id", clientId);
            params.add("redirect_uri", redirectUri);
            params.add("code", code);

            HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(params, httpHeaders);
            TokenDto.KakaoResponse response =
                    restTemplate.postForObject(tokenUri, request, TokenDto.KakaoResponse.class);

            String decodedIdToken = new String(Base64.getDecoder().decode(response.getId_token().split("\\.")[1]));
            ObjectMapper objectMapper = new ObjectMapper();
            TokenDto.KakaoIdToken idToken = objectMapper.readValue(decodedIdToken, TokenDto.KakaoIdToken.class);

            String providerUserId = idToken.getSub();

            OauthAccount oauthAccount = oauthAccountRepository.findByProviderUserId(providerUserId).orElseGet(() -> {
                User user = userService.saveUser(
                        UserDto.Request.builder()
                                .nickname(idToken.getNickname())
                                .email(idToken.getEmail())
                                .avatar(idToken.getPicture())
                                .build()
                );

                return oauthAccountRepository.save(OauthAccount.builder()
                        .providerUserId(idToken.getSub())
                        .user(user)
                        .provider(OauthType.KAKAO)
                        .build()
                );
            });

            return oauthAccount.getUser().getId();
        } catch (Exception e) {
            return null;
        }
    }
}
