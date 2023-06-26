package com.joara.book.domain.model.reply;


import com.joara.book.domain.model.reply.type.ReplyStatus;
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
public class Reply {
	public UUID id;
	public UUID commentId;
	public UUID memberId;
	public String nickname;
	public String content;
	public ReplyStatus status;
	public OffsetDateTime createdAt;
	public OffsetDateTime updatedAt;
	public OffsetDateTime deletedAt;
}
