package com.object.objectserver.domain.auth.controller;

import com.object.objectserver.domain.auth.entity.OauthType;
import com.object.objectserver.domain.auth.service.OauthService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Slf4j
@RestController
@RequestMapping("/api/v1/oauth")
@RequiredArgsConstructor
public class OauthController {

    // [추후 도메인 등록 후 수정 필요]
    private static String WEBSITE_URI = "http://localhost:5173";
    private final ApplicationContext applicationContext;

    @GetMapping
    public void redirectLoginPage(@RequestParam OauthType type, HttpServletResponse response) throws IOException {
        OauthService oauthService = getOauthService(type);
        response.sendRedirect(oauthService.getRedirectUrl());
    }

    @GetMapping("/{type}/callback")
    public void callback(
            @PathVariable OauthType type,
            @RequestParam String code,
            HttpSession session,
            HttpServletResponse response
    ) {

        OauthService oAuthService = getOauthService(type);
        String userSessionId = oAuthService.process(code);
        try {
            session.setAttribute("session_id", userSessionId);
            response.sendRedirect(WEBSITE_URI);
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

    public OauthService getOauthService(OauthType type) {
        return applicationContext.getBean(type.getServiceBeanName(), OauthService.class);
    }

}
