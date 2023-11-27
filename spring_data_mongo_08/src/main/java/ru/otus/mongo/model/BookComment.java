package ru.otus.mongo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("comments")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookComment {
    @Id
    private String id;

    private String text;

    @DBRef
    private Book book;

    public String toShortString() {
        return "Comment(id=" + getId() + ", commentText=" + getText() + ")";
    }
}
