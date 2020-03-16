package com.pais.ua.entity;

public class BreveteEntity {
	private Long idBrevete;
	private Long idConductor;
	private String codBrevete;
	private String fecVgciaIni;
	private String fecVgciaFin;
	
	public BreveteEntity() {
		
	}
	
	public BreveteEntity(Long idConductor,String codBrevete,String fecVgciaIni,String fecVgciaFin) {
		this.idConductor = idConductor;
		this.codBrevete = codBrevete;
		this.fecVgciaIni = fecVgciaIni;
		this.fecVgciaFin = fecVgciaFin;
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
	
	
}
