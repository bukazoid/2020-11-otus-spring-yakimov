package ru.yakimov.spring.mongo.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ru.yakimov.spring.mongo.domain.User;
import ru.yakimov.spring.mongo.repositories.UserRepository;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

	private final UserRepository dao;

	@Transactional(readOnly = true)
	@Override
	public long count() {
		return dao.count();
	}

	@Transactional(readOnly = true)
	@Override
	public User read(String id) {
		return dao.findById(id).get();
	}

	@Transactional
	@Override
	public User create(User author) {
		return dao.save(author);
	}

	@Transactional(readOnly = true)
	@Override
	public List<User> readAll() {
		return dao.findAll();
	}

	@Override
	public User update(User genre) {
		return dao.save(genre);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = dao.findByLogin(username);

		if (user == null) {
			throw new UsernameNotFoundException(username);
		}

		return new org.springframework.security.core.userdetails.User(user.getLogin(), user.getPassword(),
				Arrays.asList(new SimpleGrantedAuthority(user.getRole())));
	}
}
