package com.condominio.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.condominio.entity.Incidente;
import com.condominio.repository.IncidenteRepository;

@Service
public class IncidenteServiceImpl implements IncidenteService{

	@Autowired
	private IncidenteRepository repository;
	
	@Override
	public List<Incidente> listaIncidete() {
		return repository.findAll();
	}

	@Override
	public Incidente insertaActualizaIncidente(Incidente obj) {
		return repository.save(obj);
	}

}
