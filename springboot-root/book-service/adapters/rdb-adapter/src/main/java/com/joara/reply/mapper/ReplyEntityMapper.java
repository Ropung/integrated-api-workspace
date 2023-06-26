package com.joara.reply.mapper;

import com.joara.book.domain.model.reply.Reply;
import com.joara.reply.entitiy.ReplyEntity;
import com.joara.support.mapper.BaseEntityMapper;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface ReplyEntityMapper extends BaseEntityMapper<Reply, ReplyEntity> {
    Reply toDomainList(ReplyEntity entityList);
}