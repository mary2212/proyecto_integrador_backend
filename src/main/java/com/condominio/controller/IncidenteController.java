package com.condominio.controller;

import java.util.Date;
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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.condominio.entity.Departamento;
import com.condominio.entity.Edificio;
import com.condominio.entity.Incidente;
import com.condominio.service.DepartamentoService;
import com.condominio.service.EdificioService;
import com.condominio.service.IncidenteService;

@RestController
@RequestMapping("/rest/incidente")
@CrossOrigin(origins = "http://localhost:4200")
public class IncidenteController {
	
	//comentario 5
	private Logger log = LoggerFactory.getLogger(IncidenteController.class);
	
	@Autowired
	private IncidenteService service;
	
	@Autowired
	private EdificioService edificioservice;
	
	@Autowired
	private DepartamentoService departamentoservice;
	
	@GetMapping("/listar")
	@ResponseBody
	public ResponseEntity<List<Incidente>> listar(){
		List<Incidente> lista = service.listaIncidente();
		return ResponseEntity.ok(lista);
	}
	
	@GetMapping("/listarPorEdificio")
	@ResponseBody
	public ResponseEntity<List<Edificio>> listarPorEdificio(){
		List<Edificio> listaEdificio = edificioservice.listaEdificio();
		return ResponseEntity.ok(listaEdificio);
	}
	
	@GetMapping("/listarDepartamento")
	@ResponseBody
	public ResponseEntity<List<Departamento>> listardepartamento(){
		List<Departamento> lista = departamentoservice.listaDepartamento();
		return ResponseEntity.ok(lista);
	}
	
	@GetMapping("/listaIncidentePorEstado/{est}")
	@ResponseBody
	public ResponseEntity<List<Incidente>> listaIncidentePorDescripcion(@PathVariable("est") String est){
		log.info("==> listaPropietarioPorNombre ==> est : " + est);
		
		List<Incidente> lista = null;
		try {
			if(est.equals("todos")) {
				lista = service.listaIncidente();
			}else {
				lista = service.listaIncidetePorEstado("%"+est+"%");
			}
		} catch (Exception e) {

		}
		return ResponseEntity.ok(lista);
	}	
	
	@GetMapping("/listaIncidenteConParametros")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> listaIncidenteEdificioDepartamento(
			@RequestParam(name= "idEdificio", required = false, defaultValue = "-1")int idEdificio,
			@RequestParam(name= "idDepartamento", required = false, defaultValue = "-1")int idDepartamento){
		Map<String, Object> salida = new HashMap<>();
		try {			
			List<Incidente> lista = service.listaIncidentePorEdificioDepartamento(idEdificio, idDepartamento);
			if(CollectionUtils.isEmpty(lista)) {
				salida.put("mensaje", "No existen datos para mostrar");
			}else {
				salida.put("lista", lista);
				salida.put("mensaje", "Existen " + lista.size() + " elementos para mostrar");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return ResponseEntity.ok(salida);
	}
	
	@GetMapping("/listaIncidenteConParametros2")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> listaIncidenteEdificioDepartamento2(
			@RequestParam(name= "idEdificio", required = false, defaultValue = "-1")int idEdificio,
			@RequestParam(name= "idDepartamento", required = false, defaultValue = "-1")int idDepartamento,
			@RequestParam(name= "estado", required = false, defaultValue = "")String estado){
		Map<String, Object> salida = new HashMap<>();
		try {			
			List<Incidente> lista = service.listaIncidentePorEstadoEdificioDepartamento(idEdificio, idDepartamento, "%"+estado+"%");
			if(CollectionUtils.isEmpty(lista)) {
				salida.put("mensaje", "No existen datos para mostrar");
			}else {
				salida.put("lista", lista);
				salida.put("mensaje", "Existen " + lista.size() + " elementos para mostrar");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return ResponseEntity.ok(salida);
	}

	//Registra
	@PostMapping("/registrar")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> registraIncidente(@RequestBody Incidente obj){
		Map<String, Object> salida = new HashMap<>();
		try {
			obj.setIdIncidente(0);
			obj.setFechaIncidente(new Date());
			obj.setEstado("No Atendido");
			Incidente objSalida = service.insertaActualizaIncidente(obj);
			if(objSalida == null) {
				salida.put("mensaje", "Error en el Registro ");
			}else {
				salida.put("mensaje", "Registro Exitoso ");
			}	
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return ResponseEntity.ok(salida);
	}
	
	@PutMapping("/actualiza")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> actualizaaIncidente(@RequestBody Incidente obj){
		Map<String, Object> salida = new HashMap<>();
		try {
			Incidente objSalida = service.insertaActualizaIncidente(obj);
			if(objSalida == null) {
				salida.put("mensaje", "Error al Actualizar ");
			}else {
				salida.put("mensaje", "Actualizacion Exitosa ");
			}	
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return ResponseEntity.ok(salida);
	}
	
	@PutMapping("/actualiza2")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> actualizaaIncidente2(@RequestBody Incidente obj){
		Map<String, Object> salida = new HashMap<>();
		try {
			obj.setEstado("Atendido");
			Incidente objSalida = service.insertaActualizaIncidente(obj);
			if(objSalida == null) {
				salida.put("mensaje", "Error al Actualizar ");
			}else {
				salida.put("mensaje", "Actualizacion Exitosa ");
			}	
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return ResponseEntity.ok(salida);
	}
	
	//Elimina
			@DeleteMapping("/{id}")
			@ResponseBody
			public ResponseEntity<HashMap<String, Object>> eliminaIncedente(@PathVariable int id){
				HashMap<String, Object> salida = new HashMap<String, Object>();
				try {		
					Optional<Incidente> optional = service.listaIncidentePorId(id);
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
