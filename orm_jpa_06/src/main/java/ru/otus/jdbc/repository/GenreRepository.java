package ru.otus.jdbc.repository;

import ru.otus.jdbc.model.Genre;

import java.util.List;
import java.util.Optional;

public interface GenreRepository {
    long count();

    Genre save(Genre book);

    Optional<Genre> getById(long id);

    Optional<Genre> getByName(String name);

    List<Genre> getAll();

    boolean deleteById(long id);

    long deleteAll();
}
