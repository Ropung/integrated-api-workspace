package com.joara.support.repository;

import java.util.List;
import java.util.Optional;

public interface BaseRepository<T, ID> {
    T save(T domain);
    Optional<T> findById(ID id);
}
