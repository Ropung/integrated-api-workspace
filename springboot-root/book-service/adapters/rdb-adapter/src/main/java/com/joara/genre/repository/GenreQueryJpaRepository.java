package com.joara.genre.repository;

import com.joara.genre.entity.GenreEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreQueryJpaRepository extends JpaRepository<GenreEntity, Long> {
}
