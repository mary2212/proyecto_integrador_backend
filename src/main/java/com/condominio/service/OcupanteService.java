package com.condominio.service;

import java.util.List;
import java.util.Optional;

import com.condominio.entity.Ocupante;


public interface OcupanteService {

	public abstract List<Ocupante> listaOcupante();
	public abstract Ocupante insertaActualizaOcupante(Ocupante obj);
	public abstract List<Ocupante> listaOcupantePorNombre(String nombreOcupante);
	public abstract List<Ocupante> listaOcupantePorNombreDiferenteDelMismo(String nombreOcupante, int idOcupante);
	public abstract Optional<Ocupante> listaOcupantePorId(int idOcupante);
	public abstract void eliminaPorId(int idOcupante);
}
