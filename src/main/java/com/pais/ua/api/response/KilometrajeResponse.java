package com.pais.ua.api.response;

import java.io.Serializable;

public class KilometrajeResponse implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long idCtlKlmtje;
	private Long idUnidad;
	private String nomUnidad;
	private Long idPlataforma;
	private String nomPlataforma;
	private Long idDenominacion;
	private String nomDenminacion;
	private Long idVehiculo;
	private String nomMarca;
	private String numPlaca;
	// AGREGAR LAS FECHAS
	private String horSalida;
	private String horLlegada;
	private Long numKlmtjeSalida;
	private Long numKlmtjeLlegada;
	private Long idLugarOrigen;
	private String nomLugarOrigen;
	private Long idLugarDestino;
	private String nomLugarDestino;
	private String txtObservacion;
	private String fecRegCtlKmtje;
	 
	
	public Long getIdVehiculo() {
		return idVehiculo;
	}
	public void setIdVehiculo(Long idVehiculo) {
		this.idVehiculo = idVehiculo;
	}
	public String getNomPlataforma() {
		return nomPlataforma;
	}
	public void setNomPlataforma(String nomPlataforma) {
		this.nomPlataforma = nomPlataforma;
	}
	public Long getIdCtlKlmtje() {
		return idCtlKlmtje;
	}
	public void setIdCtlKlmtje(Long idCtlKlmtje) {
		this.idCtlKlmtje = idCtlKlmtje;
	}
	public Long getIdUnidad() {
		return idUnidad;
	}
	public void setIdUnidad(Long idUnidad) {
		this.idUnidad = idUnidad;
	}
	public String getNomUnidad() {
		return nomUnidad;
	}
	public void setNomUnidad(String nomUnidad) {
		this.nomUnidad = nomUnidad;
	}
	public Long getIdPlataforma() {
		return idPlataforma;
	}
	public void setIdPlataforma(Long idPlataforma) {
		this.idPlataforma = idPlataforma;
	}
	public Long getIdDenominacion() {
		return idDenominacion;
	}
	public void setIdDenominacion(Long idDenominacion) {
		this.idDenominacion = idDenominacion;
	}
	public String getNomDenminacion() {
		return nomDenminacion;
	}
	public void setNomDenminacion(String nomDenminacion) {
		this.nomDenminacion = nomDenminacion;
	}
	public String getNomMarca() {
		return nomMarca;
	}
	public void setNomMarca(String nomMarca) {
		this.nomMarca = nomMarca;
	}
	public String getNumPlaca() {
		return numPlaca;
	}
	public void setNumPlaca(String numPlaca) {
		this.numPlaca = numPlaca;
	}
	public String getHorSalida() {
		return horSalida;
	}
	public void setHorSalida(String horSalida) {
		this.horSalida = horSalida;
	}
	public String getHorLlegada() {
		return horLlegada;
	}
	public void setHorLlegada(String horLlegada) {
		this.horLlegada = horLlegada;
	}
	public Long getNumKlmtjeSalida() {
		return numKlmtjeSalida;
	}
	public void setNumKlmtjeSalida(Long numKlmtjeSalida) {
		this.numKlmtjeSalida = numKlmtjeSalida;
	}
	public Long getNumKlmtjeLlegada() {
		return numKlmtjeLlegada;
	}
	public void setNumKlmtjeLlegada(Long numKlmtjeLlegada) {
		this.numKlmtjeLlegada = numKlmtjeLlegada;
	}
	public Long getIdLugarOrigen() {
		return idLugarOrigen;
	}
	public void setIdLugarOrigen(Long idLugarOrigen) {
		this.idLugarOrigen = idLugarOrigen;
	}
	public String getNomLugarOrigen() {
		return nomLugarOrigen;
	}
	public void setNomLugarOrigen(String nomLugarOrigen) {
		this.nomLugarOrigen = nomLugarOrigen;
	}
	public Long getIdLugarDestino() {
		return idLugarDestino;
	}
	public void setIdLugarDestino(Long idLugarDestino) {
		this.idLugarDestino = idLugarDestino;
	}
	public String getNomLugarDestino() {
		return nomLugarDestino;
	}
	public void setNomLugarDestino(String nomLugarDestino) {
		this.nomLugarDestino = nomLugarDestino;
	}
	public String getTxtObservacion() {
		return txtObservacion;
	}
	public void setTxtObservacion(String txtObservacion) {
		this.txtObservacion = txtObservacion;
	}
	public String getFecRegCtlKmtje() {
		return fecRegCtlKmtje;
	}
	public void setFecRegCtlKmtje(String fecRegCtlKmtje) {
		this.fecRegCtlKmtje = fecRegCtlKmtje;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
