package com.pais.ua.api.response;

import java.io.Serializable;

public class ClaseResponse implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Long idClase;
	private String nomClase;
	
	public Long getIdClase() {
		return idClase;
	}
	public void setIdClase(Long idClase) {
		this.idClase = idClase;
	}
	public String getNomClase() {
		return nomClase;
	}
	public void setNomClase(String nomClase) {
		this.nomClase = nomClase;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	

}
