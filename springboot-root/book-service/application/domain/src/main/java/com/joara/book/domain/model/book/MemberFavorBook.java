package com.joara.book.domain.model.book;

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
public class MemberFavorBook {
	public UUID id;
	public Long bookId;
	public List<Long> genreIdList;
	public UUID memberId;
	public String nickname;
	public String bookTitle;
	public OffsetDateTime createdAt;
	public OffsetDateTime updatedAt;
	public OffsetDateTime deletedAt;
}