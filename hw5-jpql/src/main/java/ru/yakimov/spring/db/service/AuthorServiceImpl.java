package ru.yakimov.spring.db.service;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import ru.yakimov.spring.db.dao.AuthorDao;
import ru.yakimov.spring.db.domain.Author;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

	private final AuthorDao dao;

	@Override
	public long count() {
		return dao.count();
	}

	@Override
	public Author read(Long id) {
		return dao.read(id);
	}

	@Override
	public Author create(Author author) {
		return dao.create(author);
	}

	@Override
	public List<Author> readAll() {
		return dao.readAll();
	}

}
