package com.asistencia.empleados;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class EmpleadosApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmpleadosApplication.class, args);
	}

}
