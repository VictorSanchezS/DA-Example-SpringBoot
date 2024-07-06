package com.uss.facturacion.almacen.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
	@GetMapping("/categorias")
	public String getCategorias(Model model) {
		return "categoria";
	}
	@GetMapping("/clientes")
	public String getClientes(Model model) {
		return "cliente";
	}
}
