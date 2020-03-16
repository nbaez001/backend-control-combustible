package com.pais.ua.model.seguridad;

public class Rol {
	
	private Long idCodigo;
	private String cidNombre;
	private String cidCodigo;
	private boolean flgActivo;


	public Long getIdCodigo() {
		return idCodigo;
	}
	public void setIdCodigo(Long idCodigo) {
		this.idCodigo = idCodigo;
	}
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
	public boolean isFlgActivo() {
		return flgActivo;
	}
	public void setFlgActivo(boolean flgActivo) {
		this.flgActivo = flgActivo;
	}

}
