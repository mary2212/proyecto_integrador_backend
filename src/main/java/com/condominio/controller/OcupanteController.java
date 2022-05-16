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

import com.condominio.entity.Ocupante;
import com.condominio.service.OcupanteService;

@RestController
@RequestMapping("/rest/ocupante")
@CrossOrigin(origins = "http://localhost:4200")
public class OcupanteController {

	@Autowired
	private OcupanteService service;
	
	//Lista
	@GetMapping
	@ResponseBody
	public ResponseEntity<List<Ocupante>> listar(){
		List<Ocupante> lista = service.listaOcupante();
		return ResponseEntity.ok(lista);
	}
	
	//Registra
		@PostMapping
		@ResponseBody
		public ResponseEntity<HashMap<String, Object>> insertaOcupante(@RequestBody Ocupante obj){
			HashMap<String, Object> salida = new HashMap<String, Object>();
			try {
				List<Ocupante> listaOcupante = service.listaOcupantePorNombre(obj.getNombreOcupante());
				if(CollectionUtils.isEmpty(listaOcupante)) {
					obj.setIdOcupante(0);
					Ocupante objSalida = service.insertaActualizaOcupante(obj);
					if(objSalida == null) {
						salida.put("mensaje", "Error en el registro ");
					}else {
						salida.put("mensaje", "Registro exitoso ");
					}				
				}else {
					salida.put("mensaje", "El Ocupante ya existe " + obj.getNombreOcupante());
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
		public ResponseEntity<HashMap<String, Object>> actualizaOcupante(@RequestBody Ocupante obj){
			HashMap<String, Object> salida = new HashMap<String, Object>();
			try {		
				Optional<Ocupante> optional = service.listaOcupantePorId(obj.getIdOcupante());
				if(optional.isPresent()) {
					List<Ocupante> listaOcupante = service.listaOcupantePorNombreDiferenteDelMismo(obj.getNombreOcupante(), obj.getIdOcupante());
					if(CollectionUtils.isEmpty(listaOcupante)) {
						Ocupante objSalida = service.insertaActualizaOcupante(obj);
						if(objSalida == null) {
							salida.put("mensaje", "Error en actualizar ");
						}else {
							salida.put("mensaje", "Actualizacion exitosa ");
						}	
					}else {
						salida.put("mensaje", "El Ocupante ya Existe " + obj.getNombreOcupante());
					}
				}else {
					salida.put("mensaje", "El ID no existe " + obj.getIdOcupante());
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
		public ResponseEntity<HashMap<String, Object>> eliminaOcupante(@PathVariable int id){
			HashMap<String, Object> salida = new HashMap<String, Object>();
			try {		
				Optional<Ocupante> optional = service.listaOcupantePorId(id);
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
