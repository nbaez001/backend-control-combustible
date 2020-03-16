package com.pais.ua.api.response;

import java.io.Serializable;

public class PerfilUsuarioModuloResponse implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Long idPerfil;
	private String nombrePerfil;
	private Long idUterritorial;
	private String nombreUTerritorial;
	private Long idPlataforma;
	private String nombrePlataforma;
	
	public Long getIdPerfil() {
		return idPerfil;
	}
	public void setIdPerfil(Long idPerfil) {
		this.idPerfil = idPerfil;
	}
	public String getNombrePerfil() {
		return nombrePerfil;
	}
	public void setNombrePerfil(String nombrePerfil) {
		this.nombrePerfil = nombrePerfil;
	}
	public Long getIdUterritorial() {
		return idUterritorial;
	}
	public void setIdUterritorial(Long idUterritorial) {
		this.idUterritorial = idUterritorial;
	}
	public String getNombreUTerritorial() {
		return nombreUTerritorial;
	}
	public void setNombreUTerritorial(String nombreUTerritorial) {
		this.nombreUTerritorial = nombreUTerritorial;
	}
	public Long getIdPlataforma() {
		return idPlataforma;
	}
	public void setIdPlataforma(Long idPlataforma) {
		this.idPlataforma = idPlataforma;
	}
	public String getNombrePlataforma() {
		return nombrePlataforma;
	}
	public void setNombrePlataforma(String nombrePlataforma) {
		this.nombrePlataforma = nombrePlataforma;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
