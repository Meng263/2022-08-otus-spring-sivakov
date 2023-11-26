package ru.otus.mongo.repository;

public interface GenreRepositoryCustom {

    boolean deleteByIdBool(String id);

    long deleteAllWithCounter();
}
