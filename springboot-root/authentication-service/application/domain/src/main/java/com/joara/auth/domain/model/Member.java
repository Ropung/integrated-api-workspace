package com.joara.auth.domain.model;

import com.joara.auth.domain.model.type.AccountStatus;
import com.joara.auth.domain.model.type.CertType;
import com.joara.auth.domain.model.type.Gender;
import com.joara.auth.domain.model.type.MemberTier;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.OffsetDateTime;
import java.util.UUID;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Member {
    public UUID id;
    public String email;
    public String password;
    public String name;
    public String nickname;
    public String phone;
    public Gender gender;
    public String birth;
    public AccountStatus status;
    public MemberTier tier;
    public CertType certificatedBy;
    public Long oauthSerial;
    public OffsetDateTime createdAt;
    public OffsetDateTime updatedAt;
    public OffsetDateTime deletedAt;
}
