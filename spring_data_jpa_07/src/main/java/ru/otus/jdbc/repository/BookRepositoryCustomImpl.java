package ru.otus.jdbc.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Repository
@RequiredArgsConstructor
public class BookRepositoryCustomImpl implements BookRepositoryCustom {
    @PersistenceContext
    private final EntityManager entityManager;

    @Override
    public boolean deleteById(long id) {
        Query query = entityManager.createQuery("delete from Book where id = :id");
        query.setParameter("id", id);
        return query.executeUpdate() == 1;
    }

    @Override
    public long deleteAllWithCounter() {
        return entityManager.createQuery("delete from Book").executeUpdate();
    }
}
