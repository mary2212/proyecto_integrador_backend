package com.condominio.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
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
	@Autowired
	private PropietarioService service;
	
	@GetMapping
	@ResponseBody
	public ResponseEntity <List<Propietario>> listarPropietarios(){
		List<Propietario> lista= service.listaTodos();
		return ResponseEntity.ok(lista);
	}
	

	
	@PostMapping
	@ResponseBody //retorna jason
	public ResponseEntity<HashMap<String, Object>> insertaPropietario(@RequestBody Propietario obj){
		HashMap<String, Object> salida= new HashMap<String,Object>();
		try {
			List<Propietario> lstPropietario = service.listaPropietarioPorDni(obj.getDniPropietario());
			if(CollectionUtils.isEmpty(lstPropietario)) { //si esta vacía 
				obj.setIdPropietario(0);
				Propietario objSalida= service.insertaActualizaPropietario(obj);
				if (objSalida==null) {
					salida.put("mensaje", "Error en registro");
				} else {
					salida.put("mensaje", "Registro Exitoso");
				}
			}else {
				salida.put("mensaje", "El DNI ya existe: " + obj.getDniPropietario());
			}
		} catch (Exception e) {
			e.printStackTrace();
			salida.put("mensaje", "Error en registro" + e.getMessage());
		}
		return ResponseEntity.ok(salida);
	}
	
	@PutMapping
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
	
}
