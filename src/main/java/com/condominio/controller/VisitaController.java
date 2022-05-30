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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
	
	@GetMapping("/listaVisitaPorEstado/{est}")
	@ResponseBody
	public ResponseEntity<List<Visita>> listaVisitaPorEstado(@PathVariable("est") String est){
		log.info("==> listaVisitaPorEstado ==> est : " + est);
		
		List<Visita> lista = null;
		try {
			if(est.equals("todos")) {
				lista = service.listaVisita();
			}else {
				lista = service.listaVisitaPorEstado(est);
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
				obj.setEstado("entrada");
				obj.setFechaEntrada(new Date());

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
	
	//Actualiza
	@PutMapping("/actualizar")
	@ResponseBody
	public ResponseEntity<HashMap<String, Object>> actualizaVisita(@RequestBody Visita obj){
		HashMap<String, Object> salida = new HashMap<String, Object>();
		try {		
			Optional<Visita> optional = service.listaVisitaPorId(obj.getIdVisita());
			if(optional.isPresent()) {
				obj.setEstado("salio");
				List<Visita> listaVisita = service.listaVisitaPorNumeroDiferenteDelMismo(obj.getFechaEntrada(), obj.getIdVisita());
				if(CollectionUtils.isEmpty(listaVisita)) {
					Visita objSalida = service.insertaActualizaVisita(obj);
					if(objSalida == null) {
						salida.put("mensaje", "Error en actualizar ");
					}else {
						salida.put("mensaje", "Actualizacion exitosa ");
					}	
				}else {
					salida.put("mensaje", "La visita ya Existe " + obj.getFechaEntrada());
				}
			}else {
				salida.put("mensaje", "El ID no existe " + obj.getIdVisita());
			}
					
		} catch (Exception e) {
			e.printStackTrace();
			salida.put("mensaje", "Error en la actualizacion " + e.getMessage());
		}		
		return ResponseEntity.ok(salida);
	}
		
}
