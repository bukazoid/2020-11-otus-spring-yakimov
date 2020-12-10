package ru.yakimov;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import ru.yakimov.services.UserIO;
import ru.yakimov.services.UserIOConsole;

@DisplayName("UserIOConsole")
public class UserIOConsoleTest {
	ByteArrayInputStream bis;

	ByteArrayOutputStream bos;

	final String testIn = "test string\n\r";
	final String testOut = "Hello!";

	UserIO userIO;

	@BeforeEach
	public void setUp() throws Exception {
		bis = new ByteArrayInputStream(testIn.getBytes(StandardCharsets.UTF_8));

		bos = new ByteArrayOutputStream();

		userIO = new UserIOConsole(bis, new PrintStream(bos));
	}

	@Test
	public void readTest() {
		String readTest = userIO.readInput();
		assertEquals(testIn.trim(), readTest);
	}

	@Test
	public void printTest() {
		userIO.printLine(testOut);

		assertEquals(testOut, new String(bos.toByteArray()).trim());
	}

}
