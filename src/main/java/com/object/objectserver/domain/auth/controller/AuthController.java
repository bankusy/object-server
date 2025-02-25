package com.object.objectserver.domain.auth.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@Slf4j
@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private static String WEBSITE_URI = "http://localhost:5173";

    @GetMapping("/session")
    public ResponseEntity<?> getSession(HttpSession session) {
        return ResponseEntity.ok(session.getAttribute("session_id"));
    }

    @PostMapping("/logout")
    public void logout(HttpSession session, HttpServletResponse response) {
        try {
            session.removeAttribute("session_id");
            response.sendRedirect(WEBSITE_URI);
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }
}
