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

import com.condominio.entity.Usuario;
import com.condominio.service.UsuarioService;

@RestController
@RequestMapping("/rest/usuario")
@CrossOrigin(origins = "http://localhost:4200")
public class UsuarioController {	
	
	private Logger log = LoggerFactory.getLogger(UsuarioController.class);
	
	@Autowired
	private UsuarioService service;
	
	@GetMapping("/listar")
	@ResponseBody
	public ResponseEntity<List<Usuario>> listar(){
		List<Usuario> lista = service.listaUsuario();
		return ResponseEntity.ok(lista);
	}
	
	//ListarPorLogin
	@GetMapping("/listaUsuarioPorlogin/{login}")
	@ResponseBody
	public ResponseEntity<List<Usuario>> listaUsuarioPorlogin(@PathVariable("login") String login){
		log.info("==> listaUsuarioPorlogin ==> nom : " + login);
		
		List<Usuario> lista = null;
		try {
			if(login.equals("todos")) {
				lista = service.listaUsuario();
			}else {
				lista = service.listaPorLogin(login);
			}
		} catch (Exception e) {

		}
		return ResponseEntity.ok(lista);
	}
	
	//Registra
	@PostMapping("/registrar")
	@ResponseBody
	public ResponseEntity<HashMap<String, Object>> insertaUsuario(@RequestBody Usuario obj){
		HashMap<String, Object> salida = new HashMap<String, Object>();
		try {
			List<Usuario> listaUsuario = service.listaUsuarioPorNombre(obj.getNombre());
			if(CollectionUtils.isEmpty(listaUsuario)) {
				obj.setIdUsuario(0);
				Usuario objSalida = service.insertaActualizaUsuario(obj);
				if(objSalida == null) {
					salida.put("mensaje", "Error en el registro ");
				}else {
					salida.put("mensaje", "Registro exitoso ");
				}				
			}else {
				salida.put("mensaje", "El nombre ya existe " + obj.getNombre());
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
	public ResponseEntity<HashMap<String, Object>> actualizaUsuario(@RequestBody Usuario obj){
		HashMap<String, Object> salida = new HashMap<String, Object>();
		try {		
			Optional<Usuario> optional = service.listaUsuarioPorId(obj.getIdUsuario());
			if(optional.isPresent()) {
				List<Usuario> listaUsuario = service.listaPorNombreDiferenteSiMismo(obj.getNombre(), obj.getIdUsuario());
				if(CollectionUtils.isEmpty(listaUsuario)) {
					Usuario objSalida = service. insertaActualizaUsuario(obj);
					if(objSalida == null) {
						salida.put("mensaje", "Error en actualizar ");
					}else {
						salida.put("mensaje", "Actualizacion exitosa ");
					}	
				}else {
					salida.put("mensaje", "El Nombre ya Existe " + obj.getNombre());
				}
			}else {
				salida.put("mensaje", "El ID no existe " + obj.getIdUsuario());
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
	public ResponseEntity<HashMap<String, Object>> eliminaUsuario(@PathVariable int id){
		HashMap<String, Object> salida = new HashMap<String, Object>();
		try {		
			Optional<Usuario> optional = service.listaUsuarioPorId(id);
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
