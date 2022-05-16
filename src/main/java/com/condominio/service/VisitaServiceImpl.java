package com.condominio.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.condominio.entity.Visita;
import com.condominio.repository.VisitaRepository;

public class VisitaServiceImpl implements VisitaService{

	@Autowired
	private VisitaRepository repository;
	@Override
	public List<Visita> listaVisita() {
		return repository.findAll();
	}

	@Override
	public Visita insertaActualizaVisita(Visita obj) {
		return repository.save(obj);
	}

}
