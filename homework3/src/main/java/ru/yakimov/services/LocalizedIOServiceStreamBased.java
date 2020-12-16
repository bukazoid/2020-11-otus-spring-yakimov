package ru.yakimov.services;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

/**
 * we may extends IOServiceStreamBased but i am not sure if it any good
 * 
 * @author pavel
 */
@Service
@AllArgsConstructor
public class LocalizedIOServiceStreamBased implements LocalizedIOService {
	private final IOService ioService;
	private final MsgService msgService;

	@Override
	public String readInput() {
		return ioService.readInput();
	}

	@Override
	public void printText(String text) {
		ioService.printText(text);
	}

	@Override
	public void printLine() {
		ioService.printLine();
	}

	@Override
	public void printLine(String format, Object... params) {
		ioService.printLine(format, params);
	}

	@Override
	public void printLocalizedText(String text) {
		ioService.printText(msgService.get(text) + " ");// we usually need space after text
	}

	@Override
	public void printLocalizedLine(String format, Object... params) {
		ioService.printLine(msgService.get(format, params));
	}

}
