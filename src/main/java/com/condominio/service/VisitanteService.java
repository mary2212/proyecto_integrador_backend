package com.condominio.service;

import java.util.List;

import com.condominio.entity.Visitante;

public interface VisitanteService {

	public abstract List<Visitante> listaVisitante();
	public abstract Visitante insertaActualizaVisitante(Visitante obj);
}
