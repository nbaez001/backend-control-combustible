/*
 * 
 */
package com.pais.ua.busImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pais.ua.api.AuthUsuario;
import com.pais.ua.api.ColeccionMenu;
import com.pais.ua.api.ListarMenu;
import com.pais.ua.bus.AccesoBus;
import com.pais.ua.dao.AccesoDao;
import com.pais.ua.model.seguridad.Menu;

@Service
public class AccesoBusImpl implements AccesoBus, UserDetailsService {

	@Autowired
	private AccesoDao accesoDao;
	
	/* (non-Javadoc)
	 * @see org.springframework.security.core.userdetails.UserDetailsService#loadUserByUsername(java.lang.String)
	 */
	@Override
	@Transactional(readOnly=true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		AuthUsuario usuario = accesoDao.autenticacionUsuario(username);
		
		if(usuario == null) {
			throw new UsernameNotFoundException("Error en el login: no existe el usuario '"+username+"' en el sistema!");
		}
		
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
		
		return new User(usuario.getCidUsuario(), usuario.getCidClave(), true, true, true, true, authorities);
		
	}
	
	@Override
	@Transactional(readOnly=true)
	public AuthUsuario findByUsername(String username) {
		
		AuthUsuario usu = accesoDao.autenticacionUsuario(username);
		
		return usu;
	}
	
	@Override
	@Transactional(readOnly=true)
	public List<ColeccionMenu> menu(int idUsuario) {
		
		List<Menu> menus = accesoDao.menu(idUsuario);
		
		return this.obtenerHijos(menus, 0L);
		
	}
	
	private List<ColeccionMenu> obtenerHijos(List<Menu> menus, Long idCodigo) {
		List<ColeccionMenu> hijos = new ArrayList<ColeccionMenu>();
		
		for(Menu menu : menus) {
			if (menu.getFidMenu().equals(idCodigo)) {
				ColeccionMenu itemMenu = new ColeccionMenu();
				itemMenu.setCidNombre(menu.getCidNombre());
				itemMenu.setCidUrl(menu.getCidUrl());
				itemMenu.setCidIcono(menu.getCidIcono());
				itemMenu.setCidCodigo(menu.getCidCodigo());
				itemMenu.setHijos(this.obtenerHijos(menus, menu.getIdCodigo()));
				hijos.add(itemMenu);
			}
		}
		
		return hijos;
	}

	@Override
	public ListarMenu menuPadres(int nroDePagina, int registrosPorPagina) {
		
		return accesoDao.menuPadres(nroDePagina, registrosPorPagina);
	}

	@Override
	public ListarMenu menuHijos(int nroDePagina, int registrosPorPagina, String nombrePadre) {
		
		return accesoDao.menuHijos(nroDePagina, registrosPorPagina, nombrePadre);
	}
}
