package com.condominio.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.condominio.entity.Visita;
import com.condominio.repository.VisitaRepository;

@Service
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

	@Override
	public Optional<Visita> listaVisitaPorId(int idVisita) {
		return repository.findById(idVisita);
	}

	@Override
	public void eliminaPorId(int idVisita) {
		repository.deleteById(idVisita);
		
	}

	@Override
	public List<Visita> listaVisitaPorEstado(String estado) {
		return repository.listaPorEstado(estado);
	}

	@Override
	public List<Visita> listaVisitaPorId2(int idVisita) {
		return repository.listaPorId(idVisita);
	}



}
