package com.condominio.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.condominio.entity.Visitante;
import com.condominio.repository.VisitanteRepository;

@Service
public class VisitanteServiceImpl implements VisitanteService{

	@Autowired
	private VisitanteRepository repository;
	
	@Override
	public List<Visitante> listaVisitante() {
		return repository.findAll();
	}

	@Override
	public Visitante insertaActualizaVisitante(Visitante obj) {
		return repository.save(obj);
	}

}
