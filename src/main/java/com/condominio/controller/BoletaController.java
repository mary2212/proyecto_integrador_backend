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

import com.condominio.entity.Boleta;
import com.condominio.entity.Incidente;
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
			List<Boleta> listaBoleta = service.listaBoletaPorUsuario(obj.getUsuario().getIdUsuario());
			if(CollectionUtils.isEmpty(listaBoleta)) {
				obj.setIdBoleta(0);
				obj.setEstado("NO PAGADO");
				

				Boleta objSalida = service.insertaActualizaBoleta(obj);
				if(objSalida == null) {
					salida.put("mensaje", "Error en el registro ");
				}else {
					salida.put("mensaje", "Registro exitoso ");
				}				
			}else {
				salida.put("mensaje", "El usuario ya existe " + obj.getUsuario().getIdUsuario());
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
					List<Boleta> listaBoleta = service.listaBoletaPorEstadoDiferenteDelMismo(obj.getEstado(), obj.getIdBoleta());
					if(CollectionUtils.isEmpty(listaBoleta)) {
						Boleta objSalida = service.insertaActualizaBoleta(obj);
						if(objSalida == null) {
							salida.put("mensaje", "Error en actualizar ");
						}else {
							salida.put("mensaje", "Actualizacion exitosa ");
						}	
					}else {
						salida.put("mensaje", "La Boleta ya Existe " + obj.getEstado());
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
	
}
