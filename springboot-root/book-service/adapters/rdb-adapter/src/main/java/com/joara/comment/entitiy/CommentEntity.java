package com.joara.comment.entitiy;

import com.joara.base.jpa.entity.UuidBaseEntity;
import com.joara.book.domain.model.comment.type.CommentStatus;
import com.joara.rdb.JoaraPostgresSchemaConstants;
import com.joara.util.time.ServerTime;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Builder.Default;
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
        name = JoaraPostgresSchemaConstants.TB_EPISODE_COMMENT,
        schema = JoaraPostgresSchemaConstants.SCHEMA
)
public class CommentEntity extends UuidBaseEntity {
    public UUID epiId;
    public UUID memberId;
    public String nickname;
    public String content;
    @Default
    @Enumerated(EnumType.STRING)
    public CommentStatus status = CommentStatus.PENDING;
    @Builder.Default
    public OffsetDateTime createdAt = ServerTime.now();
    public OffsetDateTime updatedAt;
    public OffsetDateTime deletedAt;
}
