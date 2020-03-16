package com.pais.ua.api.response;

import java.io.Serializable;

public class EmpleadoPuestoResponse implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long idPersona;
	private Long idEmpleado;
	private Long idPuesto;
	private String nomPuesto;
	private String nombre1;
	private String nombre2;
	private String apPaterno;
	private String apMaterno;
	
	public Long getIdPersona() {
		return idPersona;
	}
	public void setIdPersona(Long idPersona) {
		this.idPersona = idPersona;
	}
	public Long getIdEmpleado() {
		return idEmpleado;
	}
	public void setIdEmpleado(Long idEmpleado) {
		this.idEmpleado = idEmpleado;
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
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	

}
