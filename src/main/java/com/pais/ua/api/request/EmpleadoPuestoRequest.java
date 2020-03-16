package com.pais.ua.api.request;

import java.io.Serializable;
import java.util.Date;

public class EmpleadoPuestoRequest implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Long idUnidadTerritorial;
	private Long idPuesto;
	private Date fecActual;
	private String nombre;
	private String apPaterno;
	private String apMaterno;
	
	public Long getIdUnidadTerritorial() {
		return idUnidadTerritorial;
	}
	public void setIdUnidadTerritorial(Long idUnidadTerritorial) {
		this.idUnidadTerritorial = idUnidadTerritorial;
	}
	public Long getIdPuesto() {
		return idPuesto;
	}
	public void setIdPuesto(Long idPuesto) {
		this.idPuesto = idPuesto;
	}
	public Date getFecActual() {
		return fecActual;
	}
	public void setFecActual(Date fecActual) {
		this.fecActual = fecActual;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApPaterno() {
		return apPaterno;
	}
	public void setApPaterno(String apPaterno) {
		this.apPaterno = apPaterno;
	}
	public String getApMaterno() {
		return apMaterno;
	}
	public void setApMaterno(String apMaterno) {
		this.apMaterno = apMaterno;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
