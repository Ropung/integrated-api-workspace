package com.joara.book.mapper;

import com.joara.book.domain.model.BookReadModels.AnalyzedBookReadModel;
import com.joara.book.domain.model.BookReadModels.BookDetailedViewReadModel;
import com.joara.book.domain.model.BookReadModels.BookListViewReadModel;
import com.joara.book.domain.model.book.Book;
import com.joara.book.entity.BookEntity;
import com.joara.book.projection.BookQueryProjections.AnalyzedBookProjection;
import com.joara.book.projection.BookQueryProjections.BookDetailedViewProjection;
import com.joara.book.projection.BookQueryProjections.BookListViewProjection;
import com.joara.support.mapper.BaseEntityMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BookEntityMapper extends BaseEntityMapper<Book, BookEntity> {
    @Override
    @Mapping(target = "genreIdList", ignore = true)

    Book toDomain(BookEntity entity);
//    {
//        assert false : "Book 엔티티만으로 Book으로 변환하는 기능은 잠시 막아 둠. 기획 확정 후 재결정."; // <<< 자바 실행 때 Assertion Enabled 된 상태로 실행이 되면 걸러주는. -- test 등에서. -- 코드 자체가 하자 있는.
//        throw new Error("Book 엔티티만으로 Book으로 변환하는 기능은 잠시 막아 둠. 기획 확정 후 재결정."); // Error는 Exception과 달리 가급적 catch 하지 말기 -- 코드 자체가 하자 있는.
//    }
    Book toDomain(BookEntity entity, List<Long> genreIdList);

    BookListViewReadModel toReadModel(
            BookListViewProjection projection,
            List<Long> genreIdList,
            List<String> genreNameList
    );

    BookDetailedViewReadModel toReadModel(
            BookDetailedViewProjection projection,
            Integer episodeSize,
            List<Long> genreIdList,
            List<String> genreNameList
    );

    AnalyzedBookReadModel toReadModel(
            AnalyzedBookProjection projection,
            List<Long> genreIds,
            List<String> genreNames
    );

}
