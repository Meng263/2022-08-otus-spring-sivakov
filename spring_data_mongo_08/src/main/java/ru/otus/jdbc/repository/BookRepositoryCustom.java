package ru.otus.jdbc.repository;

public interface BookRepositoryCustom {

    boolean deleteByIdBool(String id);

    long deleteAllWithCounter();
}
