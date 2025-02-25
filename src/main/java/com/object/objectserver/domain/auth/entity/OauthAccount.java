package com.object.objectserver.domain.auth.entity;

import com.object.objectserver.domain.common.entity.BaseEntity;
import com.object.objectserver.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "T_OAUTH_ACCOUNTS")
@NoArgsConstructor
public class OauthAccount extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long oauth_id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Enumerated(EnumType.STRING)
    private OauthType provider;

    private String providerUserId;

    @Builder
    public OauthAccount(User user, OauthType provider, String providerUserId) {
        this.user = user;
        this.provider = provider;
        this.providerUserId = providerUserId;
    }
}

