package com.condominio.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.condominio.entity.Boleta;
import com.condominio.repository.BoletaRepository;

@Service
public class BoletaServiceImpl implements BoletaService{
	
	@Autowired
	private BoletaRepository repository;
	
	@Override
	public List<Boleta> listaBoleta() {
		return repository.findAll();
	}

	@Override
	public Boleta insertaActualizaBoleta(Boleta obj) {
		return repository.save(obj);
	}

	@Override
	public void eliminarPorId(int idBoleta) {
		repository.deleteById(idBoleta);
		
	}

	@Override
	public List<Boleta> listaBoletaPorUsuario(int idUsuario) {
		// TODO Auto-generated method stub
		return repository.listaBoletaPorUsuario(idUsuario);
	}

	@Override
	public Optional<Boleta> listaBoletaPorId(int idBoleta) {
		// TODO Auto-generated method stub
		return repository.findById(idBoleta);
	}

	@Override
	public List<Boleta> listaBoletaPorEstadoDiferenteDelMismo(String estado, int idBoleta) {
		// TODO Auto-generated method stub
		return repository.listaPorEstadoDiferenteSiMismo(estado, idBoleta);
	}

	@Override
	public List<Boleta> listaBoletaPorEstado(String estado) {
		// TODO Auto-generated method stub
		return repository.listaBoletaPorEstado(estado);
	}

	@Override
	public List<Boleta> listaBoletaPorEstadoServicioNombre(String estado, int idServicio, int idPropietario) {
		// TODO Auto-generated method stub
		return repository.listaBoletaPorEstadoServicioNombre(estado, idServicio, idPropietario);
	}

}
