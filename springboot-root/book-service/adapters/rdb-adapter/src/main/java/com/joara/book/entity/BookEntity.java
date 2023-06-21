package com.joara.book.entity;

import com.joara.base.jpa.entity.BaseEntity;
import com.joara.book.domain.model.book.type.BookStatus;
import com.joara.rdb.JoaraPostgresSchemaConstants;
import com.joara.util.time.ServerTime;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
        name = JoaraPostgresSchemaConstants.TB_BOOK,
        schema = JoaraPostgresSchemaConstants.SCHEMA
)
public class BookEntity extends BaseEntity {
    public UUID memberId;
    public Long genreId;
    public String genreKor;
    public String nickname;
    public String title;
    public String description;
    public String coverUrl;
    @Builder.Default
    @Enumerated(EnumType.STRING)
    public BookStatus status = BookStatus.PENDING;
    @Builder.Default
    public Long totalViewCount = 0L;
    @Builder.Default
    public Long totalHeartCount = 0L;
    @Builder.Default
    public Long favorCount = 0L;
    @Builder.Default
    public OffsetDateTime createdAt = ServerTime.now();
    public OffsetDateTime updatedAt;
    public OffsetDateTime deletedAt;
    @Builder.Default
    public Double score = 0.0;
}