package ru.yakimov.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import ru.yakimov.services.UserIO;
import ru.yakimov.services.UserIOConsole;

/**
 * has plan to change it in future
 */
@Configuration
public class UserIOConfig {

	@Bean
	public UserIO getUserIO() {
		return new UserIOConsole();
	}
}
