package com.condominio.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.condominio.entity.Ocupante;

public interface OcupanteRepository extends JpaRepository<Ocupante, Integer>{

	@Query("select e from Ocupante e where e.nombreOcupante = ?1")
	public List<Ocupante> listaPorNombreOcupante(String nombreOcupante);
	
	@Query("select e from Ocupante e where e.nombreOcupante = ?1 and e.idOcupante <> ?2")
	public List<Ocupante> listaPorNombreDiferenteSiMismo(String nombreOcupante, int idOcupante);
}
