package com.condominio.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.condominio.entity.Propietario;
import com.condominio.repository.PropietarioRepository;

@Service
public class PropietarioServiceImpl implements PropietarioService {
	
	@Autowired //autoinyecta
	private PropietarioRepository repository;

	@Override
	public List<Propietario> listaTodos() {
		return repository.findAll();
	}
	//ListaPorNombre
	@Override
	public List<Propietario> listaPropietarioPorNombre(String nombrePropietario) {
		return repository.listaPropietarioPorNombre(nombrePropietario);
	}

	@Override
	public Propietario insertaActualizaPropietario(Propietario obj) {
		return repository.save(obj);
	}

	@Override
	public List<Propietario> listaPropietarioPorDni(String dniPropietario) {
		return repository.listaPropietarioPorDni(dniPropietario);
	}

	@Override
	public List<Propietario> listaPropPorDniDifSiMismo(String dniPropietario, int idPropietario) {
		return repository.listaPropPorDniDifSiMismo(dniPropietario, idPropietario);
	}

	@Override
	public Optional<Propietario> listaPropPorId(int idPropietario) {
		
		return repository.findById(idPropietario);
	}
	@Override
	public void eliminaPorId(int idPropietario) {
		repository.deleteById(idPropietario);
		
	}


	
	
}
