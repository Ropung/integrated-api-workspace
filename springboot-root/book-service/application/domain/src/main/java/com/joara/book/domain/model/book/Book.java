package com.joara.book.domain.model.book;


import com.joara.book.domain.model.book.type.BookStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.OffsetDateTime;
import java.util.UUID;


@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Builder
public class Book {
	public Long id;
	public UUID memberId;
	public Long genreId; // list view -> genre name
	public String genreKor;
	public String nickname; // list view
	public String title; // list view
	public String description; // list view
	public String coverUrl; // list view
	public BookStatus status; // list view
	public Long totalViewCount; // list view
	public Long totalHeartCount; // list view
	public Long favorCount; // list view
	public OffsetDateTime createdAt; // list view
	public OffsetDateTime updatedAt; // list view
	public OffsetDateTime deletedAt; // list view
}

