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
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor
@Builder
@ToString
@Table(
		name = JoaraPostgresSchemaConstants.TB_EPISODE,
		schema = JoaraPostgresSchemaConstants.SCHEMA,
		catalog = JoaraPostgresSchemaConstants.SCHEMA
)
public class EpisodeEntity extends UuidBaseEntity {
	public Long bookId;
	public UUID memberId;
	public Long epiNum;
	public String bookTitle;
	public String nickname;
	public String epiTitle;
	public String content;
	public String quote;
	public String cover;
	@Builder.Default
	public Long viewCount = 0L;
	@Builder.Default
	public Long heartCount= 0L;
	@Builder.Default
	public Long commentCount= 0L;
	@Builder.Default
	@Enumerated(EnumType.STRING)
	public EpisodeStatus status = EpisodeStatus.PENDING;
	@Builder.Default
	public OffsetDateTime createdAt = ServerTime.now();
	public OffsetDateTime updatedAt;
	public OffsetDateTime deletedAt;
}