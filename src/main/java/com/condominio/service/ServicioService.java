package com.condominio.service;

import java.util.List;

import com.condominio.entity.Servicio;

public interface ServicioService {

	public abstract List<Servicio> listaServicio();
	public abstract Servicio insertaActualizaServicio(Servicio obj);
}
