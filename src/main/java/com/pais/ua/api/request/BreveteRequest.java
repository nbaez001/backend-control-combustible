package com.pais.ua.api.request;

import java.io.Serializable;

public class BreveteRequest implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Long idBrevete;
	private Long idConductor;
	private String codBrevete;
	private String fecVgciaIni;
	private String fecVgciaFin;
	private int fidIdUsuarioReg;
	private String fecReg;
	private String txtIpmaqReg;
	
	public int getFidIdUsuarioReg() {
		return fidIdUsuarioReg;
	}
	public void setFidIdUsuarioReg(int fidIdUsuarioReg) {
		this.fidIdUsuarioReg = fidIdUsuarioReg;
	}
	public String getFecReg() {
		return fecReg;
	}
	public void setFecReg(String fecReg) {
		this.fecReg = fecReg;
	}
	public String getTxtIpmaqReg() {
		return txtIpmaqReg;
	}
	public void setTxtIpmaqReg(String txtIpmaqReg) {
		this.txtIpmaqReg = txtIpmaqReg;
	}
	public Long getIdBrevete() {
		return idBrevete;
	}
	public void setIdBrevete(Long idBrevete) {
		this.idBrevete = idBrevete;
	}
	public Long getIdConductor() {
		return idConductor;
	}
	public void setIdConductor(Long idConductor) {
		this.idConductor = idConductor;
	}
	public String getCodBrevete() {
		return codBrevete;
	}
	public void setCodBrevete(String codBrevete) {
		this.codBrevete = codBrevete;
	}
	public String getFecVgciaIni() {
		return fecVgciaIni;
	}
	public void setFecVgciaIni(String fecVgciaIni) {
		this.fecVgciaIni = fecVgciaIni;
	}
	public String getFecVgciaFin() {
		return fecVgciaFin;
	}
	public void setFecVgciaFin(String fecVgciaFin) {
		this.fecVgciaFin = fecVgciaFin;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
