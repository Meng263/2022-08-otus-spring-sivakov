package ru.otus.mongo.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.otus.mongo.MongoSpringBootTest;
import ru.otus.mongo.model.Genre;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@MongoSpringBootTest
class GenreRepositoryTest {

    @Autowired
    private GenreRepository genreRepository;

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
        List<Genre> genres = genreRepository.findAll();
        assertEquals(genres.size(), DEFAULT_REPOSITORY_SIZE);
        assertEquals(genres.get(0).getName(), "HORROR");
        assertEquals(genres.get(1).getName(), "DRAMA");
    }

    @DisplayName("жанр должен корректно обновляться")
    @Test
    void genreShouldBeUpdatedCorrect() {
        Genre initGenre = Genre.builder().name("new_genre").build();
        Genre newGenre = genreRepository.save(initGenre);
        String genreId = newGenre.getId();
        Genre forUpdate = new Genre(genreId, "updated name");
        genreRepository.save(forUpdate);
        Optional<Genre> optional = genreRepository.findById(genreId);
        assertTrue(optional.isPresent());
        Genre genre = optional.get();
        assertEquals(genre.getId(), genreId);
        assertEquals(genre.getName(), forUpdate.getName());
    }

    @DisplayName("должен корректно возвращать жанр по id")
    @Test
    void genreShouldBeReturnedById() {
        String horrorId = "10";
        Optional<Genre> optional = genreRepository.findById(horrorId);
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
        assertTrue(genreRepository.deleteByIdBool(newGenre.getId()));
        assertFalse(genreRepository.deleteByIdBool(newGenre.getId()));
    }

    @DisplayName("должны находить жанр по имени")
    @Test
    void authorShouldBeFoundByName() {
        String name = "HORROR";
        Optional<Genre> optionalAuthor = genreRepository.findByName(name);
        assertThat(optionalAuthor).isPresent();
        Genre genre = optionalAuthor.get();
        assertThat(genre).isNotNull();
        assertEquals(name, genre.getName());
        assertNotEquals(0, genre.getId());
    }

    @DisplayName("Должны удалить всех и получить их количество")
    @Test
    void authorsShouldBeRemovedAndReturnsCount() {
        long count = genreRepository.deleteAllWithCounter();
        assertEquals(DEFAULT_REPOSITORY_SIZE, count);
        long countAfterRemoveAll = genreRepository.count();
        assertThat(countAfterRemoveAll).isZero();
    }

    private static final int DEFAULT_REPOSITORY_SIZE = 3;
}
