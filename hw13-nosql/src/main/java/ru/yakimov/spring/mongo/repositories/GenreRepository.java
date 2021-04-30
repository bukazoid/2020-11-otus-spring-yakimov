package ru.yakimov.spring.mongo.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import ru.yakimov.spring.mongo.domain.Genre;

public interface GenreRepository extends MongoRepository<Genre, String> {
//
//	@Query("SELECT COUNT(g) FROM Genre g")
//	long count();
	Genre findByName(String name);
}
