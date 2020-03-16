package com.pais.ua.busImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pais.ua.api.ApiOutResponse;
import com.pais.ua.api.request.KilometrajeRequest;
import com.pais.ua.bus.ControlCombustibleBus;
import com.pais.ua.dao.ControlCombustibleDao;

@Service
public class ControlCombustibleBusImpl implements ControlCombustibleBus {
	
	@Autowired
	private ControlCombustibleDao controlCombustibleDao;

	@Override
	public ApiOutResponse ListadoKiometraje(KilometrajeRequest objeto) {
		ApiOutResponse rpta = new ApiOutResponse();
		
		System.out.println("buss ");
		
		try {
				rpta = controlCombustibleDao.ListadoKiometraje(objeto);
		} catch (Exception e) {
			//mensajeExcepcion(rpta, e, "listarUnidadTerritorial");
		}	
		return rpta;
	}
}
