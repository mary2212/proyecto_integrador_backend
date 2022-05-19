package com.condominio.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.condominio.entity.Propietario;

public interface PropietarioRepository extends JpaRepository<Propietario, Integer>{
	
 @Query("select e from Propietario e where e.dniPropietario = ?1")
 public List<Propietario> listaPropietarioPorDni(String dniPropietario);
 
 @Query("select e from Propietario e where e.dniPropietario = ?1 and e.idPropietario <> ?2")
 public List<Propietario> listaPropPorDniDifSiMismo(String dniPropietario, int idPropietario);
 
 @Query("select e from Propietario e where e.nombrePropietario = ?1")
 public List<Propietario> listaPropietarioPorNombre(String nombrePropietario);

}
