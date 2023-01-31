package ru.otus.jdbc.dao;

import ru.otus.jdbc.model.Genre;

import java.util.List;
import java.util.Optional;

public interface GenreDao {
    long count();

    Genre insert(Genre genre);

    Genre update(Genre genre);

    boolean deleteById(long id);

    Optional<Genre> getById(long id);

    List<Genre> getAll();
}
