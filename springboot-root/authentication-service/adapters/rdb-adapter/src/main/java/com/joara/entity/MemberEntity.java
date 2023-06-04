package com.joara.entity;

import com.joara.base.jpa.entity.UuidBaseEntity;
import com.joara.domain.model.type.AccountStatus;
import com.joara.domain.model.type.Gender;
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
    public OffsetDateTime birth;
    @Builder.Default
    public OffsetDateTime createdAt = ServerTime.now();
    public OffsetDateTime updatedAt;
    public OffsetDateTime deletedAt;
    @Enumerated(EnumType.STRING)
    public AccountStatus status;
}