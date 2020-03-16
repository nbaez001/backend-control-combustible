package com.pais.ua.api.request;

import java.io.Serializable;

public class BuscaEmpleadoRequest implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String apePaterno;
	private String apeMaterno;
	private String nomPrimero;
	private Long idPuesto;
	private String numDocIden;
	
	public String getApePaterno() {
		return apePaterno;
	}
	public void setApePaterno(String apePaterno) {
		this.apePaterno = apePaterno;
	}
	public String getApeMaterno() {
		return apeMaterno;
	}
	public void setApeMaterno(String apeMaterno) {
		this.apeMaterno = apeMaterno;
	}
	public String getNomPrimero() {
		return nomPrimero;
	}
	public void setNomPrimero(String nomPrimero) {
		this.nomPrimero = nomPrimero;
	}
	public Long getIdPuesto() {
		return idPuesto;
	}
	public void setIdPuesto(Long idPuesto) {
		this.idPuesto = idPuesto;
	}
	public String getNumDocIden() {
		return numDocIden;
	}
	public void setNumDocIden(String numDocIden) {
		this.numDocIden = numDocIden;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
