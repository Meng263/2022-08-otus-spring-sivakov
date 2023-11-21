package ru.otus.jdbc.repository;

public interface GenreRepositoryCustom {

    boolean deleteById(long id);

    long deleteAllWithCounter();
}
