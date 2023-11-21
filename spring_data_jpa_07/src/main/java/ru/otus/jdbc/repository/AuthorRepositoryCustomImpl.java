package ru.otus.jdbc.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.otus.jdbc.model.Author;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class AuthorRepositoryCustomImpl implements AuthorRepositoryCustom {
    @PersistenceContext
    private final EntityManager entityManager;

    private final AuthorRepository authorRepository;

    @Override
    public boolean deleteById(long id) {
        return authorRepository.findById(id).map(author -> {
            entityManager.remove(author);
            return !entityManager.contains(author);
        }).orElse(false);
    }

    @Override
    public long deleteAllWithCounter() {
        return entityManager.createQuery("delete from Author").executeUpdate();
    }
}
