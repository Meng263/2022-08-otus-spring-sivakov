package ru.otus.mongo.repository;

import com.mongodb.client.result.DeleteResult;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import ru.otus.mongo.model.Author;
import ru.otus.mongo.model.Book;


@Repository
@RequiredArgsConstructor
public class AuthorRepositoryCustomImpl implements AuthorRepositoryCustom {
    private final MongoTemplate mongoTemplate;

    @Override
    public boolean deleteByIdBool(String id) {
        DeleteResult deleteResult = mongoTemplate.remove(new Query(Criteria.where("id").is(id)), Author.class);
        return deleteResult.getDeletedCount() == 1;
    }

    @Override
    public long deleteAllWithCounter() {
        DeleteResult deleteResult = mongoTemplate.remove(new Query(), Author.class);
        return deleteResult.getDeletedCount();
    }
}
