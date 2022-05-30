package com.condominio.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.condominio.entity.Visita;

public interface VisitaService {

	public abstract List<Visita> listaVisita();
	
	public abstract Visita insertaActualizaVisita(Visita obj);
	
	public abstract Optional<Visita> listaVisitaPorId(int idVisita);
	
	public abstract List<Visita> listaVisitaPorId2(int idVisita);
	
	public abstract void eliminaPorId(int idVisita);
	
	public abstract List<Visita> listaVisitaPorEstado(String estado);
	
	public abstract List<Visita> listaVisitaPorNumeroDiferenteDelMismo(Date fechaEntrada, int idVisita);

	
}
