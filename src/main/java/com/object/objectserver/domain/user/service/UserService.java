package com.object.objectserver.domain.user.service;

import com.object.objectserver.domain.user.dto.UserDto;
import com.object.objectserver.domain.user.entity.User;
import com.object.objectserver.domain.user.entity.UserSequence;
import com.object.objectserver.domain.user.repository.UserSequenceRepository;
import com.object.objectserver.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserSequenceRepository userSequenceRepository;

    @Transactional(readOnly = true)
    public UserDto.Response getUserById(String id) {
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("User Not Found"));
        return UserDto.Response.from(user);
    }

    @Transactional(readOnly = true)
    public Page<UserDto.Response> getUsers(Pageable pageable) {
        return userRepository.findAll(pageable).map(UserDto.Response::from);
    }

    @Transactional
    public User saveUser(UserDto.Request request) {

        UserSequence userSequence = userSequenceRepository.findById("HM").
                orElseThrow(() -> new IllegalArgumentException("Sequence Not Found"));

        // 올리기 전
        int sequence = userSequence.getSeq_value();
        userSequence.incrementSequence();
        return userRepository.save(User.builder()
                        .id(String.format("HM%08d", sequence))
                        .nickname(request.getNickname())
                        .email(request.getEmail())
                        .avatar(request.getAvatar())
                        .build()
        );
    }
}
