package ru.otus.jdbc.service;

import ru.otus.jdbc.model.Book;

import java.util.List;

public interface BookService {
    long getCount();

    Book addBook(String name, long authorId, long genreId);

    Book changeBook(long id, String name, long authorId, long genreId);

    Book findBookById(long id);

    List<Book> getAll();

    boolean deleteBookById(long id);
}
