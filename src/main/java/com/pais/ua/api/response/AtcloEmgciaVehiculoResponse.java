package com.pais.ua.api.response;

import java.io.Serializable;

public class AtcloEmgciaVehiculoResponse implements Serializable  {
	private static final long serialVersionUID = 1L;
	
	private Long idVehiculo;
	private Long idAtcloEmgciaDenominacion;
	private Long idAtcloEmgcia;
	private String nombreAtcloEmgcia;
	private int flgTieneAdm;
	private int flgOperativoAdm;
	private int flgVigenteAdm;
	private int flgTiene;
	private int flgOperativo;
	private int flgVigente;
	private String fecVigencia;
	private String nombreDenominacion;
	private String codPatrimonial;
	private String observacion;
	
	public String getObservacion() {
		return observacion;
	}
	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}
	public Long getIdVehiculo() {
		return idVehiculo;
	}
	public void setIdVehiculo(Long idVehiculo) {
		this.idVehiculo = idVehiculo;
	}
	public Long getIdAtcloEmgciaDenominacion() {
		return idAtcloEmgciaDenominacion;
	}
	public void setIdAtcloEmgciaDenominacion(Long idAtcloEmgciaDenominacion) {
		this.idAtcloEmgciaDenominacion = idAtcloEmgciaDenominacion;
	}
	public Long getIdAtcloEmgcia() {
		return idAtcloEmgcia;
	}
	public void setIdAtcloEmgcia(Long idAtcloEmgcia) {
		this.idAtcloEmgcia = idAtcloEmgcia;
	}
	public String getNombreAtcloEmgcia() {
		return nombreAtcloEmgcia;
	}
	public void setNombreAtcloEmgcia(String nombreAtcloEmgcia) {
		this.nombreAtcloEmgcia = nombreAtcloEmgcia;
	}
	public int getFlgTieneAdm() {
		return flgTieneAdm;
	}
	public void setFlgTieneAdm(int flgTieneAdm) {
		this.flgTieneAdm = flgTieneAdm;
	}
	public int getFlgOperativoAdm() {
		return flgOperativoAdm;
	}
	public void setFlgOperativoAdm(int flgOperativoAdm) {
		this.flgOperativoAdm = flgOperativoAdm;
	}
	public int getFlgVigenteAdm() {
		return flgVigenteAdm;
	}
	public void setFlgVigenteAdm(int flgVigenteAdm) {
		this.flgVigenteAdm = flgVigenteAdm;
	}
	public int getFlgTiene() {
		return flgTiene;
	}
	public void setFlgTiene(int flgTiene) {
		this.flgTiene = flgTiene;
	}
	public int getFlgOperativo() {
		return flgOperativo;
	}
	public void setFlgOperativo(int flgOperativo) {
		this.flgOperativo = flgOperativo;
	}
	public int getFlgVigente() {
		return flgVigente;
	}
	public void setFlgVigente(int flgVigente) {
		this.flgVigente = flgVigente;
	}
	public String getFecVigencia() {
		return fecVigencia;
	}
	public void setFecVigencia(String fecVigencia) {
		this.fecVigencia = fecVigencia;
	}
	public String getNombreDenominacion() {
		return nombreDenominacion;
	}
	public void setNombreDenominacion(String nombreDenominacion) {
		this.nombreDenominacion = nombreDenominacion;
	}
	public String getCodPatrimonial() {
		return codPatrimonial;
	}
	public void setCodPatrimonial(String codPatrimonial) {
		this.codPatrimonial = codPatrimonial;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
