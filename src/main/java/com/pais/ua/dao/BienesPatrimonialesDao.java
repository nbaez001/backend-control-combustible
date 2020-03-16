package com.pais.ua.dao;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

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

public interface BienesPatrimonialesDao {
	ApiOutResponse listarUnidadTerritorial();
	ApiOutResponse listarPlataformasPorUnidadTerritorial(int uTerri);
	ApiOutResponse listarDenominaBienPorPlataforma(DenominacionBienPorPlataformaRequest objeto);
	ApiOutResponse buscarvehiculo(int id_ut,int id_pla,int id_deno,String fec_ini,String fec_fin);
	ApiOutResponse buscarVehiculoPost(BuscaVehiculoRequest objeto);
	ApiOutResponse buscarAtcloEmgciaVehiculo(BienPatrimonialRequest objeto);
	ApiOutResponse grabarAtcloEmgciaXVehiculo(List<AtcloEmgciaXVehiculoRequest> objeto);
	ApiOutResponse listarPuesto();
	ApiOutResponse buscarEmpleadoPuestoParaConductor(EmpleadoPuestoRequest objeto);
	ApiOutResponse listarConductorVehiculo(ConductorVehiculoRequest objeto);
	ApiOutResponse grabarConductorBrevete(ConductorBreveteRequest objeto);
	ApiOutResponse actualizarConductorBrevete(ConductorBreveteRequest objeto);
	ApiOutResponse eliminarConductorBrevete(EliminaConductorBreveteRequest objeto);
	ApiOutResponse listarPerfilUsuarioModulo(PerfilUsuarioModuloRequest objeto);
	ApiOutResponse modificarRenovarBrevete(BreveteRequest objeto);
	ApiOutResponse filtrarDenominacion(FiltroDenominacionRequest objeto);
	ApiOutResponse listarTipoLubricante();
	ApiOutResponse listarLubricanteVehiculo(TipoLbcteVhcloRequest objeto);
	ApiOutResponse listarClase();
	ApiOutResponse listarGrupo();
	ApiOutResponse buscarEmpleadoPuesto(BuscaEmpleadoRequest objeto);
	//ApiOutResponse buscarGrupoElectrogeno(BuscaGElectrogenoRequest objeto);
}
