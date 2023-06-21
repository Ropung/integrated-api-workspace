package com.joara.genre.entity;

import com.joara.base.jpa.entity.BaseEntity;
import com.joara.rdb.JoaraPostgresSchemaConstants;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@ToString
@Table(
		name = JoaraPostgresSchemaConstants.TB_BOOK_GENRE,
		schema = JoaraPostgresSchemaConstants.SCHEMA
)
public class GenreEntity extends BaseEntity {
	@Column(name = "genre_kor")
	public String kor;

	@Column(name = "genre_eng")
	public String eng;
}