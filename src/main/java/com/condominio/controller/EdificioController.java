package com.condominio.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

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

import com.condominio.entity.Edificio;
import com.condominio.service.EdificioService;

@RestController
@RequestMapping("/rest/edificio")
@CrossOrigin(origins = "http://localhost:4200")
public class EdificioController {

	@Autowired
	private EdificioService service;
	
	//Lista
	@GetMapping
	@ResponseBody
	public ResponseEntity<List<Edificio>> listar(){
		List<Edificio> lista = service.listaEdificio();
		return ResponseEntity.ok(lista);
	}
	
	//Registra
	@PostMapping
	@ResponseBody
	public ResponseEntity<HashMap<String, Object>> insertaEdificio(@RequestBody Edificio obj){
		HashMap<String, Object> salida = new HashMap<String, Object>();
		try {
			List<Edificio> listaEdificio = service.listaEdificioPorNombre(obj.getNomEdificio());
			if(CollectionUtils.isEmpty(listaEdificio)) {
				obj.setIdEdificio(0);
				Edificio objSalida = service.insertaActualizaEdificio(obj);
				if(objSalida == null) {
					salida.put("mensaje", "Error en el registro ");
				}else {
					salida.put("mensaje", "Registro exitoso ");
				}				
			}else {
				salida.put("mensaje", "El nombre ya existe " + obj.getNomEdificio());
			}			
		} catch (Exception e) {
			e.printStackTrace();
			salida.put("mensaje", "Error en el registro " + e.getMessage());
		}		
		return ResponseEntity.ok(salida);
	}
	
	//Actualiza
	@PutMapping
	@ResponseBody
	public ResponseEntity<HashMap<String, Object>> actualizaEdificio(@RequestBody Edificio obj){
		HashMap<String, Object> salida = new HashMap<String, Object>();
		try {		
			Optional<Edificio> optional = service.listaEdificioPorId(obj.getIdEdificio());
			if(optional.isPresent()) {
				List<Edificio> listaEdificio = service.listaEdificioPorNombreDiferenteDelMismo(obj.getNomEdificio(), obj.getIdEdificio());
				if(CollectionUtils.isEmpty(listaEdificio)) {
					Edificio objSalida = service.insertaActualizaEdificio(obj);
					if(objSalida == null) {
						salida.put("mensaje", "Error en actualizar ");
					}else {
						salida.put("mensaje", "Actualizacion exitosa ");
					}	
				}else {
					salida.put("mensaje", "El Nombre ya Existe " + obj.getNomEdificio());
				}
			}else {
				salida.put("mensaje", "El ID no existe " + obj.getIdEdificio());
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
	public ResponseEntity<HashMap<String, Object>> eliminaEdificio(@PathVariable int id){
		HashMap<String, Object> salida = new HashMap<String, Object>();
		try {		
			Optional<Edificio> optional = service.listaEdificioPorId(id);
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