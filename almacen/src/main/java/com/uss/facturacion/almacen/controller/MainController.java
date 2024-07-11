package com.uss.facturacion.almacen.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
	@GetMapping("/aulas")
	public String getAulas(Model model) {
		return "aula";
	}
	@GetMapping("/clientes")
	public String getClientes(Model model) {
		return "cliente";
	}
	@GetMapping("/alumnos")
	public String getAlumnos(Model model) {
		return "alumno";
	}
}
