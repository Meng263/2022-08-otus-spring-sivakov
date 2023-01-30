package ru.otus.jdbc.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import ru.otus.jdbc.model.Author;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class AuthorDaoJdbc implements AuthorDao {
    private final NamedParameterJdbcOperations operations;

    @Override
    public long count() {
        return operations.getJdbcOperations().queryForObject("select count (*) from authors;", Long.class);
    }

    @Override
    public Author insert(Author author) {
        var keyHolder = new GeneratedKeyHolder();
        operations.update("insert into authors (name) values :name",
                new MapSqlParameterSource(Map.of("name", author.getName())),
                keyHolder);
        author.setId(keyHolder.getKey().longValue());
        return author;
    }

    @Override
    public Author update(Author author) {
        operations.update("update authors set name = :name",
                Map.of("name", author.getName()));
        return author;
    }

    @Override
    public boolean deleteById(long id) {
        return operations.update("delete from authors where id = :id", Map.of("id", id)) == 1;
    }

    @Override
    public Author getById(long id) {
        return operations.queryForObject("select id, name from authors where id = :id",
                Map.of("id", id),
                new AuthorMapper());
    }

    @Override
    public List<Author> getAll() {
        return operations.query("select id, name from authors", new AuthorMapper());
    }

    private static class AuthorMapper implements RowMapper<Author> {

        @Override
        public Author mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Author(rs.getLong("id"), rs.getString("name"));
        }
    }
}
