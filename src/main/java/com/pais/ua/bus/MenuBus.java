package com.pais.ua.bus;

import com.pais.ua.model.seguridad.Menu;

public interface MenuBus {
	public void eliminar(int idMenu);

	public void crear(Menu menu);

	public void actualizar(Menu menu);
}
