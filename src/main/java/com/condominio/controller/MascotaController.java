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

import com.condominio.entity.Mascota;
import com.condominio.service.MascotaService;

@RestController
@RequestMapping("/rest/mascota")
@CrossOrigin(origins = "http://localhost:4200")
public class MascotaController {

	@Autowired
	private MascotaService service;
	
	//Lista
	@GetMapping
	@ResponseBody
	public ResponseEntity<List<Mascota>> listar(){
		List<Mascota> lista = service.listaMascota();
		return ResponseEntity.ok(lista);
	}
	
	    //Registra
		@PostMapping
		@ResponseBody
		public ResponseEntity<HashMap<String, Object>> insertaMascota(@RequestBody Mascota obj){
			HashMap<String, Object> salida = new HashMap<String, Object>();
			try {
				List<Mascota> listaMascota = service.listaMascotaPorDescripcion(obj.getDescripcion());
				if(CollectionUtils.isEmpty(listaMascota)) {
					obj.setIdMascota(0);
					Mascota objSalida = service.insertaActualizaMacota(obj);
					if(objSalida == null) {
						salida.put("mensaje", "Error en el registro ");
					}else {
						salida.put("mensaje", "Registro exitoso ");
					}				
				}else {
					salida.put("mensaje", "La Descripcion ya existe " + obj.getDescripcion());
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
		public ResponseEntity<HashMap<String, Object>> actualizaMascota(@RequestBody Mascota obj){
			HashMap<String, Object> salida = new HashMap<String, Object>();
			try {		
				Optional<Mascota> optional = service.listaMascotaPorId(obj.getIdMascota());
				if(optional.isPresent()) {
					List<Mascota> listaMascota = service.listaMascotaPorDescripcionDiferenteDelMismo(obj.getDescripcion(), obj.getIdMascota());
					if(CollectionUtils.isEmpty(listaMascota)) {
						Mascota objSalida = service.insertaActualizaMacota(obj);
						if(objSalida == null) {
							salida.put("mensaje", "Error en actualizar ");
						}else {
							salida.put("mensaje", "Actualizacion exitosa ");
						}	
					}else {
						salida.put("mensaje", "La Mascota ya Existe " + obj.getDescripcion());
					}
				}else {
					salida.put("mensaje", "El ID no existe " + obj.getIdMascota());
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
		public ResponseEntity<HashMap<String, Object>> eliminaMascota(@PathVariable int id){
			HashMap<String, Object> salida = new HashMap<String, Object>();
			try {		
				Optional<Mascota> optional = service.listaMascotaPorId(id);
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
