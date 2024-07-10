package com.uss.facturacion.delivery.entidad;

import java.util.Date;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

//Anotaciones - JPA - API
@Entity
@Table(name = "categorias")
@EntityListeners(AuditingEntityListener.class)
public class Categoria {
	@Id
	@GeneratedValue (strategy= GenerationType.IDENTITY)
	private int id;
	@Column(unique = true,nullable = false, length = 100)
	private String nombre;
	@Column(nullable = true, length = 255)
	private String descripción;
	
	@Column(name="created_at",nullable = false, updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@CreatedDate
	private Date createdAt;
	
	@Column(name="update_at",nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@CreatedDate
	private Date updateAt;
	
}


