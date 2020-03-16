package com.pais.ua.api;

import java.util.List;

import com.pais.ua.model.seguridad.Menu;

public class ListarMenu {

	List<Menu> lista;
	int totalRegistros;
	
	public List<Menu> getLista() {
		return lista;
	}
	public void setLista(List<Menu> lista) {
		this.lista = lista;
	}
	public int getTotalRegistros() {
		return totalRegistros;
	}
	public void setTotalRegistros(int totalRegistros) {
		this.totalRegistros = totalRegistros;
	}
	
}
