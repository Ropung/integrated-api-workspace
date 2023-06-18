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
    public UUID id; // not required
    public String email;
    public String password; // not required
    public String name;
    public String nickname;
    public String phone;
    public Gender gender;
    public String birth;
    public AccountStatus status; // not required
    public MemberTier tier;
    public CertType certificatedBy; // not required
    public Long oauthSerial; // not required
    public OffsetDateTime createdAt; // not required
    public OffsetDateTime updatedAt; // not required
    public OffsetDateTime deletedAt; // not required
}
