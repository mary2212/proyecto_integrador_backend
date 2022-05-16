package com.condominio.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.condominio.entity.Ocupante;
import com.condominio.repository.OcupanteRepository;

@Service
public class OcupanteServiceImpl implements OcupanteService{

	@Autowired
	private OcupanteRepository repository;
	@Override
	public List<Ocupante> listaOcupante() {
		return repository.findAll();
	}

	@Override
	public Ocupante insertaActualizaOcupante(Ocupante obj) {
		return repository.save(obj);
	}

	@Override
	public List<Ocupante> listaOcupantePorNombre(String nombreOcupante) {
		return repository.listaPorNombreOcupante(nombreOcupante);
	}

	@Override
	public List<Ocupante> listaOcupantePorNombreDiferenteDelMismo(String nombreOcupante, int idOcupante) {
		return repository.listaPorNombreDiferenteSiMismo(nombreOcupante, idOcupante);
	}

	@Override
	public Optional<Ocupante> listaOcupantePorId(int idOcupante) {
		return repository.findById(idOcupante);
	}

	@Override
	public void eliminaPorId(int idOcupante) {
		repository.deleteById(idOcupante);
		
	}

}
