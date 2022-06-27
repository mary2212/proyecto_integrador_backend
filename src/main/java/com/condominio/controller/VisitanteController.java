package com.condominio.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.condominio.entity.Visitante;
import com.condominio.service.VisitanteService;

@RestController
@RequestMapping("/rest/visitante")
@CrossOrigin(origins = "http://localhost:4200")
public class VisitanteController {
	
	private Logger log = LoggerFactory.getLogger(VisitanteController.class);

	@Autowired
	private VisitanteService service;
	
	//Lista
	@GetMapping("/listar")
	@ResponseBody
	public ResponseEntity<List<Visitante>> listar(){
		List<Visitante> lista = service.listaVisitante();
		return ResponseEntity.ok(lista);
	}
		
	//ListaPorDNI
	@GetMapping("/listaVisitantePorDNI/{dni}")
	@ResponseBody
	public ResponseEntity<List<Visitante>> listaVisitantePorDNI(@PathVariable("dni") String dni){
		log.info("==> listaVisitantePorDNI ==> dni : " + dni);
		
		List<Visitante> lista = null;
		try {
			if(dni.equals("todos")) {
				lista = service.listaVisitante();
			}else {
				lista = service.listaVisitantePorDNI(dni);
			}
		} catch (Exception e) {

		}
		return ResponseEntity.ok(lista);
	}
	
	//Registra
	@PostMapping("/registrar")
	@ResponseBody
	public ResponseEntity<HashMap<String, Object>> insertaVisitante(@RequestBody Visitante obj){
		HashMap<String, Object> salida = new HashMap<String, Object>();
		try {
			List<Visitante> listaVisitante = service.listaVisitantePorDNI(obj.getDniVisitante());
			if(CollectionUtils.isEmpty(listaVisitante)) {
				obj.setIdVisitante(0);
				obj.setFechaRegistro(new Date());
				Visitante objSalida = service.insertaActualizaVisitante(obj);
				if(objSalida == null) {
					salida.put("mensaje", "Error en el registro ");
				}else {
					salida.put("mensaje", "Registro exitoso ");
				}				
			}else {
				salida.put("mensaje", "El Visitante ya existe " + obj.getDniVisitante());
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
	public ResponseEntity<HashMap<String, Object>> actualizaVisitante(@RequestBody Visitante obj){
		HashMap<String, Object> salida = new HashMap<String, Object>();
		try {		
			Optional<Visitante> optional = service.listaVisitantePorId(obj.getIdVisitante());
			if(optional.isPresent()) {
				List<Visitante> listaVisitante = service.listaVisitantePorNombreDiferenteDelMismo(obj.getDniVisitante(), obj.getIdVisitante());
				if(CollectionUtils.isEmpty(listaVisitante)) {
					Visitante objSalida = service.insertaActualizaVisitante(obj);
					if(objSalida == null) {
						salida.put("mensaje", "Error en actualizar ");
					}else {
						salida.put("mensaje", "Actualizacion exitosa ");
					}	
				}else {
					salida.put("mensaje", "El Visitante ya Existe " + obj.getDniVisitante());
				}
			}else {
				salida.put("mensaje", "El ID no existe " + obj.getIdVisitante());
			}
					
		} catch (Exception e) {
			e.printStackTrace();
			salida.put("mensaje", "Error en la actualizacion " + e.getMessage());
		}		
		return ResponseEntity.ok(salida);
	}
	
	//Elimina
	@DeleteMapping("/{id}")
	@ResponseBody
	public ResponseEntity<HashMap<String, Object>> eliminaVisitante(@PathVariable int id){
		HashMap<String, Object> salida = new HashMap<String, Object>();
		try {		
			Optional<Visitante> optional = service.listaVisitantePorId(id);
			if(optional.isPresent()) {
				service.eliminaPorId(id);
				salida.put("mensaje", "Eliminacion exitosa ");
			}else {
				salida.put("mensaje", "El ID no existe " + id);
			}					
		} catch (Exception e) {
			e.printStackTrace();
			salida.put("mensaje", "Error en la eliminacion " + e.getMessage());
		}		
		return ResponseEntity.ok(salida);
	}
	
}
