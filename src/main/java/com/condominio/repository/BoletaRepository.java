package com.condominio.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.condominio.entity.Boleta;



public interface BoletaRepository extends JpaRepository<Boleta, Integer> {

	@Query("select b from Boleta b where b.usuario.idUsuario = ?1")
	public List<Boleta> listaBoletaPorUsuario(int idUsuario);
	
	@Query("select b from Boleta b where b.estado = ?1 and b.idBoleta <> ?2")
	public List<Boleta> listaPorEstadoDiferenteSiMismo(String estado, int idBoleta);
	
	@Query("select b from Boleta b where b.estado = ?1")
	public List<Boleta> listaBoletaPorEstado(String estado);
	
	@Query("select b from Boleta b where (?1 is '' or b.estado= ?1) and (?2 is -1 or b.servicio.idServicio= ?2) and (?3 is -1 or b.propietario.idPropietario= ?3)")
	public List<Boleta> listaBoletaPorEstadoServicioNombre(String estado, int idServicio, int idPropietario);
}
