package com.object.objectserver.domain.user.entity;

import com.object.objectserver.domain.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "T_USERS")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User extends BaseEntity {

    @Id
    private String id;

    @Enumerated(EnumType.STRING)
    private UserRole role;

    private String email;
    private String password;
    private String nickname;
    private String phone;
    private String birth;
    private String avatar;
    private String bio;
}
