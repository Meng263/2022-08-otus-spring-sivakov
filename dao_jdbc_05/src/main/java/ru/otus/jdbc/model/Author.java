package ru.otus.jdbc.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Author {
    public Author(String name) {
        this.name = name;
    }

    private long id;

    private final String name;
}
