package ru.yakimov.spring.db.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import ru.yakimov.spring.db.domain.Author;
import ru.yakimov.spring.db.repositories.AuthorRepository;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

	private final AuthorRepository repo;

	@Transactional(readOnly = true)
	@Override
	public long count() {
		return repo.count();
	}

	@Transactional(readOnly = true)
	@Override
	public Author read(Long id) {
		return repo.read(id);
	}

	@Transactional
	@Override
	public Author create(Author author) {
		return repo.create(author);
	}

	@Transactional(readOnly = true)
	@Override
	public List<Author> readAll() {
		return repo.readAll();
	}

}
