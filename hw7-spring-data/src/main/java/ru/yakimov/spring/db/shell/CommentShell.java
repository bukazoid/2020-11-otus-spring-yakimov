package ru.yakimov.spring.db.shell;

import java.util.List;

import org.springframework.shell.standard.ShellCommandGroup;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import lombok.RequiredArgsConstructor;
import ru.yakimov.spring.db.domain.Book;
import ru.yakimov.spring.db.domain.BookComment;
import ru.yakimov.spring.db.service.BookService;
import ru.yakimov.spring.db.service.CommentService;
import ru.yakimov.spring.db.service.ObjectToStringTransformer;

@ShellCommandGroup("comment")
@ShellComponent
@RequiredArgsConstructor
public class CommentShell {
	private final BookService bookService;
	private final CommentService service;
	private final ObjectToStringTransformer transfromer;

	@ShellMethod(value = "List of coments", key = { "cl", "clist", "comments", "commentList" })
	public String commentList(@ShellOption long bookId) {
		List<BookComment> authors = service.readByBook(bookId);
		return transfromer.commentsToLine(authors);
	}

	@ShellMethod(value = "Add Comment", key = { "ca", "cadd", "commentAdd" })
	public String authorAdd(@ShellOption long bookId, @ShellOption String text) {
		Book book = bookService.read(bookId);
		if (book == null) {
			return "Book is not found by Id = " + bookId;
		}
		BookComment result = service.create(new BookComment(null, book, text));
		return "created comment: \n" + transfromer.toLine(result);
	}

	@ShellMethod(value = "Read Comment", key = { "cr", "cread", "comment", "commentRead" })
	public String commentRead(@ShellOption Long id) {
		BookComment result = service.read(id);
		return "read comment: \n" + transfromer.toLine(result);
	}

	@ShellMethod(value = "Delete Comment", key = { "cd", "cdel", "cdelete", "commentDelete" })
	public String commentRemove(@ShellOption Long id) {
		service.delete(id);
		return "deleted comment: \n" + id;
	}

	@ShellMethod(value = "Update Comment", key = { "cu", "cupdate", "commentUpdate" })
	public String commentRemove(@ShellOption Long id, @ShellOption String newText) {
		BookComment comment = service.read(id);
		if (comment == null) {
			return "comment is not found by Id: " + id;
		}

		comment.setText(newText);
		service.update(comment);

		return "updated comment: \n" + transfromer.toLine(comment);
	}

}
