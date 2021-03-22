package ru.yakimov.spring.mvc.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import ru.yakimov.spring.mvc.domain.Genre;
import ru.yakimov.spring.mvc.repositories.GenreRepository;

@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {

	private final GenreRepository dao;

	@Transactional(readOnly = true)
	@Override
	public long count() {
		return dao.count();
	}

	@Transactional(readOnly = true)
	@Override
	public Genre read(Long id) {
		return dao.findById(id).get();
	}

	@Transactional
	@Override
	public Genre create(Genre author) {
		return dao.save(author);
	}

	@Transactional(readOnly = true)
	@Override
	public List<Genre> readAll() {
		return dao.findAll();
	}
	
	@Override
	public Genre update(Genre genre) {
		return dao.save(genre);
	}
}
