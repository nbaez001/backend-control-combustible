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

import com.pais.ua.bus.BienesPatrimonialesBus;
import com.pais.ua.api.ApiOutResponse;
import com.pais.ua.api.request.AtcloEmgciaXVehiculoRequest;
import com.pais.ua.api.request.BienPatrimonialRequest;
import com.pais.ua.api.request.BreveteRequest;
import com.pais.ua.api.request.BuscaEmpleadoRequest;
import com.pais.ua.api.request.BuscaGElectrogenoRequest;
import com.pais.ua.api.request.BuscaVehiculoRequest;
import com.pais.ua.api.request.ConductorBreveteRequest;
import com.pais.ua.api.request.ConductorVehiculoRequest;
import com.pais.ua.api.request.DenominacionBienPorPlataformaRequest;
import com.pais.ua.api.request.EliminaConductorBreveteRequest;
import com.pais.ua.api.request.EmpleadoPuestoRequest;
import com.pais.ua.api.request.FiltroDenominacionRequest;
import com.pais.ua.api.request.PerfilUsuarioModuloRequest;
import com.pais.ua.api.request.TipoLbcteVhcloRequest;

import io.swagger.annotations.Api;

@CrossOrigin(origins = { "http://localhost:4200", "http://vulcano.pais.gob.pe", "http://backend.pais.gob.pe", "http://app.pais.gob.pe" })
@RestController
@RequestMapping("/bienesPatrimoniales")
@Api(value = "Proyectos")
public class BienesPatrimonialesController {
	
	@Autowired
	private BienesPatrimonialesBus bienesPatrimonialesBus;

	@GetMapping("/listarUnidadTerritorial") // PARA COMBO UNIDAD TERRITORIAL
	public ApiOutResponse listarUnidadTerritorial() {
		return bienesPatrimonialesBus.listarUnidadTerritorial();
	}

	@GetMapping("/listarPlataformasPorUnidadTerritorial/{uTerri}") // PARA COMBO DE PLATAFORMAS POR UNIDAD TERRITORIAL
	public ApiOutResponse listarPlataformasPorUnidadTerritorial(@PathVariable int uTerri) {
		return bienesPatrimonialesBus.listarPlataformasPorUnidadTerritorial(uTerri);
	}
	
	@PostMapping("/listarDenominaBienPorPlataforma") // PARA LISTA DE BIENES PERTENECIENTES A UNA PLATAFORMA U OFICNA TERRITORIAL EN PARTICULAR
	public ApiOutResponse listarDenominaBienPorPlataforma(@RequestBody DenominacionBienPorPlataformaRequest objeto) {
		return bienesPatrimonialesBus.listarDenominaBienPorPlataforma(objeto);
	}
	
	@GetMapping("/buscarvehiculo/{id_ut}/{id_pla}/{id_deno}/{fec_ini}/{fec_fin}") // BOTON BUSCAR DEL MODULO BUSCAR VEHICULO
	public ApiOutResponse buscarvehiculo(@PathVariable int id_ut, @PathVariable int id_pla, @PathVariable int id_deno, @PathVariable String fec_ini,@PathVariable String fec_fin) {
		return bienesPatrimonialesBus.buscarvehiculo(id_ut,id_pla,id_deno,fec_ini,fec_fin);
	}

	@PostMapping("/buscarvehiculo") // BOTON BUSCAR DEL MODULO BUSCAR VEHICULO
	public ApiOutResponse buscarVehiculoPost(@RequestBody BuscaVehiculoRequest objeto) {
		return bienesPatrimonialesBus.buscarVehiculoPost(objeto);
	}
	
	@PostMapping("/buscarAtcloEmgciaXVehiculo") // AL HACER CLIC EN OPCION DE LA GRILLA: DECLARACION DE ARTICULOS DE EMERGENCIA
	public ApiOutResponse buscarAtcloEmgciaXVehiculo(@RequestBody BienPatrimonialRequest objeto) {
		return bienesPatrimonialesBus.buscarAtcloEmgciaVehiculo(objeto);
	}
	
	@PostMapping("/grabarAtcloEmgciaXVehiculo") // BOTON GUARDAR DE LA DELARACION DE ARTICULOS DE EMERGENCIA
	public ApiOutResponse grabarAtcloEmgciaXVehiculo(@RequestBody List<AtcloEmgciaXVehiculoRequest> objeto, HttpServletRequest request) {
		return bienesPatrimonialesBus.grabarAtcloEmgciaXVehiculo(objeto,request);
	}
	
	@PostMapping("/listarPuesto") // PARA COMBO PUESTO
	public ApiOutResponse listarPuesto() {
		return bienesPatrimonialesBus.listarPuesto();
	}
	
