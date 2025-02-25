package com.object.objectserver.domain.user.controller;


import com.object.objectserver.domain.user.dto.UserDto;
import com.object.objectserver.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    /* [유저 조회] */
    @GetMapping("/{id}")
    public ResponseEntity<?> getUser(@PathVariable String id) {
        UserDto.Response response = userService.getUserById(id);
        return ResponseEntity.ok(response);
    }

    /* [전체 유저 조회] */
    @GetMapping
    public ResponseEntity<?> getUsers(Pageable pageable) {
        Page<UserDto.Response> response = userService.getUsers(pageable);
        return ResponseEntity.ok(response);
    }

}
