package com.condominio.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.condominio.entity.Departamento;
import com.condominio.repository.DepartamentoRepository;


@Service
public class DepartamentoServiceImpl implements DepartamentoService {

	@Autowired
	private DepartamentoRepository repository;
	
	@Override
	public List<Departamento> listaDepartamento() {
		return repository.findAll();
	}

	@Override
	public Departamento insertaActualizaDepartamento(Departamento obj) {
		return repository.save(obj);
	}

	@Override
	public List<Departamento> listaDepartamentoPorNumero(int numeroDepartamento) {
		return repository.listaPorNumero(numeroDepartamento);
	}

	@Override
	public List<Departamento> listaDepartamentoPorNumeroDiferenteDelMismo(int numeroDepartamento,
			int idDepartamento) {
		return repository.listaPorNumeroDiferenteSiMismo(numeroDepartamento, idDepartamento);
	}

	@Override
	public Optional<Departamento> listaDepartamentoPorId(int idDepartamento) {
		return repository.findById(idDepartamento);
	}

	@Override
	public void eliminaPorId(int idDepartamento) {
		repository.deleteById(idDepartamento);
		
	}



}
