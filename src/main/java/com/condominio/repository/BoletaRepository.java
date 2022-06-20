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
}
