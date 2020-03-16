package com.pais.ua.api.request;

import java.io.Serializable;

public class BienPatrimonialRequest implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private long idVehiculo;
	private long idDenominacion;
	
	public long getIdVehiculo() {
		return idVehiculo;
	}
	public void setIdVehiculo(long idVehiculo) {
		this.idVehiculo = idVehiculo;
	}
	public long getIdDenominacion() {
		return idDenominacion;
	}
	public void setIdDenominacion(long idDenominacion) {
		this.idDenominacion = idDenominacion;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
