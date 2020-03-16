package com.pais.ua.api.request;

import java.io.Serializable;

public class DenominacionBienPorPlataformaRequest implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long idUTerritorial;
	private Long idPlataforma;
	private String cadGrupo;
	private String cadClase;
	
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
	public String getCadGrupo() {
		return cadGrupo;
	}
	public void setCadGrupo(String cadGrupo) {
		this.cadGrupo = cadGrupo;
	}
	public String getCadClase() {
		return cadClase;
	}
	public void setCadClase(String cadClase) {
		this.cadClase = cadClase;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
