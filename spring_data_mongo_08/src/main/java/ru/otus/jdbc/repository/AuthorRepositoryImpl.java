package ru.otus.jdbc.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;


@Repository
@RequiredArgsConstructor
public class AuthorRepositoryImpl implements AuthorRepositoryCustom {
    private final MongoTemplate mongoTemplate;

    @Override
    public boolean deleteByIdBool(String id) {
//        mongoTemplate
        return false;
    }

    @Override
    public long deleteAllWithCounter() {
        return 0;
    }
}
