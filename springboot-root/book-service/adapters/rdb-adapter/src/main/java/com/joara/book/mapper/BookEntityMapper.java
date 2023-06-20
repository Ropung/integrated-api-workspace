package com.joara.book.mapper;

import com.joara.book.domain.model.book.Book;
import com.joara.book.entity.BookEntity;
import com.joara.support.mapper.BaseEntityMapper;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BookEntityMapper extends BaseEntityMapper<Book, BookEntity> {
    List<Book> toDomainList(List<BookEntity> entityList);
    List<Book> toEntityList(List<BookEntity> domainList);
}
