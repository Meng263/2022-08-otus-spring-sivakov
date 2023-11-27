package ru.otus.mongo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.mongo.model.Author;
import ru.otus.mongo.model.Book;
import ru.otus.mongo.model.Genre;
import ru.otus.mongo.repository.AuthorRepository;
import ru.otus.mongo.repository.BookRepository;
import ru.otus.mongo.repository.GenreRepository;

import java.util.List;

@Component
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final GenreRepository genreRepository;

    @Override
    public long getCount() {
        return bookRepository.count();
    }

    @Override
    @Transactional
    public Book changeBook(String id, String name, String authorId, String genreId) {
        Author author = authorRepository.findById(authorId).orElseThrow();
        Genre genre = genreRepository.findById(genreId).orElseThrow();
        Book book = Book.builder()
                .id(id)
                .name(name)
                .author(author)
                .genre(genre)
                .build();
        bookRepository.save(book);
        return bookRepository.findById(id).orElseThrow();
    }

    @Transactional
    @Override
    public Book addBook(String name, String authorId, String genreId) {
        Author author = authorRepository.findById(authorId).orElseThrow();
        Genre genre = genreRepository.findById(genreId).orElseThrow();
        Book book = Book.builder()
                .name(name)
                .author(author)
                .genre(genre)
                .build();

        Book inserted = bookRepository.save(book);
        return bookRepository.findById(inserted.getId()).orElseThrow();
    }

    @Override
    @Transactional(readOnly = true)
    public Book findBookById(String id) {
        return bookRepository.findById(id).orElseThrow();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Book> getAll() {
        return bookRepository.findAll();
    }

    @Override
    @Transactional
    public boolean deleteBookById(String id) {
        return bookRepository.deleteByIdBool(id);
    }
}
