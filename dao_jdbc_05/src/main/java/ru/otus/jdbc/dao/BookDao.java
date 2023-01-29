package ru.otus.jdbc.dao;

import ru.otus.jdbc.model.Author;

import java.util.List;

public interface BookDao {
    void insert(Author author);

    void deleteById(long id);

    Author getById(long id);

    List<Author> getAll();
}
