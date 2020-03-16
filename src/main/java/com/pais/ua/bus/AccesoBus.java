/*
 * 
 */
package com.pais.ua.bus;

import java.util.List;

import com.pais.ua.api.AuthUsuario;
import com.pais.ua.api.ColeccionMenu;
import com.pais.ua.api.ListarMenu;

public interface AccesoBus {

	public AuthUsuario findByUsername(String username);
	
	public List<ColeccionMenu> menu(int idUsuario);

	public ListarMenu menuPadres(int nroDePagina, int registrosPorPagina);
	
	public ListarMenu menuHijos(int nroDePagina, int registrosPorPagina, String nombrePadre);
	
}
