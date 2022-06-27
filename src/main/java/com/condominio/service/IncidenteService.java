package com.condominio.service;

import java.util.List;
import java.util.Optional;

import com.condominio.entity.Departamento;
import com.condominio.entity.Edificio;
import com.condominio.entity.Incidente;


public interface IncidenteService {

	//comentario 3
	
	public abstract List<Incidente> listaIncidente();
	
	public abstract List<Edificio> listaEdificio();
	
	public abstract List<Departamento> listaDepartamento();
	
	public abstract Incidente insertaActualizaIncidente(Incidente obj);
	
	public abstract void eliminaPorId(int idIncidente);
	
	public abstract List<Incidente> listaIncidetePorEstado(String estado);
	
	public abstract List<Incidente> listaPorDescripcionLike(String descripcion);
	
	public abstract List<Incidente> listaIncidentePorEstadoDiferenteDelMismo(int estado, int idIncidente);
	
	public abstract Optional<Incidente> listaIncidentePorId(int idIncidente);
	
	public List<Incidente> listaIncidentePorEdificioDepartamento(int idEdificio, int idDepartamento);
	

	public List<Incidente> listaIncidentePorEdificioDepartamento2(int idEdificio, int idDepartamento, String estado);

}
