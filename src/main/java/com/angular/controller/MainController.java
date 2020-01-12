package com.angular.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.reactive.result.view.Rendering;

@Controller
public class MainController {

	@GetMapping("/")
	public Rendering index() {
		return Rendering.view("index").build();
	}
	
	@GetMapping("/blog")
	public Rendering blog() {
		return Rendering.view("index").build();
	}
}
