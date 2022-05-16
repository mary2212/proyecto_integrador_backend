package com.condominio.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.condominio.entity.Incidente;

public interface IncidenteRepository extends JpaRepository<Incidente, Integer> {

}
