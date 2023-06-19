package com.joara.book.usecase.mapper;

import com.joara.book.domain.model.book.Book;
import com.joara.book.usecase.dto.BookCommandDto.BookCreateRequestDto;
import com.joara.book.usecase.dto.BookQueryDto.BookReadByOneResponseDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BookDtoMapper {
	Book from(BookCreateRequestDto dto);
	BookReadByOneResponseDto toBookReadByOneRequestDto(Book book);
}
