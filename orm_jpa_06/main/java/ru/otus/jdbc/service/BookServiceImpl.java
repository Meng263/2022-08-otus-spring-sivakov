package ru.otus.jdbc.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.otus.jdbc.dao.AuthorDao;
import ru.otus.jdbc.dao.BookDao;
import ru.otus.jdbc.dao.GenreDao;
import ru.otus.jdbc.model.Author;
import ru.otus.jdbc.model.Book;
import ru.otus.jdbc.model.Genre;

import java.util.List;

@Component
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookDao bookDao;
    private final AuthorDao authorDao;
    private final GenreDao genreDao;

    @Override
    public long getCount() {
        return bookDao.count();
    }

    @Override
    public Book addBook(String name, long authorId, long genreId) {
        Author author = authorDao.getById(authorId).orElseThrow();
        Genre genre = genreDao.getById(genreId).orElseThrow();
        Book inserted = bookDao.insert(new Book(name, author, genre));
        return bookDao.getById(inserted.getId()).orElseThrow();
    }

    @Override
    public Book changeBook(long id, String name, long authorId, long genreId) {
        Author author = authorDao.getById(authorId).orElseThrow();
        Genre genre = genreDao.getById(genreId).orElseThrow();
        bookDao.update(new Book(id, name, author, genre));
        return bookDao.getById(id).orElseThrow();
    }

    @Override
    public Book findBookById(long id) {
        return bookDao.getById(id).orElseThrow();
    }

    @Override
    public List<Book> getAll() {
        return bookDao.getAll();
    }

    @Override
    public boolean deleteBookById(long id) {
        return bookDao.deleteById(id);
    }
}
