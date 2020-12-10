package ru.yakimov.services;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class WelcomeMessageService {
	private final UserIO userIO;
	private final QMessages messages;

	public void sayHello() {
		// intro
		userIO.printLine("#".repeat(20));
		userIO.printLine(messages.get("welcome"));
		userIO.printLine("#".repeat(20));
		userIO.printLine();
	}
}
