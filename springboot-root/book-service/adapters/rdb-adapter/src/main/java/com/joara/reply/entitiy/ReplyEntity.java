package com.joara.reply.entitiy;

import com.joara.base.jpa.entity.UuidBaseEntity;
import com.joara.book.domain.model.reply.type.ReplyStatus;
import com.joara.rdb.JoaraPostgresSchemaConstants;
import com.joara.util.time.ServerTime;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@ToString
@Table(
        name = JoaraPostgresSchemaConstants.TB_EPISODE_REPLY,
        schema = JoaraPostgresSchemaConstants.SCHEMA
)
public class ReplyEntity extends UuidBaseEntity {
    public UUID commentId;
    public UUID memberId;
    public String nickname;
    public String content;
    @Builder.Default
    @Enumerated(EnumType.STRING)
    public ReplyStatus status = ReplyStatus.PENDING;
    @Builder.Default
    public OffsetDateTime createdAt = ServerTime.now();
    public OffsetDateTime updatedAt;
    public OffsetDateTime deletedAt;
}
