package ru.otus.jdbc.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import ru.otus.jdbc.model.Author;
import ru.otus.jdbc.model.Book;
import ru.otus.jdbc.model.Genre;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@JdbcTest
@Import(BookDaoJdbc.class)
class BookDaoJdbcTest {

    @Autowired
    private BookDaoJdbc bookDao;

    Author authorHelper = new Author(100, "author_helper");
    Genre genreHelper = new Genre(100, "genre_helper");

    @DisplayName("возвращать ожидаемое количество книг в БД")
    @Test
    void shouldReturnExpectedBookCount() {
        long actualPersonsCount = bookDao.count();
        assertThat(actualPersonsCount).isEqualTo(DEFAULT_REPOSITORY_SIZE);
    }

    @DisplayName("вставка должна генерировать идентификатор")
    @Test
    void shouldBookBeAdded() {
        String bookName = "new bookName";
        Book newBook = bookDao.insert(new Book(bookName, authorHelper, genreHelper));
        assertNotEquals(newBook.getId(), 0L);
        assertEquals(newBook.getName(), bookName);
    }

    @DisplayName("должен корректно возвращать список книг")
    @Test
    void listBooksShouldBeReturnedCorrect() {
        List<Book> books = bookDao.getAll();
        assertEquals(books.size(), DEFAULT_REPOSITORY_SIZE);
        assertEquals(books.get(0).getName(), "RUSALKA");
        assertEquals(books.get(1).getName(), "MASQARAD");
    }

    @DisplayName("книга должен корректно обновляться")
    @Test
    void bookShouldBeUpdatedCorrect() {
        Book newBook = bookDao.getById(10L).orElseThrow();
        long newBookId = newBook.getId();
        Book forUpdate = new Book(newBookId, "updated name", authorHelper, genreHelper);
        bookDao.update(forUpdate);
        Optional<Book> optional = bookDao.getById(newBookId);
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
        Optional<Book> optional = bookDao.getById(rusalkaId);
        assertTrue(optional.isPresent());
        Book author = optional.get();
        assertEquals(author.getName(), "RUSALKA");
        assertEquals(author.getId(), rusalkaId);
    }

    @DisplayName("книга должена удаляться по id")
    @Test
    void bookShouldBeDeleted() {
        int rusalkaId = 10;
        assertTrue(bookDao.deleteById(rusalkaId));
        assertFalse(bookDao.deleteById(rusalkaId));
    }

    private static final int DEFAULT_REPOSITORY_SIZE = 2;
}
