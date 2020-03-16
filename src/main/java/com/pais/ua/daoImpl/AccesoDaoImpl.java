package com.pais.ua.daoImpl;

import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.jfree.util.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import com.pais.ua.api.AuthUsuario;
import com.pais.ua.api.ListarMenu;
import com.pais.ua.dao.AccesoDao;
import com.pais.ua.model.seguridad.Menu;
import com.pais.ua.util.Constantes;

@Repository
public class AccesoDaoImpl implements AccesoDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	private SimpleJdbcCall jdbcCall;

	@SuppressWarnings("unchecked")
	@Override
	public AuthUsuario autenticacionUsuario(String username) {

		AuthUsuario usuario = null;

		try {
			jdbcCall = new SimpleJdbcCall(jdbcTemplate.getDataSource());
			jdbcCall
				.withSchemaName("SEGURIDAD")
				.withProcedureName("autenticacionUsuario")
				.declareParameters(new SqlParameter("USERNAME", Types.VARCHAR));

			MapSqlParameterSource parametros = new MapSqlParameterSource();
			parametros.addValue("USERNAME", username);

			Map<String, Object> results = jdbcCall.execute(parametros);
			List<Map<String, Object>> rs = (List<Map<String, Object>>) results.get(Constantes.RETURN_RESULT_SET_PREFIX);

			if (rs.size() > 0) {
				for (Map<String, Object> map : rs) {

					usuario = new AuthUsuario();

					usuario.setIdCodigo((Long) 			map.get("ID_CODIGO"));
					usuario.setCidUsuario((String) 		map.get("CID_USUARIO").toString().trim());
					usuario.setCidClave((String) 		map.get("CID_CLAVE").toString().trim());
					usuario.setCidNombre((String) 		map.get("CID_NOMBRE").toString().trim());
					//ACTUALIZADO POR EL MOMENTO
					if(usuario.getCidUsuario().equals("42195976")) {
						usuario.setIdCodigoPerfil(1L);
						usuario.setCidNombrePerfil(null);
						usuario.setCidCodigoPerfil(null);
					}else if(usuario.getCidUsuario().equals("42596272")) {
						usuario.setIdCodigoPerfil(2L);
						usuario.setCidNombrePerfil(null);
						usuario.setCidCodigoPerfil(null);
					}else if(usuario.getCidUsuario().equals("44705433")) {
						usuario.setIdCodigoPerfil(3L);
						usuario.setCidNombrePerfil(null);
						usuario.setCidCodigoPerfil(null);
					}
					
						
				}
			}

		} catch (Exception e) {
			Log.info("error al autenticar");
		}

		return usuario;
	}

	@Override
	public List<Menu> menu(int idUsuario) {
		
		List<Menu> listaMenu = new ArrayList<>();
		Menu menu = null;

		try {
			jdbcCall = new SimpleJdbcCall(jdbcTemplate.getDataSource());
			jdbcCall
			.withSchemaName("SEGURIDAD")
			.withProcedureName("coleccionMenu")
			.declareParameters(new SqlParameter("ID_USUARIO", Types.INTEGER));

			MapSqlParameterSource parametros = new MapSqlParameterSource();
			parametros.addValue("ID_USUARIO", idUsuario);

			Map<String, Object> results = jdbcCall.execute(parametros);
			List<Map<String, Object>> rs = (List<Map<String, Object>>) results.get(Constantes.RETURN_RESULT_SET_PREFIX);

			if (rs.size() > 0) {
				for (Map<String, Object> map : rs) {

					menu = new Menu();
					
					menu.setIdCodigo(Long.parseLong(map.get("ID_CODIGO").toString()));
					menu.setFidMenu(Long.parseLong(map.get("FID_MENU").toString())		);
					menu.setCidNombre((String) 		map.get("CID_NOMBRE").toString().trim());
					menu.setCidUrl((String) 		map.get("CID_URL").toString().trim());
					menu.setCidIcono((String) 		map.get("CID_ICONO").toString().trim());
					menu.setCidCodigo((String)      map.get("CID_CODIGO").toString());
					
					
					listaMenu.add(menu);
					
				}
			}

		} catch (Exception e) {
			Log.info("error al obtener menu");
		}

		return listaMenu;
	}

	@Override
	public ListarMenu menuPadres(int nroDePagina, int registrosPorPagina) {
		
		ListarMenu listaMenu = new ListarMenu();
		List<Menu> lista = new ArrayList<>();
		Menu menu = null;

		try {
			jdbcCall = new SimpleJdbcCall(jdbcTemplate.getDataSource());
			jdbcCall
			//.withCatalogName("seguridad")
			.withProcedureName("SP_MENU_PADRES")
			.declareParameters(
					new SqlParameter("NroDePagina", Types.INTEGER),
					new SqlParameter("RegistrosPorPagina", Types.INTEGER),
					new SqlOutParameter("TotalRegistros", 	Types.INTEGER)
			);

			MapSqlParameterSource parametros = new MapSqlParameterSource();
			parametros.addValue("NroDePagina", 			nroDePagina);
			parametros.addValue("RegistrosPorPagina", 	registrosPorPagina);

			Map<String, Object> results = jdbcCall.execute(parametros);
			int totalRegistros = (Integer) results.get("TotalRegistros");
			List<Map<String, Object>> rs = (List<Map<String, Object>>) results.get(Constantes.RETURN_RESULT_SET_PREFIX);

			if (rs.size() > 0) {
				for (Map<String, Object> map : rs) {

					menu = new Menu();
					
					
					lista.add(menu);
					
				}
			}
			
			listaMenu.setLista(lista);
			listaMenu.setTotalRegistros(totalRegistros);

		} catch (Exception e) {
			Log.info("error al llenar menu padre");
		}

		return listaMenu;
		
	}

	@Override
	public ListarMenu menuHijos(int nroDePagina, int registrosPorPagina, String nombrePadre) {
		
		ListarMenu listaMenu = new ListarMenu();
		List<Menu> lista = new ArrayList<>();
		Menu menu = null;

		try {
			jdbcCall = new SimpleJdbcCall(jdbcTemplate.getDataSource());
			jdbcCall
			//.withCatalogName("seguridad")
			.withProcedureName("SP_MENU_HIJOS")
			.declareParameters(
					new SqlParameter("NombrePadre", Types.VARCHAR),
					new SqlParameter("NroDePagina", Types.INTEGER),
					new SqlParameter("RegistrosPorPagina", Types.INTEGER),
					new SqlOutParameter("TotalRegistros", 	Types.INTEGER)
			);

			MapSqlParameterSource parametros = new MapSqlParameterSource();
			parametros.addValue("NombrePadre", 			nombrePadre);
			parametros.addValue("NroDePagina", 			nroDePagina);
			parametros.addValue("RegistrosPorPagina", 	registrosPorPagina);

			Map<String, Object> results = jdbcCall.execute(parametros);
			int totalRegistros = (Integer) results.get("TotalRegistros");
			List<Map<String, Object>> rs = (List<Map<String, Object>>) results.get(Constantes.RETURN_RESULT_SET_PREFIX);

			if (rs.size() > 0) {
				for (Map<String, Object> map : rs) {

					menu = new Menu();
		
					
					lista.add(menu);
					
				}
			}
			
			listaMenu.setLista(lista);
			listaMenu.setTotalRegistros(totalRegistros);

		} catch (Exception e) {
			Log.info("error al obtener menu hijo");
		}

		return listaMenu;
		
	}

}
