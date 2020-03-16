/*
 * 
 */
package com.pais.ua.dao;

import java.util.List;

import com.pais.ua.api.AuthUsuario;
import com.pais.ua.api.ListarMenu;
import com.pais.ua.model.seguridad.Menu;

public interface AccesoDao {

	public AuthUsuario autenticacionUsuario(String username);
	
	public List<Menu> menu(int idUsuario);
	
	public ListarMenu menuPadres(int nroDePagina, int registrosPorPagina);
	
	public ListarMenu menuHijos(int nroDePagina, int registrosPorPagina, String nombrePadre);
	
}
