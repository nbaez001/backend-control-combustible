/*
 * 
 */
package com.pais.ua.model.seguridad;

public class Usuario {
	
	private Long idCodigo;
	private String cidUsuario;
	private String cidClave;
	private boolean flgActivo;
	private Long fidEmpleado;

	
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
	public boolean isFlgActivo() {
		return flgActivo;
	}
	public void setFlgActivo(boolean flgActivo) {
		this.flgActivo = flgActivo;
	}
	public Long getIdCodigo() {
		return idCodigo;
	}
	public void setIdCodigo(Long idCodigo) {
		this.idCodigo = idCodigo;
	}
	public Long getFidEmpleado() {
		return fidEmpleado;
	}
	public void setFidEmpleado(Long fidEmpleado) {
		this.fidEmpleado = fidEmpleado;
	}

}
