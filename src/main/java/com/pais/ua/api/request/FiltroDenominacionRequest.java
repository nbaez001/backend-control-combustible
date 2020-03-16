package com.pais.ua.api.request;

import java.io.Serializable;

public class FiltroDenominacionRequest implements Serializable {
	private static final long serialVersionUID = 1L;
	
	String idCodigo;
	String fidClase;
	String fidGrupo;
	String cidCodigo;
	String cidNombre;
	
	public String getIdCodigo() {
		return idCodigo;
	}
	public void setIdCodigo(String idCodigo) {
		this.idCodigo = idCodigo;
	}
	public String getFidClase() {
		return fidClase;
	}
	public void setFidClase(String fidClase) {
		this.fidClase = fidClase;
	}
	public String getFidGrupo() {
		return fidGrupo;
	}
	public void setFidGrupo(String fidGrupo) {
		this.fidGrupo = fidGrupo;
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
