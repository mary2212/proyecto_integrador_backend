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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.condominio.entity.Visita;
import com.condominio.service.VisitaService;

@RestController
@RequestMapping("/rest/visita")
@CrossOrigin(origins = "http://localhost:4200")
public class VisitaController {
	
	private Logger log = LoggerFactory.getLogger(VisitaController.class);
	
	@Autowired
	private VisitaService service;
	
	@GetMapping("/listar")
	@ResponseBody
	public ResponseEntity<List<Visita>> listar(){
		List<Visita> lista = service.listaVisita();
		return ResponseEntity.ok(lista);
	}	
	
	@GetMapping("/listaVisitaPorID2/{id}")
	@ResponseBody
	public ResponseEntity<Optional<Visita>> listaVisitaPorid2(@PathVariable("id") int idVisita){
		log.info("==> listaVisitaPorid2 ==> id : " + idVisita);
		
		Optional<Visita> lista = service.listaVisitaPorId(idVisita);
		try {
			if(lista.equals(lista)) {
				lista = service.listaVisitaPorId(idVisita);
			}else {
				lista = service.listaVisitaPorId(idVisita);
			}
		} catch (Exception e) {

		}
		return ResponseEntity.ok(lista);
	}	
	
	@GetMapping("/listaVisitaPorID/{id}")
	@ResponseBody
	public ResponseEntity<Visita> listaVisitaPorVisitante(@PathVariable("id")int idVisita){
		Optional<Visita> optVisita = service.listaVisitaPorId(idVisita);		
		if(optVisita.isPresent()) {
			return ResponseEntity.ok(optVisita.get());
		}else {
			return ResponseEntity.badRequest().build();
		}
	}	
	
	@PostMapping("/registrar")
	@ResponseBody
	public ResponseEntity<HashMap<String, Object>> insertaVisita(@RequestBody Visita obj){
		HashMap<String, Object> salida = new HashMap<String, Object>();
		try {
			List<Visita> listaVisita = service.listaVisitaPorId2(obj.getIdVisita());
			if(CollectionUtils.isEmpty(listaVisita)) {
				obj.setIdVisita(0);
				Visita objSalida = service.insertaActualizaVisita(obj);
				if(objSalida == null) {
					salida.put("mensaje", "Error en el registro ");
				}else {
					salida.put("mensaje", "Registro exitoso ");
				}				
			}else {
				salida.put("mensaje", "El Número ya existe " + obj.getIdVisita());
			}			
		} catch (Exception e) {
			e.printStackTrace();
			salida.put("mensaje", "Error en el registro " + e.getMessage());
		}		
		return ResponseEntity.ok(salida);
	}
	
	
	
	
	
	
}
