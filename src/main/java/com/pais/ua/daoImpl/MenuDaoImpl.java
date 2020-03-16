package com.pais.ua.daoImpl;

import java.sql.Types;

import org.jfree.util.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import com.pais.ua.dao.MenuDao;
import com.pais.ua.model.seguridad.Menu;

@Repository
public class MenuDaoImpl implements MenuDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	private SimpleJdbcCall jdbcCall;

	@Override
	public void crear(Menu menu) {
		
		jdbcCall = new SimpleJdbcCall(jdbcTemplate.getDataSource());
		jdbcCall
		.withSchemaName("SEGURIDAD")
		.withProcedureName("menuMantenimiento")
		.declareParameters(
				new SqlParameter("PROCESO", 		Types.VARCHAR),
				new SqlParameter("FID_MENU", 		Types.INTEGER),
				new SqlParameter("CID_NOMBRE", 		Types.VARCHAR),
				new SqlParameter("CID_URL", 		Types.VARCHAR),
				new SqlParameter("CID_ICONO", 		Types.VARCHAR),
				new SqlParameter("NUM_SECUENCIA", 	Types.INTEGER),
				new SqlParameter("FLG_ACTIVO", 		Types.INTEGER),
				new SqlParameter("FID_ACCION", 		Types.INTEGER),
				new SqlParameter("ID_CODIGO", 		Types.INTEGER)
		);

		MapSqlParameterSource parametros = new MapSqlParameterSource();
		parametros.addValue("PROCESO", 			"CREAR");
		parametros.addValue("FID_MENU", 		menu.getFidMenu());
		parametros.addValue("CID_NOMBRE", 		menu.getCidNombre());
		parametros.addValue("CID_URL", 			menu.getCidUrl());
		parametros.addValue("CID_ICONO", 		menu.getCidIcono());
		parametros.addValue("NUM_SECUENCIA", 	menu.getNumSecuencia());
		parametros.addValue("FLG_ACTIVO", 		menu.getFlgActivo());
		parametros.addValue("ID_CODIGO", 		null);
		
		jdbcCall.execute(parametros);
		
	}

	@Override
	public void actualizar(Menu menu) {
		
		try {
			jdbcCall = new SimpleJdbcCall(jdbcTemplate.getDataSource());
			jdbcCall
			.withSchemaName("SEGURIDAD")
			.withProcedureName("menuMantenimiento")
			.declareParameters(
					new SqlParameter("PROCESO", 		Types.VARCHAR),
					new SqlParameter("FID_MENU", 		Types.INTEGER),
					new SqlParameter("CID_NOMBRE", 		Types.VARCHAR),
					new SqlParameter("CID_URL", 		Types.VARCHAR),
					new SqlParameter("CID_ICONO", 		Types.VARCHAR),
					new SqlParameter("NUM_SECUENCIA", 	Types.INTEGER),
					new SqlParameter("FLG_ACTIVO", 		Types.INTEGER),
					new SqlParameter("FID_ACCION", 		Types.INTEGER),
					new SqlParameter("ID_CODIGO", 		Types.INTEGER)
			);

			MapSqlParameterSource parametros = new MapSqlParameterSource();
			parametros.addValue("PROCESO", 			"EDITAR");
			parametros.addValue("FID_MENU", 		menu.getFidMenu());
			parametros.addValue("CID_NOMBRE", 		menu.getCidNombre());
			parametros.addValue("CID_URL", 			menu.getCidUrl());
			parametros.addValue("CID_ICONO", 		menu.getCidIcono());
			parametros.addValue("NUM_SECUENCIA", 	menu.getNumSecuencia());
			parametros.addValue("FLG_ACTIVO", 		menu.getFlgActivo());
			parametros.addValue("ID_CODIGO", 		menu.getIdCodigo());
			
			jdbcCall.execute(parametros);
			
		} catch (Exception e) {
			Log.info("error al actualizar menu");
		}
		
	}
	
	@Override
	public void eliminar(int idMenu) {

			jdbcCall = new SimpleJdbcCall(jdbcTemplate.getDataSource());
			jdbcCall
			.withSchemaName("SEGURIDAD")
			.withProcedureName("menuMantenimiento")
			.declareParameters(
					new SqlParameter("PROCESO", 		Types.VARCHAR),
					new SqlParameter("FID_MENU", 		Types.INTEGER),
					new SqlParameter("CID_NOMBRE", 		Types.VARCHAR),
					new SqlParameter("CID_URL", 		Types.VARCHAR),
					new SqlParameter("CID_ICONO", 		Types.VARCHAR),
					new SqlParameter("NUM_SECUENCIA", 	Types.INTEGER),
					new SqlParameter("FLG_ACTIVO", 		Types.INTEGER),
					new SqlParameter("FID_ACCION", 		Types.INTEGER),
					new SqlParameter("ID_CODIGO", 		Types.INTEGER)
			);

			MapSqlParameterSource parametros = new MapSqlParameterSource();
			parametros.addValue("PROCESO", 			"BORRAR");
			parametros.addValue("FID_MENU", 		null);
			parametros.addValue("CID_NOMBRE", 		null);
			parametros.addValue("CID_URL", 			null);
			parametros.addValue("CID_ICONO", 		null);
			parametros.addValue("NUM_SECUENCIA", 	null);
			parametros.addValue("FLG_ACTIVO", 		null);
			parametros.addValue("FID_ACCION", 		null);
			parametros.addValue("ID_CODIGO", 		idMenu);

			jdbcCall.execute(parametros);
		
	}
}
