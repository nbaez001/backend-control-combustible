package com.pais.ua.entity;

import java.math.BigDecimal;

public class TipoLbcteVhcloEntity {
	private Long idTipoLbcteVhclo;
	private Long idTipoLbcte;
	private String nomTipoLbcte;
	private Long idVehiculo;
	private Long numKlmtje;
	private BigDecimal ctdad;
	private String fecAsigcion;
	private short actual;
	
	public TipoLbcteVhcloEntity() {
		
	}
	public TipoLbcteVhcloEntity(Long idTipoLbcte, String nomTipoLbcte, Long idVehiculo, Long numKlmtje,BigDecimal ctdad, String fecAsigcion, short actual) {
		this.idTipoLbcte = idTipoLbcte;
		this.nomTipoLbcte = nomTipoLbcte;
		this.idVehiculo = idVehiculo;
		this.numKlmtje = numKlmtje;
		this.ctdad = ctdad;
		this.fecAsigcion = fecAsigcion;
		this.actual = actual;
	}
	
	
	public Long getIdTipoLbcteVhclo() {
		return idTipoLbcteVhclo;
	}
	public void setIdTipoLbcteVhclo(Long idTipoLbcteVhclo) {
		this.idTipoLbcteVhclo = idTipoLbcteVhclo;
	}
	public Long getIdTipoLbcte() {
		return idTipoLbcte;
	}
	public void setIdTipoLbcte(Long idTipoLbcte) {
		this.idTipoLbcte = idTipoLbcte;
	}
	public String getNomTipoLbcte() {
		return nomTipoLbcte;
	}
	public void setNomTipoLbcte(String nomTipoLbcte) {
		this.nomTipoLbcte = nomTipoLbcte;
	}
	public Long getIdVehiculo() {
		return idVehiculo;
	}
	public void setIdVehiculo(Long idVehiculo) {
		this.idVehiculo = idVehiculo;
	}
	public Long getNumKlmtje() {
		return numKlmtje;
	}
	public void setNumKlmtje(Long numKlmtje) {
		this.numKlmtje = numKlmtje;
	}
	public BigDecimal getCtdad() {
		return ctdad;
	}
	public void setCtdad(BigDecimal ctdad) {
		this.ctdad = ctdad;
	}
	public String getFecAsigcion() {
		return fecAsigcion;
	}
	public void setFecAsigcion(String fecAsigcion) {
		this.fecAsigcion = fecAsigcion;
	}
	public short getActual() {
		return actual;
	}
	public void setActual(short actual) {
		this.actual = actual;
	}
	
}
