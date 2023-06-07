package com.joara.episode.entity;

import com.joara.base.jpa.entity.UuidBaseEntity;
import com.joara.rdb.JoaraPostgresSchemaConstants;
import com.joara.util.time.ServerTime;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@ToString
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Table(
		name = JoaraPostgresSchemaConstants.TB_EPI_SUB_REPLY,
		schema = JoaraPostgresSchemaConstants.SCHEMA,
				catalog = JoaraPostgresSchemaConstants.SCHEMA
)
public class EpisodeSubReplyEntity extends UuidBaseEntity {
	public UUID epiSubReplyId;
	public String epiSubReplyContent;
	@Builder.Default
	public OffsetDateTime createdAt = ServerTime.now();
	public OffsetDateTime updatedAt;
	public OffsetDateTime deletedAt;
}