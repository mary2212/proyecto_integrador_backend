package com.condominio.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.condominio.entity.Incidente;
import com.condominio.repository.IncidenteRepository;

@Service
public class IncidenteServiceImpl implements IncidenteService{

	@Autowired
	private IncidenteRepository repository;

	@Override
	public List<Incidente> listaIncidente() {
		return repository.findAll() ;
	}

	@Override
	public Incidente insertaActualizaIncidente(Incidente obj) {
		return repository.save(obj);
	}

	@Override
	public void eliminaPorId(int idIncidente) {
		repository.deleteById(idIncidente);		
	}

	@Override
	public List<Incidente> listaIncidetePorEstado(String estado) {
		return repository.listaPorEstado(estado);
	}

	@Override
	public List<Incidente> listaIncidentePorEstadoDiferenteDelMismo(int estado, int idIncidente) {
		return repository.listaPorEstadoDiferenteSiMismo(estado, idIncidente);
	}

	@Override
	public Optional<Incidente> listaIncidentePorId(int idIncidente) {
		return repository.findById(idIncidente);
	}

	@Override
	public List<Incidente> listaIncidetePorDescripcion(String descripcion) {
		return repository.listaPorDescripcion(descripcion);
	}

	@Override
	public List<Incidente> listaIncidentePorEdificioDepartamentoEstado(int idEdificio, int idDepartamento,
			int estado) {
		return repository.listaIncidentePorEdificioDepartamentoEstado(idEdificio, idDepartamento, estado);
	}
	


}
