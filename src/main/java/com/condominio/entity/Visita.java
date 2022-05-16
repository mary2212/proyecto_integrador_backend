package com.condominio.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

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
	
	private Date fechaEntrada;
	private Date fechaSalida;
	private String Estado;
	
	@ManyToOne
	@JoinColumn(name="idDepartamento")
	private Departamento departamento;
}