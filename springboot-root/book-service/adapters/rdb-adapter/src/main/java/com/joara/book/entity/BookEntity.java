package com.joara.book.entity;

import com.joara.base.jpa.entity.UuidBaseEntity;
import com.joara.rdb.JoaraPostgresSchemaConstants;
import com.joara.util.time.ServerTime;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

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
		name = JoaraPostgresSchemaConstants.TB_BOOK,
		schema = JoaraPostgresSchemaConstants.SCHEMA,
				catalog = JoaraPostgresSchemaConstants.SCHEMA
)
public class BookEntity extends UuidBaseEntity {
	public UUID memberId;
	public String memberNickname;
	public UUID genreId;
	public String title;
	public String description;
	public String coverUrl;
	public String isbn;
	public String cip;
	@Builder.Default
	public Double avgScore = 0.;
	@Builder.Default
	public OffsetDateTime createdAt = ServerTime.now();
	public OffsetDateTime updatedAt;
	public OffsetDateTime deletedAt;
}

