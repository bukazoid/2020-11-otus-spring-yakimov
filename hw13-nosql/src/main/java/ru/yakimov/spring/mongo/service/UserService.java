package ru.yakimov.spring.mongo.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import ru.yakimov.spring.mongo.domain.User;

public interface UserService extends UserDetailsService {

	long count();

	User read(String id);

	User create(User user);

	List<User> readAll();

	User update(User user);

	UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
}
