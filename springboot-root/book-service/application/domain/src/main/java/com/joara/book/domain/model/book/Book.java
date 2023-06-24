package com.joara.book.domain.model.book;


import com.joara.book.domain.model.book.type.BookStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;


@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Builder
public class Book {
	public Long id;
	public UUID memberId;
	public List<Long> genreIdList;
	public String nickname;
	public String title;
	public String description;
	public String coverUrl;
	public BookStatus status;
	public Long totalViewCount;
	public Long totalHeartCount;
	public Long favorCount;
	public OffsetDateTime createdAt;
	public OffsetDateTime updatedAt;
	public OffsetDateTime deletedAt;
	public Double score;
}
