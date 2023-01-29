package ru.otus.jdbc.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Set;

@Data
@RequiredArgsConstructor
public class Author {
    private final long id;

    private final String name;

//    private final Set<Book> books;

}
