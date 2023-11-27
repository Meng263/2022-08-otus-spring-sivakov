package ru.otus.mongo.repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
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
class AuthorRepositoryTest {
    @Autowired
    private AuthorRepository authorRepository;

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

    @DisplayName("возвращать ожидаемое количество авторов в БД")
    @Test
    void shouldReturnExpectedAuthorCount() {
        long actualPersonsCount = authorRepository.count();
        assertThat(actualPersonsCount).isEqualTo(DEFAULT_REPOSITORY_SIZE);
    }

    @DisplayName("вставка должна генерировать идентификатор")
    @Test
    void shouldAuthorBeAdded() {
        String authorName = "new authorName";
        Author author = Author.builder().name(authorName).build();
        Author newAuthor = authorRepository.save(author);
        assertNotNull(newAuthor.getId());
        assertEquals(newAuthor.getName(), authorName);
    }

    @DisplayName("должен корректно возвращать список авторов")
    @Test
    void listAuthorsShouldBeReturnedCorrect() {
        List<Author> authors = authorRepository.findAll();
        assertEquals(authors.size(), DEFAULT_REPOSITORY_SIZE);
        assertEquals(authors.get(0).getName(), "PUSHKIN");
        assertEquals(authors.get(1).getName(), "LERMONTOV");
    }

    @DisplayName("автор должен корректно обновляться")
    @Test
    void authorShouldBeUpdatedCorrect() {
        Author initAuthor = Author.builder().name("new_author").build();
        Author newAuthor = authorRepository.save(initAuthor);
        String authorId = newAuthor.getId();
        Author forUpdate = new Author(authorId, "updated name");
        authorRepository.save(forUpdate);
        Optional<Author> optional = authorRepository.findById(authorId);
        assertTrue(optional.isPresent());
        Author author = optional.get();
        assertEquals(author.getId(), authorId);
        assertEquals(author.getName(), forUpdate.getName());
    }

    @DisplayName("должен корректно возвращать автора по id")
    @Test
    void authorShouldBeReturnedById() {
        String pushkinId = "10";
        Optional<Author> optional = authorRepository.findById(pushkinId);
        assertTrue(optional.isPresent());
        Author author = optional.get();
        assertEquals(author.getName(), "PUSHKIN");
        assertEquals(author.getId(), pushkinId);
    }

    @DisplayName("автор должен удаляться по id")
    @Test
    void authorShouldBeDeleted() {
        Author initAuthor = Author.builder().name("new_author").build();
        Author newAuthor = authorRepository.save(initAuthor);
        assertTrue(authorRepository.deleteByIdBool(newAuthor.getId()));
        assertFalse(authorRepository.deleteByIdBool(newAuthor.getId()));
    }

    @DisplayName("должны находить автора по имени")
    @Test
    void authorShouldBeFoundByName() {
        String name = "LERMONTOV";
        Optional<Author> optionalAuthor = authorRepository.findByName(name);
        assertThat(optionalAuthor).isPresent();
        Author author = optionalAuthor.get();
        assertThat(author).isNotNull();
        assertEquals(name, author.getName());
        assertNotEquals(0, author.getId());
    }

    @DisplayName("Должны удалить всех и получить их количество")
    @Test
    void authorsShouldBeRemovedAndReturnsCount() {
        long count = authorRepository.deleteAllWithCounter();
        assertEquals(DEFAULT_REPOSITORY_SIZE, count);
        long countAfterRemoveAll = authorRepository.count();
        assertThat(countAfterRemoveAll).isZero();
    }

    private static final int DEFAULT_REPOSITORY_SIZE = 3;
}
