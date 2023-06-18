package com.joara.book.entity;

import com.joara.base.jpa.entity.UuidBaseEntity;
import com.joara.rdb.JoaraPostgresSchemaConstants;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Table(
		name = JoaraPostgresSchemaConstants.TB_BOOK_TAG,
		schema = JoaraPostgresSchemaConstants.SCHEMA
)
public class BookTagEntity extends UuidBaseEntity {
	public Long bookId;
	public String tagName;
}
