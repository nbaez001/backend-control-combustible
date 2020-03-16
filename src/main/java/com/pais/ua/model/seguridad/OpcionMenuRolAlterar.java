package com.pais.ua.model.seguridad;

public class OpcionMenuRolAlterar {
	private Long idCodigo;
	private Long fidOpcionMenuRol;
	private Long fidUsuario;
	private boolean flgActivo;

	
	public Long getIdCodigo() {
		return idCodigo;
	}
	public void setIdCodigo(Long idCodigo) {
		this.idCodigo = idCodigo;
	}
	public Long getFidOpcionMenuRol() {
		return fidOpcionMenuRol;
	}
	public void setFidOpcionMenuRol(Long fidOpcionMenuRol) {
		this.fidOpcionMenuRol = fidOpcionMenuRol;
	}
	public Long getFidUsuario() {
		return fidUsuario;
	}
	public void setFidUsuario(Long fidUsuario) {
		this.fidUsuario = fidUsuario;
	}
	public boolean isFlgActivo() {
		return flgActivo;
	}
	public void setFlgActivo(boolean flgActivo) {
		this.flgActivo = flgActivo;
	}
	
}
