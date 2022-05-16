package com.condominio.service;

import java.util.List;

import com.condominio.entity.Rol;

public interface RolService {

	public abstract List<Rol> listaRol();
	public abstract Rol insertaActualizaRol(Rol obj);
}
