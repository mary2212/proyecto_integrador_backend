package com.condominio.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.condominio.entity.Visitante;

public interface VisitanteRepository extends JpaRepository<Visitante, Integer>{

	@Query("select e from Visitante e where e.dniVisitante =?1")
	public List<Visitante> listaVisitantePorDNI(String dniVisitante);
	
	@Query("select e from Visitante e where e.dniVisitante = ?1 and e.idVisitante <> ?2")
	public List<Visitante> listaPorNombreDiferenteSiMismo(String dniVisitante, int idVisitante);
}
