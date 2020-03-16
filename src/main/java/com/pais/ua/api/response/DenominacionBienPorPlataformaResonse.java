package com.pais.ua.api.response;

import java.io.Serializable;

public class DenominacionBienPorPlataformaResonse implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long idDenominacion;
	private String nomDenominacion;
	
	public Long getIdDenominacion() {
		return idDenominacion;
	}
	public void setIdDenominacion(Long idDenominacion) {
		this.idDenominacion = idDenominacion;
	}
	public String getNomDenominacion() {
		return nomDenominacion;
	}
	public void setNomDenominacion(String nomDenominacion) {
		this.nomDenominacion = nomDenominacion;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
