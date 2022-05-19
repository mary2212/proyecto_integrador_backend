package com.condominio.service;

import java.util.List;
import java.util.Optional;

import com.condominio.entity.Usuario;

public interface UsuarioService {

	public abstract List<Usuario> listaUsuario();
	
	public abstract Usuario insertaActualizaUsuario(Usuario obj);
	
	public abstract List<Usuario> listaUsuarioPorNombre(String nombre);
	
	public abstract List<Usuario> listaPorLogin(String login);
	
	public abstract Optional<Usuario> listaUsuarioPorId(int idUsuario);
	
	public abstract List<Usuario> listaPorNombreDiferenteSiMismo(String nombre, int idUsuario);
	
	public abstract void eliminaPorId(int idUsuario);	

}
