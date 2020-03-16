package com.pais.ua.api.request;

import java.io.Serializable;
import java.util.Date;

public class AtcloEmgciaXVehiculoRequest implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private long fidAtcloEmgciaDenominacion;
	private long fidVhclo;
	private short flgTiene;
	private short flgOperativo;
	private short flgVigente;
	private Date fecVigencia;
	private short flgActual;
	private String txtObservacion;
	private short flgActivo;
	private int fidIdUsuarioReg;
	private Date fecReg;
	private String txtIpmaqReg;
	
	public long getFidAtcloEmgciaDenominacion() {
		return fidAtcloEmgciaDenominacion;
	}
	public void setFidAtcloEmgciaDenominacion(long fidAtcloEmgciaDenominacion) {
		this.fidAtcloEmgciaDenominacion = fidAtcloEmgciaDenominacion;
	}
	public long getFidVhclo() {
		return fidVhclo;
	}
	public void setFidVhclo(long fidVhclo) {
		this.fidVhclo = fidVhclo;
	}
	public short getFlgTiene() {
		return flgTiene;
	}
	public void setFlgTiene(short flgTiene) {
		this.flgTiene = flgTiene;
	}
	public short getFlgOperativo() {
		return flgOperativo;
	}
	public void setFlgOperativo(short flgOperativo) {
		this.flgOperativo = flgOperativo;
	}
	public short getFlgVigente() {
		return flgVigente;
	}
	public void setFlgVigente(short flgVigente) {
		this.flgVigente = flgVigente;
	}
	public Date getFecVigencia() {
		return fecVigencia;
	}
	public void setFecVigencia(Date fecVigencia) {
		this.fecVigencia = fecVigencia;
	}
	public short getFlgActual() {
		return flgActual;
	}
	public void setFlgActual(short flgActual) {
		this.flgActual = flgActual;
	}
	public String getTxtObservacion() {
		return txtObservacion;
	}
	public void setTxtObservacion(String txtObservacion) {
		this.txtObservacion = txtObservacion;
	}
	public short getFlgActivo() {
		return flgActivo;
	}
	public void setFlgActivo(short flgActivo) {
		this.flgActivo = flgActivo;
	}
	public int getFidIdUsuarioReg() {
		return fidIdUsuarioReg;
	}
	public void setFidIdUsuarioReg(int fidIdUsuarioReg) {
		this.fidIdUsuarioReg = fidIdUsuarioReg;
	}
	public Date getFecReg() {
		return fecReg;
	}
	public void setFecReg(Date fecReg) {
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
	
}
