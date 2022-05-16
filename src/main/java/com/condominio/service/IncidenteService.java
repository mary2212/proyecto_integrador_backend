package com.condominio.service;

import java.util.List;


import com.condominio.entity.Incidente;


public interface IncidenteService {

	public abstract List<Incidente> listaIncidete();
	public abstract Incidente insertaActualizaIncidente(Incidente obj);
}
