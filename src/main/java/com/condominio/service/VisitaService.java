package com.condominio.service;

import java.util.List;

import com.condominio.entity.Visita;

public interface VisitaService {

	public abstract List<Visita> listaVisita();
	public abstract Visita insertaActualizaVisita(Visita obj);
}
