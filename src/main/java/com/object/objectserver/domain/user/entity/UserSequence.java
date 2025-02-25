package com.object.objectserver.domain.user.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;

@Entity
@Getter
@Table(name = "T_USER_SEQUENCE")
public class UserSequence {

    @Id
    private String seq_name;
    private int seq_value;

    public void incrementSequence() {
        ++this.seq_value;
    };
}
