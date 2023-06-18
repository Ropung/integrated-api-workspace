package com.joara.book.entity._old;


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

@Entity
@ToString
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Table(
		name = JoaraPostgresSchemaConstants.TB_BOOK_GENRE,
		schema = JoaraPostgresSchemaConstants.SCHEMA,
		catalog = JoaraPostgresSchemaConstants.SCHEMA
)
public class BookGenreEntity extends UuidBaseEntity {
	public String kor;
	public String eng;
}