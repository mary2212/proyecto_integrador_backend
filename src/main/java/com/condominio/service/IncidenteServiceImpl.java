package com.condominio.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.condominio.entity.Departamento;
import com.condominio.entity.Edificio;
import com.condominio.entity.Incidente;
import com.condominio.repository.DepartamentoRepository;
import com.condominio.repository.EdificioRepository;
import com.condominio.repository.IncidenteRepository;

@Service
public class IncidenteServiceImpl implements IncidenteService{

	@Autowired
	private IncidenteRepository repository;
	
	@Autowired
	private EdificioRepository edificiorepository;
	
	@Autowired
	private DepartamentoRepository departamentorepository;
	

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
	public List<Incidente> listaPorDescripcionLike(String descripcion) {
		return repository.listaPorDescripcionLike(descripcion);
	}

	@Override
	public List<Incidente> listaIncidentePorEdificioDepartamento(int idEdificio, int idDepartamento) {
		return repository.listaIncidentePorEdificioDepartamento(idEdificio, idDepartamento);
	}

	@Override
	public List<Edificio> listaEdificio() {
		return edificiorepository.findAll();
	}

	@Override
	public List<Departamento> listaDepartamento() {
		return departamentorepository.findAll();
	}

}
