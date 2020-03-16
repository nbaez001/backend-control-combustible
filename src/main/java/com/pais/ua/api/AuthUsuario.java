package com.pais.ua.api;

public class AuthUsuario {
	private Long idCodigo;
	private String cidUsuario;
	private String cidClave;
	private String cidNombre;
	private Long idCodigoPerfil;
	private String cidCodigoPerfil;
	private String cidNombrePerfil;

	
	public String getCidUsuario() {
		return cidUsuario;
	}
	public void setCidUsuario(String cidUsuario) {
		this.cidUsuario = cidUsuario;
	}
	public String getCidClave() {
		return cidClave;
	}
	public void setCidClave(String cidClave) {
		this.cidClave = cidClave;
	}
	public String getCidNombre() {
		return cidNombre;
	}
	public void setCidNombre(String cidNombre) {
		this.cidNombre = cidNombre;
	}
	

	public Long getIdCodigo() {
		return idCodigo;
	}
	public void setIdCodigo(Long idCodigo) {
		this.idCodigo = idCodigo;
	}
	public Long getIdCodigoPerfil() {
		return idCodigoPerfil;
	}
	public void setIdCodigoPerfil(Long idCodigoPerfil) {
		this.idCodigoPerfil = idCodigoPerfil;
	}
	public String getCidCodigoPerfil() {
		return cidCodigoPerfil;
	}
	public void setCidCodigoPerfil(String cidCodigoPerfil) {
		this.cidCodigoPerfil = cidCodigoPerfil;
	}
	public String getCidNombrePerfil() {
		return cidNombrePerfil;
	}
	public void setCidNombrePerfil(String cidNombrePerfil) {
		this.cidNombrePerfil = cidNombrePerfil;
	}
	
	
}
