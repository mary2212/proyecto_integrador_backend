package com.condominio.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name= "visitante")
@Getter
@Setter
public class Visitante {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idVisitante;
	private String nombreVisitante;
	private String apellidoVisitante;
	private String dniVisitante;
	private Date fechaRegistro;

}