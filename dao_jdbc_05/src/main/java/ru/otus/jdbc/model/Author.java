package ru.otus.jdbc.model;

import lombok.Data;

@Data
public class Author {
    public Author(String name) {
        this.name = name;
    }

    public Author(long id, String name) {
        this.id = id;
        this.name = name;
    }

    private long id;

    private final String name;

//    private final Set<Book> books;

}
