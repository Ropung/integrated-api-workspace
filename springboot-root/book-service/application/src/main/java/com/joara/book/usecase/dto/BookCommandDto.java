package com.joara.book.usecase.dto;

import com.joara.book.domain.model.book.type.BookStatus;
import lombok.Builder;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public record BookCommandDto() {
	
	public record BookCreateRequestDto(
			Long genreId,
			@Size(max = 30, min = 3, message = "책 제목은 3~30 자리입니다.")
			String title,
			@Size(max = 255, message = "책 소개는 최대 255 자리입니다.")
        	String description,
			BookStatus status
	) {}

	public record BookModifyRequestDto(
			@NotNull
			Long bookId,
			String title,
			String description,
			// TODO file,
			BookStatus status // FIXME ... (기획 검토)
	) {}

	public record BookRemoveRequestDto(
			@NotNull
			Long bookId
	) {}

	@Builder
	public record BookCreateResponseDto(
			Boolean success
	) {}

	@Builder
	public record BookModifyResponseDto(
			Boolean success
	) {}

	@Builder
	public record BookRemoveResponseDto(
			Boolean success
	) {}
}
