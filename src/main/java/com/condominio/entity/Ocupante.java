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
@Table(name= "ocupante")
@Getter
@Setter
public class Ocupante {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idOcupante;
	private String nombreOcupante;
	private String apellidoOcupante;
	private String dniOcupante;
	private String sexo;
	
	@ManyToOne
	@JoinColumn(name="idDepartamento")
	private Departamento departamento;
}
