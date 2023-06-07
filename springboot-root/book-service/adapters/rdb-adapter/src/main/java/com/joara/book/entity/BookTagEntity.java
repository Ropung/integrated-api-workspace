package com.joara.book.entity;


import com.joara.base.jpa.entity.UuidBaseEntity;
import com.joara.rdb.JoaraPostgresSchemaConstants;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

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
		name = JoaraPostgresSchemaConstants.TB_BOOK_TAG,
		schema = JoaraPostgresSchemaConstants.SCHEMA,
				catalog = JoaraPostgresSchemaConstants.SCHEMA
)
public class BookTagEntity extends UuidBaseEntity {
	public UUID bookId;
	public UUID bookFreeTagId;
}
