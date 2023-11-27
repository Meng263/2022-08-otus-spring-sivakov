package ru.otus.mongo.repository;

public interface AuthorRepositoryCustom {
    boolean deleteByIdBool(String id);

    long deleteAllWithCounter();
}
