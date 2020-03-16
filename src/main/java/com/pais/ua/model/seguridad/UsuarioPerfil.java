package com.pais.ua.model.seguridad;

public class UsuarioPerfil {

	private Long idCodigo;
	private Long fidUsuario;
	private Long fidPerfil;
	private boolean flgActivo;

	
	public Long getIdCodigo() {
		return idCodigo;
	}
	public void setIdCodigo(Long idCodigo) {
		this.idCodigo = idCodigo;
	}
	public Long getFidUsuario() {
		return fidUsuario;
	}
	public void setFidUsuario(Long fidUsuario) {
		this.fidUsuario = fidUsuario;
	}
	public Long getFidPerfil() {
		return fidPerfil;
	}
	public void setFidPerfil(Long fidPerfil) {
		this.fidPerfil = fidPerfil;
	}
	public boolean isFlgActivo() {
		return flgActivo;
	}
	public void setFlgActivo(boolean flgActivo) {
		this.flgActivo = flgActivo;
	}
	
}
