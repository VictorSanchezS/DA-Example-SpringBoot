package com.asistencia.empleados.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
	@GetMapping("/areas")
	public String getAreas(Model model) {
		return "area";
	}
	@GetMapping("/indexs")
	public String getIndex(Model model) {
		return "indexs";
	}

}
