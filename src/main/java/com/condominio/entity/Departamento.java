package com.condominio.entity;

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
@Table(name= "departamento")
@Getter
@Setter
public class Departamento {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idDepartamento;
	private int numeroDepartamento;
	private int piso;
	private int metrosCuadrados;
	
	@ManyToOne
	@JoinColumn(name="idEdificio")
	private Edificio edificio;
	
	private String estado;
}

