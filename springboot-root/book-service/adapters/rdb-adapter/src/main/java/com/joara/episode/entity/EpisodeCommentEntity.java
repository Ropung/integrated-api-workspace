package com.joara.episode.entity;

import com.joara.base.jpa.entity.UuidBaseEntity;
import com.joara.book.domain.model.episode.type.EpisodeStatus;
import com.joara.rdb.JoaraPostgresSchemaConstants;
import com.joara.util.time.ServerTime;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@ToString
@Table(
		name = JoaraPostgresSchemaConstants.TB_EPISODE_COMMENT,
		schema = JoaraPostgresSchemaConstants.SCHEMA
)
public class EpisodeCommentEntity extends UuidBaseEntity {
	public UUID epiId;
	public String memberId;
	public String nickname;
	public String content;
	@Builder.Default
	@Enumerated(EnumType.STRING)
	public EpisodeStatus status = EpisodeStatus.PENDING;
	@Builder.Default
	public OffsetDateTime createdAt = ServerTime.now();
	public OffsetDateTime updatedAt;
	public OffsetDateTime deletedAt;
}