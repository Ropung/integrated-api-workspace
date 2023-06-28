package com.joara.support.repository;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface BaseCommandRepository<T, ID> {
    T save(T domain);
    Optional<T> findById(ID id);
}
