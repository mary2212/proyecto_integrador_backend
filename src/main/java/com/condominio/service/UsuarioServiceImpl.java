package com.condominio.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.condominio.entity.Usuario;
import com.condominio.repository.UsuarioRepository;

@Service
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

	@Override
	public List<Usuario> listaPorLogin(String login) {
		return repository.listaPorlogin(login);
	}

	@Override
	public List<Usuario> listaUsuarioPorNombre(String nombre) {
		return repository.listaPorNombre(nombre);
	}

	@Override
	public Optional<Usuario> listaUsuarioPorId(int idUsuario) {
		return repository.findById(idUsuario);
	}

	@Override
	public List<Usuario> listaPorNombreDiferenteSiMismo(String nombre, int idUsuario) {
		return repository.listaPorNombreDiferenteSiMismo(nombre, idUsuario);
	}

	@Override
	public void eliminaPorId(int idUsuario) {
		repository.deleteById(idUsuario);
		
	}


}
