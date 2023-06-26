package com.joara.comment.mapper;

import com.joara.book.domain.model.comment.Comment;
import com.joara.comment.entitiy.CommentEntity;
import com.joara.support.mapper.BaseEntityMapper;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface CommentEntityMapper extends BaseEntityMapper<Comment, CommentEntity> {
    Comment toDomainList(CommentEntity entityList);
}