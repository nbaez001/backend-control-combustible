package com.pais.ua.model.seguridad;

public class Perfil {

	private Long idCodigo;
	private String cidNombre;
	private String cidCodigo;
	private Long fidModulo;
	private boolean flgActivo;

	
	public String getCidNombre() {
		return cidNombre;
	}
	public void setCidNombre(String cidNombre) {
		this.cidNombre = cidNombre;
	}
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
	public Long getFidModulo() {
		return fidModulo;
	}
	public void setFidModulo(Long fidModulo) {
		this.fidModulo = fidModulo;
	}
	public boolean isFlgActivo() {
		return flgActivo;
	}
	public void setFlgActivo(boolean flgActivo) {
		this.flgActivo = flgActivo;
	}
	
	
}
