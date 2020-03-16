package com.pais.ua.api.request;

import java.io.Serializable;

public class EliminaConductorBreveteRequest implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Long idConductor;
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

	public Long getIdConductor() {
		return idConductor;
	}

	public void setIdConductor(Long idConductor) {
		this.idConductor = idConductor;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	

}
