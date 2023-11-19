package ru.otus.jdbc.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import ru.otus.jdbc.model.Author;
import ru.otus.jdbc.model.Book;
import ru.otus.jdbc.model.Genre;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@Import(BookRepositoryJpa.class)
class BookRepositroyJpaTest {

    @Autowired
    private BookRepositoryJpa bookRepository;

    Author authorHelper = new Author(100, "author_helper");
    Genre genreHelper = new Genre(100, "genre_helper");

    @DisplayName("возвращать ожидаемое количество книг в БД")
    @Test
    void shouldReturnExpectedBookCount() {
        long actualPersonsCount = bookRepository.count();
        assertThat(actualPersonsCount).isEqualTo(DEFAULT_REPOSITORY_SIZE);
    }

    @DisplayName("вставка должна генерировать идентификатор")
    @Test
    void shouldBookBeAdded() {
        String bookName = "new bookName";
        Book initBook = Book.builder()
                .name(bookName)
                .author(authorHelper)
                .genre(genreHelper)
                .build();

        Book newBook = bookRepository.save(initBook);
        assertNotEquals(newBook.getId(), 0L);
        assertEquals(newBook.getName(), bookName);
    }

    @DisplayName("должен корректно возвращать список книг")
    @Test
    void listBooksShouldBeReturnedCorrect() {
        List<Book> books = bookRepository.getAll();
        assertEquals(books.size(), DEFAULT_REPOSITORY_SIZE);
        assertEquals(books.get(0).getName(), "RUSALKA");
        assertEquals(books.get(1).getName(), "MASQARAD");
    }

    @DisplayName("книга должен корректно обновляться")
    @Test
    void bookShouldBeUpdatedCorrect() {
        Book newBook = bookRepository.getById(10L).orElseThrow();
        long newBookId = newBook.getId();
        Book forUpdate = Book.builder()
                .id(newBookId)
                .name("updated name")
                .author(authorHelper)
                .genre(genreHelper)
                .build();
        bookRepository.save(forUpdate);
        Optional<Book> optional = bookRepository.getById(newBookId);
        assertTrue(optional.isPresent());
        Book author = optional.get();
        assertEquals(author.getId(), newBookId);
        assertEquals(author.getName(), forUpdate.getName());
        assertEquals(author.getGenre(), forUpdate.getGenre());
        assertEquals(author.getAuthor(), forUpdate.getAuthor());
    }

    @DisplayName("должен корректно возвращать книгу по id")
    @Test
    void bookShouldBeReturnedById() {
        int rusalkaId = 10;
        Optional<Book> optional = bookRepository.getById(rusalkaId);
        assertTrue(optional.isPresent());
        Book author = optional.get();
        assertEquals(author.getName(), "RUSALKA");
        assertEquals(author.getId(), rusalkaId);
    }

    @DisplayName("книга должена удаляться по id")
    @Test
    void bookShouldBeDeleted() {
        int rusalkaId = 10;
        assertTrue(bookRepository.deleteById(rusalkaId));
        assertFalse(bookRepository.deleteById(rusalkaId));
    }

    private static final int DEFAULT_REPOSITORY_SIZE = 2;
}
