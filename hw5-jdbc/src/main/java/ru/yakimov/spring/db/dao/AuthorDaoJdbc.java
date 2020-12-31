package ru.yakimov.spring.db.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import ru.yakimov.spring.db.domain.Author;

@Repository
public class AuthorDaoJdbc implements AuthorDao {
	private final NamedParameterJdbcOperations jdbc;

	public AuthorDaoJdbc(NamedParameterJdbcOperations jdbc) {
		this.jdbc = jdbc;
	}

	@Override
	public long count() {
		return jdbc.getJdbcOperations().queryForObject("SELECT count(*) FROM authors", Long.class);
	}

	@Override
	public Author read(Long id) {
		return jdbc.queryForObject("SELECT author_id, name FROM authors WHERE author_id = :id", Map.of("id", id),
				new AuthorMapper());
	}

	@Override
	public Author create(Author author) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		SqlParameterSource params = new MapSqlParameterSource(Map.of("name", author.getName()));
		jdbc.update("INSERT INTO authors (name) VALUES (:name)", params, keyHolder);
		long id = keyHolder.getKey().longValue();
		return new Author(id, author.getName());
	}

	@Override
	public List<Author> readAll() {
		return jdbc.query("SELECT author_id, name FROM authors", new AuthorMapper());
	}

	private static class AuthorMapper implements RowMapper<Author> {
		@Override
		public Author mapRow(ResultSet resultSet, int i) throws SQLException {

			long id = resultSet.getLong("author_id");
			String name = resultSet.getString("name");

			return new Author(id, name);
		}
	}
}