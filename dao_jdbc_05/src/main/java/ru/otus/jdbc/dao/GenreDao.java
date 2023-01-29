package ru.otus.jdbc.dao;

import ru.otus.jdbc.model.Author;
import ru.otus.jdbc.model.Genre;

import java.util.List;

public interface GenreDao {
    void insert(Genre genre);

    void deleteById(long id);

    Author getById(long id);

    List<Genre> getAll();

}
