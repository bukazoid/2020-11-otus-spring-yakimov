package ru.yakimov;

import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;
import org.springframework.shell.standard.ShellOption;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import ru.yakimov.domain.LoadQuestionsException;
import ru.yakimov.services.Quiz;

@ShellComponent
@RequiredArgsConstructor
public class AppShell {
	@NonNull
	private Quiz quiz;

	private String userName;

	@ShellMethod(value = "Login command", key = { "l", "login" })
	public String login(@ShellOption(defaultValue = "anonymous") String userName) {
		this.userName = userName;
		return String.format("Welcome, %s!", userName);
	}

	@ShellMethod(value = "Proceed random question", key = { "r", "rnd", "random" })
	@ShellMethodAvailability(value = "isLoggedIn")
	public void publishEvent() throws LoadQuestionsException {
		quiz.proceedRandomQuestion(userName);
	}

	@ShellMethod(value = "Proceed queez", key = { "q", "quiz" })
	@ShellMethodAvailability(value = "isLoggedIn")
	public void proceedQuiz() throws LoadQuestionsException {
		quiz.proceedQuiz(userName);
	}

	@SuppressWarnings("unused")
	private Availability isLoggedIn() {
		return userName == null ? Availability.unavailable("Login first") : Availability.available();
	}
}
