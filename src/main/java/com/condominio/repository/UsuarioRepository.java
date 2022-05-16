package com.condominio.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.condominio.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{

}
