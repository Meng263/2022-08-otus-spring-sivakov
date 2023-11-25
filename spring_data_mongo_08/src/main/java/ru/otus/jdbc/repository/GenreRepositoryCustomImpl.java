package ru.otus.jdbc.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GenreRepositoryCustomImpl implements GenreRepositoryCustom {
    private final MongoTemplate mongoTemplate;

    @Override
    public boolean deleteByIdBool(String id) {
        return false;
    }

    @Override
    public long deleteAllWithCounter() {
        return 0;
    }
}
