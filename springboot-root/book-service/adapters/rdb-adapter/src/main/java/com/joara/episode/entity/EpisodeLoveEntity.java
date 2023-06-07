package com.joara.episode.entity;

import com.joara.base.jpa.entity.UuidBaseEntity;
import com.joara.rdb.JoaraPostgresSchemaConstants;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@ToString
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Table(
		name = JoaraPostgresSchemaConstants.TB_EPI_LOVE,
		schema = JoaraPostgresSchemaConstants.SCHEMA,
				catalog = JoaraPostgresSchemaConstants.SCHEMA
)
public class EpisodeLoveEntity extends UuidBaseEntity {
	public UUID memberId;
	public UUID epiId;
	public Boolean isEpi;
}
