package ru.yakimov.spring.db.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * short version of book, for internal usage
 */
@Data
@AllArgsConstructor
public class BookShort {
	private final Long id;

	private final String title;
	
	private final Long author;
	private final Long genre;
}
