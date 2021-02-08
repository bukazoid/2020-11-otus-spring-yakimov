package ru.yakimov.spring.db.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ru.yakimov.spring.db.domain.Genre;

public interface GenreRepository extends JpaRepository<Genre, Long> {

	@Query("SELECT COUNT(g) FROM Genre g")
	long count();

}
