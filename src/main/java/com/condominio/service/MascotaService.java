package com.condominio.service;

import java.util.List;
import java.util.Optional;

import com.condominio.entity.Mascota;


public interface MascotaService {

	public abstract List<Mascota> listaMascota();
	
	public abstract Mascota insertaActualizaMacota(Mascota obj);
	
	public abstract List<Mascota> listaMascotaPorDescripcion(String descripcion);
	
	public abstract List<Mascota> listaMascotaPorDescripcionDiferenteDelMismo(String descripcion, int idMascota);
	
	public abstract Optional<Mascota> listaMascotaPorId(int idDepartamento);
	
	public abstract void eliminaPorId(int idMascota);
	

}
