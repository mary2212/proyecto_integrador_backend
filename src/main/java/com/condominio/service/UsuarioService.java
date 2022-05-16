package com.condominio.service;

import java.util.List;

import com.condominio.entity.Usuario;

public interface UsuarioService {

	public abstract List<Usuario> listaUsuario();
	public abstract Usuario insertaActualizaUsuario(Usuario obj);
}
