package com.condominio.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.condominio.entity.Departamento;

public interface DepartamentoRepository extends JpaRepository<Departamento, Integer> {
	
	@Query("select e from Departamento e where e.numeroDepartamento = ?1")
	public List<Departamento> listaPorNumero(int numeroDepartamento);
	
	@Query("select e from Departamento e where e.numeroDepartamento = ?1 and e.idDepartamento <> ?2")
	public List<Departamento> listaPorNumeroDiferenteSiMismo(int numeroDepartamento, int idDepartamento);
	
	@Query("select e from Departamento e where e.estado = ?1")
	public List<Departamento> listaPorEstado(String estado);
}
