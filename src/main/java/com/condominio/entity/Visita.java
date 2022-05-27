package com.condominio.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name= "visita")
@Getter
@Setter
public class Visita {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idVisita;
	
	@ManyToOne
	@JoinColumn(name="idVisitante")
	private Visitante visitante;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
	private Date fechaEntrada;
	@DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
	private Date fechaSalida;
	
	private String estado;
	
	@ManyToOne
	@JoinColumn(name="idDepartamento")
	private Departamento departamento;
}