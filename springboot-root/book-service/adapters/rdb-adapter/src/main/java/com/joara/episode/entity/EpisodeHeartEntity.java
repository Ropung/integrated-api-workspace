package com.joara.episode.entity;

import com.joara.base.jpa.entity.UuidBaseEntity;
import com.joara.rdb.JoaraPostgresSchemaConstants;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@ToString
@Table(
		name = JoaraPostgresSchemaConstants.TB_EPISODE_HEART,
		schema = JoaraPostgresSchemaConstants.SCHEMA
)
public class EpisodeHeartEntity extends UuidBaseEntity {
	public UUID memberId;
	public UUID eipId;
	public String nickname;

}