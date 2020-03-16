/*
 * @author Jorge Christian Ambrocio Sernaque
 * @since  20/03/2019
 */
package com.pais.ua.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pais.ua.bus.AccesoBus;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@CrossOrigin(origins = { "http://localhost:4200", "http://vulcano.pais.gob.pe", "http://backend.pais.gob.pe", "http://app.pais.gob.pe" })
@RestController
@RequestMapping("/api")
public class AccesoController {
	
	
	@Autowired
	private AccesoBus accesoBus;
	
	@ApiOperation(value = "Status token", tags="seguridad")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Consulta exitosa!"),
	        @ApiResponse(code = 404, message = "El cliente ID no existe en la base de datos!"),
			@ApiResponse(code = 500, message = "Error al realizar la consulta en la base de datos!")
	})
	@GetMapping("/status")
	public boolean status(){
		
		return true;
				
	}
	
}
