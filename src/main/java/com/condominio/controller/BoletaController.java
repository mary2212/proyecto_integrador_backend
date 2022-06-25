package com.condominio.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.condominio.entity.Boleta;
import com.condominio.entity.Propietario;
import com.condominio.service.BoletaService;
import com.condominio.service.PropietarioService;

@RestController
@RequestMapping("/rest/boleta")
@CrossOrigin(origins = "http://localhost:4200")
public class BoletaController {

	private Logger log = LoggerFactory.getLogger(IncidenteController.class);
	
	@Autowired
	private BoletaService service;
	
	@Autowired
	private PropietarioService service1;
	
	@GetMapping("/listar")
	@ResponseBody
	public ResponseEntity<List<Boleta>> listar(){
		List<Boleta> lista = service.listaBoleta();
		return ResponseEntity.ok(lista);
	}
	
	
	@GetMapping("/listaBoletaPorDni/{dni}")
	@ResponseBody
	public ResponseEntity<List<Propietario>> listaBoletaPorDni(@PathVariable("dni") String dni){
		log.info("==> listaPropietarioPorNombre ==> est : " + dni);
		
		List<Propietario> lista = null;
		try {
			if(dni.equals("todos")) {
				lista = service1.listaTodos();
			}else {
				lista = service1.listaPropietarioPorDni(dni);
			}
		} catch (Exception e) {

		}
		return ResponseEntity.ok(lista);
	}	
	
	

	
	@GetMapping("/listaBoletaPorEstado/{estado}")
	@ResponseBody
	public ResponseEntity<List<Boleta>> listaBoletaPorIdUsuario(@PathVariable("estado") String estado){
		log.info("==> listaPropietarioPorNombre ==> est : " + estado);
		
		List<Boleta> lista = null;
		try {
			if(estado.equals("todos")) {
				lista = service.listaBoleta();
			}else {
				lista = service.listaBoletaPorEstado(estado);
			}
		} catch (Exception e) {

		}
		return ResponseEntity.ok(lista);
	}	
	
	
	
	
	//Registra
	@PostMapping("/registrar")
	@ResponseBody
	public ResponseEntity<HashMap<String, Object>> insertaBoleta(@RequestBody Boleta obj){
		HashMap<String, Object> salida = new HashMap<String, Object>();
		try {
			Optional<Boleta> optional = service.listaBoletaPorId(obj.getIdBoleta());
			if(optional.isEmpty()) {
				obj.setIdBoleta(0);
				obj.setEstado("NO PAGADO");
				

				Boleta objSalida = service.insertaActualizaBoleta(obj);
				if(objSalida == null) {
					salida.put("mensaje", "Error en el registro ");
				}else {
					salida.put("mensaje", "Registro exitoso ");
				}				
			}else {
				salida.put("mensaje", "La boleta ya existe " + obj.getIdBoleta());
			}			
		} catch (Exception e) {
			e.printStackTrace();
			salida.put("mensaje", "Error en el registro " + e.getMessage());
		}		
		return ResponseEntity.ok(salida);
	}
	
	//Actualiza
		@PutMapping("/actualizar")
		@ResponseBody
		public ResponseEntity<HashMap<String, Object>> actualizaBoleta(@RequestBody Boleta obj){
			HashMap<String, Object> salida = new HashMap<String, Object>();
			try {		
				Optional<Boleta> optional = service.listaBoletaPorId(obj.getIdBoleta());
				if(optional.isPresent()) {
					if(optional.get().getEstado().equals("NO PAGADO")) {
						Boleta objSalida = service.insertaActualizaBoleta(obj);
						if(objSalida == null) {
							salida.put("mensaje", "Error en actualizar ");
						}else {
							salida.put("mensaje", "Actualizacion exitosa ");
						}	
					}else {
						salida.put("mensaje", "La Boleta está en ESTADO: " + obj.getEstado());
					}
				}else {
					salida.put("mensaje", "El ID no existe " + obj.getIdBoleta());
				}
						
			} catch (Exception e) {
				e.printStackTrace();
				salida.put("mensaje", "Error en la actualizacion " + e.getMessage());
			}		
			return ResponseEntity.ok(salida);
		
		}
		
		@GetMapping("/listaBoletaEstadoServicioProp")
		@ResponseBody
		public ResponseEntity<Map<String, Object>> listaBoletaEstadoServicioProp(
				@RequestParam(name= "estado", required = true, defaultValue = "")String estado,
				@RequestParam(name= "idServicio", required = false, defaultValue = "-1")int idServicio,
				@RequestParam(name= "idPropietario", required = false, defaultValue = "-1")int idPropietario){
			
			Map<String, Object> salida= new HashMap<>();
			try {
				List<Boleta> lista= service.listaBoletaPorEstadoServicioNombre(estado, idServicio, idPropietario);
				if(CollectionUtils.isEmpty(lista)) {
					salida.put("mensaje", "No existen dato para mostrar");
				}else {
					salida.put("lista", lista);
					salida.put("mensaje", "Existen " + lista.size() + " elementos para mostrar");
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
					return ResponseEntity.ok(salida);
			
		}
}
