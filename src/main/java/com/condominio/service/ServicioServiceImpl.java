package com.condominio.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.condominio.entity.Servicio;
import com.condominio.repository.ServicioRepository;

@Service
public class ServicioServiceImpl implements ServicioService{

	@Autowired
	private ServicioRepository repository;
	
	@Override
	public List<Servicio> listaServicio() {
		return repository.findAll();
	}

	@Override
	public Servicio insertaActualizaServicio(Servicio obj) {
		return repository.save(obj);
	}

}
