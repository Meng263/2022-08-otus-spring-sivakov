package ru.otus.jdbc.repository;

public interface CommentRepositoryCustom {

    boolean deleteByIdBool(String id);

    long deleteAllWithCounter();
}
