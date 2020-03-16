package com.pais.ua.dao;

import com.pais.ua.model.seguridad.Menu;

public interface MenuDao {

	public void eliminar(int idMenu);
	
	public void crear(Menu menu);
	
	public void actualizar(Menu menu);
}
