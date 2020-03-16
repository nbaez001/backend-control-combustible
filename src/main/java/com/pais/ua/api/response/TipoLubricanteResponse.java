package com.pais.ua.api.response;

import java.io.Serializable;

public class TipoLubricanteResponse implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long idCodigo;
	private String cidCodigo;
	private String cidNombre;
	
	public Long getIdCodigo() {
		return idCodigo;
	}
	public void setIdCodigo(Long idCodigo) {
		this.idCodigo = idCodigo;
	}
	public String getCidCodigo() {
		return cidCodigo;
	}
	public void setCidCodigo(String cidCodigo) {
		this.cidCodigo = cidCodigo;
	}
	public String getCidNombre() {
		return cidNombre;
	}
	public void setCidNombre(String cidNombre) {
		this.cidNombre = cidNombre;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
