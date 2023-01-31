package ru.otus.jdbc.model;

import lombok.Data;

@Data
public class Genre {

    public Genre(String name) {
        this.name = name;
    }

    public Genre(long id, String name) {
        this.id = id;
        this.name = name;
    }

    private long id;

    private final String name;

}
