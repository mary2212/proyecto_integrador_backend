package com.condominio.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name= "incidente")
@Getter
@Setter
public class Incidente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idIncidente;
	private String descripcion;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	private Date fechaIncidente;
	
	@ManyToOne
	@JoinColumn(name="idDepartamento")
	private Departamento departamento;
	
	@ManyToOne
	@JoinColumn(name="idEdificio")
	private Edificio edificio;
	
	private String estado;
	
	private String solucion;
}
