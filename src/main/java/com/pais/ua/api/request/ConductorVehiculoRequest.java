package com.pais.ua.api.request;

import java.io.Serializable;

public class ConductorVehiculoRequest implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Long idVehiculo;

	public Long getIdVehiculo() {
		return idVehiculo;
	}

	public void setIdVehiculo(Long idVehiculo) {
		this.idVehiculo = idVehiculo;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	

}
