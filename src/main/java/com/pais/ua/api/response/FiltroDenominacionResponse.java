package com.pais.ua.api.response;

import java.io.Serializable;

public class FiltroDenominacionResponse implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long idDenominacion;
	private Long idClase;
	private Long idGrupo;
	private String cidCodigo;
	private String cidNombre;
	
	public Long getIdDenominacion() {
		return idDenominacion;
	}
	public void setIdDenominacion(Long idDenominacion) {
		this.idDenominacion = idDenominacion;
	}
	public Long getIdClase() {
		return idClase;
	}
	public void setIdClase(Long idClase) {
		this.idClase = idClase;
	}
	public Long getIdGrupo() {
		return idGrupo;
	}
	public void setIdGrupo(Long idGrupo) {
		this.idGrupo = idGrupo;
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
