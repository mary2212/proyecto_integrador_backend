package com.condominio.service;

import java.util.List;
import java.util.Optional;

import com.condominio.entity.Propietario;

public interface PropietarioService {
	
	public abstract List<Propietario> listaTodos();

	public abstract Propietario insertaActualizaPropietario(Propietario obj);
	
	public abstract List<Propietario> listaPropietarioPorDni(String dniPropietario);
	
	public abstract List<Propietario> listaPropPorDniDifSiMismo (String dniPropietario, int idPropietario);
	
	public abstract Optional<Propietario> listaPropPorId(int idPropietario);
	
	public abstract List<Propietario> listaPropietarioPorNombre(String nombrePropietario);
	
	public abstract void eliminaPorId(int idPropietario);
	}
