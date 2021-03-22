package ru.yakimov.spring.mvc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ru.yakimov.spring.mvc.domain.Genre;

public interface UserRepository extends JpaRepository<Genre, Long> {

	@Query("SELECT COUNT(g) FROM Genre g")
	long count();

}
