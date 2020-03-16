package com.pais.ua.model.seguridad;

public class OpcionMenuRol {
	private Long idCodigo;
	private Long fidOpcionMenu;
	private Long fidRol;
	private boolean flgActivo;


	public Long getIdCodigo() {
		return idCodigo;
	}
	public void setIdCodigo(Long idCodigo) {
		this.idCodigo = idCodigo;
	}
	public Long getFidOpcionMenu() {
		return fidOpcionMenu;
	}
	public void setFidOpcionMenu(Long fidOpcionMenu) {
		this.fidOpcionMenu = fidOpcionMenu;
	}
	public Long getFidRol() {
		return fidRol;
	}
	public void setFidRol(Long fidRol) {
		this.fidRol = fidRol;
	}
	public boolean isFlgActivo() {
		return flgActivo;
	}
	public void setFlgActivo(boolean flgActivo) {
		this.flgActivo = flgActivo;
	}
	
}
