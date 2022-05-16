package com.condominio.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
@Embeddable
public class Rol_has_opcionPK implements Serializable{

	private static final long serialVersionUID = 1L;
	private int idRol;
	private int idOpcion;
}
