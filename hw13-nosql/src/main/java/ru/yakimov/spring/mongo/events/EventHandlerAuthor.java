package ru.yakimov.spring.mongo.events;

import org.bson.Document;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeDeleteEvent;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ru.yakimov.spring.mongo.domain.Author;
import ru.yakimov.spring.mongo.repositories.BookRepository;

@Slf4j
@Component
@RequiredArgsConstructor
public class EventHandlerAuthor extends AbstractMongoEventListener<Author> {

	private final BookRepository bookRepository;

	@Override
	public void onBeforeDelete(BeforeDeleteEvent<Author> event) {
		Document document = event.getSource();
		log.info("onBeforeDelete: {}", document);
		String authorId = document.get("_id").toString();
		if (bookRepository.existsByAuthorId(authorId)) {
			throw new RuntimeException("Author has book");
		}

		super.onBeforeDelete(event);
	}
}
