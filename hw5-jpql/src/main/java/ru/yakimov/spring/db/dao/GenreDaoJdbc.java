package ru.yakimov.spring.db.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import ru.yakimov.spring.db.domain.Genre;

@Repository
public class GenreDaoJdbc implements GenreDao {
	private final NamedParameterJdbcOperations jdbc;

	public GenreDaoJdbc(NamedParameterJdbcOperations jdbc) {
		this.jdbc = jdbc;
	}

	@Override
	public long count() {
		return jdbc.getJdbcOperations().queryForObject("SELECT count(*) FROM genres", Long.class);
	}

	@Override
	public Genre read(Long id) {
		return jdbc.queryForObject("SELECT genre_id, name FROM genres WHERE genre_id = :id", Map.of("id", id),
				new GenreMapper());
	}

	@Override
	public Genre create(Genre author) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		MapSqlParameterSource params = new MapSqlParameterSource(Map.of("name", author.getName()));
		jdbc.update("INSERT INTO genres (name) VALUES (:name)", params, keyHolder);
		long id = keyHolder.getKey().longValue();
		return new Genre(id, author.getName());
	}

	@Override
	public List<Genre> readAll() {
		return jdbc.query("SELECT genre_id, name FROM genres", new GenreMapper());
	}

	private static class GenreMapper implements RowMapper<Genre> {
		@Override
		public Genre mapRow(ResultSet resultSet, int i) throws SQLException {

			long id = resultSet.getLong("genre_id");
			String name = resultSet.getString("name");

			return new Genre(id, name);
		}
	}
}