package com.uss.facturacion.delivery.entidad;

import java.util.Date;

import org.springframework.data.annotation.CreatedDate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "usuarios")
public class Usuario {
	@Id
	@GeneratedValue (strategy= GenerationType.IDENTITY)
	private int id;
	@Column(unique = true,nullable = false, length = 50)
	private String nombre;
	@Column(unique = true,nullable = false, length = 100)
	private String email;
	@Column(unique = true,nullable = false, length = 64)
	private String password;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="rol_id")
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	private Roles roles;
	
	@Column(name="created_at",nullable = false, updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@CreatedDate
	private Date createdAt;
	
	@Column(name="update_at",nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@CreatedDate
	private Date updateAt;
}
