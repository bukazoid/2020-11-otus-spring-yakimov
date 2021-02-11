package ru.yakimov.spring.db.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import ru.yakimov.spring.db.domain.Author;
import ru.yakimov.spring.db.repositories.AuthorRepository;

@DisplayName("AuthorServiceImpl")
@ExtendWith(MockitoExtension.class)
public class AuthorServiceImplTest {

	@Mock
	private AuthorRepository authorDao;

	private AuthorService authorService;

	@BeforeEach
	private void init() {
		authorService = new AuthorServiceImpl(authorDao);
	}

	@DisplayName("should return 17")
	@Test
	void shouldReturnCount() {
		long expected = 17L;
		when(authorDao.count()).thenReturn(expected);

		long count = authorService.count();
		assertEquals(expected, count);
	}

	@Test
	@DisplayName("should return me")
	void shouldGetById() {
		Author expected = new Author(11L, "Me");
		when(authorDao.read(eq(null))).thenReturn(expected);// why not?

		Author author = authorService.read(null);
		assertEquals(expected, author);
	}
}
