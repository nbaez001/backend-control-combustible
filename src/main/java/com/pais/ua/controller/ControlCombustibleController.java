package com.pais.ua.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.pais.ua.bus.ControlCombustibleBus;
import com.pais.ua.api.ApiOutResponse;
import com.pais.ua.api.request.KilometrajeRequest;

import io.swagger.annotations.Api;

@CrossOrigin(origins = { "http://localhost:4200", "http://vulcano.pais.gob.pe", "http://backend.pais.gob.pe", "http://app.pais.gob.pe" })
@RestController
@RequestMapping("/controlCombustible")
@Api(value = "Proyectos")
public class ControlCombustibleController {
	
	@Autowired
	private ControlCombustibleBus controlCombustibleBus;

	@PostMapping("/ListadoKiometraje") // AL HACER CLIC EN OPCION DE LA GRILLA: DECLARACION DE ARTICULOS DE EMERGENCIA
	public ApiOutResponse ListadoKiometraje(@RequestBody KilometrajeRequest objeto) {
		return controlCombustibleBus.ListadoKiometraje(objeto);
	}

}

