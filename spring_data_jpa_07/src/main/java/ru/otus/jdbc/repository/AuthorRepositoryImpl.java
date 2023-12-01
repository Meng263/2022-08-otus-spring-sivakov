package ru.otus.jdbc.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@RequiredArgsConstructor
public class AuthorRepositoryImpl implements AuthorRepositoryCustom {
    @PersistenceContext
    private final EntityManager entityManager;

    @Override
    public boolean deleteById(long id) {
        Query query = entityManager.createQuery("delete from Author where id = :id");
        query.setParameter("id", id);
        return query.executeUpdate() == 1;
    }

    @Override
    public long deleteAllWithCounter() {
        return entityManager.createQuery("delete from Author").executeUpdate();
    }
}
