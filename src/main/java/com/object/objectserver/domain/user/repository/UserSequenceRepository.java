package com.object.objectserver.domain.user.repository;

import com.object.objectserver.domain.user.entity.UserSequence;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserSequenceRepository extends JpaRepository<UserSequence, String> {
}
