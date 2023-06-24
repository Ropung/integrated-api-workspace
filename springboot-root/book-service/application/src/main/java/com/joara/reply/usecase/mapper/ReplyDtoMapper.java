package com.joara.reply.usecase.mapper;

import com.joara.book.domain.model.reply.Reply;
import com.joara.reply.usecase.dto.ReplyCommandDto.ReplyCreateRequestDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ReplyDtoMapper {
	Reply from(ReplyCreateRequestDto dto);
}
