package com.object.objectserver.domain.user.repository;

import com.object.objectserver.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}
