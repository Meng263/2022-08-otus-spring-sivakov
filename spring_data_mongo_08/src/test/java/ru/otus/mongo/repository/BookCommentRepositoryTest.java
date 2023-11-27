package ru.otus.mongo.repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
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
public class BookCommentRepositoryTest {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

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

    private final Book bookHelper = Book.builder().id("100").name("book helper").build();

    @DisplayName("возвращать ожидаемое количество коментов в БД")
    @Test
    void shouldReturnExpectedAuthorCount() {
        long actualPersonsCount = commentRepository.count();
        assertThat(actualPersonsCount).isEqualTo(DEFAULT_REPOSITORY_SIZE);
    }

    @DisplayName("вставка должна генерировать идентификатор")
    @Test
    void shouldAuthorBeAdded() {
        String commentText = "some text";
        BookComment bookComment = BookComment.builder().text(commentText).book(bookHelper).build();
        BookComment newComment = commentRepository.save(bookComment);
        assertNotEquals(newComment.getId(), 0L);
        assertEquals(newComment.getText(), commentText);
    }

    @DisplayName("должен корректно возвращать список коментов")
    @Test
    void listAuthorsShouldBeReturnedCorrect() {
        List<BookComment> authors = commentRepository.findAll();
        assertEquals(authors.size(), DEFAULT_REPOSITORY_SIZE);
        assertEquals(authors.get(0).getText(), "COMMENT1");
        assertEquals(authors.get(1).getText(), "COMMENT2");
    }

    @DisplayName("коммент должен корректно обновляться")
    @Test
    void authorShouldBeUpdatedCorrect() {
        BookComment bookComment = BookComment.builder().text("init text").book(bookHelper).build();
        BookComment savedComment = commentRepository.save(bookComment);
        String commentId = savedComment.getId();

        BookComment commentForUpdate = BookComment.builder().id(commentId).text("updated text").book(bookHelper).build();
        commentRepository.save(commentForUpdate);
        Optional<BookComment> optional = commentRepository.findById(commentId);
        assertTrue(optional.isPresent());
        BookComment comment = optional.get();
        assertEquals(comment.getId(), commentId);
        assertEquals(comment.getText(), commentForUpdate.getText());
    }

    @DisplayName("должен корректно возвращать автора по id")
    @Test
    void authorShouldBeReturnedById() {
        String firstCommentId = "10";
        Optional<BookComment> firstCommentOptional = commentRepository.findById(firstCommentId);
        assertTrue(firstCommentOptional.isPresent());
        BookComment comment = firstCommentOptional.get();
        assertEquals(comment.getText(), "COMMENT1");
        assertEquals(comment.getId(), firstCommentId);
    }

    @DisplayName("автор должен удаляться по id")
    @Test
    void authorShouldBeDeleted() {
        BookComment comment = BookComment.builder().book(bookHelper).text("init").build();
        BookComment savedComment = commentRepository.save(comment);
        assertTrue(commentRepository.deleteByIdBool(savedComment.getId()));
        assertFalse(commentRepository.deleteByIdBool(savedComment.getId()));
    }

    @DisplayName("Должны удалить всех и получить их количество")
    @Test
    void authorsShouldBeRemovedAndReturnsCount() {
        long count = commentRepository.deleteAllWithCounter();
        assertEquals(DEFAULT_REPOSITORY_SIZE, count);
        long countAfterRemoveAll = commentRepository.count();
        assertThat(countAfterRemoveAll).isZero();
    }

    private static final int DEFAULT_REPOSITORY_SIZE = 6;

}
