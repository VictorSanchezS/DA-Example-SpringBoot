package com.asistencia.empleados.entity;

import java.util.Date;
import java.util.Set;
import jakarta.persistence.*;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "empleados")
@EntityListeners(AuditingEntityListener.class)
public class Empleado {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(nullable = false, length = 100)
	private String nombre;

	@Column(unique = true, nullable = false, length = 15)
	private String documento;

	@Column(nullable = true, length = 15)
	private String telefono;

	@Column(nullable = true, length = 100)
	private String direccion;

	@Column(nullable = true, length = 50)
	private String email;

	@ManyToOne
	@JoinColumn(name = "area_id", nullable = false)
	private Area area;

	@Column(name = "created_at", nullable = false, updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@CreatedDate
	private Date createdAt;

	@Column(name = "updated_at", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@LastModifiedDate
	private Date updatedAt;

	@OneToMany(mappedBy = "empleado", cascade = CascadeType.ALL)
	private Set<Asistencia> asistencias;

	@OneToMany(mappedBy = "empleado", cascade = CascadeType.ALL)
	private Set<Permiso> permisos;
}
