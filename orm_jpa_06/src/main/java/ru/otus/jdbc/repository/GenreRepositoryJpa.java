package ru.otus.jdbc.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.jdbc.model.Genre;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class GenreRepositoryJpa implements GenreRepository {
    @PersistenceContext
    private final EntityManager entityManager;

    @Override
    public long count() {
        return entityManager.createQuery("select count(*) from Genre", Long.class)
                .getSingleResult();
    }

    @Override
    public Genre save(Genre genre) {
        Genre result;
        if (genre.getId() == 0) {
            entityManager.persist(genre);
            entityManager.flush();
            result = genre;
        } else {
            result = entityManager.merge(genre);
        }
        return result;
    }

    @Override
    public boolean deleteById(long id) {
        return getById(id).map(genre -> {
            entityManager.remove(genre);
            return !entityManager.contains(genre);
        }).orElse(false);
    }

    @Override
    public long deleteAll() {
        return entityManager.createQuery("delete from Genre").executeUpdate();
    }

    @Override
    public Optional<Genre> getById(long id) {
        return Optional.ofNullable(entityManager.find(Genre.class, id));
    }

    @Override
    public Optional<Genre> getByName(String name) {
        TypedQuery<Genre> query = entityManager.createQuery(
                "select g from Genre g where g.name = :name", Genre.class);
        query.setParameter("name", name);
        return query.getResultStream().findFirst();
    }

    @Override
    public List<Genre> getAll() {
        return entityManager.createQuery("select b from Genre b", Genre.class).getResultList();
    }
}
