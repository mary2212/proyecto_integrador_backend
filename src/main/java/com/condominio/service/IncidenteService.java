package com.condominio.service;

import java.util.List;
import java.util.Optional;

import com.condominio.entity.Incidente;


public interface IncidenteService {

	public abstract List<Incidente> listaIncidente();
	
	public abstract Incidente insertaActualizaIncidente(Incidente obj);
	
	public abstract void eliminaPorId(int idIncidente);
	
	public abstract List<Incidente> listaIncidetePorEstado(String estado);
	
	public abstract List<Incidente> listaIncidetePorDescripcion(String descripcion);
	
	public abstract List<Incidente> listaIncidentePorEstadoDiferenteDelMismo(int estado, int idIncidente);
	
	public abstract Optional<Incidente> listaIncidentePorId(int idIncidente);
	
	public List<Incidente> listaIncidentePorEdificioDepartamentoEstado(int idEdificio, int idDepartamento, int estado);
}
