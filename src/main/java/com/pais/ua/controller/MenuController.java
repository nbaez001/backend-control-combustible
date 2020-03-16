package com.pais.ua.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pais.ua.bus.MenuBus;
import com.pais.ua.model.seguridad.Menu;
import com.pais.ua.util.Constantes;

import io.swagger.annotations.Api;

@CrossOrigin(origins = { "http://localhost:4200", "http://vulcano.pais.gob.pe", "http://backend.pais.gob.pe", "http://app.pais.gob.pe" })
@RestController
@RequestMapping("/menus")
@Api(value = "Menus")
public class MenuController {
	
	@Autowired
	private MenuBus menuBus;

	@PostMapping("/crear")
	public ResponseEntity<?> crear(@RequestBody Menu menu){
		
		Map<String, Object> response = new HashMap<>();

		try {
			
			menuBus.crear(menu);
			
		} catch(DataAccessException e) {
			
			response.put(Constantes.MENSAJE, "Error al realizar insert en la base de datos!");
			response.put(Constantes.ERROR, e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put(Constantes.MENSAJE, "El menu ha sido creado con exito!");

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
				
	}
	
	@PutMapping("/actualizar")
	public ResponseEntity<?> actualizar(@RequestBody Menu menu){
		
		Map<String, Object> response = new HashMap<>();

		try {
			
			menuBus.actualizar(menu);
			
		} catch(DataAccessException e) {
			
			response.put(Constantes.MENSAJE, "Error al realizar update en la base de datos!");
			response.put(Constantes.ERROR, e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put(Constantes.MENSAJE, "El menu ha sido actualizado con exito!");
				
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
				
	}
	
	@DeleteMapping("/eliminar/{idMenu}")
	public ResponseEntity<?> eliminar(@PathVariable int idMenu){
		
		Map<String, Object> response = new HashMap<>();

		try {

			menuBus.eliminar(idMenu);

		} catch (DataAccessException e) {
			response.put(Constantes.MENSAJE, "Error al realizar delete en la base de datos!");
			response.put(Constantes.ERROR, e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put(Constantes.MENSAJE, "El menu ha sido eliminado con exito!");
		
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
				
	}

}
