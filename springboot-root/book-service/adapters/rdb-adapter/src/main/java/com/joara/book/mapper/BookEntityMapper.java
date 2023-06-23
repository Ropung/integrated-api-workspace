package com.joara.book.mapper;

import com.joara.book.domain.model.BookReadModels.BookDetailedViewReadModel;
import com.joara.book.domain.model.BookReadModels.BookListViewReadModel;
import com.joara.book.domain.model.book.Book;
import com.joara.book.entity.BookEntity;
import com.joara.book.projection.BookQueryProjections.BookDetailedViewProjection;
import com.joara.book.projection.BookQueryProjections.BookListViewProjection;
import com.joara.support.mapper.BaseEntityMapper;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BookEntityMapper extends BaseEntityMapper<Book, BookEntity> {
    BookListViewReadModel toReadModel(BookListViewProjection projection,
            List<Long> genreIdList,
            List<String> genreNameList
    );

    BookDetailedViewReadModel toReadModel(
            BookDetailedViewProjection projection,
            List<Long> genreIdList,
            List<String> genreNameList
    );
}
