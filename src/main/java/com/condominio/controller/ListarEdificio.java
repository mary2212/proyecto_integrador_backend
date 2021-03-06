package com.condominio.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.condominio.entity.Edificio;
import com.condominio.service.EdificioService;

@RestController
@RequestMapping("/rest/listar")
@CrossOrigin(origins = "http://localhost:4200")
public class ListarEdificio {
	
	@Autowired
	private EdificioService service;
	
	@GetMapping("/edificio")
	@ResponseBody
	public ResponseEntity<List<Edificio>> listar(){
		List<Edificio> lista = service.listaEdificio();
		return ResponseEntity.ok(lista);
	}
	

}
