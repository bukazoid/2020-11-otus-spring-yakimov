package ru.yakimov.spring.db.service;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import ru.yakimov.spring.db.dao.GenreDao;
import ru.yakimov.spring.db.domain.Genre;

@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {

	private final GenreDao dao;

	@Override
	public long count() {
		return dao.count();
	}

	@Override
	public Genre read(Long id) {
		return dao.read(id);
	}

	@Override
	public Genre create(Genre author) {
		return dao.create(author);
	}

	@Override
	public List<Genre> readAll() {
		return dao.readAll();
	}

}
