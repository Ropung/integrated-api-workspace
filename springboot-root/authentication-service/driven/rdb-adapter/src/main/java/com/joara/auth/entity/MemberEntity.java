package com.joara.auth.entity;

import com.joara.auth.domain.model.type.AccountStatus;
import com.joara.auth.domain.model.type.CertType;
import com.joara.auth.domain.model.type.Gender;
import com.joara.base.jpa.entity.UuidBaseEntity;
import com.joara.rdb.JoaraPostgresSchemaConstants;
import com.joara.util.time.ServerTime;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import java.time.OffsetDateTime;

@Entity
@ToString
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Table(
        name = JoaraPostgresSchemaConstants.TB_MEMBER,
        schema = JoaraPostgresSchemaConstants.SCHEMA,
		catalog = JoaraPostgresSchemaConstants.SCHEMA
)
public class MemberEntity extends UuidBaseEntity {
    public String email;
    public String password;
    public String name;
    public String nickname;
    public String phone;
    @Enumerated(EnumType.STRING)
    public Gender gender;
    public String birth;
    @Enumerated(EnumType.STRING)
    @Builder.Default
    public AccountStatus status = AccountStatus.PENDING;
    public String tier;
    @Enumerated(EnumType.STRING)
    @Builder.Default
    public CertType certificatedBy = CertType.SELF;
    public Long oauthSerial;
    @Builder.Default
    public OffsetDateTime createdAt = ServerTime.now();
    public OffsetDateTime updatedAt;
    public OffsetDateTime deletedAt;
}