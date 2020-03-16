package com.pais.ua.api.response;

import java.io.Serializable;

public class BuscaEmpleadoResponse implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Long idEmpleado;
	private Long idPersona;
	private Long idTipDocIden;
	private String numDocIden;
	private String apPaterno;
	private String apMaterno;
	private String nomPrimero;
	private String nomSegundo;
	private Long idPuesto;
	private String nomPuesto;
	private Long idModalidadContrato;
	private String nomModalidadContrato;
	private String sigModalidadContrato;
	private Long idEntidad;
	private String nomEntidad;
	private Long idSector;
	private String nomSectorLargo;
	private String nomSectorCorto; 
	private Long idTipoGobierno;
	private String cidTipoGobierno;
	private String nomTipoGobierno;
	private Long idArea;
	private String cidArea;
	private String nomArea;
	private Long idUnidad;
	private String cidUnidad;
	private String nomUnidad;
	private Long idUTerritorial;
	private String nomUTerritorial;
	private Long idPlataforma;
	private String nomPlataforma;
	private Long idUTPlataforma;
	private String nomUTPlataforma;
	
	
	public String getCidArea() {
		return cidArea;
	}
	public void setCidArea(String cidArea) {
		this.cidArea = cidArea;
	}
	public Long getIdEmpleado() {
		return idEmpleado;
	}
	public void setIdEmpleado(Long idEmpleado) {
		this.idEmpleado = idEmpleado;
	}
	public Long getIdPersona() {
		return idPersona;
	}
	public void setIdPersona(Long idPersona) {
		this.idPersona = idPersona;
	}
	public Long getIdTipDocIden() {
		return idTipDocIden;
	}
	public void setIdTipDocIden(Long idTipDocIden) {
		this.idTipDocIden = idTipDocIden;
	}
	public String getNumDocIden() {
		return numDocIden;
	}
	public void setNumDocIden(String numDocIden) {
		this.numDocIden = numDocIden;
	}
	public String getApPaterno() {
		return apPaterno;
	}
	public void setApPaterno(String apPaterno) {
		this.apPaterno = apPaterno;
	}
	public String getApMaterno() {
		return apMaterno;
	}
	public void setApMaterno(String apMaterno) {
		this.apMaterno = apMaterno;
	}
	public String getNomPrimero() {
		return nomPrimero;
	}
	public void setNomPrimero(String nomPrimero) {
		this.nomPrimero = nomPrimero;
	}
	public String getNomSegundo() {
		return nomSegundo;
	}
	public void setNomSegundo(String nomSegundo) {
		this.nomSegundo = nomSegundo;
	}
	public Long getIdPuesto() {
		return idPuesto;
	}
	public void setIdPuesto(Long idPuesto) {
		this.idPuesto = idPuesto;
	}
	public String getNomPuesto() {
		return nomPuesto;
	}
	public void setNomPuesto(String nomPuesto) {
		this.nomPuesto = nomPuesto;
	}
	public Long getIdModalidadContrato() {
		return idModalidadContrato;
	}
	public void setIdModalidadContrato(Long idModalidadContrato) {
		this.idModalidadContrato = idModalidadContrato;
	}
	public String getNomModalidadContrato() {
		return nomModalidadContrato;
	}
	public void setNomModalidadContrato(String nomModalidadContrato) {
		this.nomModalidadContrato = nomModalidadContrato;
	}
	public String getSigModalidadContrato() {
		return sigModalidadContrato;
	}
	public void setSigModalidadContrato(String sigModalidadContrato) {
		this.sigModalidadContrato = sigModalidadContrato;
	}
	public Long getIdEntidad() {
		return idEntidad;
	}
	public void setIdEntidad(Long idEntidad) {
		this.idEntidad = idEntidad;
	}
	public String getNomEntidad() {
		return nomEntidad;
	}
	public void setNomEntidad(String nomEntidad) {
		this.nomEntidad = nomEntidad;
	}
	public Long getIdSector() {
		return idSector;
	}
	public void setIdSector(Long idSector) {
		this.idSector = idSector;
	}
	public String getNomSectorLargo() {
		return nomSectorLargo;
	}
	public void setNomSectorLargo(String nomSectorLargo) {
		this.nomSectorLargo = nomSectorLargo;
	}
	public String getNomSectorCorto() {
		return nomSectorCorto;
	}
	public void setNomSectorCorto(String nomSectorCorto) {
		this.nomSectorCorto = nomSectorCorto;
	}
	public Long getIdTipoGobierno() {
		return idTipoGobierno;
	}
	public void setIdTipoGobierno(Long idTipoGobierno) {
		this.idTipoGobierno = idTipoGobierno;
	}
	public String getCidTipoGobierno() {
		return cidTipoGobierno;
	}
	public void setCidTipoGobierno(String cidTipoGobierno) {
		this.cidTipoGobierno = cidTipoGobierno;
	}
	public String getNomTipoGobierno() {
		return nomTipoGobierno;
	}
	public void setNomTipoGobierno(String nomTipoGobierno) {
		this.nomTipoGobierno = nomTipoGobierno;
	}
	public Long getIdArea() {
		return idArea;
	}
	public void setIdArea(Long idArea) {
		this.idArea = idArea;
	}
	public String getNomArea() {
		return nomArea;
	}
	public void setNomArea(String nomArea) {
		this.nomArea = nomArea;
	}
	public Long getIdUnidad() {
		return idUnidad;
	}
	public void setIdUnidad(Long idUnidad) {
		this.idUnidad = idUnidad;
	}
	public String getCidUnidad() {
		return cidUnidad;
	}
	public void setCidUnidad(String cidUnidad) {
		this.cidUnidad = cidUnidad;
	}
	public String getNomUnidad() {
		return nomUnidad;
	}
	public void setNomUnidad(String nomUnidad) {
		this.nomUnidad = nomUnidad;
	}
	public Long getIdUTerritorial() {
		return idUTerritorial;
	}
	public void setIdUTerritorial(Long idUTerritorial) {
		this.idUTerritorial = idUTerritorial;
	}
	public String getNomUTerritorial() {
		return nomUTerritorial;
	}
	public void setNomUTerritorial(String nomUTerritorial) {
		this.nomUTerritorial = nomUTerritorial;
	}
	public Long getIdPlataforma() {
		return idPlataforma;
	}
	public void setIdPlataforma(Long idPlataforma) {
		this.idPlataforma = idPlataforma;
	}
	public String getNomPlataforma() {
		return nomPlataforma;
	}
	public void setNomPlataforma(String nomPlataforma) {
		this.nomPlataforma = nomPlataforma;
	}
	public Long getIdUTPlataforma() {
		return idUTPlataforma;
	}
	public void setIdUTPlataforma(Long idUTPlataforma) {
		this.idUTPlataforma = idUTPlataforma;
	}
	public String getNomUTPlataforma() {
		return nomUTPlataforma;
	}
	public void setNomUTPlataforma(String nomUTPlataforma) {
		this.nomUTPlataforma = nomUTPlataforma;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
