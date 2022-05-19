package com.condominio.controller;

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

import com.condominio.entity.Propietario;
import com.condominio.service.PropietarioService;

@RestController
@RequestMapping("/rest/propietario")
@CrossOrigin(origins = "http://localhost:4200")
public class PropietarioController {
	
	private Logger log = LoggerFactory.getLogger(PropietarioController.class);
	
	@Autowired
	private PropietarioService service;
	
	@GetMapping("/listar")
	@ResponseBody
	public ResponseEntity <List<Propietario>> listarPropietarios(){
		List<Propietario> lista= service.listaTodos();
		return ResponseEntity.ok(lista);
	}
	
	@GetMapping("/listaPropietarioPorNombre/{nom}")
	@ResponseBody
	public ResponseEntity<List<Propietario>> listaPropietarioPorNombre(@PathVariable("nom") String nom){
		log.info("==> listaPropietarioPorNombre ==> est : " + nom);
		
		List<Propietario> lista = null;
		try {
			if(nom.equals("todos")) {
				lista = service.listaTodos();
			}else {
				lista = service.listaPropietarioPorNombre(nom);
			}
		} catch (Exception e) {

		}
		return ResponseEntity.ok(lista);
	}	
	
	//Registra
		@PostMapping("/registrar")
		@ResponseBody
		public ResponseEntity<HashMap<String, Object>> insertaPropietario(@RequestBody Propietario obj){
			HashMap<String, Object> salida = new HashMap<String, Object>();
			try {
				List<Propietario> listaPropietario = service.listaPropietarioPorDni(obj.getDniPropietario());
				if(CollectionUtils.isEmpty(listaPropietario)) {
					obj.setIdPropietario(0);
					Propietario objSalida = service.insertaActualizaPropietario(obj);
					if(objSalida == null) {
						salida.put("mensaje", "Error en el registro ");
					}else {
						salida.put("mensaje", "Registro exitoso ");
					}				
				}else {
					salida.put("mensaje", "El DNI ya existe " + obj.getDniPropietario());
				}			
			} catch (Exception e) {
				e.printStackTrace();
				salida.put("mensaje", "Error en el registro " + e.getMessage());
			}		
			return ResponseEntity.ok(salida);
		}
	
	@PutMapping("/actualizar")
	@ResponseBody //retorna jason
	public ResponseEntity<HashMap<String, Object>> actualizaPropietario(@RequestBody Propietario obj){
		HashMap<String, Object> salida= new HashMap<String,Object>();
		try {
			Optional <Propietario> optional = service.listaPropPorId(obj.getIdPropietario());
			if(optional.isPresent()) {
				List<Propietario> lstPropietario = service.listaPropPorDniDifSiMismo(obj.getDniPropietario(),obj.getIdPropietario());
					if(CollectionUtils.isEmpty(lstPropietario)) {
						Propietario objSalida= service.insertaActualizaPropietario(obj);
						if(objSalida == null) {
							salida.put("mensaje", "Error en actualizar ");
						}else {
							salida.put("mensaje", "Actualizacion exitosa ");
						}
					}else {
						salida.put("mensaje", "El DNI ya existe: " + obj.getDniPropietario());
					}
			}else {
				salida.put("mensaje", "El ID no existe: " + obj.getIdPropietario());
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			salida.put("mensaje", "Error en registro" + e.getMessage());
		}
		return ResponseEntity.ok(salida);
	}
	
	//Elimina
	@DeleteMapping("/{id}")
	@ResponseBody
	public ResponseEntity<HashMap<String, Object>> eliminaPropietario(@PathVariable int id){
		HashMap<String, Object> salida = new HashMap<String, Object>();
		try {		
			Optional<Propietario> optional = service.listaPropPorId(id);
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
