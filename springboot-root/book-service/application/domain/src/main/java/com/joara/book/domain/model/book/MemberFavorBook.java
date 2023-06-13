package com.joara.book.domain.model.book;

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
public class MemberFavorBook {
	public UUID id;
	public UUID memberId;
	private UUID bookId;
	private String favorBookName;
	private OffsetDateTime createdAt;
}