package ru.yakimov.spring.mvc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ru.yakimov.spring.mvc.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {

	@Query("SELECT COUNT(u) FROM User u")
	long count();

	User findByLogin(String login);
}
