package com.condominio.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.condominio.entity.Usuario;
import com.condominio.repository.UsuarioRepository;

public class UsuarioServiceImpl implements UsuarioService{

	@Autowired
	private UsuarioRepository repository;
	
	@Override
	public List<Usuario> listaUsuario() {
		return repository.findAll();
	}

	@Override
	public Usuario insertaActualizaUsuario(Usuario obj) {
		return repository.save(obj);
	}

}
