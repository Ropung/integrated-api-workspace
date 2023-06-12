package com.joara.episode.entity;


import com.joara.base.jpa.entity.UuidBaseEntity;
import com.joara.book.domain.model.episode.type.EpisodeStatus;
import com.joara.rdb.JoaraPostgresSchemaConstants;
import com.joara.util.time.ServerTime;
import lombok.*;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@ToString
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Table(
		name = JoaraPostgresSchemaConstants.TB_EPI,
		schema = JoaraPostgresSchemaConstants.SCHEMA,
				catalog = JoaraPostgresSchemaConstants.SCHEMA
)
public class EpisodeEntity extends UuidBaseEntity {
	public UUID bookId;
	public UUID memberId;
	public String nickname;
	public String title;
	public String content;
	public String review;
	public String coverUrl;
	public Integer viewCount;
	@Enumerated(EnumType.STRING)
	public EpisodeStatus status;
	@Builder.Default
	public OffsetDateTime createdAt = ServerTime.now();
	public OffsetDateTime updatedAt;
	public OffsetDateTime deletedAt;
}
