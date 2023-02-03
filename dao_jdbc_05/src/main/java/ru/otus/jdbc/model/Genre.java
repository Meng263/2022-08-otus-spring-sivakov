package ru.otus.jdbc.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Genre {

    public Genre(String name) {
        this.name = name;
    }

    public Genre(long id) {
        this.id = id;
        this.name = "";
    }

    private long id;

    private final String name;

}
