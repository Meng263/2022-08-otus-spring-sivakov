package ru.otus.jdbc.repository;

public interface GenreRepositoryCustom {

    boolean deleteByIdBool(String id);

    long deleteAllWithCounter();
}
