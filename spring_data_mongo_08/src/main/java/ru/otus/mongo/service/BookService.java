package ru.otus.mongo.service;

import ru.otus.mongo.model.Book;

import java.util.List;

public interface BookService {
    long getCount();

    Book addBook(String name, String authorId, String genreId);

    Book changeBook(String id, String name, String authorId, String genreId);

    Book findBookById(String id);

    List<Book> getAll();

    boolean deleteBookById(String id);
}