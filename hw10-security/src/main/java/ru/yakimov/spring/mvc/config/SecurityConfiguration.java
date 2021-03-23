package ru.yakimov.spring.mvc.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ru.yakimov.spring.mvc.service.BookServiceImpl;
import ru.yakimov.spring.mvc.service.UserService;

@Slf4j
@RequiredArgsConstructor
@EnableWebSecurity
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	private final UserService userService;

	@Override
	public void configure(WebSecurity web) {
		web.ignoring().antMatchers("/");
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
		//works nice without loginPage("/page/login")
		http.csrf().disable().authorizeRequests().antMatchers("/").permitAll().and().authorizeRequests()
				.antMatchers("/built/**").permitAll().and().authorizeRequests().antMatchers("/page/login").permitAll()
				.and().formLogin().loginProcessingUrl("/perform_login").loginPage("/page/login").defaultSuccessUrl("/page/books", true).and()				
				.authorizeRequests().anyRequest().authenticated();
	
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new PasswordEncoder() {
			@Override
			public String encode(CharSequence charSequence) {
				log.info("encode: {}", charSequence);
				return charSequence.toString();
			}

			@Override
			public boolean matches(CharSequence charSequence, String s) {
				log.info("matches {} = {}", charSequence, s);
				return charSequence.toString().equals(s);
			}
		};
	}

	@Autowired
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userService).passwordEncoder(passwordEncoder());

	}
}
