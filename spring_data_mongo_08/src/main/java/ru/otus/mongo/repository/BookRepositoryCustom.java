package ru.otus.mongo.repository;

public interface BookRepositoryCustom {

    boolean deleteByIdBool(String id);

    long deleteAllWithCounter();
}
