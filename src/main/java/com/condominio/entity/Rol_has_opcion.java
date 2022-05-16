package com.condominio.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name= "rol_has_opcion")
@Getter
@Setter
public class Rol_has_opcion {

	@EmbeddedId
	private Rol_has_opcionPK rol_Has_OpcionPK;
	
	@ManyToOne
	@JoinColumn(name="idRol", nullable = false, insertable = false, updatable = false)
	private Rol rol;
	
	@ManyToOne
	@JoinColumn(name="idOpcion", nullable = false, insertable = false, updatable = false)
	private Opcion opcion;
}