	@PostMapping("/buscarEmpleadoPuestoParaConductor") // BUSCA EMPLEADOS POR FILTRO DE NOMBRE Y/O APELLIDOS PARA UN PUESTO Y UNIDAD TERRITORIAL DETERMINADO
	public ApiOutResponse buscarEmpleadoPuestoParaConductor(@RequestBody EmpleadoPuestoRequest objeto) {
		return bienesPatrimonialesBus.buscarEmpleadoPuestoParaConductor(objeto);
	}
	
	@PostMapping("/listarConductorVehiculo") // LISTA QUE MUESTRA EL HISTORICO DE CONDUCTORES
	public ApiOutResponse listarConductorVehiculo(@RequestBody ConductorVehiculoRequest objeto) {
		return bienesPatrimonialesBus.listarConductorVehiculo(objeto);
	}
	
	@PostMapping("/grabarActualizarConductorBrevete") // GRABA NUEVO Y/O ACTUALIZA EL REGISTRO DE CONDUCTOR Y SU BREVETE
	public ApiOutResponse grabarConductorBrevete(@RequestBody ConductorBreveteRequest objeto, HttpServletRequest request) {
		return bienesPatrimonialesBus.grabarConductorBrevete(objeto,request);
	}
	
	@PostMapping("/eliminarConductorBrevete") // GRABA NUEVO Y/O ACTUALIZA EL REGISTRO DE CONDUCTOR Y SU BREVETE
	public ApiOutResponse eliminarConductorBrevete(@RequestBody EliminaConductorBreveteRequest objeto, HttpServletRequest request) {
		return bienesPatrimonialesBus.eliminarConductorBrevete(objeto,request);
	}
	
	@PostMapping("/listarPerfilUsuarioModulo") // MUESTRA LOS DATOS DE UN PERFIL ASIGNADO A UN MODULO ESPECIFICO
	public ApiOutResponse listarPerfilUsuarioModulo(@RequestBody PerfilUsuarioModuloRequest objeto) {
		return bienesPatrimonialesBus.listarPerfilUsuarioModulo(objeto);
	}
	
	@PostMapping("/modificarRenovarBrevete") // PERMITE MODIFICAR O RENOVAR UN BREVETE DE UN CONDUCTOR REGISTRADO Y ASIGNADO A UN VEHICULO
	public ApiOutResponse modificarRenovarBrevete(@RequestBody BreveteRequest objeto, HttpServletRequest request) {
		return bienesPatrimonialesBus.modificarRenovarBrevete(objeto,request);
	}
	
	@PostMapping("/filtrarDenominacion") // FILTRA LA LISTA DE DENOMINACIONES ACTIVAS POR UN TEXTO DETERMINADO
	public ApiOutResponse filtrarDenominacion(@RequestBody FiltroDenominacionRequest objeto) {
		return bienesPatrimonialesBus.filtrarDenominacion(objeto);
	}
	
	@PostMapping("/listarTipoLubricante") // PARA COMBO TIPO DE LUBRICANTE
	public ApiOutResponse listarTipoLubricante() {
		return bienesPatrimonialesBus.listarTipoLubricante();
	}
	
	@PostMapping("/listarLubricanteVehiculo") // LISTA EL HISTORICO DEL LUBRICANTE PREVENTVO REGISTRADO PARA UN VEHICULO
	public ApiOutResponse listarLubricanteVehiculo(@RequestBody TipoLbcteVhcloRequest objeto) {
		return bienesPatrimonialesBus.listarLubricanteVehiculo(objeto);
	}
	
	@PostMapping("/listarClase") // LISTA PARA EL COMBO DE CLASE
	public ApiOutResponse listarClase() {
		return bienesPatrimonialesBus.listarClase();
	}

	@PostMapping("/listarGrupo") // LISTA PARA EL COMBO DE GRUPO
	public ApiOutResponse listarGrupo() {
		return bienesPatrimonialesBus.listarGrupo();
	}
	
	@PostMapping("/buscarEmpleadoPuesto") // LISTA PARA EL COMBO DE GRUPO
	public ApiOutResponse buscarEmpleadoPuesto(@RequestBody BuscaEmpleadoRequest objeto) {
		return bienesPatrimonialesBus.buscarEmpleadoPuesto(objeto);
	}
	
	/*
	@PostMapping("/buscarGrupoElectrogeno") // LISTA LOS GRUPOS ELECTROGENOS DE UNA PLATAFORMA Y/O UNIDAD TERRITORIAL
	public ApiOutResponse buscarGrupoElectrogeno(@RequestBody BuscaGElectrogenoRequest objeto) {
		return bienesPatrimonialesBus.buscarGrupoElectrogeno(objeto);
	}
	*/
}

