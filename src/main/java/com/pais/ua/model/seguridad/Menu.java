package com.pais.ua.model.seguridad;

public class Menu {
	
	private Long idCodigo;
	private Long fidMenu;
	private String cidNombre;
	private String cidUrl;
	private String cidIcono;
	private String cidCodigo;
	private int numSecuencia;
	private int flgActivo;

	
	public String getCidCodigo() {
		return cidCodigo;
	}
	public void setCidCodigo(String cidCodigo) {
		this.cidCodigo = cidCodigo;
	}
	public Long getIdCodigo() {
		return idCodigo;
	}
	public void setIdCodigo(Long idCodigo) {
		this.idCodigo = idCodigo;
	}
	public Long getFidMenu() {
		return fidMenu;
	}
	public void setFidMenu(Long fidMenu) {
		this.fidMenu = fidMenu;
	}
	public String getCidNombre() {
		return cidNombre;
	}
	public void setCidNombre(String cidNombre) {
		this.cidNombre = cidNombre;
	}
	public String getCidUrl() {
		return cidUrl;
	}
	public void setCidUrl(String cidUrl) {
		this.cidUrl = cidUrl;
	}
	public String getCidIcono() {
		return cidIcono;
	}
	public void setCidIcono(String cidIcono) {
		this.cidIcono = cidIcono;
	}
	public int getNumSecuencia() {
		return numSecuencia;
	}
	public void setNumSecuencia(int numSecuencia) {
		this.numSecuencia = numSecuencia;
	}
	public int getFlgActivo() {
		return flgActivo;
	}
	public void setFlgActivo(int flgActivo) {
		this.flgActivo = flgActivo;
	}
	
}
