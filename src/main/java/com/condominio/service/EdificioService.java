package com.condominio.service;

import java.util.List;
import java.util.Optional;

import com.condominio.entity.Edificio;

public interface EdificioService {

	public abstract List<Edificio> listaEdificio();
	public abstract Edificio insertaActualizaEdificio(Edificio obj);
	public abstract List<Edificio> listaEdificioPorNombre(String nomEdificio);
	public abstract List<Edificio> listaEdificioPorNombreDiferenteDelMismo(String nomEdificio, int idEdificio);
	public abstract Optional<Edificio> listaEdificioPorId(int idEdificio);
	public abstract void eliminaPorId(int idEdificio);
}
