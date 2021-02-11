package ru.yakimov.spring.db.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ru.yakimov.spring.db.domain.Book;

public interface BookRepository extends JpaRepository<Book, Long> {

	@Query("SELECT COUNT(b) FROM Book b")
	long count();

	// @EntityGraph(attributePaths = "author", type = EntityGraphType.LOAD) as an
	// option
	@EntityGraph(value = "book-author", type = EntityGraphType.LOAD) // ...
	List<Book> findAll();
}
