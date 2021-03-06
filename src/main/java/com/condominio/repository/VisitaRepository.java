package com.condominio.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.condominio.entity.Visita;

public interface VisitaRepository extends JpaRepository<Visita, Integer>{

	@Query("select e from Visita e where e.idVisita = ?1")
	public List<Visita> listaPorId(int idVisita);
	
	@Query("select e from Visita e where e.estado = ?1")
	public List<Visita> listaPorEstado(String estado);
	
	@Query("select e from Visita e where e.fechaEntrada = ?1 and e.idVisita <> ?2")
	public List<Visita> listaPorNumeroDiferenteSiMismo(Date fechaEntrada, int idVisita);
}
