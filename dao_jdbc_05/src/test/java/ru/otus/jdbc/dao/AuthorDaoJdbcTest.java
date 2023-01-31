package ru.otus.jdbc.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import ru.otus.jdbc.model.Author;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@JdbcTest
@Import(AuthorDaoJdbc.class)
class AuthorDaoJdbcTest {

    @Autowired
    private AuthorDaoJdbc authorDao;

    @DisplayName("возвращать ожидаемое количество пёрсонов в БД")
    @Test
    void shouldReturnExpectedPersonCount() {
        long actualPersonsCount = authorDao.count();
        assertThat(actualPersonsCount).isEqualTo(DEFAULT_REPOSITORY_SIZE);
    }

    @DisplayName("вставка должна генерировать идентификатор")
    @Test
    void shouldAuthorBeAdded() {
        String authorName = "new authorName";
        Author newAuthor = authorDao.insert(new Author(authorName));
        assertNotEquals(newAuthor.getId(), 0L);
        assertEquals(newAuthor.getName(), authorName);
    }

    @DisplayName("должен корректно возвращать список пользователей")
    @Test
    void listPersonsShouldBeReturnedCorrect() {
        List<Author> authors = authorDao.getAll();
        assertEquals(authors.size(), DEFAULT_REPOSITORY_SIZE);
        assertEquals(authors.get(0).getName(), "PUSHKIN");
        assertEquals(authors.get(1).getName(), "LERMONTOV");
    }

    @DisplayName("пользователь должен корректно обновляться")
    @Test
    void personShouldBeUpdatedCorrect() {
        Author newAuthor = authorDao.insert(new Author("new_author"));
        long authorId = newAuthor.getId();
        Author forUpdate = new Author(authorId, "updated name");
        authorDao.update(forUpdate);
        Optional<Author> optional = authorDao.getById(authorId);
        assertTrue(optional.isPresent());
        Author author = optional.get();
        assertEquals(author.getId(), authorId);
        assertEquals(author.getName(), forUpdate.getName());
    }

    @DisplayName("должен корректно возвращать пользователя по id")
    @Test
    void personShouldBeReturnedById() {
        int pushkinId = 10;
        Optional<Author> optional = authorDao.getById(pushkinId);
        assertTrue(optional.isPresent());
        Author author = optional.get();
        assertEquals(author.getName(), "PUSHKIN");
        assertEquals(author.getId(), pushkinId);
    }

    @DisplayName("пользователь должен удаляться по id")
    @Test
    void personShouldBeDeleted() {
        Author newAuthor = authorDao.insert(new Author("new_author"));
        assertTrue(authorDao.deleteById(newAuthor.getId()));
        assertFalse(authorDao.deleteById(newAuthor.getId()));
    }

    private static final int DEFAULT_REPOSITORY_SIZE = 2;
}
