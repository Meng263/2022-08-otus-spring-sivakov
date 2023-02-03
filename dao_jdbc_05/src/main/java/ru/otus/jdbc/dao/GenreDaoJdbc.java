package ru.otus.jdbc.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import ru.otus.jdbc.model.Genre;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class GenreDaoJdbc implements GenreDao {
    private final NamedParameterJdbcOperations operations;

    @Override
    public long count() {
        Long count = operations.getJdbcOperations().queryForObject("select count (*) from genres;", Long.class);
        return count == null ? 0 : count;
    }

    @Override
    public Genre insert(Genre genre) {
        var keyHolder = new GeneratedKeyHolder();
        operations.update("insert into genres (name) values :name",
                new MapSqlParameterSource(Map.of("name", genre.getName())),
                keyHolder);
        genre.setId(keyHolder.getKey().longValue());
        return genre;
    }

    @Override
    public Genre update(Genre genre) {
        operations.update("update genres set name = :name",
                Map.of("name", genre.getName()));
        return genre;
    }

    @Override
    public boolean deleteById(long id) {
        return operations.update("delete from genres where id = :id", Map.of("id", id)) == 1;
    }

    @Override
    public Optional<Genre> getById(long id) {
        return operations.query("select id, name from genres where id = :id",
                Map.of("id", id),
                new GenreMapper()).stream().findFirst();
    }

    @Override
    public List<Genre> getAll() {
        return operations.query("select id, name from genres", new GenreMapper());
    }

    private static class GenreMapper implements RowMapper<Genre> {

        @Override
        public Genre mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Genre(rs.getLong("id"), rs.getString("name"));
        }
    }
}
