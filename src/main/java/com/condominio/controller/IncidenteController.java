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

import com.condominio.entity.Incidente;
import com.condominio.service.IncidenteService;

@RestController
@RequestMapping("/rest/incidente")
@CrossOrigin(origins = "http://localhost:4200")
public class IncidenteController {
	
	private Logger log = LoggerFactory.getLogger(IncidenteController.class);
	
	@Autowired
	private IncidenteService service;
	
	@GetMapping("/listar")
	@ResponseBody
	public ResponseEntity<List<Incidente>> listar(){
		List<Incidente> lista = service.listaIncidente();
		return ResponseEntity.ok(lista);
	}
	
	@GetMapping("/listaIncidentePorDescripcion/{desc}")
	@ResponseBody
	public ResponseEntity<List<Incidente>> listaIncidentePorDescripcion(@PathVariable("desc") String desc){
		log.info("==> listaPropietarioPorNombre ==> est : " + desc);
		
		List<Incidente> lista = null;
		try {
			if(desc.equals("todos")) {
				lista = service.listaIncidente();
			}else {
				lista = service.listaIncidetePorDescripcion(desc);
			}
		} catch (Exception e) {

		}
		return ResponseEntity.ok(lista);
	}	
	
	@GetMapping("/listaIncidenteConParametros")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> listaDocenteEdificioDepartamentoEstado(
			@RequestParam(name= "idEdificio", required = false, defaultValue = "-1")int idEdificio,
			@RequestParam(name= "idDepartamento", required = false, defaultValue = "-1")int idDepartamento,
			@RequestParam(name= "estado", required = true, defaultValue = "1")int estado){
		Map<String, Object> salida = new HashMap<>();
		try {
			List<Incidente> lista = service.listaIncidentePorEdificioDepartamentoEstado(idEdificio,idDepartamento,estado);
			if(CollectionUtils.isEmpty(lista)) {
				salida.put("mensaje", "No existen datos para mostrar");
			}else {
				salida.put("lista", lista);
				salida.put("mensaje", "Existen " + lista.size() + " incidentes para mostrar");
			}
		} catch (Exception e) {
			e.printStackTrace();
			salida.put("mensaje", "Error al listar" + e.getMessage());
		}		
				
		
		return ResponseEntity.ok(salida);
	}
	
	//Registra
	@PostMapping("/registrar")
	@ResponseBody
	public ResponseEntity<HashMap<String, Object>> insertaIncidente(@RequestBody Incidente obj){
		HashMap<String, Object> salida = new HashMap<String, Object>();
		try {
			List<Incidente> listaIncidente = service.listaIncidetePorDescripcion(obj.getDescripcion());
			if(CollectionUtils.isEmpty(listaIncidente)) {
				obj.setIdIncidente(0);
				obj.setFechaIncidente(new Date());

				Incidente objSalida = service.insertaActualizaIncidente(obj);
				if(objSalida == null) {
					salida.put("mensaje", "Error en el registro ");
				}else {
					salida.put("mensaje", "Registro exitoso ");
				}				
			}else {
				salida.put("mensaje", "El Incidente ya existe " + obj.getDescripcion());
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
	public ResponseEntity<HashMap<String, Object>> actualizaIncidente(@RequestBody Incidente obj){
		HashMap<String, Object> salida = new HashMap<String, Object>();
		try {		
			Optional<Incidente> optional = service.listaIncidentePorId(obj.getIdIncidente());
			if(optional.isPresent()) {
				List<Incidente> listaDepartamento = service.listaIncidentePorEstadoDiferenteDelMismo(obj.getEstado(), obj.getIdIncidente());
				if(CollectionUtils.isEmpty(listaDepartamento)) {
					Incidente objSalida = service.insertaActualizaIncidente(obj);
					if(objSalida == null) {
						salida.put("mensaje", "Error en actualizar ");
					}else {
						salida.put("mensaje", "Actualizacion exitosa ");
					}	
				}else {
					salida.put("mensaje", "El Departamento ya Existe " + obj.getEstado());
				}
			}else {
				salida.put("mensaje", "El ID no existe " + obj.getIdIncidente());
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
