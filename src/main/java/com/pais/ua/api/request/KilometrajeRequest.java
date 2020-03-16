package com.pais.ua.api.request;

import java.io.Serializable;
import java.util.Date;

public class KilometrajeRequest implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Long idUnidad;
	private Long idPlataforma;
	private Long idVehiculo;
	private Date fecInicio;
	private Date fecFin;
	
	public Long getIdUnidad() {
		return idUnidad;
	}
	public void setIdUnidad(Long idUnidad) {
		this.idUnidad = idUnidad;
	}
	public Long getIdPlataforma() {
		return idPlataforma;
	}
	public void setIdPlataforma(Long idPlataforma) {
		this.idPlataforma = idPlataforma;
	}
	public Long getIdVehiculo() {
		return idVehiculo;
	}
	public void setIdVehiculo(Long idVehiculo) {
		this.idVehiculo = idVehiculo;
	}
	public Date getFecInicio() {
		return fecInicio;
	}
	public void setFecInicio(Date fecInicio) {
		this.fecInicio = fecInicio;
	}
	public Date getFecFin() {
		return fecFin;
	}
	public void setFecFin(Date fecFin) {
		this.fecFin = fecFin;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	

}
