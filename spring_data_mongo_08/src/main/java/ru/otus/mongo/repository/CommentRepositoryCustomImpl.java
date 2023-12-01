package ru.otus.mongo.repository;

import com.mongodb.client.result.DeleteResult;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import ru.otus.mongo.model.Book;
import ru.otus.mongo.model.BookComment;

@RequiredArgsConstructor
public class CommentRepositoryCustomImpl implements CommentRepositoryCustom {
    private final MongoTemplate mongoTemplate;

    @Override
    public boolean deleteByIdBool(String id) {
        DeleteResult deleteResult = mongoTemplate.remove(new Query(Criteria.where("id").is(id)), BookComment.class);
        return deleteResult.getDeletedCount() == 1;
    }

    @Override
    public long deleteAllWithCounter() {
        DeleteResult deleteResult = mongoTemplate.remove(new Query(), BookComment.class);
        return deleteResult.getDeletedCount();
    }
}
