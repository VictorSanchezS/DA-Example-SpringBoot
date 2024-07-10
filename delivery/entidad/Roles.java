package com.uss.facturacion.delivery.entidad;

import java.util.Date;

import org.springframework.data.annotation.CreatedDate;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "roles")
public class Roles {
	@Id
	@GeneratedValue (strategy= GenerationType.IDENTITY)
	private int id;
	@Column(unique = true,nullable = false, length = 50)
	private String nombre;
	private boolean activo;
	
	@Column(name="created_at",nullable = false, updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@CreatedDate
	private Date createdAt;
	
	@Column(name="update_at",nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@CreatedDate
	private Date updateAt;
}
