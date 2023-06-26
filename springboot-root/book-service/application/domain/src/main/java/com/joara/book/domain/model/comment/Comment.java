package com.joara.book.domain.model.comment;


import com.joara.book.domain.model.comment.type.CommentStatus;
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
public class Comment {
	public UUID id;
	public UUID epiId;
	public UUID memberId;
	public String nickname;
	public String content;
	public CommentStatus status;
	public OffsetDateTime createdAt;
	public OffsetDateTime updatedAt;
	public OffsetDateTime deletedAt;
}
