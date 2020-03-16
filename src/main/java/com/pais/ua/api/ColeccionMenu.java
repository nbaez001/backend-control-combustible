package com.pais.ua.api;

import java.util.List;

public class ColeccionMenu {
	List<ColeccionMenu> hijos;
	String cidNombre;
	String cidUrl;
	String cidIcono;
	String cidCodigo;

	public String getCidCodigo() {
		return cidCodigo;
	}

	public void setCidCodigo(String cidCodigo) {
		this.cidCodigo = cidCodigo;
	}

	public String getCidNombre() {
		return cidNombre;
	}

	public void setCidNombre(String cidNombre) {
		this.cidNombre = cidNombre;
	}

	public String getCidUrl() {
		return cidUrl;
	}

	public void setCidUrl(String cidUrl) {
		this.cidUrl = cidUrl;
	}

	public String getCidIcono() {
		return cidIcono;
	}

	public void setCidIcono(String cidIcono) {
		this.cidIcono = cidIcono;
	}

	public List<ColeccionMenu> getHijos() {
		return hijos;
	}

	public void setHijos(List<ColeccionMenu> hijos) {
		this.hijos = hijos;
	}
}
