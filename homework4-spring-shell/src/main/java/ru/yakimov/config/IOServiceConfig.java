package ru.yakimov.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import ru.yakimov.services.IOService;
import ru.yakimov.services.IOServiceStreamBased;

/**
 * has plan to change it in future
 */
@Configuration
public class IOServiceConfig {

	@Bean
	public IOService userIO() {
		return new IOServiceStreamBased(System.in, System.out);
	}
}
