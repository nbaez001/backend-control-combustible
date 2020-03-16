package com.pais.ua.model.seguridad;

public class OpcionMenuAlterar {
	private Long idCodigo;
	private Long fidUsuario;
	private Long fidOpcionMenu;
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
	public Long getFidOpcionMenu() {
		return fidOpcionMenu;
	}
	public void setFidOpcionMenu(Long fidOpcionMenu) {
		this.fidOpcionMenu = fidOpcionMenu;
	}
	public boolean isFlgActivo() {
		return flgActivo;
	}
	public void setFlgActivo(boolean flgActivo) {
		this.flgActivo = flgActivo;
	}
	
}
