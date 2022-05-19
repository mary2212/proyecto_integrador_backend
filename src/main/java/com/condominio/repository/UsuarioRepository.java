package com.condominio.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.condominio.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{

	@Query("select e from Usuario e where e.nombre = ?1")
	public List<Usuario> listaPorNombre(String nombre);

	
	@Query("select e from Usuario e where e.login = ?1")
	public List<Usuario> listaPorlogin(String login);

	@Query("select e from Usuario e where e.nombre = ?1 and e.idUsuario <> ?2")
	public List<Usuario> listaPorNombreDiferenteSiMismo(String nombre, int idUsuario);
}
