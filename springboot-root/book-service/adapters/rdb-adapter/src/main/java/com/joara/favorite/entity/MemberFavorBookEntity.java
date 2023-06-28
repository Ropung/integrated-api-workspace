package com.joara.favorite.entity;

import com.joara.base.jpa.entity.UuidBaseEntity;
import com.joara.rdb.JoaraPostgresSchemaConstants;
import com.joara.util.time.ServerTime;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Table(
        name = JoaraPostgresSchemaConstants.TB_MEMBER_FAVOR_BOOK,
        schema = JoaraPostgresSchemaConstants.SCHEMA
)
public class MemberFavorBookEntity extends UuidBaseEntity {
    public Long bookId;
    public Long genreId;
    public UUID memberId;
    public String nickname;
    public String bookTitle;
    @Builder.Default
    public OffsetDateTime createdAt = ServerTime.now();
    public OffsetDateTime updatedAt;
    public OffsetDateTime deletedAt;
}
