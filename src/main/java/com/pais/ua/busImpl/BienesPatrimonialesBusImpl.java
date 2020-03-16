package com.pais.ua.busImpl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pais.ua.bus.BienesPatrimonialesBus;
import com.pais.ua.dao.BienesPatrimonialesDao;
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
import com.pais.ua.util.Constantes;
import com.pais.ua.util.Util;

@Service
public class BienesPatrimonialesBusImpl implements BienesPatrimonialesBus {
	
	@Autowired
	private BienesPatrimonialesDao bienesPatrimonialesDao;

	@Override
	public ApiOutResponse listarUnidadTerritorial() {
		ApiOutResponse rpta = new ApiOutResponse();
		
		System.out.println("buss ");
		
		try {
				rpta = bienesPatrimonialesDao.listarUnidadTerritorial();
		} catch (Exception e) {
			//mensajeExcepcion(rpta, e, "listarUnidadTerritorial");
		}	
		return rpta;
	}
	
	@Override
	public ApiOutResponse listarPlataformasPorUnidadTerritorial(int uTerri) {
		ApiOutResponse rpta = new ApiOutResponse();
		
		try {
				rpta = bienesPatrimonialesDao.listarPlataformasPorUnidadTerritorial(uTerri);
		} catch (Exception e) {
			//mensajeExcepcion(rpta, e, "listarPlataformasPorUnidadTerritorial");
		}	
		return rpta;
	}
	
	@Override
	public ApiOutResponse listarDenominaBienPorPlataforma(DenominacionBienPorPlataformaRequest objeto) {
		ApiOutResponse rpta = new ApiOutResponse();
		
		try {
				rpta = bienesPatrimonialesDao.listarDenominaBienPorPlataforma(objeto);
		} catch (Exception e) {
			//mensajeExcepcion(rpta, e, "listarDenominaBienPorPlataforma");
		}	
		return rpta;
	}
	
	@Override
	public ApiOutResponse buscarvehiculo(int id_ut,int id_pla,int id_deno,String fec_ini,String fec_fin) {
		ApiOutResponse rpta = new ApiOutResponse();
		
		try {
			rpta = bienesPatrimonialesDao.buscarvehiculo(id_ut,id_pla,id_deno,fec_ini,fec_fin);
		} catch (Exception e) {
			//mensajeExcepcion(rpta, e, "listarDenominaBienPorPlataforma");
		}	
		return rpta;
	}
	
	@Override
	public ApiOutResponse buscarVehiculoPost(BuscaVehiculoRequest objeto) {
		ApiOutResponse rpta = new ApiOutResponse();
		
		try {
			rpta = bienesPatrimonialesDao.buscarVehiculoPost(objeto);
		} catch (Exception e) {
			//mensajeExcepcion(rpta, e, "listarDenominaBienPorPlataforma");
		}	
		return rpta;
	}

	@Override
	public ApiOutResponse buscarAtcloEmgciaVehiculo(BienPatrimonialRequest objeto) {
		ApiOutResponse rpta = new ApiOutResponse();
		
		try {
			rpta = bienesPatrimonialesDao.buscarAtcloEmgciaVehiculo(objeto);
		} catch (Exception e) {
			//mensajeExcepcion(rpta, e, "listarDenominaBienPorPlataforma");
		}	
		return rpta;
	}
	
	@Override
	public ApiOutResponse grabarAtcloEmgciaXVehiculo(List<AtcloEmgciaXVehiculoRequest> objeto,HttpServletRequest request) {
		ApiOutResponse rpta = new ApiOutResponse();
		
		try {
			for(AtcloEmgciaXVehiculoRequest art: objeto) {
				art.setTxtIpmaqReg(Util.getClientIp(request));
			}
			rpta = bienesPatrimonialesDao.grabarAtcloEmgciaXVehiculo(objeto);
		} catch (Exception e) {
			//mensajeExcepcion(rpta, e, "listarDenominaBienPorPlataforma");
		}	
		return rpta;
	}
	
	@Override
	public ApiOutResponse listarPuesto() {
		ApiOutResponse rpta = new ApiOutResponse();
		
		try {
			rpta = bienesPatrimonialesDao.listarPuesto();
		} catch (Exception e) {
			//mensajeExcepcion(rpta, e, "listarDenominaBienPorPlataforma");
		}	
		return rpta;
	}
	
	@Override
	public ApiOutResponse buscarEmpleadoPuestoParaConductor(EmpleadoPuestoRequest objeto) {
		ApiOutResponse rpta = new ApiOutResponse();
		
		try {
			rpta = bienesPatrimonialesDao.buscarEmpleadoPuestoParaConductor(objeto);
		} catch (Exception e) {
			//mensajeExcepcion(rpta, e, "listarDenominaBienPorPlataforma");
		}	
		return rpta;
	}
	
	
	@Override
	public ApiOutResponse listarConductorVehiculo(ConductorVehiculoRequest objeto) {
		ApiOutResponse rpta = new ApiOutResponse();
		
		try {
			rpta = bienesPatrimonialesDao.listarConductorVehiculo(objeto);
		} catch (Exception e) {
			//mensajeExcepcion(rpta, e, "listarDenominaBienPorPlataforma");
		}	
		return rpta;
	}
	
