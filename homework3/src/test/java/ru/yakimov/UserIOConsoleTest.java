package ru.yakimov;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import ru.yakimov.services.IOService;
import ru.yakimov.services.IOServiceStreamBased;

@DisplayName("UserIOConsole")
public class UserIOConsoleTest {
	private ByteArrayInputStream bis;

	private ByteArrayOutputStream bos;

	private final String testIn = "test string\n\r";
	private final String testOut = "Hello!";

	private IOService userIO;

	@BeforeEach
	public void setUp() throws Exception {
		bis = new ByteArrayInputStream(testIn.getBytes(StandardCharsets.UTF_8));

		bos = new ByteArrayOutputStream();

		userIO = new IOServiceStreamBased(bis, new PrintStream(bos));
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
