package com.condominio.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name= "propietario")
public class Propietario {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name="idPropietario")
	private int idPropietario;
	
	private String nombrePropietario;
	private String apellidoPropietario;
	private String telefonoPropietario;
	private String dniPropietario;
	
	@Temporal(TemporalType.DATE)
	private Date fechaNacPropietario;
	
	@Temporal(TemporalType.DATE)
	private Date fechaRegistroPropietario;
	
	@ManyToOne
	@JoinColumn(name="idUsuario")
	private Usuario usuario;
	
	@ManyToOne
	@JoinColumn(name="idDepartamento")
	private Departamento departamento;
	

}
