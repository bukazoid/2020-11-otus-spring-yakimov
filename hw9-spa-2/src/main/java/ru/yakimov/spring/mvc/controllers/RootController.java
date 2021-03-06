package ru.yakimov.spring.mvc.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class RootController {
	@RequestMapping(value = { "/", "/page/**" })
	public String index() {
		return "index";
	}

}
