package com.joara.book.mapper;

import com.joara.book.domain.model.BookReadModels.BookListViewReadModel;
import com.joara.book.domain.model.book.Book;
import com.joara.book.entity.BookEntity;
import com.joara.book.projection.BookQueryProjections.BookListViewProjection;
import com.joara.support.mapper.BaseEntityMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BookEntityMapper extends BaseEntityMapper<Book, BookEntity> {
    @Mapping(target = "genreName", source = "genreKor")
    BookListViewReadModel toReadModel(BookListViewProjection projection);
}
