package ru.otus.jdbc.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import ru.otus.jdbc.model.Book;
import ru.otus.jdbc.model.BookComment;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CommentRepositoryJpa implements CommentRepository {
    @PersistenceContext
    EntityManager entityManager;

    @Override
    public long count() {
        return entityManager.createQuery("select count(c) from BookComment c", Long.class).getSingleResult();
    }

    @Override
    public BookComment save(BookComment comment) {
        BookComment result;
        if (comment.getId() == 0) {
            entityManager.persist(comment);
            entityManager.flush();
            result = comment;
        } else {
            result = entityManager.merge(comment);
        }
        return result;
    }

    @Override
    public Optional<BookComment> getById(long id) {
        return Optional.ofNullable(entityManager.find(BookComment.class, id));
    }

    @Override
    public List<BookComment> getAll() {
        return entityManager.createQuery("select c from BookComment c", BookComment.class).getResultList();
    }

    @Override
    public boolean deleteById(long id) {
        return getById(id).map(comment -> {
            entityManager.remove(comment);
            return !entityManager.contains(comment);
        }).orElse(false);
    }

    @Override
    public long deleteAll() {
        return entityManager.createQuery("delete from BookComment").executeUpdate();
    }
}
