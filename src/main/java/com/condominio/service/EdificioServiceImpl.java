package com.condominio.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.condominio.entity.Edificio;
import com.condominio.repository.EdificioRepository;

@Service
public class EdificioServiceImpl implements EdificioService {

	@Autowired
	private EdificioRepository repository;
	
	//Lista
	@Override
	public List<Edificio> listaEdificio() {
		return repository.findAll();
	}

	//Registra
	@Override
	public Edificio insertaActualizaEdificio(Edificio obj) {
		return repository.save(obj);
	}
	//ListaPorNombre
	@Override
	public List<Edificio> listaEdificioPorNombre(String nomEdificio) {
		return repository.listaPorNombre(nomEdificio);
	}
	//ActualizaPorNombreDiferente
	@Override
	public List<Edificio> listaEdificioPorNombreDiferenteDelMismo(String nomEdificio, int idEdificio) {
		return repository.listaPorNombreDiferenteSiMismo(nomEdificio, idEdificio);
	}
	//ActualizaPorID
	@Override
	public Optional<Edificio> listaEdificioPorId(int idEdificio) {
		return repository.findById(idEdificio);
	}
	//Elimina
	@Override
	public void eliminaPorId(int idEdificio) {
		repository.deleteById(idEdificio);		
	}
}
