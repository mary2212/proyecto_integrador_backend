package com.condominio.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.condominio.entity.Edificio;

public interface EdificioRepository extends JpaRepository<Edificio, Integer> {

	@Query("select e from Edificio e where e.nomEdificio = ?1")
	public List<Edificio> listaPorNombre(String nomEdificio);
	
	@Query("select e from Edificio e where e.nomEdificio = ?1 and e.idEdificio <> ?2")
	public List<Edificio> listaPorNombreDiferenteSiMismo(String nomEdificio, int idEdificio);
}
