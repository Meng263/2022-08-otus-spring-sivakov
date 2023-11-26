package ru.otus.mongo.repository;

public interface CommentRepositoryCustom {

    boolean deleteByIdBool(String id);

    long deleteAllWithCounter();
}
