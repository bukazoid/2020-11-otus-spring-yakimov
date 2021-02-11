package ru.yakimov.spring.db.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import ru.yakimov.spring.db.domain.BookComment;
import ru.yakimov.spring.db.repositories.CommentRepository;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

	private final CommentRepository dao;

	@Transactional(readOnly = true)
	@Override
	public long count() {
		return dao.count();
	}

	@Transactional(readOnly = true)
	@Override
	public BookComment read(Long id) {
		return dao.read(id);
	}

	@Transactional
	@Override
	public BookComment create(BookComment book) {
		return dao.create(book);
	}

	@Transactional(readOnly = true)
	@Override
	public List<BookComment> readAll() {
		return dao.readAll();
	}

	@Transactional
	@Override
	public void delete(Long id) {
		dao.delete(id);
	}

	@Transactional
	@Override
	public void update(BookComment comment) {
		dao.update(comment);
	}

}
