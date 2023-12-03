package ru.otus.jdbc.repository;

public interface CommentRepositoryCustom {

    boolean deleteById(long id);

    long deleteAllWithCounter();
}
