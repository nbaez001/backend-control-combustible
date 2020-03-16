package com.pais.ua.api.request;

import java.io.Serializable;

import com.pais.ua.entity.BreveteEntity;

public class ConductorBreveteRequest implements Serializable {
		
	private static final long serialVersionUID = 1L;
	
	private Long idConductor;
	private Long idVehiculo;
	private Long idEmpleado;
	private int fidIdUsuarioReg;
	private String fecReg;
	private String txtIpmaqReg;
	private BreveteEntity brevete;
	
	
	public Long getIdConductor() {
		return idConductor;
	}
	public void setIdConductor(Long idConductor) {
		this.idConductor = idConductor;
	}
	public Long getIdVehiculo() {
		return idVehiculo;
	}
	public void setIdVehiculo(Long idVehiculo) {
		this.idVehiculo = idVehiculo;
	}
	public Long getIdEmpleado() {
		return idEmpleado;
	}
	public void setIdEmpleado(Long idEmpleado) {
		this.idEmpleado = idEmpleado;
	}
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
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public BreveteEntity getBrevete() {
		return brevete;
	}
	public void setBrevete(BreveteEntity brevete) {
		this.brevete = brevete;
	}

}
