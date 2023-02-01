package ru.otus.jdbc.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import ru.otus.jdbc.model.Genre;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@JdbcTest
@Import(GenreDaoJdbc.class)
class GenreDaoJdbcTest {

    @Autowired
    private GenreDaoJdbc genreDao;

    @DisplayName("возвращать ожидаемое количество жанров в БД")
    @Test
    void shouldReturnExpectedGenresCount() {
        long actualPersonsCount = genreDao.count();
        assertThat(actualPersonsCount).isEqualTo(DEFAULT_REPOSITORY_SIZE);
    }

    @DisplayName("вставка должна генерировать идентификатор")
    @Test
    void shouldGenreBeAdded() {
        String genreName = "new genreName";
        Genre newAuthor = genreDao.insert(new Genre(genreName));
        assertNotEquals(newAuthor.getId(), 0L);
        assertEquals(newAuthor.getName(), genreName);
    }

    @DisplayName("должен корректно возвращать список жанров")
    @Test
    void listGenresShouldBeReturnedCorrect() {
        List<Genre> genres = genreDao.getAll();
        assertEquals(genres.size(), DEFAULT_REPOSITORY_SIZE);
        assertEquals(genres.get(0).getName(), "HORROR");
        assertEquals(genres.get(1).getName(), "DRAMA");
    }

    @DisplayName("жанр должен корректно обновляться")
    @Test
    void genreShouldBeUpdatedCorrect() {
        Genre newGenre = genreDao.insert(new Genre("new_genre"));
        long genreId = newGenre.getId();
        Genre forUpdate = new Genre(genreId, "updated name");
        genreDao.update(forUpdate);
        Optional<Genre> optional = genreDao.getById(genreId);
        assertTrue(optional.isPresent());
        Genre genre = optional.get();
        assertEquals(genre.getId(), genreId);
        assertEquals(genre.getName(), forUpdate.getName());
    }

    @DisplayName("должен корректно возвращать жанр по id")
    @Test
    void genreShouldBeReturnedById() {
        int horrorId = 10;
        Optional<Genre> optional = genreDao.getById(horrorId);
        assertTrue(optional.isPresent());
        Genre genre = optional.get();
        assertEquals(genre.getName(), "HORROR");
        assertEquals(genre.getId(), horrorId);
    }

    @DisplayName("жанр должен удаляться по id")
    @Test
    void genreShouldBeDeleted() {
        Genre newGenre = genreDao.insert(new Genre("new_genre"));
        assertTrue(genreDao.deleteById(newGenre.getId()));
        assertFalse(genreDao.deleteById(newGenre.getId()));
    }

    private static final int DEFAULT_REPOSITORY_SIZE = 3;
}
