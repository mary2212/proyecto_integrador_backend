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

import com.condominio.entity.Departamento;
import com.condominio.service.DepartamentoService;

@RestController
@RequestMapping("/rest/departamento")
@CrossOrigin(origins = "http://localhost:4200")
public class DepartamentoController {
	
	@Autowired
	private DepartamentoService service;
	
	@GetMapping
	@ResponseBody
	public ResponseEntity<List<Departamento>> listar(){
		List<Departamento> lista = service.listaDepartamento();
		return ResponseEntity.ok(lista);
	}
	
	//Registra
	@PostMapping
	@ResponseBody
	public ResponseEntity<HashMap<String, Object>> insertaDepartamento(@RequestBody Departamento obj){
		HashMap<String, Object> salida = new HashMap<String, Object>();
		try {
			List<Departamento> listaDepartamento = service.listaDepartamentoPorNumero(obj.getNumeroDepartamento());
			if(CollectionUtils.isEmpty(listaDepartamento)) {
				obj.setIdDepartamento(0);
				Departamento objSalida = service.insertaActualizaDepartamento(obj);
				if(objSalida == null) {
					salida.put("mensaje", "Error en el registro ");
				}else {
					salida.put("mensaje", "Registro exitoso ");
				}				
			}else {
				salida.put("mensaje", "El Número ya existe " + obj.getNumeroDepartamento());
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
		public ResponseEntity<HashMap<String, Object>> actualizaDepartamento(@RequestBody Departamento obj){
			HashMap<String, Object> salida = new HashMap<String, Object>();
			try {		
				Optional<Departamento> optional = service.listaDepartamentoPorId(obj.getIdDepartamento());
				if(optional.isPresent()) {
					List<Departamento> listaDepartamento = service.listaDepartamentoPorNumeroDiferenteDelMismo(obj.getNumeroDepartamento(), obj.getIdDepartamento());
					if(CollectionUtils.isEmpty(listaDepartamento)) {
						Departamento objSalida = service.insertaActualizaDepartamento(obj);
						if(objSalida == null) {
							salida.put("mensaje", "Error en actualizar ");
						}else {
							salida.put("mensaje", "Actualizacion exitosa ");
						}	
					}else {
						salida.put("mensaje", "El Departamento ya Existe " + obj.getNumeroDepartamento());
					}
				}else {
					salida.put("mensaje", "El ID no existe " + obj.getIdDepartamento());
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
		public ResponseEntity<HashMap<String, Object>> eliminaDepartamento(@PathVariable int id){
			HashMap<String, Object> salida = new HashMap<String, Object>();
			try {		
				Optional<Departamento> optional = service.listaDepartamentoPorId(id);
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
