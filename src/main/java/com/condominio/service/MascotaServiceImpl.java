package com.condominio.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.condominio.entity.Mascota;
import com.condominio.repository.MascotaRepository;

@Service
public class MascotaServiceImpl implements MascotaService{

	@Autowired
	private MascotaRepository repository;
	
	@Override
	public List<Mascota> listaMascota() {
		return repository.findAll();
	}

	@Override
	public Mascota insertaActualizaMacota(Mascota obj) {
		return repository.save(obj);
	}

	@Override
	public List<Mascota> listaMascotaPorDescripcion(String descripcion) {
		return repository.listaPorDescripcion(descripcion);
	}

	@Override
	public List<Mascota> listaMascotaPorDescripcionDiferenteDelMismo(String descripcion, int idMascota) {
		return repository.listaPorDescripcionDiferenteSiMismo(descripcion, idMascota);
	}

	@Override
	public Optional<Mascota> listaMascotaPorId(int idDepartamento) {
		return repository.findById(idDepartamento);
	}

	@Override
	public void eliminaPorId(int idMascota) {
		repository.deleteById(idMascota);
		
	}

}
