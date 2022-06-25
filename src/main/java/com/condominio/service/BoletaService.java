package com.condominio.service;

import java.util.List;
import java.util.Optional;

import com.condominio.entity.Boleta;



public interface BoletaService {

	public abstract List<Boleta> listaBoleta(); 
	
	public abstract Boleta insertaActualizaBoleta(Boleta obj);
	
	public abstract void eliminarPorId(int idBoleta);
	
	public List<Boleta> listaBoletaPorUsuario(int idUsuario);
	
	public abstract List<Boleta> listaBoletaPorEstadoDiferenteDelMismo(String estado, int idBoleta);
	
	public abstract Optional<Boleta> listaBoletaPorId(int idBoleta);
	
	public List<Boleta> listaBoletaPorEstado(String estado);
	
	public List<Boleta> listaBoletaPorEstadoServicioNombre(String estado, int idServicio, int idPropietario);
}
