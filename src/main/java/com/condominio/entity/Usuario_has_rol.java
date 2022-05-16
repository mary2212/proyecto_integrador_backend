package com.condominio.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name= "usuario_has_rol")
@Getter
@Setter
public class Usuario_has_rol {

	@EmbeddedId
	private Usuario_has_rolPK usuario_has_RolPK;
	
	@ManyToOne
	@JoinColumn(name="idUsuario", nullable = false, insertable = false, updatable = false)
	private Usuario usuario;
	
	@ManyToOne
	@JoinColumn(name="idRol", nullable = false, insertable = false, updatable = false)
	private Rol rol;
}
