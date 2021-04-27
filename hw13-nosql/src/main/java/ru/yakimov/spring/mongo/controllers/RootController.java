package ru.yakimov.spring.mongo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RootController {
	@RequestMapping(value = { "/", "/page/**" })
	public String index() {
		return "index";
	}

}
