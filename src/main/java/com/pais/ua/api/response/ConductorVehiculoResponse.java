package com.pais.ua.api.response;

import java.io.Serializable;

public class ConductorVehiculoResponse implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long idPersona;
	private String nombre1;
	private String nombre2;
	private String apPat;
	private String apMat;
	private Long idEmpleado;
	private Long idVehiculo;
	private String fecConductorIni;
	private String fecConcductorFin;
	private short flgConductorActual;
	private Long idBrevete;
	private String numBrevete;
	private String vigenBreveteIni;
	private String vigenBreveteFin;
	private String fecRegBrevete;
	private short flgActualBrevete;
	private Long idConductor;
	
	public String getFecRegBrevete() {
		return fecRegBrevete;
	}
	public void setFecRegBrevete(String fecRegBrevete) {
		this.fecRegBrevete = fecRegBrevete;
	}

	public short getFlgActualBrevete() {
		return flgActualBrevete;
	}
	public void setFlgActualBrevete(short flgActualBrevete) {
		this.flgActualBrevete = flgActualBrevete;
	}

	public Long getIdConductor() {
		return idConductor;
	}
	public void setIdConductor(Long idConductor) {
		this.idConductor = idConductor;
	}
	public Long getIdPersona() {
		return idPersona;
	}
	public void setIdPersona(Long idPersona) {
		this.idPersona = idPersona;
	}
	public String getNombre1() {
		return nombre1;
	}
	public void setNombre1(String nombre1) {
		this.nombre1 = nombre1;
	}
	public String getNombre2() {
		return nombre2;
	}
	public void setNombre2(String nombre2) {
		this.nombre2 = nombre2;
	}
	public String getApPat() {
		return apPat;
	}
	public void setApPat(String apPat) {
		this.apPat = apPat;
	}
	public String getApMat() {
		return apMat;
	}
	public void setApMat(String apMat) {
		this.apMat = apMat;
	}
	public Long getIdEmpleado() {
		return idEmpleado;
	}
	public void setIdEmpleado(Long idEmpleado) {
		this.idEmpleado = idEmpleado;
	}
	public Long getIdVehiculo() {
		return idVehiculo;
	}
	public void setIdVehiculo(Long idVehiculo) {
		this.idVehiculo = idVehiculo;
	}
	public String getFecConductorIni() {
		return fecConductorIni;
	}
	public void setFecConductorIni(String fecConductorIni) {
		this.fecConductorIni = fecConductorIni;
	}
	public String getFecConcductorFin() {
		return fecConcductorFin;
	}
	public void setFecConcductorFin(String fecConcductorFin) {
		this.fecConcductorFin = fecConcductorFin;
	}
	public short getFlgConductorActual() {
		return flgConductorActual;
	}
	public void setFlgConductorActual(short flgConductorActual) {
		this.flgConductorActual = flgConductorActual;
	}
	public Long getIdBrevete() {
		return idBrevete;
	}
	public void setIdBrevete(Long idBrevete) {
		this.idBrevete = idBrevete;
	}
	public String getNumBrevete() {
		return numBrevete;
	}
	public void setNumBrevete(String numBrevete) {
		this.numBrevete = numBrevete;
	}
	public String getVigenBreveteIni() {
		return vigenBreveteIni;
	}
	public void setVigenBreveteIni(String vigenBreveteIni) {
		this.vigenBreveteIni = vigenBreveteIni;
	}
	public String getVigenBreveteFin() {
		return vigenBreveteFin;
	}
	public void setVigenBreveteFin(String vigenBreveteFin) {
		this.vigenBreveteFin = vigenBreveteFin;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	

}
