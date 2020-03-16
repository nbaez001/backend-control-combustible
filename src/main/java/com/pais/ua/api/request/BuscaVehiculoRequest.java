package com.pais.ua.api.request;

import java.io.Serializable;

public class BuscaVehiculoRequest implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private Long idUTerritorial;
	private Long idPlataforma;
	private Long idDenominacion;
	private String fecIni;
	private String fecFin;
	
	public Long getIdUTerritorial() {
		return idUTerritorial;
	}
	public void setIdUTerritorial(Long idUTerritorial) {
		this.idUTerritorial = idUTerritorial;
	}
	public Long getIdPlataforma() {
		return idPlataforma;
	}
	public void setIdPlataforma(Long idPlataforma) {
		this.idPlataforma = idPlataforma;
	}
	public Long getIdDenominacion() {
		return idDenominacion;
	}
	public void setIdDenominacion(Long idDenominacion) {
		this.idDenominacion = idDenominacion;
	}
	public String getFecIni() {
		return fecIni;
	}
	public void setFecIni(String fecIni) {
		this.fecIni = fecIni;
	}
	public String getFecFin() {
		return fecFin;
	}
	public void setFecFin(String fecFin) {
		this.fecFin = fecFin;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
