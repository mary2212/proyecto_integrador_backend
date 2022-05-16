package com.condominio.service;

import java.util.List;
import java.util.Optional;

import com.condominio.entity.Departamento;


public interface DepartamentoService {

	public abstract List<Departamento> listaDepartamento();
	public abstract Departamento insertaActualizaDepartamento(Departamento ojb);
	public abstract List<Departamento> listaDepartamentoPorNumero(int numeroDepartamento);
	public abstract List<Departamento> listaDepartamentoPorNumeroDiferenteDelMismo(int numeroDepartamento, int idDepartamento);
	public abstract Optional<Departamento> listaDepartamentoPorId(int idDepartamento);
	public abstract void eliminaPorId(int idDepartamento);
}

