package ru.yakimov.services;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class WelcomeMessageService {
	private final IOService userIO;
	private final MsgService messages;

	public void sayHello() {
		// intro
		String welcome = messages.get("welcome");
		userIO.printLine("#".repeat(welcome.length()));
		userIO.printLine(welcome);
		userIO.printLine("#".repeat(welcome.length()));
		userIO.printLine();
	}
}