	@Override
	public ApiOutResponse grabarConductorBrevete(ConductorBreveteRequest objeto, HttpServletRequest request) {
		ApiOutResponse rpta = new ApiOutResponse();
		
		try {
			objeto.setTxtIpmaqReg(Util.getClientIp(request));
			if (objeto.getIdConductor() == 0) {
				rpta = bienesPatrimonialesDao.grabarConductorBrevete(objeto);
			}else {
				rpta = bienesPatrimonialesDao.actualizarConductorBrevete(objeto);
			}
		} catch (Exception e) {
			//mensajeExcepcion(rpta, e, "listarDenominaBienPorPlataforma");
		}	
		return rpta;
	}
	
	
	@Override
	public ApiOutResponse eliminarConductorBrevete(EliminaConductorBreveteRequest objeto, HttpServletRequest request) {
		ApiOutResponse rpta = new ApiOutResponse();
		
		try {
			objeto.setTxtIpmaqReg(Util.getClientIp(request));
			rpta = bienesPatrimonialesDao.eliminarConductorBrevete(objeto);
		} catch (Exception e) {
			//mensajeExcepcion(rpta, e, "listarDenominaBienPorPlataforma");
		}	
		return rpta;
	}
	
	@Override
	public ApiOutResponse listarPerfilUsuarioModulo(PerfilUsuarioModuloRequest objeto) {
		ApiOutResponse rpta = new ApiOutResponse();
		
		try {
			rpta = bienesPatrimonialesDao.listarPerfilUsuarioModulo(objeto);
		} catch (Exception e) {
			//mensajeExcepcion(rpta, e, "listarDenominaBienPorPlataforma");
		}	
		return rpta;
	}
	
	@Override
	public ApiOutResponse modificarRenovarBrevete(BreveteRequest objeto, HttpServletRequest request) {
		ApiOutResponse rpta = new ApiOutResponse();
		
		try {
			objeto.setTxtIpmaqReg(Util.getClientIp(request));
			rpta = bienesPatrimonialesDao.modificarRenovarBrevete(objeto);
		} catch (Exception e) {
			//mensajeExcepcion(rpta, e, "listarDenominaBienPorPlataforma");
		}	
		return rpta;
	}	
	
	@Override
	public ApiOutResponse filtrarDenominacion(FiltroDenominacionRequest objeto) {
		ApiOutResponse rpta = new ApiOutResponse();
		
		try {
			rpta = bienesPatrimonialesDao.filtrarDenominacion(objeto);
		} catch (Exception e) {
			//mensajeExcepcion(rpta, e, "listarDenominaBienPorPlataforma");
		}	
		return rpta;
	}
	
	@Override
	public ApiOutResponse listarTipoLubricante() {
		ApiOutResponse rpta = new ApiOutResponse();
		
		try {
			rpta = bienesPatrimonialesDao.listarTipoLubricante();
		} catch (Exception e) {
			//mensajeExcepcion(rpta, e, "listarDenominaBienPorPlataforma");
		}	
		return rpta;
	}
	
	@Override
	public ApiOutResponse listarLubricanteVehiculo(TipoLbcteVhcloRequest objeto) {
		ApiOutResponse rpta = new ApiOutResponse();
		
		try {
			rpta = bienesPatrimonialesDao.listarLubricanteVehiculo(objeto);
		} catch (Exception e) {
			//mensajeExcepcion(rpta, e, "listarDenominaBienPorPlataforma");
		}	
		return rpta;
	}
	
	@Override
	public ApiOutResponse listarClase() {
		ApiOutResponse rpta = new ApiOutResponse();
		
		try {
			rpta = bienesPatrimonialesDao.listarClase();
		} catch (Exception e) {
			//mensajeExcepcion(rpta, e, "listarDenominaBienPorPlataforma");
		}	
		return rpta;
	}
	
	@Override
	public ApiOutResponse listarGrupo() {
		ApiOutResponse rpta = new ApiOutResponse();
		
		try {
			rpta = bienesPatrimonialesDao.listarGrupo();
		} catch (Exception e) {
			//mensajeExcepcion(rpta, e, "listarDenominaBienPorPlataforma");
		}	
		return rpta;
	}
	
	
	@Override
	public ApiOutResponse buscarEmpleadoPuesto(BuscaEmpleadoRequest objeto) {
		ApiOutResponse rpta = new ApiOutResponse();
		
		try {
			rpta = bienesPatrimonialesDao.buscarEmpleadoPuesto(objeto);
		} catch (Exception e) {
			//mensajeExcepcion(rpta, e, "listarDenominaBienPorPlataforma");
		}	
		return rpta;
	}
	
	/*
	@Override
	public ApiOutResponse buscarGrupoElectrogeno(BuscaGElectrogenoRequest objeto) {
		ApiOutResponse rpta = new ApiOutResponse();
		
		try {
				rpta = bienesPatrimonialesDao.buscarGrupoElectrogeno(objeto);
		} catch (Exception e) {
			//mensajeExcepcion(rpta, e, "listarDenominaBienPorPlataforma");
		}	
		return rpta;
	}*/
}
