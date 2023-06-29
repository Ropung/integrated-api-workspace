package com.joara.favorite.mapper;

import com.joara.book.domain.model.book.MemberFavorBook;
import com.joara.favorite.entity.MemberFavorBookEntity;
import com.joara.support.mapper.BaseEntityMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface FavoriteEntityMapper extends BaseEntityMapper<MemberFavorBook, MemberFavorBookEntity> {
    @Override
    @Mapping(target = "genreIdList", ignore = true)
    MemberFavorBook toDomain(MemberFavorBookEntity entity);
//    {
////        assert false : "Book 엔티티만으로 Book으로 변환하는 기능은 잠시 막아 둠. 기획 확정 후 재결정."; // <<< 자바 실행 때 Assertion Enabled 된 상태로 실행이 되면 걸러주는. -- test 등에서. -- 코드 자체가 하자 있는.
////        throw new Error("Book 엔티티만으로 Book으로 변환하는 기능은 잠시 막아 둠. 기획 확정 후 재결정."); // Error는 Exception과 달리 가급적 catch 하지 말기 -- 코드 자체가 하자 있는.
//    }

    MemberFavorBook toReadModel(
            MemberFavorBookEntity entity,
            String coverUrl,
            List<Long> genreIdList,
            List<String> genreNameList
    );
}
