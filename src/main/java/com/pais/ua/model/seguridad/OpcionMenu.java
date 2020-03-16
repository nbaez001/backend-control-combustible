package com.pais.ua.model.seguridad;

public class OpcionMenu {
	
	private Long idCodigo;
	private int fidPerfil;
	private int fidMenu;
	private boolean flgActivo;

	
	public Long getIdCodigo() {
		return idCodigo;
	}
	public void setIdCodigo(Long idCodigo) {
		this.idCodigo = idCodigo;
	}
	public int getFidPerfil() {
		return fidPerfil;
	}
	public void setFidPerfil(int fidPerfil) {
		this.fidPerfil = fidPerfil;
	}
	public int getFidMenu() {
		return fidMenu;
	}
	public void setFidMenu(int fidMenu) {
		this.fidMenu = fidMenu;
	}
	public boolean isFlgActivo() {
		return flgActivo;
	}
	public void setFlgActivo(boolean flgActivo) {
		this.flgActivo = flgActivo;
	}
	
}
