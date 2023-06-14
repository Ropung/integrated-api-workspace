package com.joara.book.domain.model.book;


import lombok.*;

import java.time.OffsetDateTime;
import java.util.UUID;


@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Builder
public class Book {
	public UUID id;
	public UUID memberId;
	public String memberNickname;
	public UUID genreId;
	public String title;
	public String description;
	public String coverUrl;
	public String isbn;
	public String cip;
	public Double avgScore;
	public OffsetDateTime createdAt;
	public OffsetDateTime updatedAt;
	public OffsetDateTime deletedAt;
}

