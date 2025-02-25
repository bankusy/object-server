package com.object.objectserver.domain.auth.repository;

import com.object.objectserver.domain.auth.entity.OauthAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OauthAccountRepository extends JpaRepository<OauthAccount, Long> {
    Optional<OauthAccount> findByProviderUserId(String providerUserId);
}
