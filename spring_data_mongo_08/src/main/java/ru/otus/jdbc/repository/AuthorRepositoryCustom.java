package ru.otus.jdbc.repository;

import ru.otus.jdbc.model.Author;

import java.util.Optional;

public interface AuthorRepositoryCustom {
    boolean deleteByIdBool(String id);

    long deleteAllWithCounter();
}
