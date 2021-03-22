package ru.yakimov.spring.mvc.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import ru.yakimov.spring.mvc.domain.Author;
import ru.yakimov.spring.mvc.repositories.AuthorRepository;
import ru.yakimov.spring.mvc.service.AuthorService;
import ru.yakimov.spring.mvc.service.AuthorServiceImpl;

@DisplayName("AuthorServiceImpl")
@ExtendWith(MockitoExtension.class)
public class AuthorServiceImplTest {

	@Mock
	private AuthorRepository authorRepo;

	private AuthorService authorService;

	@BeforeEach
	private void init() {
		authorService = new AuthorServiceImpl(authorRepo);
	}

	@DisplayName("should return 17")
	@Test
	void shouldReturnCount() {
		long expected = 17L;
		when(authorRepo.count()).thenReturn(expected);

		long count = authorService.count();
		assertEquals(expected, count);
	}

	@Test
	@DisplayName("should return me")
	void shouldGetById() {
		Author expected = new Author(11L, "Me");
		when(authorRepo.findById(eq(11L))).thenReturn(Optional.of(expected));

		Author author = authorService.read(11L);
		assertEquals(expected, author);
	}
}
