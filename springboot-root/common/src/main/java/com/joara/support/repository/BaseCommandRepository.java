package com.joara.support.repository;

import java.util.Optional;

public interface BaseCommandRepository<T, ID> {
    T save(T domain);
    Optional<T> findById(ID id);
}
