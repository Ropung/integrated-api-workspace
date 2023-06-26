package com.joara.book.entity;

import com.joara.base.jpa.entity.UuidBaseEntity;
import com.joara.rdb.JoaraPostgresSchemaConstants;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Table(
        name = JoaraPostgresSchemaConstants.TB_BOOK_GENRE_MAP,
        schema = JoaraPostgresSchemaConstants.SCHEMA
)
public class BookGenreMapEntity extends UuidBaseEntity {
    public Long bookId;
    public Long genreId;
}
