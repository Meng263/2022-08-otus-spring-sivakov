package ru.otus.jdbc.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.Rollback;
import ru.otus.jdbc.model.Author;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@Import(AuthorRepository.class)
class AuthorRepositoryJpaTest {

    @Autowired
    private AuthorRepository authorRepository;

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
        assertNotEquals(newAuthor.getId(), 0L);
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
        long authorId = newAuthor.getId();
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
        long pushkinId = 10;
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
        assertTrue(authorRepository.deleteById(newAuthor.getId()));
        assertFalse(authorRepository.deleteById(newAuthor.getId()));
    }

    private static final int DEFAULT_REPOSITORY_SIZE = 3;
}
