package ru.otus.jdbc.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import ru.otus.jdbc.model.Genre;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@Import(GenreRepositoryJpa.class)
class GenreRepositoryJpaTest {

    @Autowired
    private GenreRepositoryJpa genreRepository;

    @DisplayName("возвращать ожидаемое количество жанров в БД")
    @Test
    void shouldReturnExpectedGenresCount() {
        long actualPersonsCount = genreRepository.count();
        assertThat(actualPersonsCount).isEqualTo(DEFAULT_REPOSITORY_SIZE);
    }

    @DisplayName("вставка должна генерировать идентификатор")
    @Test
    void shouldGenreBeAdded() {
        String genreName = "new genreName";
        Genre genre = Genre.builder().name(genreName).build();
        Genre newAuthor = genreRepository.save(genre);
        assertNotEquals(newAuthor.getId(), 0L);
        assertEquals(newAuthor.getName(), genreName);
    }

    @DisplayName("должен корректно возвращать список жанров")
    @Test
    void listGenresShouldBeReturnedCorrect() {
        List<Genre> genres = genreRepository.getAll();
        assertEquals(genres.size(), DEFAULT_REPOSITORY_SIZE);
        assertEquals(genres.get(0).getName(), "HORROR");
        assertEquals(genres.get(1).getName(), "DRAMA");
    }

    @DisplayName("жанр должен корректно обновляться")
    @Test
    void genreShouldBeUpdatedCorrect() {
        Genre initGenre = Genre.builder().name("new_genre").build();
        Genre newGenre = genreRepository.save(initGenre);
        long genreId = newGenre.getId();
        Genre forUpdate = new Genre(genreId, "updated name");
        genreRepository.save(forUpdate);
        Optional<Genre> optional = genreRepository.getById(genreId);
        assertTrue(optional.isPresent());
        Genre genre = optional.get();
        assertEquals(genre.getId(), genreId);
        assertEquals(genre.getName(), forUpdate.getName());
    }

    @DisplayName("должен корректно возвращать жанр по id")
    @Test
    void genreShouldBeReturnedById() {
        int horrorId = 10;
        Optional<Genre> optional = genreRepository.getById(horrorId);
        assertTrue(optional.isPresent());
        Genre genre = optional.get();
        assertEquals(genre.getName(), "HORROR");
        assertEquals(genre.getId(), horrorId);
    }

    @DisplayName("жанр должен удаляться по id")
    @Test
    void genreShouldBeDeleted() {
        Genre genre = Genre.builder().name("new_genre").build();
        Genre newGenre = genreRepository.save(genre);
        assertTrue(genreRepository.deleteById(newGenre.getId()));
        assertFalse(genreRepository.deleteById(newGenre.getId()));
    }

    private static final int DEFAULT_REPOSITORY_SIZE = 3;
}
