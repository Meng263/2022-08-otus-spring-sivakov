package ru.otus.mongo.repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.otus.mongo.MongoSpringBootTest;
import ru.otus.mongo.model.Author;
import ru.otus.mongo.model.Book;
import ru.otus.mongo.model.BookComment;
import ru.otus.mongo.model.Genre;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@MongoSpringBootTest
class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    private final Author authorHelper = new Author("100", "author_helper");
    private final Genre genreHelper = new Genre("100", "genre_helper");

    private List<Author> authors;
    private List<Genre> genres;
    private List<Book> books;
    private List<BookComment> comments;

    @BeforeEach
    void setUp() {
        authors = mongoTemplate.findAll(Author.class);
        genres = mongoTemplate.findAll(Genre.class);
        books = mongoTemplate.findAll(Book.class);
        comments = mongoTemplate.findAll(BookComment.class);
    }

    @AfterEach
    void tearDown() {
        mongoTemplate.getDb().drop();
        mongoTemplate.insertAll(authors);
        mongoTemplate.insertAll(genres);
        mongoTemplate.insertAll(books);
        mongoTemplate.insertAll(comments);
    }

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
        List<Book> books = bookRepository.findAll();
        assertEquals(books.size(), DEFAULT_REPOSITORY_SIZE);
        assertEquals(books.get(0).getName(), "RUSALKA");
        assertEquals(books.get(1).getName(), "MASQARAD");
    }

    @DisplayName("книга должен корректно обновляться")
    @Test
    void bookShouldBeUpdatedCorrect() {
        Book newBook = bookRepository.findById("10").orElseThrow();
        String newBookId = newBook.getId();
        Book forUpdate = Book.builder()
                .id(newBookId)
                .name("updated name")
                .author(authorHelper)
                .genre(genreHelper)
                .build();
        bookRepository.save(forUpdate);
        Optional<Book> optional = bookRepository.findById(newBookId);
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
        String rusalkaId = "10";
        Optional<Book> optional = bookRepository.findById(rusalkaId);
        assertTrue(optional.isPresent());
        Book author = optional.get();
        assertEquals(author.getName(), "RUSALKA");
        assertEquals(author.getId(), rusalkaId);
    }

    @DisplayName("книга должена удаляться по id")
    @Test
    void bookShouldBeDeleted() {
        String rusalkaId = "10";
        assertTrue(bookRepository.deleteByIdBool(rusalkaId));
        assertFalse(bookRepository.deleteByIdBool(rusalkaId));
    }

    @DisplayName("должны находить книгу по имени")
    @Test
    void authorShouldBeFoundByName() {
        String name = "RUSALKA";
        Optional<Book> optionalAuthor = bookRepository.findByName(name);
        assertThat(optionalAuthor).isPresent();
        Book book = optionalAuthor.get();
        assertThat(book).isNotNull();
        assertEquals(name, book.getName());
        assertNotEquals(0, book.getId());
    }

    @DisplayName("Должны удалить всех и получить их количество")
    @Test
    void authorsShouldBeRemovedAndReturnsCount() {
        long count = bookRepository.deleteAllWithCounter();
        assertEquals(DEFAULT_REPOSITORY_SIZE, count);
        long countAfterRemoveAll = bookRepository.count();
        assertThat(countAfterRemoveAll).isZero();
    }

    private static final int DEFAULT_REPOSITORY_SIZE = 3;
}
