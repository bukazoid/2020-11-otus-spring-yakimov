package ru.yakimov.services;

import org.springframework.stereotype.Service;

@Service
public class WelcomeMessage {
	private final UserIO userIO;

	public WelcomeMessage(UserIO userIO) {
		this.userIO = userIO;
	}

	public void sayHello() {
		// intro
		userIO.printLine("#".repeat(20));
		userIO.printLine("Welcome to our quiz!");
		userIO.printLine("#".repeat(20));
		userIO.printLine();
	}
}
