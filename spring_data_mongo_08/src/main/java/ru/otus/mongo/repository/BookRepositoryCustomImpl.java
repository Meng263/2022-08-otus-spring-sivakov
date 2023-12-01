package ru.otus.mongo.repository;

import com.mongodb.client.result.DeleteResult;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import ru.otus.mongo.model.Book;

@RequiredArgsConstructor
public class BookRepositoryCustomImpl implements BookRepositoryCustom {
    private final MongoTemplate mongoTemplate;

    @Override
    public boolean deleteByIdBool(String id) {
        DeleteResult deleteResult = mongoTemplate.remove(new Query(Criteria.where("id").is(id)), Book.class);
        return deleteResult.getDeletedCount() == 1;
    }

    @Override
    public long deleteAllWithCounter() {
        DeleteResult deleteResult = mongoTemplate.remove(new Query(), Book.class);
        return deleteResult.getDeletedCount();
    }
}
