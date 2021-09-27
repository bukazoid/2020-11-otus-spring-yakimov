package ru.yakimov.spring.mvc.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import ru.yakimov.spring.mvc.domain.User;

public interface UserService extends UserDetailsService {

	long count();

	User read(Long id);

	User create(User user);

	List<User> readAll();

	User update(User user);

	UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
}
