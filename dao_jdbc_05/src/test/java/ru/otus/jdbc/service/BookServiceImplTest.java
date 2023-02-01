package ru.otus.jdbc.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.otus.jdbc.dao.AuthorDao;
import ru.otus.jdbc.dao.BookDao;
import ru.otus.jdbc.dao.GenreDao;
import ru.otus.jdbc.model.Author;
import ru.otus.jdbc.model.Book;
import ru.otus.jdbc.model.Genre;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest
class BookServiceImplTest {

    @MockBean
    private BookDao bookDao;
    @MockBean
    private AuthorDao authorDao;

    @MockBean
    private GenreDao genreDao;

    @Autowired
    private BookServiceImpl bookService;

    @DisplayName("должен возвращать ожидаемое количество книг в БД")
    @Test
    void shouldReturnExpectedBooksCount() {
        long expectedCount = 2;

        given(bookDao.count()).willReturn(expectedCount);
        var actualBooksCount = bookService.getCount();

        assertEquals(actualBooksCount, expectedCount);
        verify(bookDao, times(1)).count();
    }


    @DisplayName("Книга должна вставляться по name, author_id, genre_id")
    @Test
    void shouldBookBeAdded() {
        Author authorHelper = new Author(10, "helper_auhtor");
        Genre genreHelper = new Genre(10, "helper_genre");
        Book book = new Book("book name", authorHelper, genreHelper);
        Book bookWithId = new Book(10,"book name", authorHelper, genreHelper);
        given(authorDao.getById(authorHelper.getId())).willReturn(Optional.of(authorHelper));
        given(genreDao.getById(genreHelper.getId())).willReturn(Optional.of(genreHelper));
        given(bookDao.getById(bookWithId.getId())).willReturn(Optional.of(bookWithId));
        given(bookDao.insert(book)).willReturn(bookWithId);

        Book newBook = bookService.addBook(book.getName(), authorHelper.getId(), genreHelper.getId());
        assertNotEquals(newBook.getId(), 0L);
        assertEquals(newBook.getName(), book.getName());
        assertEquals(newBook.getAuthor(), authorHelper);
        assertEquals(newBook.getGenre(), genreHelper);
    }

    @DisplayName("должен корректно возвращать список книг")
    @Test
    void listBooksShouldBeReturnedCorrect() {
        List<Book> bookList = List.of(new Book("Pikovaja Dama", new Author("Puskin"), new Genre("Drama")));
        given(bookDao.getAll()).willReturn(bookList);

        assertEquals(bookService.getAll(), bookList);
    }

    @DisplayName("книга должен корректно обновляться")
    @Test
    void bookShouldBeUpdatedCorrect() {
        Author firstAuthor = new Author(10, "first_auhtor");
        Author secondAuthor = new Author(20, "second_auhtor");
        Genre firstGenre = new Genre(10, "fist_genre");
        Genre secondGenre = new Genre(20, "second_genre");
        Book book = new Book(10, "book", firstAuthor, firstGenre);
        String newBookName = "newBookName";
        Book newBook = new Book(10, newBookName, secondAuthor, secondGenre);
        given(authorDao.getById(firstAuthor.getId())).willReturn(Optional.of(firstAuthor));
        given(authorDao.getById(secondAuthor.getId())).willReturn(Optional.of(secondAuthor));
        given(genreDao.getById(firstGenre.getId())).willReturn(Optional.of(firstGenre));
        given(genreDao.getById(secondGenre.getId())).willReturn(Optional.of(secondGenre));
        given(bookDao.update(newBook)).willReturn(newBook);
        given(bookDao.getById(newBook.getId())).willReturn(Optional.of(newBook));

        Book updatedBook = bookService.changeBook(book.getId(), newBookName, secondAuthor.getId(), secondGenre.getId());
        assertEquals(updatedBook.getId(), book.getId());
        assertEquals(updatedBook.getName(), newBookName);
        assertEquals(updatedBook.getAuthor(), secondAuthor);
        assertEquals(updatedBook.getGenre(), secondGenre);
    }

    @DisplayName("должен корректно возвращать книгу по id")
    @Test
    void bookShouldBeReturnedById() {
        Author firstAuthor = new Author(10, "first_auhtor");
        Genre firstGenre = new Genre(10, "fist_genre");
        Book book = new Book(10, "book", firstAuthor, firstGenre);
        given(authorDao.getById(firstAuthor.getId())).willReturn(Optional.of(firstAuthor));
        given(genreDao.getById(firstGenre.getId())).willReturn(Optional.of(firstGenre));
        given(bookDao.getById(book.getId())).willReturn(Optional.of(book));

        Book gettedBook = bookService.findBookById(book.getId());
        assertEquals(gettedBook.getId(), book.getId());
        assertEquals(gettedBook.getName(), book.getName());
        assertEquals(gettedBook.getAuthor(), firstAuthor);
        assertEquals(gettedBook.getGenre(), firstGenre);
    }

    @DisplayName("книга должен удаляться по id")
    @Test
    void bookShouldBeDeleted() {
        Author firstAuthor = new Author(10, "first_auhtor");
        Genre firstGenre = new Genre(10, "fist_genre");
        Book book = new Book(10, "book", firstAuthor, firstGenre);
        given(bookDao.deleteById(book.getId())).willReturn(true).willReturn(false);

        assertTrue(bookService.deleteBookById(book.getId()));
        assertFalse(bookService.deleteBookById(book.getId()));
        verify(bookDao, times(2)).deleteById(book.getId());
    }
}
