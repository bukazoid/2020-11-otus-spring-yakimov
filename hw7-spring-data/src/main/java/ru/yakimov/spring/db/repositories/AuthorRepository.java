package ru.yakimov.spring.db.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ru.yakimov.spring.db.domain.Author;

public interface AuthorRepository extends JpaRepository<Author, Long> {

	@Query("SELECT COUNT(a) FROM Author a")
	long count();

}
