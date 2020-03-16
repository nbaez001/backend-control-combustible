package com.pais.ua.busImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pais.ua.bus.MenuBus;
import com.pais.ua.dao.MenuDao;
import com.pais.ua.model.seguridad.Menu;

@Service
public class MenuBusImpl implements MenuBus {

	@Autowired
	private MenuDao menuDao;

	@Override
	@Transactional
	public void eliminar(int idMenu) {
		
		menuDao.eliminar(idMenu);
		
	}

	@Override
	@Transactional
	public void crear(Menu menu) {
		
		menuDao.crear(menu);
		
	}

	@Override
	@Transactional
	public void actualizar(Menu menu) {
		
		menuDao.actualizar(menu);
		
	}
	
}
