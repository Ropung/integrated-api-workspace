package com.joara.auth.entity;

import com.joara.auth.domain.model.type.CertType;
import com.joara.base.jpa.entity.UuidBaseEntity;
import com.joara.auth.domain.model.type.AccountStatus;
import com.joara.auth.domain.model.type.Gender;
import com.joara.rdb.JoaraPostgresSchemaConstants;
import com.joara.util.time.ServerTime;
import lombok.*;

import javax.persistence.*;
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
    @Builder.Default
    public OffsetDateTime createdAt = ServerTime.now();
    public OffsetDateTime updatedAt;
    public OffsetDateTime deletedAt;
    @Enumerated(EnumType.STRING)
    public AccountStatus status;
    @Enumerated(EnumType.STRING)
    @Builder.Default
    public CertType certificatedBy = CertType.SELF;
    public Long oauthSerial;
}