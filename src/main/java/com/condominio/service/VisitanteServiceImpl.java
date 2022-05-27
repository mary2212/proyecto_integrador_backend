package com.condominio.service;

import java.util.List;
import java.util.Optional;

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

	@Override
	public List<Visitante> listaVisitantePorDNI(String dniVisitante) {
		return repository.listaVisitantePorDNI(dniVisitante);
	}

	@Override
	public Optional<Visitante> listaVisitantePorId(int idVisitante) {
		return repository.findById(idVisitante);
	}

	@Override
	public List<Visitante> listaVisitantePorNombreDiferenteDelMismo(String nombreVisitante, int idVisitante) {
		return repository.listaPorNombreDiferenteSiMismo(nombreVisitante, idVisitante);
	}

	@Override
	public void eliminaPorId(int idVisitante) {
		repository.deleteById(idVisitante);
		
	}

}
