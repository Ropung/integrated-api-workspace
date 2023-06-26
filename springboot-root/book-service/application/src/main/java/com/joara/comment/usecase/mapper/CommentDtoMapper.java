package com.joara.comment.usecase.mapper;


import com.joara.book.domain.model.comment.Comment;
import com.joara.comment.usecase.dto.CommentCommandDto.CommentCreateRequestDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CommentDtoMapper {
	Comment from(CommentCreateRequestDto dto);
}
