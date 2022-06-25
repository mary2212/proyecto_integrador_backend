package com.condominio.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.condominio.entity.Incidente;

public interface IncidenteRepository extends JpaRepository<Incidente, Integer> {

	//comentario 2
	
	@Query("select e from Incidente e where e.estado = ?1 and e.idIncidente <> ?2")
	public List<Incidente> listaPorEstadoDiferenteSiMismo(int estado, int idIncidente);
	
	@Query("select e from Incidente e where e.estado like ?1")
	public List<Incidente> listaPorEstado(String estado);
	
	@Query("select e from Incidente e where e.descripcion like ?1")
	public List<Incidente> listaPorDescripcionLike(String descripcion);
		
	@Query("select e from Incidente e where (?1 is -1 or e.edificio.idEdificio = ?1) and (?2 is -1 or e.departamento.idDepartamento = ?2)")
	public List<Incidente> listaIncidentePorEdificioDepartamento(int idEdificio, int idDepartamento);
	
	@Query("select e from Incidente e where (?1 is -1 or e.edificio.idEdificio = ?1) and (?2 is -1 or e.departamento.idDepartamento = ?2) and (?3 is '' or e.estado like ?3)")
	public List<Incidente> listaIncidentePorEstadoEdificioDepartamento(int idEdificio, int idDepartamento,String estado);
}

