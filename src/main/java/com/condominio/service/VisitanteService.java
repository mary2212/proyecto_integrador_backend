package com.condominio.service;

import java.util.List;
import java.util.Optional;

import com.condominio.entity.Visitante;

public interface VisitanteService {

	public abstract List<Visitante> listaVisitante();
	
	public abstract Visitante insertaActualizaVisitante(Visitante obj);
	
	public abstract List<Visitante> listaVisitantePorDNI(String dniVisitante);
	
	public abstract List<Visitante> listaVisitantePorNombreDiferenteDelMismo(String dniVisitante, int idVisitante);
	
	public abstract Optional<Visitante> listaVisitantePorId(int idVisitante);
	
	public abstract void eliminaPorId(int idVisitante);
	
}
