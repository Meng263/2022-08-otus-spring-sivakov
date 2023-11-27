package ru.otus.jdbc.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import ru.otus.jdbc.model.Book;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class BookRepositoryJpa implements BookRepository {
    @PersistenceContext
    private final EntityManager entityManager;

    @Override
    public long count() {
        return entityManager.createQuery("select count(*) from Book", Long.class)
                .getSingleResult();
    }

    @Override
    public Book save(Book book) {
        Book result;
        if (book.getId() == 0) {
            entityManager.persist(book);
            entityManager.flush();
            result = book;
        } else {
            result = entityManager.merge(book);
        }
        return result;
    }

    @Override
    public boolean deleteById(long id) {
        return getById(id).map(book -> {
            entityManager.remove(book);
            return !entityManager.contains(book);
        }).orElse(false);
    }

    @Override
    public long deleteAll() {
        return entityManager.createQuery("delete from Book").executeUpdate();
    }

    @Override
    public Optional<Book> getById(long id) {
        return Optional.ofNullable(entityManager.find(Book.class, id));
    }

    @Override
    public Optional<Book> getByName(String name) {
        TypedQuery<Book> query = entityManager.createQuery(
                "select b from Book b where b.name = :name", Book.class);
        query.setParameter("name", name);
        return query.getResultStream().findFirst();
    }

    @Override
    public List<Book> getAll() {
        return entityManager.createQuery("select b from Book b", Book.class).getResultList();
    }
}
