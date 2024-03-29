package ru.otus.jdbc.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.jdbc.model.Author;
import ru.otus.jdbc.model.Book;
import ru.otus.jdbc.model.Genre;
import ru.otus.jdbc.repository.AuthorRepository;
import ru.otus.jdbc.repository.BookRepository;
import ru.otus.jdbc.repository.GenreRepository;

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
    public Book changeBook(long id, String name, long authorId, long genreId) {
        Author author = authorRepository.findById(authorId).orElseThrow();
        Genre genre = genreRepository.findById(genreId).orElseThrow();
        Book book = bookRepository.findById(id).orElseThrow();
        book.setAuthor(author);
        book.setGenre(genre);
        bookRepository.save(book);
        return bookRepository.findById(id).orElseThrow();
    }

    @Override
    @Transactional
    public Book addBook(String name, long authorId, long genreId) {
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
    public Book findBookById(long id) {
        return bookRepository.findById(id).orElseThrow();
    }

    @Override
    public List<Book> getAll() {
        return bookRepository.findAll();
    }

    @Override
    public boolean deleteBookById(long id) {
        return bookRepository.deleteById(id);
    }
}
