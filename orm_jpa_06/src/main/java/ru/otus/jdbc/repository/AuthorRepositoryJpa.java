package ru.otus.jdbc.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import ru.otus.jdbc.model.Author;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class AuthorRepositoryJpa implements AuthorRepository {
    @PersistenceContext
    private final EntityManager entityManager;

    @Override
    public long count() {
        return entityManager.createQuery("select count(*) from Author", Long.class)
                .getSingleResult();
    }

    @Override
    public Author save(Author author) {
        Author result;
        if (author.getId() == 0) {
            entityManager.persist(author);
            entityManager.flush();
            result = author;
        } else {
            result = entityManager.merge(author);
        }
        return result;
    }

    @Override
    public boolean deleteById(long id) {
        return getById(id).map(author -> {
            entityManager.remove(author);
            return !entityManager.contains(author);
        }).orElse(false);
    }

    @Override
    public long deleteAll() {
        return entityManager.createQuery("delete from Author").executeUpdate();
    }

    @Override
    public Optional<Author> getById(long id) {
        return Optional.ofNullable(entityManager.find(Author.class, id));
    }

    @Override
    public Optional<Author> getByName(String name) {
        TypedQuery<Author> query = entityManager.createQuery(
                "select a from Author a where a.name = :name", Author.class);
        query.setParameter("name", name);
        return query.getResultStream().findFirst();
    }

    @Override
    public List<Author> getAll() {
        return entityManager.createQuery("select a from Author a", Author.class).getResultList();
    }
}
