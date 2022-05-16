package com.condominio.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.condominio.entity.Rol;
import com.condominio.repository.RolRepository;

@Service
public class RolServiceImpl implements RolService{

	@Autowired
	private RolRepository repository;
	@Override
	public List<Rol> listaRol() {
		return repository.findAll();
	}

	@Override
	public Rol insertaActualizaRol(Rol obj) {
		return repository.save(obj);
	}

}
