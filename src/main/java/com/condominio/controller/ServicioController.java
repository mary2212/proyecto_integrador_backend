package com.condominio.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.condominio.entity.Servicio;
import com.condominio.service.ServicioService;

@RestController
@RequestMapping("/rest/servicio")
@CrossOrigin(origins = "http://localhost:4200")
public class ServicioController  {

	
	@Autowired
	private ServicioService service;
	
	
	@GetMapping("/listar")
	@ResponseBody
	public ResponseEntity<List<Servicio>> listar(){
		List<Servicio> lista = service.listaServicio();
		return ResponseEntity.ok(lista);
	}
}
