package ru.otus.jdbc.repository;

import ru.otus.jdbc.model.Author;

import java.util.Optional;

public interface AuthorRepositoryCustom {
    boolean deleteById(long id);

    long deleteAllWithCounter();
}
