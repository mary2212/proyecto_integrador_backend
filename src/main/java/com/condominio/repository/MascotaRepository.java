package com.condominio.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.condominio.entity.Mascota;

public interface MascotaRepository extends JpaRepository<Mascota, Integer>{

	@Query("select e from Mascota e where e.descripcion = ?1")
	public List<Mascota> listaPorDescripcion(String descripcion);
	
	@Query("select e from Mascota e where e.descripcion =?1 and e.idMascota <> ?2")
	public List<Mascota> listaPorDescripcionDiferenteSiMismo(String descripcion, int idMascota);
	
	@Query("select e from Mascota e where e.descripcion = ?1")
	public List<Mascota> listaMascotaPorDescripcion(String descripcion);
	

	
}
