package com.joara.auth.domain.model;

import com.joara.auth.domain.model.type.AccountStatus;
import com.joara.auth.domain.model.type.Gender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.OffsetDateTime;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Builder
public class Member {
    public UUID id;
    public String email;
    public String password;
    public String name;
    public String nickname;
    public String phone;
    public Gender gender;
    public OffsetDateTime birth;
    public OffsetDateTime createdAt;
    public OffsetDateTime updatedAt;
    public OffsetDateTime deletedAt;
    public AccountStatus status;
}
