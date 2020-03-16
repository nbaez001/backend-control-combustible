package com.pais.ua.daoImpl;

import java.math.BigDecimal;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import com.pais.ua.dao.BienesPatrimonialesDao;
import com.pais.ua.entity.TipoLbcteVhcloEntity;
import com.microsoft.sqlserver.jdbc.SQLServerDataTable;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import com.pais.ua.api.ApiOutResponse;
import com.pais.ua.api.Item;
import com.pais.ua.api.request.AtcloEmgciaXVehiculoRequest;
import com.pais.ua.api.request.BienPatrimonialRequest;
import com.pais.ua.api.request.BreveteRequest;
import com.pais.ua.api.request.BuscaEmpleadoRequest;
import com.pais.ua.api.request.BuscaGElectrogenoRequest;
import com.pais.ua.api.request.BuscaVehiculoRequest;
import com.pais.ua.api.request.ConductorBreveteRequest;
import com.pais.ua.api.request.ConductorVehiculoRequest;
import com.pais.ua.api.request.DenominacionBienPorPlataformaRequest;
import com.pais.ua.api.request.EliminaConductorBreveteRequest;
import com.pais.ua.api.request.EmpleadoPuestoRequest;
import com.pais.ua.api.request.FiltroDenominacionRequest;
import com.pais.ua.api.request.PerfilUsuarioModuloRequest;
import com.pais.ua.api.request.TipoLbcteVhcloRequest;
import com.pais.ua.api.response.AtcloEmgciaVehiculoResponse;
import com.pais.ua.api.response.BienPatrimonialResponse;
import com.pais.ua.api.response.BuscaEmpleadoResponse;
import com.pais.ua.api.response.BuscaVehiculoResponse;
import com.pais.ua.api.response.ClaseResponse;
import com.pais.ua.api.response.ConductorVehiculoResponse;
import com.pais.ua.api.response.DenominacionBienPorPlataformaResonse;
import com.pais.ua.api.response.EmpleadoPuestoResponse;
import com.pais.ua.api.response.FiltroDenominacionResponse;
import com.pais.ua.api.response.GrupoResponse;
import com.pais.ua.api.response.PerfilUsuarioModuloResponse;
import com.pais.ua.api.response.TipoLbcteVhcloResponse;
import com.pais.ua.api.response.TipoLubricanteResponse;
import com.pais.ua.util.Constantes;
import com.pais.ua.util.Util;

@Repository
public class BienesPatrimonialesDaoImpl implements BienesPatrimonialesDao {
	
	private  final Log log = LogFactory.getLog(getClass());
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	private SimpleJdbcCall jdbcCall;
	
	@Override
	public ApiOutResponse listarUnidadTerritorial() {
		log.info("[DAO listarUnidadTerritorial] INICIO  ");	
		ApiOutResponse outResponse = new ApiOutResponse();
		SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate.getDataSource());
		List<Item> unidadTs = new ArrayList<>();
		
		int codigoRpta=0;
		String mensajeRpta="";
		try {
			
	
			jdbcCall.withSchemaName("UADM")
			.withProcedureName("bienesPatrimonialesDB")
			.declareParameters(
					new SqlParameter("ID_OPCION", 		Types.VARCHAR),
					new SqlParameter("ID_BUSCADO", 		Types.INTEGER),
					new SqlParameter("CAD_LIKE", 		Types.VARCHAR),
					new SqlOutParameter("COD_RESULTADO", 	Types.INTEGER),
					new SqlOutParameter("MSG_RESULTADO", 	Types.VARCHAR)
				);
	
			MapSqlParameterSource parametros = new MapSqlParameterSource();
			parametros.addValue("ID_OPCION","LISTA_UNIDAD_TERRITORIAL");
			parametros.addValue("ID_BUSCADO",0);
			parametros.addValue("CAD_LIKE","");
			Map<String, Object> results = jdbcCall.execute(parametros);
			
			List<Map<String, Object>> rs = (List<Map<String, Object>>) results.get(Constantes.RETURN_RESULT_SET_PREFIX);
			
			codigoRpta=Integer.parseInt(results.get("COD_RESULTADO").toString());
			mensajeRpta=results.get("MSG_RESULTADO").toString();
			
			if (results.isEmpty()||results.size()<1) {
				log.info("Sin registros, verificar el procedimiento bienesPatrimonialesDB>>");
				return  new ApiOutResponse();
			}
			
			if (codigoRpta!=1) {//VERIFICACION DE ERROR EN LA BD
				log.error("[listarUnidadTerritorial] Ocurrio un error en la operacion del Procedimiento almacenado bienesPatrimonialesDB : "+mensajeRpta);
				outResponse.setCodResultado(codigoRpta);
				outResponse.setTotal(0);
				outResponse.setResponse("Error en la BD");
				outResponse.setMsgResultado(mensajeRpta);
				return outResponse;
			}
			  
			if(rs!=null) {
				if (rs.size() > 0) {
					for (Map<String, Object> map : rs) {
						Item item = new Item();
						item.setIdCodigo(Long.parseLong(map.get("ID_CODIGO").toString()) );
						item.setCidNombre(map.get("CID_NOMBRE")!=null ? map.get("CID_NOMBRE").toString() : "" );
						
						unidadTs.add(item);
					}
					outResponse.setResponse(unidadTs);
					outResponse.setCodResultado( results.get("COD_RESULTADO")!=null ? Integer.parseInt(results.get("COD_RESULTADO").toString()) : 500);
					outResponse.setMsgResultado( results.get("MSG_RESULTADO")!=null ? results.get("MSG_RESULTADO").toString() : "-" );
				}else {
					outResponse.setCodResultado(2);//Codigo para indicar que no existen registros en la BD
					outResponse.setTotal(0);
					outResponse.setResponse("");
					outResponse.setMsgResultado("¡No existen registros!");
				}
			}
			
		} catch (Exception e) {
			log.error("DAO listarUnidadTerritorial>>>>"+this.getClass().getName(), e);
			outResponse.setCodResultado(500);
			outResponse.setMsgResultado( "Error en la Base de datos!.");
		}
			
		log.info("[DAO listarUnidadTerritorial] FIN  ");
		return outResponse;
	}
	
	@Override
	public ApiOutResponse listarPlataformasPorUnidadTerritorial(int cod_uTerri) {
		log.info("[DAO listarPlataformasPorUnidadTerritorial] INICIO  ");	
		ApiOutResponse outResponse = new ApiOutResponse();
		List<Item> tambos = new ArrayList<>();
		
		int codigoRpta=0, flgResult1 = 0, flgResult2 = 0;
		String mensajeRpta="";
		try {
			SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate.getDataSource());
	
			jdbcCall.withSchemaName("UADM")
			.withProcedureName("bienesPatrimonialesDB")
			.declareParameters(
					new SqlParameter("ID_OPCION", 		Types.VARCHAR),
					new SqlParameter("ID_BUSCADO", 		Types.INTEGER),
					new SqlParameter("CAD_LIKE", 		Types.VARCHAR),
					new SqlOutParameter("COD_RESULTADO", 	Types.INTEGER),
					new SqlOutParameter("MSG_RESULTADO", 	Types.VARCHAR)
				);
	
			MapSqlParameterSource parametros = new MapSqlParameterSource();
			parametros.addValue("ID_OPCION","LISTA_PLATAFORMA_U_TERRITORIAL");
			parametros.addValue("ID_BUSCADO",cod_uTerri);
			parametros.addValue("CAD_LIKE","");
			
			Map<String, Object> results = jdbcCall.execute(parametros);
			List<Map<String, Object>> rs = (List<Map<String, Object>>) results.get(Constantes.RETURN_RESULT_SET_PREFIX);
			List<Map<String, Object>> rs2 = (List<Map<String, Object>>) results.get("#result-set-2");
			
//			System.out.println("=====================================");
//			System.out.println(Constantes.RETURN_RESULT_SET_PREFIX);
//			System.out.println(results);						
//			System.out.println(rs);
//			System.out.println(rs2);
//			System.out.println("=====================================");
			
			codigoRpta=Integer.parseInt(results.get("COD_RESULTADO").toString());
			mensajeRpta=results.get("MSG_RESULTADO").toString();
			
			if (results.isEmpty()||results.size()<1) {
				log.info("Sin registros, verificar el procedimiento bienesPatrimonialesDB>>");
				return  new ApiOutResponse();
			}
			
			if (codigoRpta!=1) {//VERIFICACION DE ERROR EN LA BD
				log.error("[listarPlataformasPorUnidadTerritorial] Ocurrio un error en la operacion del Procedimiento almacenado bienesPatrimonialesDB : "+mensajeRpta);
				outResponse.setCodResultado(codigoRpta);
				outResponse.setTotal(0);
				outResponse.setResponse("Error en la BD");
				outResponse.setMsgResultado(mensajeRpta);
				return outResponse;
			}
			
			if(rs2!=null) {
				if (rs2.size() > 0) {
					for (Map<String, Object> map : rs2) {
						Item item = new Item();
						item.setIdCodigo(Long.parseLong(map.get("OT_ID_CODIGO").toString()) );
						item.setCidCodigo( map.get("OT_ID_PLATAFORMA")!=null ? map.get("OT_ID_PLATAFORMA").toString() : "");
						item.setCidNombre(map.get("OT_CID_NOMBRE")!=null ? map.get("OT_CID_NOMBRE").toString() : "" );
						
						tambos.add(item);
					}
					flgResult1 = 1;
				}else {
					flgResult1 = 0;
				}
			}
			if(rs!=null) {
				if (rs.size() > 0) {
					for (Map<String, Object> map : rs) {
						Item item = new Item();
						item.setIdCodigo(Long.parseLong(map.get("ID_CODIGO").toString()) );
						item.setCidCodigo( map.get("ID_PLATAFORMA")!=null ? map.get("ID_PLATAFORMA").toString() : "");
						item.setCidNombre(map.get("CID_NOMBRE")!=null ? map.get("CID_NOMBRE").toString() : "" );
						
						tambos.add(item);
					}
					flgResult2 = 1;
				}else {
					if (flgResult1 == 0) {
						flgResult2 = 0;
					}
				}
			}
			if (flgResult1 == 1 || flgResult2 == 1) {
				outResponse.setResponse(tambos);
				outResponse.setCodResultado( results.get("COD_RESULTADO")!=null ? Integer.parseInt(results.get("COD_RESULTADO").toString()) : 500);
				outResponse.setMsgResultado( results.get("MSG_RESULTADO")!=null ? results.get("MSG_RESULTADO").toString() : "-" );
			}else {
				outResponse.setCodResultado(2);//Codigo para indicar que no existen registros en la BD
				outResponse.setTotal(0);
				outResponse.setResponse("");
				outResponse.setMsgResultado("¡No existen registros!");
			}
		} catch (Exception e) {
			log.error("DAO listarPlataformasPorUnidadTerritorial>>>>"+this.getClass().getName(), e);
			outResponse.setCodResultado(500);
			outResponse.setMsgResultado( "Error en la Base de datos!.");
		}
			
		log.info("[DAO listarPlataformasPorUnidadTerritorial] FIN  ");
		return outResponse;
	}
	
	@Override
	public ApiOutResponse listarDenominaBienPorPlataforma(DenominacionBienPorPlataformaRequest objeto) {
		log.info("[DAO listarDenominaBienPorPlataforma] INICIO  ");	
		ApiOutResponse outResponse = new ApiOutResponse();
		List<DenominacionBienPorPlataformaResonse> denominaciones = new ArrayList<>(); // LISTA DE DENOMINACIONES PARA PLATAFORMAS U OFICINAS RELACIONADAS A UNIDAD TERRITORIAL
		
		int codigoRpta = 0, flgResult1 = 0, flgResult2 = 0;
		String mensajeRpta="";
		
		try {
			SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate.getDataSource());
	
			jdbcCall.withSchemaName("UADM")
			.withProcedureName("listaDenominacionPlataforma")
			.declareParameters(
					new SqlParameter("ID_OPCION", 		Types.VARCHAR),
					new SqlParameter("ID_PLATAFORMA", 		Types.BIGINT),
					new SqlParameter("ID_UNIDAD_TERRITORIAL", 		Types.BIGINT),
					new SqlParameter("CAD_LIKE", 		Types.VARCHAR),
					new SqlOutParameter("COD_RESULTADO", 	Types.INTEGER),
					new SqlOutParameter("MSG_RESULTADO", 	Types.VARCHAR)
				);
	
			MapSqlParameterSource parametros = new MapSqlParameterSource();
			String txtCaso = "";
			Long idCodigo = (long)0;
			
			if (objeto.getIdUTerritorial() == 0) {
				txtCaso = "LISTA_POR__OFCNA_PLAT";
			}else {
				if (objeto.getIdUTerritorial() > 0 && objeto.getIdPlataforma() == 0) {
					txtCaso = "LISTA_POR__OFCNA_PLAT";
				}
				if (objeto.getIdUTerritorial() > 0 && objeto.getIdPlataforma() == -1) {
					txtCaso = "LISTA_POR_OFICINA";
				}
				if (objeto.getIdUTerritorial() > 0 && objeto.getIdPlataforma() > 0) {
					txtCaso = "LISTA_POR_PLATAFORMA";
				}
			}
			
			String cadGrupoClase = "%" + objeto.getCadGrupo()+objeto.getCadClase() + "%";
			parametros.addValue("ID_OPCION",txtCaso);
			parametros.addValue("ID_PLATAFORMA",objeto.getIdPlataforma());
			parametros.addValue("ID_UNIDAD_TERRITORIAL",objeto.getIdUTerritorial());
			parametros.addValue("CAD_LIKE",cadGrupoClase);
			
			Map<String, Object> results = jdbcCall.execute(parametros);
			List<Map<String, Object>> rs = (List<Map<String, Object>>) results.get(Constantes.RETURN_RESULT_SET_PREFIX);
				
			codigoRpta=Integer.parseInt(results.get("COD_RESULTADO").toString());
			mensajeRpta=results.get("MSG_RESULTADO").toString();

			if (results.isEmpty()||results.size()<1) {
				log.info("Sin registros, verificar el procedimiento listaDenominacionPlataforma>>");
				return  new ApiOutResponse();
			}
			
			if (codigoRpta!=1) {//VERIFICACION DE ERROR EN LA BD
				log.error("[listarDenominaBienPorPlataforma] Ocurrio un error en la operacion del Procedimiento almacenado listaDenominacionPlataforma : "+mensajeRpta);
				outResponse.setCodResultado(codigoRpta);
				outResponse.setTotal(0);
				outResponse.setResponse("Error en la BD");
				outResponse.setMsgResultado(mensajeRpta);
				return outResponse;
			}

			if (txtCaso.equalsIgnoreCase("LISTA_POR__OFCNA_PLAT")) {
				List<Map<String, Object>> rs2 = (List<Map<String, Object>>) results.get("#result-set-2");
				if(rs2!=null) {
					if (rs2.size() > 0) {
						for (Map<String, Object> mapeo : rs2) {
							DenominacionBienPorPlataformaResonse item = new DenominacionBienPorPlataformaResonse();
							item.setIdDenominacion(mapeo.get("ID_CODIGO")!=null ? (long)mapeo.get("ID_CODIGO"):0);
							item.setNomDenominacion(mapeo.get("CID_NOMBRE")!=null ? mapeo.get("CID_NOMBRE").toString() : "" );
							
							denominaciones.add(item);
						}
						flgResult1 = 1;
					}else {
						flgResult1 = 0;
					}
				}
			}
			
			if(rs!=null) {
				if (rs.size() > 0) {
					for (Map<String, Object> map : rs) {
						DenominacionBienPorPlataformaResonse item = new DenominacionBienPorPlataformaResonse();
						item.setIdDenominacion(map.get("ID_CODIGO")!=null ? (long)map.get("ID_CODIGO"):0);
						item.setNomDenominacion(map.get("CID_NOMBRE")!=null ? map.get("CID_NOMBRE").toString() : "" );
						
						denominaciones.add(item);
					}
					flgResult2 = 1;
				}else {
					if (flgResult1 == 0) {
						flgResult2 = 0;
					}
				}
			}
			if (flgResult1 == 1 || flgResult2 == 1) {
				outResponse.setResponse(denominaciones);
				outResponse.setCodResultado( results.get("COD_RESULTADO")!=null ? Integer.parseInt(results.get("COD_RESULTADO").toString()) : 500);
				outResponse.setMsgResultado( results.get("MSG_RESULTADO")!=null ? results.get("MSG_RESULTADO").toString() : "-" );
			}else {
				outResponse.setCodResultado(2);//Codigo para indicar que no existen registros en la BD
				outResponse.setTotal(0);
				outResponse.setResponse("");
				outResponse.setMsgResultado("¡No existen registros!");
			}
		} catch (Exception e) {
			log.error("DAO listarDenominaBienPorPlataforma>>>>"+this.getClass().getName(), e);
			outResponse.setCodResultado(500);
			outResponse.setMsgResultado( "Error en la Base de datos!.");
		}
			
		log.info("[DAO listarDenominaBienPorPlataforma] FIN  ");
		return outResponse;
	}
	
	@Override
	public ApiOutResponse buscarvehiculo(int id_ut,int id_pla,int id_deno,String fec_ini,String fec_fin) {
		log.info("[DAO buscarvehiculo] INICIO  ");	
		ApiOutResponse outResponse = new ApiOutResponse();
		List<BienPatrimonialResponse> bienesPatrimoniales = new ArrayList<>();
		
		int codigoRpta=0;
		String mensajeRpta="", txtCaso = "";
		
		if (id_ut == 0) {
			txtCaso = "BUSCA_VHCLO_OFCNA_PLAT";
		}else {
			if (id_ut > 0 && id_pla == 0) {
				txtCaso = "BUSCA_VHCLO_OFCNA_PLAT";
			}
			if (id_ut > 0 && id_pla == -1) {
				txtCaso = "BUSCA_VEHICULO_OFCNA";
			}
			if (id_ut > 0 && id_pla > 0) {
				txtCaso = "BUSCA_VEHICULO_PLAT";
			}
		}
		
		try {
			SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate.getDataSource());
	
			jdbcCall.withSchemaName("UADM")
			.withProcedureName("buscarBienesPatrimoniales")
			.declareParameters(
					new SqlParameter("ID_OPCION", 		Types.VARCHAR),
					new SqlParameter("ID_UT", 		Types.VARCHAR),
					new SqlParameter("ID_PLA", 		Types.VARCHAR),
					new SqlParameter("ID_DENO", 		Types.VARCHAR),
					new SqlParameter("CAD_LIKE", 		Types.VARCHAR),
					new SqlParameter("FEC_INI", 		Types.VARCHAR),
					new SqlParameter("FEC_FIN", 		Types.VARCHAR),
					new SqlOutParameter("COD_RESULTADO", 	Types.INTEGER),
					new SqlOutParameter("MSG_RESULTADO", 	Types.VARCHAR)
				);
	
			MapSqlParameterSource parametros = new MapSqlParameterSource();
			
			String cid_ut = id_ut != 0 ? String.valueOf(id_ut) : "", 
					cid_pla = id_pla != 0 ? String.valueOf(id_pla) : "",
					cid_deno = id_deno != 0 ? String.valueOf(id_deno) : "";
					
			int flgResult1 = 0, flgResult2 = 0;
					
			parametros.addValue("ID_OPCION",txtCaso);
			parametros.addValue("ID_UT","%"+cid_ut+"%");
			parametros.addValue("ID_PLA","%"+cid_pla+"%");
			parametros.addValue("ID_DENO","%"+cid_deno+"%");
			parametros.addValue("CAD_LIKE","");
			parametros.addValue("FEC_INI",fec_ini);
			parametros.addValue("FEC_FIN",fec_fin);
			
			Map<String, Object> results = jdbcCall.execute(parametros);
			List<Map<String, Object>> rs = (List<Map<String, Object>>) results.get(Constantes.RETURN_RESULT_SET_PREFIX);
			
			codigoRpta=Integer.parseInt(results.get("COD_RESULTADO").toString());
			mensajeRpta=results.get("MSG_RESULTADO").toString();
			
			if (results.isEmpty()||results.size()<1) {
				log.info("Sin registros, verificar el procedimiento buscarBienesPatrimoniales>>");
				return  new ApiOutResponse();
			}
			
			if (codigoRpta!=1) {//VERIFICACION DE ERROR EN LA BD
				log.error("[buscarvehiculo] Ocurrio un error en la operacion del Procedimiento almacenado buscarBienesPatrimoniales : "+mensajeRpta);
				outResponse.setCodResultado(codigoRpta);
				outResponse.setTotal(0);
				outResponse.setResponse("Error en la BD");
				outResponse.setMsgResultado(mensajeRpta);
				return outResponse;
			}
			
			if (txtCaso == "BUSCA_VHCLO_OFCNA_PLAT") {
				List<Map<String, Object>> rs2 = (List<Map<String, Object>>) results.get("#result-set-2");
				if(rs2!=null) {
					if (rs2.size() > 0) {
						for (Map<String, Object> mapeo : rs2) {
							BienPatrimonialResponse listadoBienPatrimonial = new BienPatrimonialResponse();
							
							listadoBienPatrimonial.setIdUnidadT(mapeo.get("ID_UT")!=null ? (long)mapeo.get("ID_UT"):0);
							listadoBienPatrimonial.setNombreUnidadT( mapeo.get("UNIDAD")!=null ? mapeo.get("UNIDAD").toString() : "");
							listadoBienPatrimonial.setIdPlataforma( mapeo.get("ID_PLA")!=null ? (long)mapeo.get("ID_PLA"):0 );
							listadoBienPatrimonial.setNombrePlataforma( mapeo.get("TAMBO")!=null ? mapeo.get("TAMBO").toString() : "");						
							listadoBienPatrimonial.setIdBien( mapeo.get("ID_BIEN")!=null ? (long)mapeo.get("ID_BIEN"):0 );
							listadoBienPatrimonial.setCodigoBienPatrimonial( mapeo.get("COD_PATRI")!=null ? mapeo.get("COD_PATRI").toString() : "");
							listadoBienPatrimonial.setIdDenominacion( mapeo.get("COD_DENO")!=null ? (long)mapeo.get("COD_DENO"):0 );
							listadoBienPatrimonial.setNombreDenominacion( mapeo.get("DENOMINACION")!=null ? mapeo.get("DENOMINACION").toString() : "");						
							listadoBienPatrimonial.setIdMarca( mapeo.get("ID_MARCA")!=null ? (long)mapeo.get("ID_MARCA"):0 );
							listadoBienPatrimonial.setNombreMarca( mapeo.get("MARCA")!=null ? mapeo.get("MARCA").toString() : "");
							listadoBienPatrimonial.setIdModelo( mapeo.get("ID_MODELO")!=null ? (long)mapeo.get("ID_MODELO"):0 );
							listadoBienPatrimonial.setNombreModelo( mapeo.get("MODELO")!=null ? mapeo.get("MODELO").toString() : "");						
							listadoBienPatrimonial.setIdTipoBien( mapeo.get("ID_TIPO_BIEN")!=null ? (long)mapeo.get("ID_TIPO_BIEN"):0 );
							listadoBienPatrimonial.setNombreTipoBien( mapeo.get("TIPO")!=null ? mapeo.get("TIPO").toString() : "");
							listadoBienPatrimonial.setSerieBien( mapeo.get("SERIE")!=null ? mapeo.get("SERIE").toString() : "");
							listadoBienPatrimonial.setIdVehiculo( mapeo.get("ID_VEHICULO")!=null ? (long)mapeo.get("ID_VEHICULO"):0 );
							listadoBienPatrimonial.setPlacaVehiculo( mapeo.get("PLACA")!=null ? mapeo.get("PLACA").toString() : "");
							listadoBienPatrimonial.setListaColor( mapeo.get("COLOR")!=null ? mapeo.get("COLOR").toString() : "");
							
							listadoBienPatrimonial.setIdEtdoCsvcion( mapeo.get("ID_ETDO_CSVCION")!=null ? (long)mapeo.get("ID_ETDO_CSVCION"):0 );
							listadoBienPatrimonial.setEstadoEtdoCsvcion( mapeo.get("ESTADO")!=null ? mapeo.get("ESTADO").toString() : "");
							
							bienesPatrimoniales.add(listadoBienPatrimonial);							
						}
						flgResult1 = 1;
					}else {
						flgResult1 = 0;
					}
				}
			}
			
			if(rs!=null) {
				if (rs.size() > 0) {
					for (Map<String, Object> map : rs) {
						BienPatrimonialResponse listadoBienPatrimonialResponse = new BienPatrimonialResponse();
						
						listadoBienPatrimonialResponse.setIdUnidadT(map.get("ID_UT")!=null ? (long)map.get("ID_UT"):0);
						listadoBienPatrimonialResponse.setNombreUnidadT( map.get("UNIDAD")!=null ? map.get("UNIDAD").toString() : "");
						listadoBienPatrimonialResponse.setIdPlataforma( map.get("ID_PLA")!=null ? (long)map.get("ID_PLA"):0 );
						listadoBienPatrimonialResponse.setNombrePlataforma( map.get("TAMBO")!=null ? map.get("TAMBO").toString() : "");						
						listadoBienPatrimonialResponse.setIdBien( map.get("ID_BIEN")!=null ? (long)map.get("ID_BIEN"):0 );
						listadoBienPatrimonialResponse.setCodigoBienPatrimonial( map.get("COD_PATRI")!=null ? map.get("COD_PATRI").toString() : "");
						listadoBienPatrimonialResponse.setIdDenominacion( map.get("COD_DENO")!=null ? (long)map.get("COD_DENO"):0 );
						listadoBienPatrimonialResponse.setNombreDenominacion( map.get("DENOMINACION")!=null ? map.get("DENOMINACION").toString() : "");						
						listadoBienPatrimonialResponse.setIdMarca( map.get("ID_MARCA")!=null ? (long)map.get("ID_MARCA"):0 );
						listadoBienPatrimonialResponse.setNombreMarca( map.get("MARCA")!=null ? map.get("MARCA").toString() : "");
						listadoBienPatrimonialResponse.setIdModelo( map.get("ID_MODELO")!=null ? (long)map.get("ID_MODELO"):0 );
						listadoBienPatrimonialResponse.setNombreModelo( map.get("MODELO")!=null ? map.get("MODELO").toString() : "");						
						listadoBienPatrimonialResponse.setIdTipoBien( map.get("ID_TIPO_BIEN")!=null ? (long)map.get("ID_TIPO_BIEN"):0 );
						listadoBienPatrimonialResponse.setNombreTipoBien( map.get("TIPO")!=null ? map.get("TIPO").toString() : "");
						listadoBienPatrimonialResponse.setSerieBien( map.get("SERIE")!=null ? map.get("SERIE").toString() : "");
						listadoBienPatrimonialResponse.setIdVehiculo( map.get("ID_VEHICULO")!=null ? (long)map.get("ID_VEHICULO"):0 );
						listadoBienPatrimonialResponse.setPlacaVehiculo( map.get("PLACA")!=null ? map.get("PLACA").toString() : "");
						listadoBienPatrimonialResponse.setListaColor( map.get("COLOR")!=null ? map.get("COLOR").toString() : "");
						
						listadoBienPatrimonialResponse.setIdEtdoCsvcion( map.get("ID_ETDO_CSVCION")!=null ? (long)map.get("ID_ETDO_CSVCION"):0 );
						listadoBienPatrimonialResponse.setEstadoEtdoCsvcion( map.get("ESTADO")!=null ? map.get("ESTADO").toString() : "");
						
						bienesPatrimoniales.add(listadoBienPatrimonialResponse);
					}
					flgResult2 = 1;
				}else {
					if (flgResult1 == 0) {
						flgResult2 = 0;
					}
				}
			}
			if (flgResult1 == 1 || flgResult2 == 1) {
				outResponse.setResponse(bienesPatrimoniales);
				outResponse.setCodResultado( results.get("COD_RESULTADO")!=null ? Integer.parseInt(results.get("COD_RESULTADO").toString()) : 500);
				outResponse.setMsgResultado( results.get("MSG_RESULTADO")!=null ? results.get("MSG_RESULTADO").toString() : "-" );
			}else {
				outResponse.setCodResultado(2);//Codigo para indicar que no existen registros en la BD
				outResponse.setTotal(0);
				outResponse.setResponse("");
				outResponse.setMsgResultado("¡No existen registros!");
			}
		} catch (Exception e) {
			log.error("DAO buscarvehiculo>>>>"+this.getClass().getName(), e);
			outResponse.setCodResultado(500);
			outResponse.setMsgResultado( "Error en la Base de datos!.");
		}
			
		log.info("[DAO buscarvehiculo] FIN  ");
		return outResponse;
	}
	
	@Override
	public ApiOutResponse buscarVehiculoPost(BuscaVehiculoRequest objeto) {
		log.info("[DAO buscarVehiculoPost] INICIO  ");	
		ApiOutResponse outResponse = new ApiOutResponse();
		List<BuscaVehiculoResponse> vehiculos = new ArrayList<>();
		
		int codigoRpta=0;
		String mensajeRpta="";
		
		try {
			SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate.getDataSource());
	
			jdbcCall.withSchemaName("UADM")
			.withProcedureName("buscarVehiculoBP")
			.declareParameters(
					new SqlParameter("ID_UT", 		Types.BIGINT),
					new SqlParameter("ID_PLA", 		Types.BIGINT),
					new SqlParameter("ID_DENO", 		Types.BIGINT),
					new SqlParameter("FEC_INI", 		Types.VARCHAR),
					new SqlParameter("FEC_FIN", 		Types.VARCHAR),
					new SqlOutParameter("COD_RESULTADO", 	Types.INTEGER),
					new SqlOutParameter("MSG_RESULTADO", 	Types.VARCHAR)
				);
	
			MapSqlParameterSource parametros = new MapSqlParameterSource();
			
			int flgResult1 = 0, flgResult2 = 0;
					
			parametros.addValue("ID_UT",objeto.getIdUTerritorial());
			parametros.addValue("ID_PLA",objeto.getIdPlataforma());
			parametros.addValue("ID_DENO",objeto.getIdDenominacion());
			if(objeto.getFecIni() == null || objeto.getFecIni().isEmpty()) {
				parametros.addValue("FEC_INI","");
			}else {
				parametros.addValue("FEC_INI",objeto.getFecIni());
			}
			if(objeto.getFecFin() == null || objeto.getFecFin().isEmpty()) {
				parametros.addValue("FEC_FIN","");
			}else {
				parametros.addValue("FEC_FIN",objeto.getFecFin());
			}
				
			
			Map<String, Object> results = jdbcCall.execute(parametros);
			List<Map<String, Object>> rs = (List<Map<String, Object>>) results.get(Constantes.RETURN_RESULT_SET_PREFIX);
			
//			System.out.println("=====================================");
//			System.out.println(results);						
//			System.out.println("=====================================");
//			
			codigoRpta=Integer.parseInt(results.get("COD_RESULTADO").toString());
			mensajeRpta=results.get("MSG_RESULTADO").toString();
			
			if (results.isEmpty()||results.size()<1) {
				log.info("Sin registros, verificar el procedimiento buscarVehiculoBP>>");
				return  new ApiOutResponse();
			}
			
			if (codigoRpta!=1) {//VERIFICACION DE ERROR EN LA BD
				log.error("[buscarVehiculoPost] Ocurrio un error en la operacion del Procedimiento almacenado buscarVehiculoBP : "+mensajeRpta);
				outResponse.setCodResultado(codigoRpta);
				outResponse.setTotal(0);
				outResponse.setResponse("Error en la BD");
				outResponse.setMsgResultado(mensajeRpta);
				return outResponse;
			}
			
			if (objeto.getIdPlataforma() == 0) {
				List<Map<String, Object>> rs2 = (List<Map<String, Object>>) results.get("#result-set-2");
				if(rs2!=null) {
					if (rs2.size() > 0) {
						for (Map<String, Object> mapeo : rs2) {
							BuscaVehiculoResponse vehiculo = new BuscaVehiculoResponse();
							
							vehiculo.setIdUTerritorial(mapeo.get("ID_UT")!=null ? (long)mapeo.get("ID_UT"):0);
							vehiculo.setNomUTerritorial( mapeo.get("UNIDAD")!=null ? mapeo.get("UNIDAD").toString() : "");
							vehiculo.setIdPlataforma( mapeo.get("ID_PLA")!=null ? (long)mapeo.get("ID_PLA"):0 );
							vehiculo.setNomPlataforma( mapeo.get("TAMBO")!=null ? mapeo.get("TAMBO").toString() : "");						
							vehiculo.setIdBien( mapeo.get("ID_BIEN")!=null ? (long)mapeo.get("ID_BIEN"):0 );
							vehiculo.setCodPatrimonial( mapeo.get("COD_PATRI")!=null ? mapeo.get("COD_PATRI").toString() : "");
							vehiculo.setIdDenominacion( mapeo.get("COD_DENO")!=null ? (long)mapeo.get("COD_DENO"):0 );
							vehiculo.setNomDenominacion( mapeo.get("DENOMINACION")!=null ? mapeo.get("DENOMINACION").toString() : "");						
							vehiculo.setIdMarca( mapeo.get("ID_MARCA")!=null ? (long)mapeo.get("ID_MARCA"):0 );
							vehiculo.setNomMarca( mapeo.get("MARCA")!=null ? mapeo.get("MARCA").toString() : "");
							vehiculo.setIdModelo( mapeo.get("ID_MODELO")!=null ? (long)mapeo.get("ID_MODELO"):0 );
							vehiculo.setNomModelo( mapeo.get("MODELO")!=null ? mapeo.get("MODELO").toString() : "");						
							vehiculo.setIdTipoBien( mapeo.get("ID_TIPO_BIEN")!=null ? (long)mapeo.get("ID_TIPO_BIEN"):0 );
							vehiculo.setNomTipoBien( mapeo.get("TIPO")!=null ? mapeo.get("TIPO").toString() : "");
							vehiculo.setCidSerie( mapeo.get("SERIE")!=null ? mapeo.get("SERIE").toString() : "");
							vehiculo.setIdVehiculo( mapeo.get("ID_VEHICULO")!=null ? (long)mapeo.get("ID_VEHICULO"):0 );
							vehiculo.setCidPlaca( mapeo.get("PLACA")!=null ? mapeo.get("PLACA").toString() : "");
							vehiculo.setCadColor( mapeo.get("COLOR")!=null ? mapeo.get("COLOR").toString() : "");
							vehiculo.setIdEstadoCsvcion( mapeo.get("ID_ETDO_CSVCION")!=null ? (long)mapeo.get("ID_ETDO_CSVCION"):0 );
							vehiculo.setNomEstadoCsvcion( mapeo.get("ESTADO")!=null ? mapeo.get("ESTADO").toString() : "");
							
							vehiculos.add(vehiculo);							
						}
						flgResult1 = 1;
					}else {
						flgResult1 = 0;
					}
				}
			}
			
			if(rs!=null) {
				if (rs.size() > 0) {
					for (Map<String, Object> map : rs) {
						BuscaVehiculoResponse vehiculo = new BuscaVehiculoResponse();
						
						vehiculo.setIdUTerritorial(map.get("ID_UT")!=null ? (long)map.get("ID_UT"):0);
						vehiculo.setNomUTerritorial( map.get("UNIDAD")!=null ? map.get("UNIDAD").toString() : "");
						vehiculo.setIdPlataforma( map.get("ID_PLA")!=null ? (long)map.get("ID_PLA"):0 );
						vehiculo.setNomPlataforma( map.get("TAMBO")!=null ? map.get("TAMBO").toString() : "");						
						vehiculo.setIdBien( map.get("ID_BIEN")!=null ? (long)map.get("ID_BIEN"):0 );
						vehiculo.setCodPatrimonial( map.get("COD_PATRI")!=null ? map.get("COD_PATRI").toString() : "");
						vehiculo.setIdDenominacion( map.get("COD_DENO")!=null ? (long)map.get("COD_DENO"):0 );
						vehiculo.setNomDenominacion( map.get("DENOMINACION")!=null ? map.get("DENOMINACION").toString() : "");						
						vehiculo.setIdMarca( map.get("ID_MARCA")!=null ? (long)map.get("ID_MARCA"):0 );
						vehiculo.setNomMarca( map.get("MARCA")!=null ? map.get("MARCA").toString() : "");
						vehiculo.setIdModelo( map.get("ID_MODELO")!=null ? (long)map.get("ID_MODELO"):0 );
						vehiculo.setNomModelo( map.get("MODELO")!=null ? map.get("MODELO").toString() : "");						
						vehiculo.setIdTipoBien( map.get("ID_TIPO_BIEN")!=null ? (long)map.get("ID_TIPO_BIEN"):0 );
						vehiculo.setNomTipoBien( map.get("TIPO")!=null ? map.get("TIPO").toString() : "");
						vehiculo.setCidSerie( map.get("SERIE")!=null ? map.get("SERIE").toString() : "");
						vehiculo.setIdVehiculo( map.get("ID_VEHICULO")!=null ? (long)map.get("ID_VEHICULO"):0 );
						vehiculo.setCidPlaca( map.get("PLACA")!=null ? map.get("PLACA").toString() : "");
						vehiculo.setCadColor( map.get("COLOR")!=null ? map.get("COLOR").toString() : "");
						vehiculo.setIdEstadoCsvcion( map.get("ID_ETDO_CSVCION")!=null ? (long)map.get("ID_ETDO_CSVCION"):0 );
						vehiculo.setNomEstadoCsvcion( map.get("ESTADO")!=null ? map.get("ESTADO").toString() : "");
						
						vehiculos.add(vehiculo);
					}
					flgResult2 = 1;
				}else {
					if (flgResult1 == 0) {
						flgResult2 = 0;
					}
				}
			}
			if (flgResult1 == 1 || flgResult2 == 1) {
				outResponse.setResponse(vehiculos);
				outResponse.setCodResultado( results.get("COD_RESULTADO")!=null ? Integer.parseInt(results.get("COD_RESULTADO").toString()) : 500);
				outResponse.setMsgResultado( results.get("MSG_RESULTADO")!=null ? results.get("MSG_RESULTADO").toString() : "-" );
			}else {
				outResponse.setCodResultado(2);//Codigo para indicar que no existen registros en la BD
				outResponse.setTotal(0);
				outResponse.setResponse("");
				outResponse.setMsgResultado("¡No existen registros!");
			}
		} catch (Exception e) {
			log.error("DAO buscarVehiculoPost>>>>"+this.getClass().getName(), e);
			outResponse.setCodResultado(500);
			outResponse.setMsgResultado( "Error en la Base de datos!.");
		}
			
		log.info("[DAO buscarVehiculoPost] FIN  ");
		return outResponse;
	}
	
	@Override
	public ApiOutResponse buscarAtcloEmgciaVehiculo(BienPatrimonialRequest objeto) {
		log.info("[DAO buscarAtcloEmgciaVehiculo] INICIO  ");	
		ApiOutResponse outResponse = new ApiOutResponse();
		List<AtcloEmgciaVehiculoResponse> listadoAtcloEmgciaVehiculos = new ArrayList<>();
		
		int codigoRpta=0;
		String mensajeRpta="";
		try {
			SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate.getDataSource());
	
			jdbcCall.withSchemaName("UADM")
			.withProcedureName("bienesPatrimonialesDB")
			.declareParameters(
					new SqlParameter("ID_OPCION", 		Types.VARCHAR),
					new SqlParameter("ID_BUSCADO", 		Types.INTEGER),
					new SqlParameter("CAD_LIKE", 		Types.VARCHAR),
					new SqlOutParameter("COD_RESULTADO", 	Types.INTEGER),
					new SqlOutParameter("MSG_RESULTADO", 	Types.VARCHAR)
				);
	
			MapSqlParameterSource parametros = new MapSqlParameterSource();

			parametros.addValue("ID_OPCION","LISTA_ATCLO_EMGCIA_POR_VEHICULO");
			parametros.addValue("ID_BUSCADO",objeto.getIdVehiculo());
			parametros.addValue("CAD_LIKE",objeto.getIdDenominacion());
			
			Map<String, Object> results = jdbcCall.execute(parametros);
			List<Map<String, Object>> rs = (List<Map<String, Object>>) results.get(Constantes.RETURN_RESULT_SET_PREFIX);
			
			codigoRpta=Integer.parseInt(results.get("COD_RESULTADO").toString());
			mensajeRpta=results.get("MSG_RESULTADO").toString();
			
			if (results.isEmpty()||results.size()<1) {
				log.info("Sin registros, verificar el procedimiento bienesPatrimonialesDB>>");
				return  new ApiOutResponse();
			}
			
			if (codigoRpta!=1) {//VERIFICACION DE ERROR EN LA BD
				log.error("[buscarAtcloEmgciaVehiculo] Ocurrio un error en la operacion del Procedimiento almacenado bienesPatrimonialesDB : "+mensajeRpta);
				outResponse.setCodResultado(codigoRpta);
				outResponse.setTotal(0);
				outResponse.setResponse("Error en la BD");
				outResponse.setMsgResultado(mensajeRpta);
				return outResponse;
			}
			
			if(rs!=null) {
				if (rs.size() > 0) {
					for (Map<String, Object> map : rs) {
						AtcloEmgciaVehiculoResponse listadoAtcloEmgciaVehiculo = new AtcloEmgciaVehiculoResponse();
						
						listadoAtcloEmgciaVehiculo.setIdAtcloEmgcia(map.get("ID_ATCLO_EMGCIA")!=null ? (long)map.get("ID_ATCLO_EMGCIA"):0);
						listadoAtcloEmgciaVehiculo.setIdAtcloEmgciaDenominacion(map.get("ID_ATCLO_EMGCIA_DENOMINACION")!=null ? (long)map.get("ID_ATCLO_EMGCIA_DENOMINACION"):0);
						listadoAtcloEmgciaVehiculo.setIdVehiculo(map.get("ID_VEHICULO")!=null ? (long)map.get("ID_VEHICULO"):0);
						listadoAtcloEmgciaVehiculo.setCodPatrimonial(map.get("COD_PATRIMONIAL")!=null ? map.get("COD_PATRIMONIAL").toString() : "");
						listadoAtcloEmgciaVehiculo.setFecVigencia(map.get("FEC_VIGENCIA")!=null ? map.get("FEC_VIGENCIA").toString() : "");
						listadoAtcloEmgciaVehiculo.setFlgOperativo(map.get("FLG_OPERATIVO")!=null ? (short)map.get("FLG_OPERATIVO"):0);
						listadoAtcloEmgciaVehiculo.setFlgOperativoAdm(map.get("FLG_OPERATIVO_ADM")!=null ? (short)map.get("FLG_OPERATIVO_ADM"):0);
						listadoAtcloEmgciaVehiculo.setFlgTiene(map.get("FLG_TIENE")!=null ? (short)map.get("FLG_TIENE"):0);
						listadoAtcloEmgciaVehiculo.setFlgTieneAdm(map.get("FLG_TIENE_ADM")!=null ? (short)map.get("FLG_TIENE_ADM"):0);
						listadoAtcloEmgciaVehiculo.setFlgVigente(map.get("FLG_VIGENTE")!=null ? (short)map.get("FLG_VIGENTE"):0);
						listadoAtcloEmgciaVehiculo.setFlgVigenteAdm(map.get("FLG_VIGENTE_ADM")!=null ? (short)map.get("FLG_VIGENTE_ADM"):0);
						listadoAtcloEmgciaVehiculo.setNombreAtcloEmgcia(map.get("ATCLO_EMGCIA")!=null ? map.get("ATCLO_EMGCIA").toString() : "");
						listadoAtcloEmgciaVehiculo.setNombreDenominacion(map.get("NOM_DENOMINACION")!=null ? map.get("NOM_DENOMINACION").toString() : "");
						listadoAtcloEmgciaVehiculo.setObservacion(map.get("OBSERVACION")!=null ? map.get("OBSERVACION").toString() : "");
					
						listadoAtcloEmgciaVehiculos.add(listadoAtcloEmgciaVehiculo);
					}
					outResponse.setResponse(listadoAtcloEmgciaVehiculos);
					outResponse.setCodResultado( results.get("COD_RESULTADO")!=null ? Integer.parseInt(results.get("COD_RESULTADO").toString()) : 500);
					outResponse.setMsgResultado( results.get("MSG_RESULTADO")!=null ? results.get("MSG_RESULTADO").toString() : "-" );
				}else {
					outResponse.setCodResultado(2);//Codigo para indicar que no existen registros en la BD
					outResponse.setTotal(0);
					outResponse.setResponse("");
					outResponse.setMsgResultado("¡No existen registros!");
				}
			}
			
		} catch (Exception e) {
			log.error("DAO buscarAtcloEmgciaVehiculo>>>>"+this.getClass().getName(), e);
			outResponse.setCodResultado(500);
			outResponse.setMsgResultado( "Error en la Base de datos!.");
		}
			
		log.info("[DAO buscarAtcloEmgciaVehiculo] FIN  ");
		return outResponse;
	}

	
	@Override
	public ApiOutResponse grabarAtcloEmgciaXVehiculo(List<AtcloEmgciaXVehiculoRequest> objetos) {
		log.info("[DAO grabarAtcloEmgciaXVehiculo] INICIO  ");	
		ApiOutResponse outResponse = new ApiOutResponse();
		
		int codigoRpta=0;
		String mensajeRpta="";
		Long id_vehiculo = (long) 0;
		
		SQLServerDataTable sdt;
		
		try {
			SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate.getDataSource());
			
			sdt = new  SQLServerDataTable();

			sdt.addColumnMetadata("FID_ATCLO_EMGCIA_DENOMINACION", Types.BIGINT);
			sdt.addColumnMetadata("FID_VHCLO", Types.BIGINT);
			sdt.addColumnMetadata("FLG_TIENE", Types.TINYINT);
			sdt.addColumnMetadata("FLG_OPERATIVO", Types.TINYINT);
			sdt.addColumnMetadata("FLG_VIGENTE", Types.TINYINT);
			sdt.addColumnMetadata("FEC_VIGENCIA", Types.VARCHAR);
			sdt.addColumnMetadata("FLG_ACTUAL", Types.TINYINT);
			sdt.addColumnMetadata("TXT_OBSERVACION", Types.VARCHAR);
			sdt.addColumnMetadata("FLG_ACTIVO", Types.TINYINT);
			sdt.addColumnMetadata("FID_ID_USUARIO_REG", Types.INTEGER);
			sdt.addColumnMetadata("FEC_REG", Types.VARCHAR);
			sdt.addColumnMetadata("TXT_IPMAQ_REG", Types.VARCHAR);
			
			for(AtcloEmgciaXVehiculoRequest objeto : objetos) {
				id_vehiculo = objeto.getFidVhclo();
					sdt.addRow(
							  objeto.getFidAtcloEmgciaDenominacion()
							, objeto.getFidVhclo()
							, objeto.getFlgTiene()
							, objeto.getFlgOperativo()
							, objeto.getFlgVigente()
							, Util.getDateyyyyMMdd(objeto.getFecVigencia())
							, objeto.getFlgActual()
							, objeto.getTxtObservacion()
							, objeto.getFlgActivo()
							, objeto.getFidIdUsuarioReg()
							, Util.getDateyyyyMMddHHmmssSSS(objeto.getFecReg())
							, objeto.getTxtIpmaqReg()
					);
			}
			
				jdbcCall.withSchemaName("UADM")
				.withProcedureName("grabarAtcloEmgciaVhclo")
				.declareParameters(
						new SqlParameter("FID_VHCLO", 		Types.BIGINT),
						new SqlParameter("LISTA_ATCLO_EMGCIA_VHCLO", 	microsoft.sql.Types.STRUCTURED),
						new SqlOutParameter("COD_RESULTADO", 	Types.INTEGER),
						new SqlOutParameter("MSG_RESULTADO", 	Types.VARCHAR)
					);
		
				MapSqlParameterSource parametros = new MapSqlParameterSource();
	
				parametros.addValue("FID_VHCLO",id_vehiculo);
				
				parametros.addValue("LISTA_ATCLO_EMGCIA_VHCLO",sdt);
				
				Map<String, Object> results = jdbcCall.execute(parametros);
				
				codigoRpta=Integer.parseInt(results.get("COD_RESULTADO").toString());
				mensajeRpta=results.get("MSG_RESULTADO").toString();
				
				if (results.isEmpty()||results.size()<1) {
					log.info("Sin registros, verificar el procedimiento grabarAtcloEmgciaVhclo>>");
					return  new ApiOutResponse();
				}
				
				if (codigoRpta!=1) {//VERIFICACION DE ERROR EN LA BD
					log.error("[grabarAtcloEmgciaXVehiculo] Ocurrio un error en la operacion del Procedimiento almacenado grabarAtcloEmgciaVhclo : "+mensajeRpta);
					outResponse.setCodResultado(codigoRpta);
					outResponse.setTotal(0);
					outResponse.setResponse("Error al insertar registros en la BD");
					outResponse.setMsgResultado(mensajeRpta);
					return outResponse;
				}
				
				outResponse.setResponse(1);
				outResponse.setCodResultado( results.get("COD_RESULTADO")!=null ? Integer.parseInt(results.get("COD_RESULTADO").toString()) : 500);
				outResponse.setMsgResultado( results.get("MSG_RESULTADO")!=null ? results.get("MSG_RESULTADO").toString() : "-" );
		} catch (Exception e) {
			log.error("DAO grabarAtcloEmgciaXVehiculo>>>>"+this.getClass().getName(), e);
			outResponse.setCodResultado(500);
			outResponse.setMsgResultado( "Error en la Base de datos!.");
		}
			
		log.info("[DAO grabarAtcloEmgciaXVehiculo] FIN  ");
		return outResponse;
	}
	
	@Override
	public ApiOutResponse listarPuesto() {
		log.info("[DAO listarPuesto] INICIO  ");	
		ApiOutResponse outResponse = new ApiOutResponse();
		SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate.getDataSource());
		List<Item> puestos = new ArrayList<>();
		
		int codigoRpta=0;
		String mensajeRpta="";
		try {
			
	
			jdbcCall.withSchemaName("UADM")
			.withProcedureName("bienesPatrimonialesDB")
			.declareParameters(
					new SqlParameter("ID_OPCION", 		Types.VARCHAR),
					new SqlParameter("ID_BUSCADO", 		Types.INTEGER),
					new SqlParameter("CAD_LIKE", 		Types.VARCHAR),
					new SqlOutParameter("COD_RESULTADO", 	Types.INTEGER),
					new SqlOutParameter("MSG_RESULTADO", 	Types.VARCHAR)
				);
	
			MapSqlParameterSource parametros = new MapSqlParameterSource();
			parametros.addValue("ID_OPCION","LISTA_PUESTO");
			parametros.addValue("ID_BUSCADO",0);
			parametros.addValue("CAD_LIKE","");
			Map<String, Object> results = jdbcCall.execute(parametros);
			
			List<Map<String, Object>> rs = (List<Map<String, Object>>) results.get(Constantes.RETURN_RESULT_SET_PREFIX);
			
			codigoRpta=Integer.parseInt(results.get("COD_RESULTADO").toString());
			mensajeRpta=results.get("MSG_RESULTADO").toString();
			
			if (results.isEmpty()||results.size()<1) {
				log.info("Sin registros, verificar el procedimiento bienesPatrimonialesDB>>");
				return  new ApiOutResponse();
			}
			
			if (codigoRpta!=1) {//VERIFICACION DE ERROR EN LA BD
				log.error("[listarPuesto] Ocurrio un error en la operacion del Procedimiento almacenado bienesPatrimonialesDB : "+mensajeRpta);
				outResponse.setCodResultado(codigoRpta);
				outResponse.setTotal(0);
				outResponse.setResponse("Error en la BD");
				outResponse.setMsgResultado(mensajeRpta);
				return outResponse;
			}
			  
			if(rs!=null) {
				if (rs.size() > 0) {
					for (Map<String, Object> map : rs) {
						Item item = new Item();
						item.setIdCodigo(Long.parseLong(map.get("ID_CODIGO").toString()) );
						item.setCidCodigo( map.get("CID_CODIGO")!=null ? map.get("CID_CODIGO").toString() : "");
						item.setCidNombre(map.get("CID_NOMBRE")!=null ? map.get("CID_NOMBRE").toString() : "" );
						
						puestos.add(item);
					}
					outResponse.setResponse(puestos);
					outResponse.setCodResultado( results.get("COD_RESULTADO")!=null ? Integer.parseInt(results.get("COD_RESULTADO").toString()) : 500);
					outResponse.setMsgResultado( results.get("MSG_RESULTADO")!=null ? results.get("MSG_RESULTADO").toString() : "-" );
				}else {
					outResponse.setCodResultado(2);//Codigo para indicar que no existen registros en la BD
					outResponse.setTotal(0);
					outResponse.setResponse("");
					outResponse.setMsgResultado("¡No existen registros!");
				}
			}
			
		} catch (Exception e) {
			log.error("DAO listarPuesto>>>>"+this.getClass().getName(), e);
			outResponse.setCodResultado(500);
			outResponse.setMsgResultado( "Error en la Base de datos!.");
		}
			
		log.info("[DAO listarPuesto] FIN  ");
		return outResponse;
	}
	
	@Override
	public ApiOutResponse buscarEmpleadoPuestoParaConductor(EmpleadoPuestoRequest objeto) {
		log.info("[DAO buscarEmpleadoPuestoParaConductor] INICIO  ");	
		ApiOutResponse outResponse = new ApiOutResponse();
		List<EmpleadoPuestoResponse> listadoEmpleadoPuestos = new ArrayList<>();
		
		int codigoRpta=0;
		String mensajeRpta="";
		try {
			SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate.getDataSource());
	
			jdbcCall.withSchemaName("UADM")
			.withProcedureName("buscaEmpleadoPuestoParaConductor")
			.declareParameters(
					new SqlParameter("ID_UTERR", 		Types.INTEGER),
					new SqlParameter("ID_PUESTO", 		Types.INTEGER),
					new SqlParameter("AP_PAT", 		Types.VARCHAR),
					new SqlParameter("AP_MAT", 		Types.VARCHAR),
					new SqlParameter("NOMBRE", 		Types.VARCHAR),
					new SqlParameter("FEC_ACT", 		Types.VARCHAR),
					new SqlOutParameter("COD_RESULTADO", 	Types.INTEGER),
					new SqlOutParameter("MSG_RESULTADO", 	Types.VARCHAR)
				);
	
			MapSqlParameterSource parametros = new MapSqlParameterSource();

			parametros.addValue("ID_UTERR",objeto.getIdUnidadTerritorial());
			parametros.addValue("ID_PUESTO",objeto.getIdPuesto());
			parametros.addValue("AP_PAT","%"+objeto.getApPaterno()+"%");
			parametros.addValue("AP_MAT","%"+objeto.getApMaterno()+"%");
			parametros.addValue("NOMBRE","%"+objeto.getNombre()+"%");
			parametros.addValue("FEC_ACT",Util.getDateyyyyMMdd(objeto.getFecActual()));
			
			Map<String, Object> results = jdbcCall.execute(parametros);
			List<Map<String, Object>> rs = (List<Map<String, Object>>) results.get(Constantes.RETURN_RESULT_SET_PREFIX);
			
			codigoRpta=Integer.parseInt(results.get("COD_RESULTADO").toString());
			mensajeRpta=results.get("MSG_RESULTADO").toString();
			
			if (results.isEmpty()||results.size()<1) {
				log.info("Sin registros, verificar el procedimiento buscaEmpleadoPuesto>>");
				return  new ApiOutResponse();
			}
			
			if (codigoRpta!=1) {//VERIFICACION DE ERROR EN LA BD
				log.error("[buscarEmpleadoPuestoParaConductor] Ocurrio un error en la operacion del Procedimiento almacenado buscaEmpleadoPuesto : "+mensajeRpta);
				outResponse.setCodResultado(codigoRpta);
				outResponse.setTotal(0);
				outResponse.setResponse("Error en la BD");
				outResponse.setMsgResultado(mensajeRpta);
				return outResponse;
			}
			
			if(rs!=null) {
				if (rs.size() > 0) {
					for (Map<String, Object> map : rs) {
						EmpleadoPuestoResponse listadoEmpleadoPuesto = new EmpleadoPuestoResponse();
						
						listadoEmpleadoPuesto.setIdPersona(map.get("ID_PERSONA")!=null ? (long)map.get("ID_PERSONA"):0);
						listadoEmpleadoPuesto.setNombre1(map.get("NOMBRE")!=null ? map.get("NOMBRE").toString() : "");
						listadoEmpleadoPuesto.setNombre2(map.get("DONOMBRE")!=null ? map.get("DONOMBRE").toString() : "");
						listadoEmpleadoPuesto.setApPaterno(map.get("PATERNO")!=null ? map.get("PATERNO").toString() : "");
						listadoEmpleadoPuesto.setApMaterno(map.get("MATERNO")!=null ? map.get("MATERNO").toString() : "");
						listadoEmpleadoPuesto.setIdEmpleado(map.get("ID_EMPLEADO")!=null ? (long)map.get("ID_EMPLEADO"):0);
						listadoEmpleadoPuesto.setIdPuesto(map.get("ID_PUESTO")!=null ? (long)map.get("ID_PUESTO"):0);
						listadoEmpleadoPuesto.setNomPuesto(map.get("PUESTO")!=null ? map.get("PUESTO").toString() : "");
					
						listadoEmpleadoPuestos.add(listadoEmpleadoPuesto);
					}
					outResponse.setResponse(listadoEmpleadoPuestos);
					outResponse.setCodResultado( results.get("COD_RESULTADO")!=null ? Integer.parseInt(results.get("COD_RESULTADO").toString()) : 500);
					outResponse.setMsgResultado( results.get("MSG_RESULTADO")!=null ? results.get("MSG_RESULTADO").toString() : "-" );
				}else {
					outResponse.setCodResultado(2);//Codigo para indicar que no existen registros en la BD
					outResponse.setTotal(0);
					outResponse.setResponse("");
					outResponse.setMsgResultado("¡No existen registros!");
				}
			}
			
		} catch (Exception e) {
			log.error("DAO buscarEmpleadoPuestoParaConductor>>>>"+this.getClass().getName(), e);
			outResponse.setCodResultado(500);
			outResponse.setMsgResultado( "Error en la Base de datos!.");
		}
			
		log.info("[DAO buscarEmpleadoPuestoParaConductor] FIN  ");
		return outResponse;
	}

	
	@Override
	public ApiOutResponse listarConductorVehiculo(ConductorVehiculoRequest objeto) {
		log.info("[DAO listarConductorVehiculo] INICIO  ");	
		ApiOutResponse outResponse = new ApiOutResponse();
		List<ConductorVehiculoResponse> ConductorVehiculos = new ArrayList<>();
		
		int codigoRpta=0;
		String mensajeRpta="";
		try {
			SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate.getDataSource());
	
			jdbcCall.withSchemaName("UADM")
			.withProcedureName("bienesPatrimonialesDB")
			.declareParameters(
					new SqlParameter("ID_OPCION", 		Types.VARCHAR),
					new SqlParameter("ID_BUSCADO", 		Types.INTEGER),
					new SqlParameter("CAD_LIKE", 		Types.VARCHAR),
					new SqlOutParameter("COD_RESULTADO", 	Types.INTEGER),
					new SqlOutParameter("MSG_RESULTADO", 	Types.VARCHAR)
				);
	
			MapSqlParameterSource parametros = new MapSqlParameterSource();

			parametros.addValue("ID_OPCION","LISTA_CONDUCTOR_VEHICULO");
			parametros.addValue("ID_BUSCADO",objeto.getIdVehiculo());
			parametros.addValue("CAD_LIKE","");
			
			Map<String, Object> results = jdbcCall.execute(parametros);
			List<Map<String, Object>> rs = (List<Map<String, Object>>) results.get(Constantes.RETURN_RESULT_SET_PREFIX);
			
//			System.out.println("=====================================");
//			System.out.println(results);						
//			System.out.println("=====================================");
			
			codigoRpta=Integer.parseInt(results.get("COD_RESULTADO").toString());
			mensajeRpta=results.get("MSG_RESULTADO").toString();
			
			if (results.isEmpty()||results.size()<1) {
				log.info("Sin registros, verificar el procedimiento bienesPatrimonialesDB>>");
				return  new ApiOutResponse();
			}
			
			if (codigoRpta!=1) {//VERIFICACION DE ERROR EN LA BD
				log.error("[listarConductorVehiculo] Ocurrio un error en la operacion del Procedimiento almacenado bienesPatrimonialesDB : "+mensajeRpta);
				outResponse.setCodResultado(codigoRpta);
				outResponse.setTotal(0);
				outResponse.setResponse("Error en la BD");
				outResponse.setMsgResultado(mensajeRpta);
				return outResponse;
			}
			
			if(rs!=null) {
				if (rs.size() > 0) {
					for (Map<String, Object> map : rs) {
						ConductorVehiculoResponse conductorVehiculo = new ConductorVehiculoResponse();
						
						conductorVehiculo.setIdPersona(map.get("ID_PERSONA")!=null ? (long)map.get("ID_PERSONA"):0);
						conductorVehiculo.setNombre1(map.get("NOMBRE")!=null ? map.get("NOMBRE").toString() : "");
						conductorVehiculo.setNombre2(map.get("DONOMBRE")!=null ? map.get("DONOMBRE").toString() : "");
						conductorVehiculo.setApPat(map.get("PATERNO")!=null ? map.get("PATERNO").toString() : "");
						conductorVehiculo.setApMat(map.get("MATERNO")!=null ? map.get("MATERNO").toString() : "");
						conductorVehiculo.setIdEmpleado(map.get("ID_EMPLEADO")!=null ? (long)map.get("ID_EMPLEADO"):0);
						conductorVehiculo.setIdVehiculo(map.get("ID_VEHICULO")!=null ? (long)map.get("ID_VEHICULO"):0);
						conductorVehiculo.setFecConductorIni(map.get("F_INICIO")!=null ? map.get("F_INICIO").toString() : "");
						conductorVehiculo.setFecConcductorFin(map.get("F_FIN")!=null ? map.get("F_FIN").toString() : "");
						conductorVehiculo.setFlgConductorActual(map.get("ACTUAL")!=null ? (short)map.get("ACTUAL") : 0);
						conductorVehiculo.setIdBrevete(map.get("ID_BREVETE")!=null ? (long)map.get("ID_BREVETE"):0);
						conductorVehiculo.setNumBrevete(map.get("NUM_BREVETE")!=null ? map.get("NUM_BREVETE").toString() : "");
						conductorVehiculo.setVigenBreveteIni(map.get("VGCIA_INCIO")!=null ? map.get("VGCIA_INCIO").toString() : "");
						conductorVehiculo.setVigenBreveteFin(map.get("VGCIA_FIN")!=null ? map.get("VGCIA_FIN").toString() : "");			
						conductorVehiculo.setIdConductor(map.get("ID_CONDUCTOR")!=null ? (long)map.get("ID_CONDUCTOR"):0);
						conductorVehiculo.setFecRegBrevete(map.get("FEC_REG_BREV")!=null ? map.get("FEC_REG_BREV").toString() : "");
						conductorVehiculo.setFlgActualBrevete(map.get("FLG_ACTUAL_BREV")!=null ? (short)map.get("FLG_ACTUAL_BREV") : 0);
				
						ConductorVehiculos.add(conductorVehiculo);
					}
					outResponse.setResponse(ConductorVehiculos);
					outResponse.setCodResultado( results.get("COD_RESULTADO")!=null ? Integer.parseInt(results.get("COD_RESULTADO").toString()) : 500);
					outResponse.setMsgResultado( results.get("MSG_RESULTADO")!=null ? results.get("MSG_RESULTADO").toString() : "-" );
				}else {
					outResponse.setCodResultado(2);//Codigo para indicar que no existen registros en la BD
					outResponse.setTotal(0);
					outResponse.setResponse("");
					outResponse.setMsgResultado("¡No existen registros!");
				}
			}
			
		} catch (Exception e) {
			log.error("DAO listarConductorVehiculo>>>>"+this.getClass().getName(), e);
			outResponse.setCodResultado(500);
			outResponse.setMsgResultado( "Error en la Base de datos!.");
		}
			
		log.info("[DAO listarConductorVehiculo] FIN  ");
		return outResponse;
	}
	
	@Override
	public ApiOutResponse grabarConductorBrevete(ConductorBreveteRequest objeto) {
		log.info("[DAO grabarConductorBrevete] INICIO  ");	
		ApiOutResponse outResponse = new ApiOutResponse();
		List<ConductorVehiculoResponse> ConductorVehiculos = new ArrayList<>();
		
		int codigoRpta=0;
		String mensajeRpta="";
		Long idConductor = (long)0;
		
		try {
			SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate.getDataSource());

				jdbcCall.withSchemaName("UADM")
				.withProcedureName("grabarConductor")
				.declareParameters(
						new SqlParameter("FID_VEHICULO", 		Types.BIGINT),
						new SqlParameter("FID_EMPLEADO", 		Types.BIGINT),
						new SqlParameter("FID_IDUSUARIOREG", 		Types.INTEGER),
						new SqlParameter("FEC_REG", 		Types.VARCHAR),
						new SqlParameter("TXT_IPMAQ_REG", 		Types.VARCHAR),
						new SqlOutParameter("ID_CONDUCTOR", 	Types.BIGINT),
						new SqlOutParameter("COD_RESULTADO", 	Types.INTEGER),
						new SqlOutParameter("MSG_RESULTADO", 	Types.VARCHAR)
					);
		
				MapSqlParameterSource parametros = new MapSqlParameterSource();
	
				parametros.addValue("FID_VEHICULO",(long)objeto.getIdVehiculo());
				parametros.addValue("FID_EMPLEADO",(long)objeto.getIdEmpleado());
				parametros.addValue("FID_IDUSUARIOREG",objeto.getFidIdUsuarioReg());
				parametros.addValue("FEC_REG",objeto.getFecReg());
				parametros.addValue("TXT_IPMAQ_REG",objeto.getTxtIpmaqReg());
							
				Map<String, Object> results = jdbcCall.execute(parametros);
				
				idConductor = Long.parseLong(results.get("ID_CONDUCTOR").toString());
				objeto.getBrevete().setIdConductor(idConductor);
				codigoRpta=Integer.parseInt(results.get("COD_RESULTADO").toString());
				mensajeRpta=results.get("MSG_RESULTADO").toString();
				
				if (results.isEmpty()||results.size()<1) {
					log.info("Sin registros, verificar el procedimiento grabarConductor>>");
					return  new ApiOutResponse();
				}
				
				if (codigoRpta!=1) {//VERIFICACION DE ERROR EN LA BD
					log.error("[grabarConductorBrevete] Ocurrio un error en la operacion del Procedimiento almacenado grabarConductor : "+mensajeRpta);
					outResponse.setCodResultado(codigoRpta);
					outResponse.setTotal(0);
					outResponse.setResponse("Error al insertar registros en la BD");
					outResponse.setMsgResultado(mensajeRpta);
					return outResponse;
				}
				
				if (idConductor >= 1) {
					try {
						SimpleJdbcCall jdbcCallB = new SimpleJdbcCall(jdbcTemplate.getDataSource());
						jdbcCallB.withSchemaName("UADM")
						.withProcedureName("grabaActualizaBrevete")
						.declareParameters(
								new SqlParameter("TIPO",			Types.VARCHAR),
								new SqlParameter("ID_BREVETE",		Types.BIGINT),
								new SqlParameter("FID_CONDUCTOR",	Types.BIGINT),
								new SqlParameter("CID_CODIGO", 		Types.VARCHAR),
								new SqlParameter("FEC_VGCIA_INICIO", Types.VARCHAR),
								new SqlParameter("FEC_VGCIA_FIN", 	Types.VARCHAR),
								new SqlParameter("FID_IDUSUARIOREG",Types.INTEGER),
								new SqlParameter("FEC_REG", 		Types.VARCHAR),
								new SqlParameter("TXT_IPMAQ_REG", 	Types.VARCHAR),
								new SqlOutParameter("COD_RESULTADO",Types.INTEGER),
								new SqlOutParameter("MSG_RESULTADO",Types.VARCHAR)
							);
				
						MapSqlParameterSource parametrosB = new MapSqlParameterSource();
			
						parametrosB.addValue("TIPO","GRABA");
						parametrosB.addValue("ID_BREVETE",0);
						parametrosB.addValue("FID_CONDUCTOR",objeto.getBrevete().getIdConductor());
						parametrosB.addValue("CID_CODIGO",objeto.getBrevete().getCodBrevete());
						parametrosB.addValue("FEC_VGCIA_INICIO",objeto.getBrevete().getFecVgciaIni());
						parametrosB.addValue("FEC_VGCIA_FIN",objeto.getBrevete().getFecVgciaFin());
						parametrosB.addValue("FID_IDUSUARIOREG",objeto.getFidIdUsuarioReg());
						parametrosB.addValue("FEC_REG",objeto.getFecReg());
						parametrosB.addValue("TXT_IPMAQ_REG",objeto.getTxtIpmaqReg());
									
						Map<String, Object> resultsB = jdbcCallB.execute(parametrosB);
						
						//System.out.println(resultsB);
						
						//System.out.println("=====================================");
						
						//Long idBrevete = Long.parseLong(resultsB.get("ID_BREVETE").toString());
						List<Map<String, Object>> rs = (List<Map<String, Object>>) resultsB.get(Constantes.RETURN_RESULT_SET_PREFIX);
						
						codigoRpta=Integer.parseInt(resultsB.get("COD_RESULTADO").toString());
						mensajeRpta=resultsB.get("MSG_RESULTADO").toString();
						
						if (resultsB.isEmpty()||resultsB.size()<1) {
							log.info("Sin registros, verificar el procedimiento grabaActualizaBrevete>>");
							return  new ApiOutResponse();
						}
						
						if (codigoRpta!=1) {//VERIFICACION DE ERROR EN LA BD
							log.error("[grabarConductorBrevete] Ocurrio un error en la operacion del Procedimiento almacenado grabaActualizaBrevete : "+mensajeRpta);
							outResponse.setCodResultado(codigoRpta);
							outResponse.setTotal(0);
							outResponse.setResponse("Error al insertar registros en la BD");
							outResponse.setMsgResultado(mensajeRpta);
							return outResponse;
						}
//						//outResponse.setResponse("Id del Conductor: " + idConductor  + " - Id del Brevete: " + idBrevete);
//						outResponse.setResponse(1);
//						outResponse.setCodResultado( resultsB.get("COD_RESULTADO")!=null ? Integer.parseInt(resultsB.get("COD_RESULTADO").toString()) : 500);
//						outResponse.setMsgResultado( resultsB.get("MSG_RESULTADO")!=null ? resultsB.get("MSG_RESULTADO").toString() : "-" );
						if(rs!=null) {
							if (rs.size() > 0) {
								for (Map<String, Object> map : rs) {
									ConductorVehiculoResponse conductorVehiculo = new ConductorVehiculoResponse();
									
									conductorVehiculo.setIdPersona(map.get("ID_PERSONA")!=null ? (long)map.get("ID_PERSONA"):0);
									conductorVehiculo.setNombre1(map.get("NOMBRE")!=null ? map.get("NOMBRE").toString() : "");
									conductorVehiculo.setNombre2(map.get("DONOMBRE")!=null ? map.get("DONOMBRE").toString() : "");
									conductorVehiculo.setApPat(map.get("PATERNO")!=null ? map.get("PATERNO").toString() : "");
									conductorVehiculo.setApMat(map.get("MATERNO")!=null ? map.get("MATERNO").toString() : "");
									conductorVehiculo.setIdEmpleado(map.get("ID_EMPLEADO")!=null ? (long)map.get("ID_EMPLEADO"):0);
									conductorVehiculo.setIdVehiculo(map.get("ID_VEHICULO")!=null ? (long)map.get("ID_VEHICULO"):0);
									conductorVehiculo.setFecConductorIni(map.get("F_INICIO")!=null ? map.get("F_INICIO").toString() : "");
									conductorVehiculo.setFecConcductorFin(map.get("F_FIN")!=null ? map.get("F_FIN").toString() : "");
									conductorVehiculo.setFlgConductorActual(map.get("ACTUAL")!=null ? (short)map.get("ACTUAL") : 0);
									conductorVehiculo.setIdBrevete(map.get("ID_BREVETE")!=null ? (long)map.get("ID_BREVETE"):0);
									conductorVehiculo.setNumBrevete(map.get("NUM_BREVETE")!=null ? map.get("NUM_BREVETE").toString() : "");
									conductorVehiculo.setVigenBreveteIni(map.get("VGCIA_INCIO")!=null ? map.get("VGCIA_INCIO").toString() : "");
									conductorVehiculo.setVigenBreveteFin(map.get("VGCIA_FIN")!=null ? map.get("VGCIA_FIN").toString() : "");
									conductorVehiculo.setFecRegBrevete(map.get("FEC_REG_BREV")!=null ? map.get("FEC_REG_BREV").toString() : "");
									conductorVehiculo.setFlgActualBrevete(map.get("FLG_ACTUAL_BREV")!=null ? (short)map.get("FLG_ACTUAL_BREV") : 0);
									conductorVehiculo.setIdConductor(map.get("ID_CONDUCTOR")!=null ? (long)map.get("ID_CONDUCTOR") : 0);
							
									ConductorVehiculos.add(conductorVehiculo);
								}
								outResponse.setResponse(ConductorVehiculos);
								outResponse.setCodResultado( results.get("COD_RESULTADO")!=null ? Integer.parseInt(results.get("COD_RESULTADO").toString()) : 500);
								outResponse.setMsgResultado( results.get("MSG_RESULTADO")!=null ? results.get("MSG_RESULTADO").toString() : "-" );
							}else {
								outResponse.setCodResultado(2);//Codigo para indicar que no existen registros en la BD
								outResponse.setTotal(0);
								outResponse.setResponse("");
								outResponse.setMsgResultado("¡No existen registros!");
							}
						}
					} catch (Exception e) {
						log.error("DAO grabarConductorBrevete - brevete>>>>"+this.getClass().getName(), e);
						outResponse.setCodResultado(500);
						outResponse.setMsgResultado( "Error en la Base de datos!.");						
					}
					
				}else {				
					outResponse.setResponse("Error al grabar Conductor: " + 0);
					outResponse.setCodResultado( results.get("COD_RESULTADO")!=null ? Integer.parseInt(results.get("COD_RESULTADO").toString()) : 500);
					outResponse.setMsgResultado( results.get("MSG_RESULTADO")!=null ? results.get("MSG_RESULTADO").toString() : "-" );
				}
		} catch (Exception e) {
			log.error("DAO grabarConductorBrevete>>>>"+this.getClass().getName(), e);
			outResponse.setCodResultado(500);
			outResponse.setMsgResultado( "Error en la Base de datos!.");
		}
			
		log.info("[DAO grabarConductorBrevete] FIN  ");
		return outResponse;
	}
	
	@Override
	public ApiOutResponse actualizarConductorBrevete(ConductorBreveteRequest objeto) {
		log.info("[DAO actualizarConductorBrevete] INICIO  ");	
		ApiOutResponse outResponse = new ApiOutResponse();
		List<ConductorVehiculoResponse> ConductorVehiculos = new ArrayList<>();
		
		int codigoRpta=0;
		String mensajeRpta="";
		Long idConductor = (long)0;
		
		try {
			SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate.getDataSource());

				jdbcCall.withSchemaName("UADM")
				.withProcedureName("actualizarConductor")
				.declareParameters(
						new SqlParameter("SU_ID_CONDUCTOR", 		Types.BIGINT),
						new SqlParameter("FID_EMPLEADO", 		Types.BIGINT),
						new SqlParameter("FID_IDUSUARIOREG", 		Types.INTEGER),
						new SqlParameter("FEC_REG", 		Types.VARCHAR),
						new SqlParameter("TXT_IPMAQ_REG", 		Types.VARCHAR),
						new SqlOutParameter("ID_CONDUCTOR", 	Types.BIGINT),
						new SqlOutParameter("COD_RESULTADO", 	Types.INTEGER),
						new SqlOutParameter("MSG_RESULTADO", 	Types.VARCHAR)
					);
		
				MapSqlParameterSource parametros = new MapSqlParameterSource();
	
				parametros.addValue("SU_ID_CONDUCTOR",(long)objeto.getIdConductor());
				parametros.addValue("FID_EMPLEADO",(long)objeto.getIdEmpleado());
				parametros.addValue("FID_IDUSUARIOREG",objeto.getFidIdUsuarioReg());
				parametros.addValue("FEC_REG",objeto.getFecReg());
				parametros.addValue("TXT_IPMAQ_REG",objeto.getTxtIpmaqReg());
							
				Map<String, Object> results = jdbcCall.execute(parametros);
				
				idConductor = Long.parseLong(results.get("ID_CONDUCTOR").toString());
				objeto.getBrevete().setIdConductor(idConductor);
				codigoRpta=Integer.parseInt(results.get("COD_RESULTADO").toString());
				mensajeRpta=results.get("MSG_RESULTADO").toString();
				
				if (results.isEmpty()||results.size()<1) {
					log.info("Sin registros, verificar el procedimiento grabarConductor>>");
					return  new ApiOutResponse();
				}
				
				if (codigoRpta!=1) {//VERIFICACION DE ERROR EN LA BD
					log.error("[actualizarConductorBrevete] Ocurrio un error en la operacion del Procedimiento almacenado grabarConductor : "+mensajeRpta);
					outResponse.setCodResultado(codigoRpta);
					outResponse.setTotal(0);
					outResponse.setResponse("Error al insertar registros en la BD");
					outResponse.setMsgResultado(mensajeRpta);
					return outResponse;
				}
				
				if (idConductor >= 1) {
					try {
						SimpleJdbcCall jdbcCallB = new SimpleJdbcCall(jdbcTemplate.getDataSource());
						jdbcCallB.withSchemaName("UADM")
						.withProcedureName("grabaActualizaBrevete")
						.declareParameters(
								new SqlParameter("TIPO",			Types.VARCHAR),
								new SqlParameter("ID_BREVETE",		Types.BIGINT),
								new SqlParameter("FID_CONDUCTOR",	Types.BIGINT),
								new SqlParameter("CID_CODIGO", 		Types.VARCHAR),
								new SqlParameter("FEC_VGCIA_INICIO", Types.VARCHAR),
								new SqlParameter("FEC_VGCIA_FIN", 	Types.VARCHAR),
								new SqlParameter("FID_IDUSUARIOREG",Types.INTEGER),
								new SqlParameter("FEC_REG", 		Types.VARCHAR),
								new SqlParameter("TXT_IPMAQ_REG", 	Types.VARCHAR),
								new SqlOutParameter("COD_RESULTADO",Types.INTEGER),
								new SqlOutParameter("MSG_RESULTADO",Types.VARCHAR)
							);
				
						MapSqlParameterSource parametrosB = new MapSqlParameterSource();
			
						parametrosB.addValue("TIPO","ACTUALIZA");
						parametrosB.addValue("ID_BREVETE",objeto.getBrevete().getIdBrevete());
						parametrosB.addValue("FID_CONDUCTOR",objeto.getBrevete().getIdConductor());
						parametrosB.addValue("CID_CODIGO",objeto.getBrevete().getCodBrevete());
						parametrosB.addValue("FEC_VGCIA_INICIO",objeto.getBrevete().getFecVgciaIni());
						parametrosB.addValue("FEC_VGCIA_FIN",objeto.getBrevete().getFecVgciaFin());
						parametrosB.addValue("FID_IDUSUARIOREG",objeto.getFidIdUsuarioReg());
						parametrosB.addValue("FEC_REG",objeto.getFecReg());
						parametrosB.addValue("TXT_IPMAQ_REG",objeto.getTxtIpmaqReg());
									
						Map<String, Object> resultsB = jdbcCallB.execute(parametrosB);
						
						//System.out.println(resultsB);
						
						//System.out.println("=====================================");
						
						//Long idBrevete = Long.parseLong(resultsB.get("ID_BREVETE").toString());
						List<Map<String, Object>> rs = (List<Map<String, Object>>) resultsB.get(Constantes.RETURN_RESULT_SET_PREFIX);
						
						codigoRpta=Integer.parseInt(resultsB.get("COD_RESULTADO").toString());
						mensajeRpta=resultsB.get("MSG_RESULTADO").toString();
						
						if (resultsB.isEmpty()||resultsB.size()<1) {
							log.info("Sin registros, verificar el procedimiento grabaActualizaBrevete>>");
							return  new ApiOutResponse();
						}
						
						if (codigoRpta!=1) {//VERIFICACION DE ERROR EN LA BD
							log.error("[actualizarConductorBrevete] Ocurrio un error en la operacion del Procedimiento almacenado grabaActualizaBrevete : "+mensajeRpta);
							outResponse.setCodResultado(codigoRpta);
							outResponse.setTotal(0);
							outResponse.setResponse("Error al insertar registros en la BD");
							outResponse.setMsgResultado(mensajeRpta);
							return outResponse;
						}
//						//outResponse.setResponse("Id del Conductor: " + idConductor  + " - Id del Brevete: " + idBrevete);
//						outResponse.setResponse(1);
//						outResponse.setCodResultado( resultsB.get("COD_RESULTADO")!=null ? Integer.parseInt(resultsB.get("COD_RESULTADO").toString()) : 500);
//						outResponse.setMsgResultado( resultsB.get("MSG_RESULTADO")!=null ? resultsB.get("MSG_RESULTADO").toString() : "-" );
						if(rs!=null) {
							if (rs.size() > 0) {
								for (Map<String, Object> map : rs) {
									ConductorVehiculoResponse conductorVehiculo = new ConductorVehiculoResponse();
									
									conductorVehiculo.setIdPersona(map.get("ID_PERSONA")!=null ? (long)map.get("ID_PERSONA"):0);
									conductorVehiculo.setNombre1(map.get("NOMBRE")!=null ? map.get("NOMBRE").toString() : "");
									conductorVehiculo.setNombre2(map.get("DONOMBRE")!=null ? map.get("DONOMBRE").toString() : "");
									conductorVehiculo.setApPat(map.get("PATERNO")!=null ? map.get("PATERNO").toString() : "");
									conductorVehiculo.setApMat(map.get("MATERNO")!=null ? map.get("MATERNO").toString() : "");
									conductorVehiculo.setIdEmpleado(map.get("ID_EMPLEADO")!=null ? (long)map.get("ID_EMPLEADO"):0);
									conductorVehiculo.setIdVehiculo(map.get("ID_VEHICULO")!=null ? (long)map.get("ID_VEHICULO"):0);
									conductorVehiculo.setFecConductorIni(map.get("F_INICIO")!=null ? map.get("F_INICIO").toString() : "");
									conductorVehiculo.setFecConcductorFin(map.get("F_FIN")!=null ? map.get("F_FIN").toString() : "");
									conductorVehiculo.setFlgConductorActual(map.get("ACTUAL")!=null ? (short)map.get("ACTUAL") : 0);
									conductorVehiculo.setIdBrevete(map.get("ID_BREVETE")!=null ? (long)map.get("ID_BREVETE"):0);
									conductorVehiculo.setNumBrevete(map.get("NUM_BREVETE")!=null ? map.get("NUM_BREVETE").toString() : "");
									conductorVehiculo.setVigenBreveteIni(map.get("VGCIA_INCIO")!=null ? map.get("VGCIA_INCIO").toString() : "");
									conductorVehiculo.setVigenBreveteFin(map.get("VGCIA_FIN")!=null ? map.get("VGCIA_FIN").toString() : "");
									conductorVehiculo.setFecRegBrevete(map.get("FEC_REG_BREV")!=null ? map.get("FEC_REG_BREV").toString() : "");
									conductorVehiculo.setFlgActualBrevete(map.get("FLG_ACTUAL_BREV")!=null ? (short)map.get("FLG_ACTUAL_BREV") : 0);
									conductorVehiculo.setIdConductor(map.get("ID_CONDUCTOR")!=null ? (long)map.get("ID_CONDUCTOR") : 0);
							
									ConductorVehiculos.add(conductorVehiculo);
								}
								outResponse.setResponse(ConductorVehiculos);
								outResponse.setCodResultado( results.get("COD_RESULTADO")!=null ? Integer.parseInt(results.get("COD_RESULTADO").toString()) : 500);
								outResponse.setMsgResultado( results.get("MSG_RESULTADO")!=null ? results.get("MSG_RESULTADO").toString() : "-" );
							}else {
								outResponse.setCodResultado(2);//Codigo para indicar que no existen registros en la BD
								outResponse.setTotal(0);
								outResponse.setResponse("");
								outResponse.setMsgResultado("¡No existen registros!");
							}
						}
					} catch (Exception e) {
						log.error("DAO actualizarConductorBrevete - brevete>>>>"+this.getClass().getName(), e);
						outResponse.setCodResultado(500);
						outResponse.setMsgResultado( "Error en la Base de datos!.");						
					}
					
				}else {				
					outResponse.setResponse("Error al grabar Conductor: " + 0);
					outResponse.setCodResultado( results.get("COD_RESULTADO")!=null ? Integer.parseInt(results.get("COD_RESULTADO").toString()) : 500);
					outResponse.setMsgResultado( results.get("MSG_RESULTADO")!=null ? results.get("MSG_RESULTADO").toString() : "-" );
				}
		} catch (Exception e) {
			log.error("DAO actualizarConductorBrevete>>>>"+this.getClass().getName(), e);
			outResponse.setCodResultado(500);
			outResponse.setMsgResultado( "Error en la Base de datos!.");
		}
			
		log.info("[DAO grabarConductorBrevete] FIN  ");
		return outResponse;
	}
	
	@Override
	public ApiOutResponse eliminarConductorBrevete(EliminaConductorBreveteRequest objeto) {
		log.info("[DAO eliminarConductorBrevete] INICIO  ");	
		ApiOutResponse outResponse = new ApiOutResponse();
		SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate.getDataSource());
		List<Item> unidadTs = new ArrayList<>();
		
		int codigoRpta=0, codUpdate1 = 0, codUpdate2 = 0;
		String mensajeRpta="";
		try {
			
	
			jdbcCall.withSchemaName("UADM")
			.withProcedureName("eliminaConductorBrevete")
			.declareParameters(
					new SqlParameter("ID_CODIGO", 		Types.BIGINT),
					new SqlParameter("FID_IDUSUARIOREG", 	Types.INTEGER),
					new SqlParameter("FEC_REG", 			Types.VARCHAR),
					new SqlParameter("TXT_IPMAQ_REG", 		Types.VARCHAR),
					new SqlOutParameter("COD_RESULTADO", 	Types.INTEGER),
					new SqlOutParameter("MSG_RESULTADO", 	Types.VARCHAR)
				);
	
			MapSqlParameterSource parametros = new MapSqlParameterSource();
			
			parametros.addValue("ID_CODIGO",objeto.getIdConductor());
			parametros.addValue("FID_IDUSUARIOREG",objeto.getFidIdUsuarioReg());
			parametros.addValue("FEC_REG",objeto.getFecReg());
			parametros.addValue("TXT_IPMAQ_REG",objeto.getTxtIpmaqReg());

			Map<String, Object> results = jdbcCall.execute(parametros);
			
			List<Map<String, Object>> rs = (List<Map<String, Object>>) results.get(Constantes.RETURN_RESULT_SET_PREFIX);

//			System.out.println("=====================================");
//			System.out.println(Constantes.RETURN_RESULT_SET_PREFIX);
//			System.out.println(results);
//			System.out.println("=====================================");
			
//			codUpdate1 = Integer.parseInt(results.get("#update-count-1").toString());
//			codUpdate2 = Integer.parseInt(results.get("#update-count-2").toString());

			codigoRpta=Integer.parseInt(results.get("COD_RESULTADO").toString());
			mensajeRpta=results.get("MSG_RESULTADO").toString();
			
			if (results.isEmpty()||results.size()<1) {
				log.info("Sin registros, verificar el procedimiento eliminaConductorBrevete>>");
				return  new ApiOutResponse();
			}
			
			if (codigoRpta!=1) {//VERIFICACION DE ERROR EN LA BD
				log.error("[eliminarConductorBrevete] Ocurrio un error en la operacion del Procedimiento almacenado eliminaConductorBrevete : "+mensajeRpta);
				outResponse.setCodResultado(codigoRpta);
				outResponse.setTotal(0);
				outResponse.setResponse(0);
				outResponse.setMsgResultado(mensajeRpta);
				return outResponse;
			}else {
				outResponse.setCodResultado(codigoRpta);
				outResponse.setResponse(1);
				outResponse.setTotal(0);
				outResponse.setMsgResultado(mensajeRpta);
			}
			  
//			if(rs!=null) {
//				if (rs.size() > 0) {
////					for (Map<String, Object> map : rs) {
////						Item item = new Item();
////						item.setIdCodigo(Long.parseLong(map.get("ID_CODIGO").toString()) );
////						item.setCidNombre(map.get("CID_NOMBRE")!=null ? map.get("CID_NOMBRE").toString() : "" );
////						
////						unidadTs.add(item);
////					}
////					if ((codUpdate1 + codUpdate2) < 2) {
////						outResponse.setResponse("0");
////					}else {
////						outResponse.setResponse("1");
////					}
//					
//					outResponse.setCodResultado( results.get("COD_RESULTADO")!=null ? Integer.parseInt(results.get("COD_RESULTADO").toString()) : 500);
//					outResponse.setResponse(results.get("COD_RESULTADO") == "1" ? 1 : 0);
//					outResponse.setMsgResultado( results.get("MSG_RESULTADO")!=null ? results.get("MSG_RESULTADO").toString() : "-" );
//				}else {
//					outResponse.setCodResultado(2);//Codigo para indicar que no existen registros en la BD
//					outResponse.setTotal(0);
//					outResponse.setResponse("");
//					outResponse.setMsgResultado("¡No existen registros!");
//				}
//			}
			
		} catch (Exception e) {
			log.error("DAO eliminarConductorBrevete>>>>"+this.getClass().getName(), e);
			outResponse.setCodResultado(500);
			outResponse.setMsgResultado( "Error en la Base de datos!.");
		}
			
		log.info("[DAO eliminarConductorBrevete] FIN  ");
		return outResponse;
	}
	
	@Override
	public ApiOutResponse listarPerfilUsuarioModulo(PerfilUsuarioModuloRequest objeto) {
		log.info("[DAO listarPerfilUsuarioModulo] INICIO  ");	
		ApiOutResponse outResponse = new ApiOutResponse();
		List<PerfilUsuarioModuloResponse> perfilUsuarioModulos = new ArrayList<>();
		
		int codigoRpta=0;
		String mensajeRpta="";
		try {
			SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate.getDataSource());
	
			jdbcCall.withSchemaName("UADM")
			.withProcedureName("listaPerfilUsuarioModulo")
			.declareParameters(
					new SqlParameter("ID_USUARIO", 		Types.BIGINT),
					new SqlParameter("ID_MODULO", 		Types.BIGINT),
					new SqlOutParameter("COD_RESULTADO", 	Types.INTEGER),
					new SqlOutParameter("MSG_RESULTADO", 	Types.VARCHAR)
				);
	
			MapSqlParameterSource parametros = new MapSqlParameterSource();

			parametros.addValue("ID_USUARIO",objeto.getIdUsuario());
			parametros.addValue("ID_MODULO",objeto.getIdModulo());
			
			Map<String, Object> results = jdbcCall.execute(parametros);
			List<Map<String, Object>> rs = (List<Map<String, Object>>) results.get(Constantes.RETURN_RESULT_SET_PREFIX);
			
			codigoRpta=Integer.parseInt(results.get("COD_RESULTADO").toString());
			mensajeRpta=results.get("MSG_RESULTADO").toString();
			
			if (results.isEmpty()||results.size()<1) {
				log.info("Sin registros, verificar el procedimiento listaPerfilUsuarioModulo>>");
				return  new ApiOutResponse();
			}
			
			if (codigoRpta!=1) {//VERIFICACION DE ERROR EN LA BD
				log.error("[listarPerfilUsuarioModulo] Ocurrio un error en la operacion del Procedimiento almacenado listaPerfilUsuarioModulo : "+mensajeRpta);
				outResponse.setCodResultado(codigoRpta);
				outResponse.setTotal(0);
				outResponse.setResponse("Error en la BD");
				outResponse.setMsgResultado(mensajeRpta);
				return outResponse;
			}
			
			if(rs!=null) {
				if (rs.size() > 0) {
					for (Map<String, Object> map : rs) {
						PerfilUsuarioModuloResponse perfilUsuarioModulo = new PerfilUsuarioModuloResponse();
						
						perfilUsuarioModulo.setIdPerfil(map.get("ID_PERFIL")!=null ? (long)map.get("ID_PERFIL"):(long)0);
						perfilUsuarioModulo.setNombrePerfil(map.get("NOM_PERFIL")!=null ? map.get("NOM_PERFIL").toString() : "");
						perfilUsuarioModulo.setIdUterritorial(map.get("ID_UTERRITORIAL")!=null ? (long)map.get("ID_UTERRITORIAL"):(long)0);
						perfilUsuarioModulo.setNombreUTerritorial(map.get("NOMBRE_UT")!=null ? map.get("NOMBRE_UT").toString() : "");
						perfilUsuarioModulo.setIdPlataforma(map.get("ID_PLAT")!=null ? (long)map.get("ID_PLAT"):(long)0);
						perfilUsuarioModulo.setNombrePlataforma(map.get("NOMBRE_PLAT")!=null ? map.get("NOMBRE_PLAT").toString() : "");
						
						perfilUsuarioModulos.add(perfilUsuarioModulo);
				}
					outResponse.setResponse(perfilUsuarioModulos);
					outResponse.setCodResultado( results.get("COD_RESULTADO")!=null ? Integer.parseInt(results.get("COD_RESULTADO").toString()) : 500);
					outResponse.setMsgResultado( results.get("MSG_RESULTADO")!=null ? results.get("MSG_RESULTADO").toString() : "-" );
				}else {
					outResponse.setCodResultado(2);//Codigo para indicar que no existen registros en la BD
					outResponse.setTotal(0);
					outResponse.setResponse("");
					outResponse.setMsgResultado("¡No existen registros!");
				}
			}
			
		} catch (Exception e) {
			log.error("DAO listarPerfilUsuarioModulo>>>>"+this.getClass().getName(), e);
			outResponse.setCodResultado(500);
			outResponse.setMsgResultado( "Error en la Base de datos!.");
		}
			
		log.info("[DAO listarPerfilUsuarioModulo] FIN  ");
		return outResponse;
	}
	
	@Override
	public ApiOutResponse modificarRenovarBrevete(BreveteRequest objeto) {
		log.info("[DAO modificarRenovarBrevete] INICIO  ");	
		ApiOutResponse outResponse = new ApiOutResponse();
		List<ConductorVehiculoResponse> conductorVehiculos = new ArrayList<>();
		
		int codigoRpta=0;
		String mensajeRpta="";
		try {
			SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate.getDataSource());
	
			jdbcCall.withSchemaName("UADM")
			.withProcedureName("grabaActualizaBrevete")
			.declareParameters(
					new SqlParameter("TIPO",			Types.VARCHAR),
					new SqlParameter("ID_BREVETE",		Types.BIGINT),
					new SqlParameter("FID_CONDUCTOR",	Types.BIGINT),
					new SqlParameter("CID_CODIGO", 		Types.VARCHAR),
					new SqlParameter("FEC_VGCIA_INICIO", Types.VARCHAR),
					new SqlParameter("FEC_VGCIA_FIN", 	Types.VARCHAR),
					new SqlParameter("FID_IDUSUARIOREG",Types.INTEGER),
					new SqlParameter("FEC_REG", 		Types.VARCHAR),
					new SqlParameter("TXT_IPMAQ_REG", 	Types.VARCHAR),
					new SqlOutParameter("COD_RESULTADO",Types.INTEGER),
					new SqlOutParameter("MSG_RESULTADO",Types.VARCHAR)
				);
	
			MapSqlParameterSource parametrosB = new MapSqlParameterSource();

			Long ID_BREVETE = objeto.getIdBrevete();
			String tipo = "";
			if (ID_BREVETE == 0) {
				tipo = "GRABA";
			}else {
				tipo = "ACTUALIZA";
			}
			
			parametrosB.addValue("TIPO",tipo);
			parametrosB.addValue("ID_BREVETE",ID_BREVETE);
			parametrosB.addValue("FID_CONDUCTOR",objeto.getIdConductor());
			parametrosB.addValue("CID_CODIGO",objeto.getCodBrevete());
			parametrosB.addValue("FEC_VGCIA_INICIO",objeto.getFecVgciaIni());
			parametrosB.addValue("FEC_VGCIA_FIN",objeto.getFecVgciaFin());
			parametrosB.addValue("FID_IDUSUARIOREG",objeto.getFidIdUsuarioReg());
			parametrosB.addValue("FEC_REG",objeto.getFecReg());
			parametrosB.addValue("TXT_IPMAQ_REG",objeto.getTxtIpmaqReg());
			
			Map<String, Object> results = jdbcCall.execute(parametrosB);
			List<Map<String, Object>> rs = (List<Map<String, Object>>) results.get(Constantes.RETURN_RESULT_SET_PREFIX);
			
			codigoRpta=Integer.parseInt(results.get("COD_RESULTADO").toString());
			mensajeRpta=results.get("MSG_RESULTADO").toString();
			
			if (results.isEmpty()||results.size()<1) {
				log.info("Sin registros, verificar el procedimiento grabaActualizaBrevete>>");
				return  new ApiOutResponse();
			}
			
			if (codigoRpta!=1) {//VERIFICACION DE ERROR EN LA BD
				log.error("[modificarRenovarBrevete] Ocurrio un error en la operacion del Procedimiento almacenado grabaActualizaBrevete : "+mensajeRpta);
				outResponse.setCodResultado(codigoRpta);
				outResponse.setTotal(0);
				outResponse.setResponse("Error en la BD");
				outResponse.setMsgResultado(mensajeRpta);
				return outResponse;
			}
			
			if(rs!=null) {
				if (rs.size() > 0) {
					for (Map<String, Object> map : rs) {
						ConductorVehiculoResponse conductorVehiculo = new ConductorVehiculoResponse();
						
						conductorVehiculo.setIdPersona(map.get("ID_PERSONA")!=null ? (long)map.get("ID_PERSONA"):0);
						conductorVehiculo.setNombre1(map.get("NOMBRE")!=null ? map.get("NOMBRE").toString() : "");
						conductorVehiculo.setNombre2(map.get("DONOMBRE")!=null ? map.get("DONOMBRE").toString() : "");
						conductorVehiculo.setApPat(map.get("PATERNO")!=null ? map.get("PATERNO").toString() : "");
						conductorVehiculo.setApMat(map.get("MATERNO")!=null ? map.get("MATERNO").toString() : "");
						conductorVehiculo.setIdEmpleado(map.get("ID_EMPLEADO")!=null ? (long)map.get("ID_EMPLEADO"):0);
						conductorVehiculo.setIdVehiculo(map.get("ID_VEHICULO")!=null ? (long)map.get("ID_VEHICULO"):0);
						conductorVehiculo.setFecConductorIni(map.get("F_INICIO")!=null ? map.get("F_INICIO").toString() : "");
						conductorVehiculo.setFecConcductorFin(map.get("F_FIN")!=null ? map.get("F_FIN").toString() : "");
						conductorVehiculo.setFlgConductorActual(map.get("ACTUAL")!=null ? (short)map.get("ACTUAL") : 0);
						conductorVehiculo.setIdBrevete(map.get("ID_BREVETE")!=null ? (long)map.get("ID_BREVETE"):0);
						conductorVehiculo.setNumBrevete(map.get("NUM_BREVETE")!=null ? map.get("NUM_BREVETE").toString() : "");
						conductorVehiculo.setVigenBreveteIni(map.get("VGCIA_INCIO")!=null ? map.get("VGCIA_INCIO").toString() : "");
						conductorVehiculo.setVigenBreveteFin(map.get("VGCIA_FIN")!=null ? map.get("VGCIA_FIN").toString() : "");
						conductorVehiculo.setFecRegBrevete(map.get("FEC_REG_BREV")!=null ? map.get("FEC_REG_BREV").toString() : "");
						conductorVehiculo.setFlgActualBrevete(map.get("FLG_ACTUAL_BREV")!=null ? (short)map.get("FLG_ACTUAL_BREV") : 0);
						conductorVehiculo.setIdConductor(map.get("ID_CONDUCTOR")!=null ? (long)map.get("ID_CONDUCTOR") : 0);
						
						conductorVehiculos.add(conductorVehiculo);
				}
					outResponse.setResponse(conductorVehiculos);
					outResponse.setCodResultado( results.get("COD_RESULTADO")!=null ? Integer.parseInt(results.get("COD_RESULTADO").toString()) : 500);
					outResponse.setMsgResultado( results.get("MSG_RESULTADO")!=null ? results.get("MSG_RESULTADO").toString() : "-" );
				}else {
					outResponse.setCodResultado(2);//Codigo para indicar que no existen registros en la BD
					outResponse.setTotal(0);
					outResponse.setResponse("");
					outResponse.setMsgResultado("¡No existen registros!");
				}
			}
			
		} catch (Exception e) {
			log.error("DAO modificarRenovarBrevete>>>>"+this.getClass().getName(), e);
			outResponse.setCodResultado(500);
			outResponse.setMsgResultado( "Error en la Base de datos!.");
		}
			
		log.info("[DAO modificarRenovarBrevete] FIN  ");
		return outResponse;
	}
	
	
	@Override
	public ApiOutResponse filtrarDenominacion(FiltroDenominacionRequest objeto) {
		log.info("[DAO filtrarDenominacion] INICIO  ");	
		ApiOutResponse outResponse = new ApiOutResponse();
		List<FiltroDenominacionResponse> FiltroDenominaciones = new ArrayList<>(); // LISTA DE DENOMINACIONES
		
		int codigoRpta=0;
		String mensajeRpta="";
		try {
			SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate.getDataSource());
	
			jdbcCall.withSchemaName("UADM")
			.withProcedureName("filtrarDenominacion")
			.declareParameters(
					new SqlParameter("ID_CODIGO", 		Types.BIGINT),
					new SqlParameter("FID_CLASE", 		Types.BIGINT),
					new SqlParameter("FID_GRUPO", 		Types.BIGINT),
					new SqlParameter("CID_CODIGO", 		Types.VARCHAR),
					new SqlParameter("CID_NOMBRE", 		Types.VARCHAR),
					new SqlOutParameter("COD_RESULTADO", 	Types.INTEGER),
					new SqlOutParameter("MSG_RESULTADO", 	Types.VARCHAR)
				);
	
			MapSqlParameterSource parametros = new MapSqlParameterSource();
			parametros.addValue("ID_CODIGO",objeto.getIdCodigo());
			parametros.addValue("FID_CLASE",objeto.getFidClase());
			parametros.addValue("FID_GRUPO",objeto.getFidGrupo());
			if (objeto.getCidCodigo().isEmpty()) {
				parametros.addValue("CID_CODIGO","");
			}else {
				parametros.addValue("CID_CODIGO","'"+objeto.getCidCodigo()+"%'");
			}
			if (objeto.getCidNombre().isEmpty()) {
				parametros.addValue("CID_NOMBRE","");
			}else {
				parametros.addValue("CID_NOMBRE","'%"+objeto.getCidNombre()+"%'");
			}
			
			Map<String, Object> results = jdbcCall.execute(parametros);
			List<Map<String, Object>> rs = (List<Map<String, Object>>) results.get(Constantes.RETURN_RESULT_SET_PREFIX);
			
			codigoRpta=Integer.parseInt(results.get("COD_RESULTADO").toString());
			mensajeRpta=results.get("MSG_RESULTADO").toString();
			
			if (results.isEmpty()||results.size()<1) {
				log.info("Sin registros, verificar el procedimiento filtrarDenominacion>>");
				return  new ApiOutResponse();
			}
			
			if (codigoRpta!=1) {//VERIFICACION DE ERROR EN LA BD
				log.error("[filtrarDenominacion] Ocurrio un error en la operacion del Procedimiento almacenado filtrarDenominacion : "+mensajeRpta);
				outResponse.setCodResultado(codigoRpta);
				outResponse.setTotal(0);
				outResponse.setResponse("Error en la BD");
				outResponse.setMsgResultado(mensajeRpta);
				return outResponse;
			}
			
			if(rs!=null) {
				if (rs.size() > 0) {
					for (Map<String, Object> map : rs) {
						FiltroDenominacionResponse FiltroDenominacion = new FiltroDenominacionResponse();
						FiltroDenominacion.setIdDenominacion(map.get("ID_DENOMINACION") !=null ? (long)map.get("ID_DENOMINACION") : 0);
						FiltroDenominacion.setIdClase(map.get("ID_CLASE") !=null ? (long)map.get("ID_CLASE") : 0);
						FiltroDenominacion.setIdGrupo(map.get("ID_GRUPO") !=null ? (long)map.get("ID_GRUPO") : 0);
						FiltroDenominacion.setCidCodigo(map.get("CID_CODIGO")!=null ? map.get("CID_CODIGO").toString() : "");
						FiltroDenominacion.setCidNombre(map.get("CID_NOMBRE")!=null ? map.get("CID_NOMBRE").toString() : "");
						
						FiltroDenominaciones.add(FiltroDenominacion);
					}
					outResponse.setResponse(FiltroDenominaciones);
					outResponse.setCodResultado( results.get("COD_RESULTADO")!=null ? Integer.parseInt(results.get("COD_RESULTADO").toString()) : 500);
					outResponse.setMsgResultado( results.get("MSG_RESULTADO")!=null ? results.get("MSG_RESULTADO").toString() : "-" );
				}else {
					outResponse.setCodResultado(2);//Codigo para indicar que no existen registros en la BD
					outResponse.setTotal(0);
					outResponse.setResponse("");
					outResponse.setMsgResultado("¡No existen registros!");
				}
			}
			
		} catch (Exception e) {
			log.error("DAO filtrarDenominacion>>>>"+this.getClass().getName(), e);
			outResponse.setCodResultado(500);
			outResponse.setMsgResultado( "Error en la Base de datos!.");
		}
			
		log.info("[DAO filtrarDenominacion] FIN  ");
		return outResponse;
	}
	
	@Override
	public ApiOutResponse listarTipoLubricante() {
		log.info("[DAO listarTipoLubricante] INICIO  ");	
		ApiOutResponse outResponse = new ApiOutResponse();
		List<TipoLubricanteResponse> tipoLubricantes = new ArrayList<>(); // LISTA DE TAMBOS O PLATAFORMAS POR UNIDAD TERRITORIAL
		
		int codigoRpta=0;
		String mensajeRpta="";
		try {
			SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate.getDataSource());
	
			jdbcCall.withSchemaName("UADM")
			.withProcedureName("listaTipoLubricante")
			.declareParameters(

					new SqlOutParameter("COD_RESULTADO", 	Types.INTEGER),
					new SqlOutParameter("MSG_RESULTADO", 	Types.VARCHAR)
				);
	
			MapSqlParameterSource parametros = new MapSqlParameterSource();

			Map<String, Object> results = jdbcCall.execute(parametros);
			List<Map<String, Object>> rs = (List<Map<String, Object>>) results.get(Constantes.RETURN_RESULT_SET_PREFIX);
			
			codigoRpta=Integer.parseInt(results.get("COD_RESULTADO").toString());
			mensajeRpta=results.get("MSG_RESULTADO").toString();
			
			if (results.isEmpty()||results.size()<1) {
				log.info("Sin registros, verificar el procedimiento listaTipoLubricante>>");
				return  new ApiOutResponse();
			}
			
			if (codigoRpta!=1) {//VERIFICACION DE ERROR EN LA BD
				log.error("[listarTipoLubricante] Ocurrio un error en la operacion del Procedimiento almacenado listaTipoLubricante : "+mensajeRpta);
				outResponse.setCodResultado(codigoRpta);
				outResponse.setTotal(0);
				outResponse.setResponse("Error en la BD");
				outResponse.setMsgResultado(mensajeRpta);
				return outResponse;
			}
			
			if(rs!=null) {
				if (rs.size() > 0) {
					for (Map<String, Object> map : rs) {
						TipoLubricanteResponse item = new TipoLubricanteResponse();
						item.setIdCodigo(Long.parseLong(map.get("ID_CODIGO").toString()) );
						item.setCidCodigo( map.get("CID_CODIGO")!=null ? map.get("CID_CODIGO").toString() : "");
						item.setCidNombre(map.get("CID_NOMBRE")!=null ? map.get("CID_NOMBRE").toString() : "" );
						
						tipoLubricantes.add(item);
					}
					outResponse.setResponse(tipoLubricantes);
					outResponse.setCodResultado( results.get("COD_RESULTADO")!=null ? Integer.parseInt(results.get("COD_RESULTADO").toString()) : 500);
					outResponse.setMsgResultado( results.get("MSG_RESULTADO")!=null ? results.get("MSG_RESULTADO").toString() : "-" );
				}else {
					outResponse.setCodResultado(2);//Codigo para indicar que no existen registros en la BD
					outResponse.setTotal(0);
					outResponse.setResponse("");
					outResponse.setMsgResultado("¡No existen registros!");
				}
			}
			
		} catch (Exception e) {
			log.error("DAO listarTipoLubricante>>>>"+this.getClass().getName(), e);
			outResponse.setCodResultado(500);
			outResponse.setMsgResultado( "Error en la Base de datos!.");
		}
			
		log.info("[DAO listarTipoLubricante] FIN  ");
		return outResponse;
	}
	
	@Override
	public ApiOutResponse listarLubricanteVehiculo(TipoLbcteVhcloRequest objeto) {
		log.info("[DAO listarLubricanteVehiculo] INICIO  ");	
		ApiOutResponse outResponse = new ApiOutResponse();
		List<TipoLbcteVhcloEntity> tipoLbcteVhclos = new ArrayList<>();
		
		int codigoRpta=0;
		String mensajeRpta="";
		try {
			SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate.getDataSource());
	
			jdbcCall.withSchemaName("UADM")
			.withProcedureName("listaTipoLubricanteVehiculo")
			.declareParameters(
					new SqlParameter("ID_VEHICULO", 		Types.BIGINT),
					new SqlOutParameter("COD_RESULTADO", 	Types.INTEGER),
					new SqlOutParameter("MSG_RESULTADO", 	Types.VARCHAR)
				);
	
			MapSqlParameterSource parametros = new MapSqlParameterSource();

			parametros.addValue("ID_VEHICULO",objeto.getIdVehiculo());
			
			Map<String, Object> results = jdbcCall.execute(parametros);
			
			//System.out.println(results);
			//System.out.println("***********************************************=====================================");
			
			List<Map<String, Object>> rs = (List<Map<String, Object>>) results.get(Constantes.RETURN_RESULT_SET_PREFIX);
			
			codigoRpta=Integer.parseInt(results.get("COD_RESULTADO").toString());
			mensajeRpta=results.get("MSG_RESULTADO").toString();
			
			if (results.isEmpty()||results.size()<1) {
				log.info("Sin registros, verificar el procedimiento listaTipoLubricanteVehiculo>>");
				return  new ApiOutResponse();
			}
			
			if (codigoRpta!=1) {//VERIFICACION DE ERROR EN LA BD
				log.error("[listarLubricanteVehiculo] Ocurrio un error en la operacion del Procedimiento almacenado listaTipoLubricanteVehiculo : "+mensajeRpta);
				outResponse.setCodResultado(codigoRpta);
				outResponse.setTotal(0);
				outResponse.setResponse("Error en la BD");
				outResponse.setMsgResultado(mensajeRpta);
				return outResponse;
			}
			
			TipoLbcteVhcloResponse tipoLbcteVhcloR = new TipoLbcteVhcloResponse();
			if(rs!=null) {
				if (rs.size() > 0) {
					
					for (Map<String, Object> map : rs) {
						
						TipoLbcteVhcloEntity tipoLbcteVhclo = new TipoLbcteVhcloEntity();
						
						tipoLbcteVhcloR.setKlmtrje(map.get("KTOTAL") !=null ? (long)map.get("KTOTAL") : (long)0);
						
						tipoLbcteVhclo.setIdVehiculo(map.get("ID_VEHICULO")!=null ? (long)map.get("ID_VEHICULO"):(long)0);
						tipoLbcteVhclo.setIdTipoLbcte(map.get("ID_LUBRIANTE")!=null ? (long)map.get("ID_LUBRIANTE"):(long)0);
						tipoLbcteVhclo.setNomTipoLbcte(map.get("LUBRICANTE")!=null ? map.get("LUBRICANTE").toString() : "");
						tipoLbcteVhclo.setIdTipoLbcteVhclo(map.get("ID_TIPO_LUBRICANTE_VEHICULO")!=null ? (long)map.get("ID_TIPO_LUBRICANTE_VEHICULO"):(long)0);
						tipoLbcteVhclo.setNumKlmtje(map.get("NUM_KLMTJE")!=null ? (long)map.get("NUM_KLMTJE"):(long)0);
						tipoLbcteVhclo.setCtdad(map.get("CTDAD")!=null ? (BigDecimal) map.get("CTDAD") : new BigDecimal("0.0"));
						tipoLbcteVhclo.setFecAsigcion(map.get("FEC_ASIGNACION")!=null ? map.get("FEC_ASIGNACION").toString() : "");
						tipoLbcteVhclo.setActual(map.get("ACTUAL")!=null ? (short)map.get("ACTUAL") : 0);
											
						tipoLbcteVhclos.add(tipoLbcteVhclo);
				}
					
					tipoLbcteVhcloR.setTipoLbcteVhclo(tipoLbcteVhclos);
					outResponse.setResponse(tipoLbcteVhcloR);
					outResponse.setCodResultado( results.get("COD_RESULTADO")!=null ? Integer.parseInt(results.get("COD_RESULTADO").toString()) : 500);
					outResponse.setMsgResultado( results.get("MSG_RESULTADO")!=null ? results.get("MSG_RESULTADO").toString() : "-" );
				}else {
//					outResponse.setCodResultado(2);//Codigo para indicar que no existen registros en la BD
//					outResponse.setTotal(0);
//					outResponse.setResponse("");
//					outResponse.setMsgResultado("¡No existen registros!");
					
					tipoLbcteVhcloR.setKlmtrje((long)0);
					outResponse.setCodResultado(1);	//Codigo para indicar que no existen registros en la BD
					outResponse.setTotal(0);
					outResponse.setResponse(tipoLbcteVhcloR);
					outResponse.setMsgResultado("¡No se encontraron Kilometrajes para el Vehículo!");
					
				}
			}
			
		} catch (Exception e) {
			log.error("DAO listarLubricanteVehiculo>>>>"+this.getClass().getName(), e);
			outResponse.setCodResultado(500);
			outResponse.setMsgResultado( "Error en la Base de datos!.");
		}
			
		log.info("[DAO listarLubricanteVehiculo] FIN  ");
		return outResponse;
	}
	
	@Override
	public ApiOutResponse listarClase() {
		log.info("[DAO listarClase] INICIO  ");	
		ApiOutResponse outResponse = new ApiOutResponse();
		List<ClaseResponse> clases = new ArrayList<>();
		
		int codigoRpta=0;
		String mensajeRpta="";
		try {
			SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate.getDataSource());
	
			jdbcCall.withSchemaName("UADM")
			.withProcedureName("listaClase")
			.declareParameters(
					new SqlOutParameter("COD_RESULTADO", 	Types.INTEGER),
					new SqlOutParameter("MSG_RESULTADO", 	Types.VARCHAR)
				);
	
			MapSqlParameterSource parametros = new MapSqlParameterSource();
			
			Map<String, Object> results = jdbcCall.execute(parametros);
			
			List<Map<String, Object>> rs = (List<Map<String, Object>>) results.get(Constantes.RETURN_RESULT_SET_PREFIX);
			
			codigoRpta=Integer.parseInt(results.get("COD_RESULTADO").toString());
			mensajeRpta=results.get("MSG_RESULTADO").toString();
			
			if (results.isEmpty()||results.size()<1) {
				log.info("Sin registros, verificar el procedimiento listaClase>>");
				return  new ApiOutResponse();
			}
			
			if (codigoRpta!=1) {//VERIFICACION DE ERROR EN LA BD
				log.error("[listarClase] Ocurrio un error en la operacion del Procedimiento almacenado listaClase : "+mensajeRpta);
				outResponse.setCodResultado(codigoRpta);
				outResponse.setTotal(0);
				outResponse.setResponse("Error en la BD");
				outResponse.setMsgResultado(mensajeRpta);
				return outResponse;
			}
			
			if(rs!=null) {
				if (rs.size() > 0) {
					TipoLbcteVhcloResponse tipoLbcteVhcloR = new TipoLbcteVhcloResponse();
					for (Map<String, Object> map : rs) {
						
						ClaseResponse clase = new ClaseResponse();
						
						tipoLbcteVhcloR.setKlmtrje(map.get("KTOTAL") !=null ? (long)map.get("KTOTAL") : 0);
						
						clase.setIdClase(map.get("ID_CLASE")!=null ? (long)map.get("ID_CLASE"):(long)0);
						clase.setNomClase(map.get("NOM_CLASE")!=null ? map.get("NOM_CLASE").toString() : "");
											
						clases.add(clase);
				}
					outResponse.setResponse(clases);
					outResponse.setCodResultado( results.get("COD_RESULTADO")!=null ? Integer.parseInt(results.get("COD_RESULTADO").toString()) : 500);
					outResponse.setMsgResultado( results.get("MSG_RESULTADO")!=null ? results.get("MSG_RESULTADO").toString() : "-" );
				}else {
					outResponse.setCodResultado(2);//Codigo para indicar que no existen registros en la BD
					outResponse.setTotal(0);
					outResponse.setResponse("");
					outResponse.setMsgResultado("¡No existen registros!");
				}
			}
			
		} catch (Exception e) {
			log.error("DAO listarClase>>>>"+this.getClass().getName(), e);
			outResponse.setCodResultado(500);
			outResponse.setMsgResultado( "Error en la Base de datos!.");
		}
			
		log.info("[DAO listarClase] FIN  ");
		return outResponse;
	}
	
	@Override
	public ApiOutResponse listarGrupo() {
		log.info("[DAO listarGrupo] INICIO  ");	
		ApiOutResponse outResponse = new ApiOutResponse();
		List<GrupoResponse> grupos = new ArrayList<>();
		
		int codigoRpta=0;
		String mensajeRpta="";
		try {
			SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate.getDataSource());
	
			jdbcCall.withSchemaName("UADM")
			.withProcedureName("listaGrupo")
			.declareParameters(
					new SqlOutParameter("COD_RESULTADO", 	Types.INTEGER),
					new SqlOutParameter("MSG_RESULTADO", 	Types.VARCHAR)
				);
	
			MapSqlParameterSource parametros = new MapSqlParameterSource();
			
			Map<String, Object> results = jdbcCall.execute(parametros);
			
			List<Map<String, Object>> rs = (List<Map<String, Object>>) results.get(Constantes.RETURN_RESULT_SET_PREFIX);
			
			codigoRpta=Integer.parseInt(results.get("COD_RESULTADO").toString());
			mensajeRpta=results.get("MSG_RESULTADO").toString();
			
			if (results.isEmpty()||results.size()<1) {
				log.info("Sin registros, verificar el procedimiento listaGrupo>>");
				return  new ApiOutResponse();
			}
			
			if (codigoRpta!=1) {//VERIFICACION DE ERROR EN LA BD
				log.error("[listarGrupo] Ocurrio un error en la operacion del Procedimiento almacenado listaGrupo : "+mensajeRpta);
				outResponse.setCodResultado(codigoRpta);
				outResponse.setTotal(0);
				outResponse.setResponse("Error en la BD");
				outResponse.setMsgResultado(mensajeRpta);
				return outResponse;
			}
			
			if(rs!=null) {
				if (rs.size() > 0) {
					for (Map<String, Object> map : rs) {
						
						GrupoResponse grupo = new GrupoResponse();
						
						grupo.setIdGrupo(map.get("ID_GRUPO")!=null ? (long)map.get("ID_GRUPO"):(long)0);
						grupo.setNomGrupo(map.get("NOM_GRUPO")!=null ? map.get("NOM_GRUPO").toString() : "");
											
						grupos.add(grupo);
				}
					outResponse.setResponse(grupos);
					outResponse.setCodResultado( results.get("COD_RESULTADO")!=null ? Integer.parseInt(results.get("COD_RESULTADO").toString()) : 500);
					outResponse.setMsgResultado( results.get("MSG_RESULTADO")!=null ? results.get("MSG_RESULTADO").toString() : "-" );
				}else {
					outResponse.setCodResultado(2);//Codigo para indicar que no existen registros en la BD
					outResponse.setTotal(0);
					outResponse.setResponse("");
					outResponse.setMsgResultado("¡No existen registros!");
				}
			}
			
		} catch (Exception e) {
			log.error("DAO listarGrupo>>>>"+this.getClass().getName(), e);
			outResponse.setCodResultado(500);
			outResponse.setMsgResultado( "Error en la Base de datos!.");
		}
			
		log.info("[DAO listarGrupo] FIN  ");
		return outResponse;
	}
	
	
	@Override
	public ApiOutResponse buscarEmpleadoPuesto(BuscaEmpleadoRequest objeto) {
		log.info("[DAO buscarEmpleadoPuesto] INICIO  ");	
		ApiOutResponse outResponse = new ApiOutResponse();
		List<BuscaEmpleadoResponse> listadoEmpleadoPuestos = new ArrayList<>();
		
		int codigoRpta=0;
		String mensajeRpta="";
		try {
			SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate.getDataSource());
	
			jdbcCall.withSchemaName("UADM")
			.withProcedureName("buscarEmpleadoPuesto")
			.declareParameters(
					new SqlParameter("AP_PAT", 		Types.VARCHAR),
					new SqlParameter("AP_MAT", 		Types.VARCHAR),
					new SqlParameter("PRI_NOMBRE", 		Types.VARCHAR),
					new SqlParameter("ID_PUESTO", 		Types.BIGINT),
					new SqlParameter("NUM_DOC_IDEN", 		Types.VARCHAR),
					new SqlOutParameter("COD_RESULTADO", 	Types.INTEGER),
					new SqlOutParameter("MSG_RESULTADO", 	Types.VARCHAR)
				);
	
			MapSqlParameterSource parametros = new MapSqlParameterSource();

			parametros.addValue("AP_PAT",objeto.getApePaterno());
			parametros.addValue("AP_MAT",objeto.getApeMaterno());
			parametros.addValue("PRI_NOMBRE",objeto.getNomPrimero());
			parametros.addValue("ID_PUESTO",objeto.getIdPuesto());
			parametros.addValue("NUM_DOC_IDEN",objeto.getNumDocIden());
			
//			System.out.println("==========================================================================");
//			System.out.println(objeto.getApePaterno());
//			System.out.println(objeto.getApeMaterno());
//			System.out.println(objeto.getNomPrimero());
//			System.out.println(objeto.getIdPuesto());
//			System.out.println(objeto.getNumDocIden());
//			System.out.println("==========================================================================");
			
			Map<String, Object> results = jdbcCall.execute(parametros);
			List<Map<String, Object>> rs = (List<Map<String, Object>>) results.get(Constantes.RETURN_RESULT_SET_PREFIX);
			
//			System.out.println("==========================================================================");
//			System.out.println(results);
//			System.out.println("==========================================================================");
			
			
			codigoRpta=Integer.parseInt(results.get("COD_RESULTADO").toString());
			mensajeRpta=results.get("MSG_RESULTADO").toString();
			
			if (results.isEmpty()||results.size()<1) {
				log.info("Sin registros, verificar el procedimiento buscarEmpleadoPuesto>>");
				return  new ApiOutResponse();
			}
			
			if (codigoRpta!=1) {//VERIFICACION DE ERROR EN LA BD
				log.error("[buscarEmpleadoPuesto] Ocurrio un error en la operacion del Procedimiento almacenado buscarEmpleadoPuesto : "+mensajeRpta);
				outResponse.setCodResultado(codigoRpta);
				outResponse.setTotal(0);
				outResponse.setResponse("Error en la BD");
				outResponse.setMsgResultado(mensajeRpta);
				return outResponse;
			}
			
			if(rs!=null) {
				if (rs.size() > 0) {
					for (Map<String, Object> map : rs) {
						BuscaEmpleadoResponse listadoEmpleadoPuesto = new BuscaEmpleadoResponse();
						
						listadoEmpleadoPuesto.setIdEmpleado(map.get("ID_EMPLEADO")!=null ? (long)map.get("ID_EMPLEADO"):0);
						listadoEmpleadoPuesto.setIdPersona(map.get("ID_PESONA")!=null ? (long)map.get("ID_PESONA"): 0);
						listadoEmpleadoPuesto.setIdTipDocIden(map.get("TIP_DOC_IDEN")!=null ? (long)map.get("TIP_DOC_IDEN"): 0);
						listadoEmpleadoPuesto.setNumDocIden(map.get("NUM_DOC_IDEN")!=null ? map.get("NUM_DOC_IDEN").toString() : "");
						listadoEmpleadoPuesto.setApPaterno(map.get("APE_PATERNO")!=null ? map.get("APE_PATERNO").toString() : "");
						listadoEmpleadoPuesto.setApMaterno(map.get("APE_MATERNO")!=null ? map.get("APE_MATERNO").toString() : "");
						listadoEmpleadoPuesto.setNomPrimero(map.get("NOMBRE1")!=null ? map.get("NOMBRE1").toString() : "");
						listadoEmpleadoPuesto.setNomSegundo(map.get("NOMBRE2")!=null ? map.get("NOMBRE2").toString() : "");
						
						listadoEmpleadoPuesto.setIdPuesto(map.get("ID_PUESTO")!=null ? (long)map.get("ID_PUESTO"):0);
						listadoEmpleadoPuesto.setNomPuesto(map.get("NOM_PUESTO")!=null ? map.get("NOM_PUESTO").toString() : "");
						listadoEmpleadoPuesto.setIdModalidadContrato(map.get("ID_MODALIDAD_CONTRATO")!=null ? (long)map.get("ID_MODALIDAD_CONTRATO"):0);
						listadoEmpleadoPuesto.setNomModalidadContrato(map.get("NOM_MODALIDAD_CONTRATO")!=null ? map.get("NOM_MODALIDAD_CONTRATO").toString() : "");
						listadoEmpleadoPuesto.setSigModalidadContrato(map.get("SIGLA_MODALIDAD_CONTRATO")!=null ? map.get("SIGLA_MODALIDAD_CONTRATO").toString() : "");
						listadoEmpleadoPuesto.setIdEntidad(map.get("ID_ENTIDAD")!=null ? (long)map.get("ID_ENTIDAD"):0);
						listadoEmpleadoPuesto.setNomEntidad(map.get("NOM_ENTIDAD")!=null ?  map.get("NOM_ENTIDAD").toString() : "");
						listadoEmpleadoPuesto.setIdSector(map.get("ID_SECTOR")!=null ? (long)map.get("ID_SECTOR"):0);
						
						listadoEmpleadoPuesto.setNomSectorCorto(map.get("NOM_SECTOR_CORTO")!=null ? map.get("NOM_SECTOR_CORTO").toString() : "");
						listadoEmpleadoPuesto.setNomSectorLargo(map.get("NOM_SECTOR_LARGO")!=null ? map.get("NOM_SECTOR_LARGO").toString() : "");
						listadoEmpleadoPuesto.setIdTipoGobierno(map.get("ID_TIPO_GOBIERNO")!=null ? (long)map.get("ID_TIPO_GOBIERNO"):0);
						listadoEmpleadoPuesto.setCidTipoGobierno(map.get("COD_TIPO_GOBIERNO")!=null ? map.get("COD_TIPO_GOBIERNO").toString() : "");
						listadoEmpleadoPuesto.setNomTipoGobierno(map.get("NOM_TIPO_GOBIERNO")!=null ? map.get("NOM_TIPO_GOBIERNO").toString() : "");
						listadoEmpleadoPuesto.setIdArea(map.get("ID_AREA")!=null ? (long)map.get("ID_AREA"):0);
						listadoEmpleadoPuesto.setCidArea(map.get("COD_AREA")!=null ? map.get("COD_AREA").toString() : "");
						listadoEmpleadoPuesto.setNomArea(map.get("NOM_AREA")!=null ? map.get("NOM_AREA").toString() : "");
						listadoEmpleadoPuesto.setIdUnidad(map.get("ID_UNIDAD")!=null ? (long)map.get("ID_UNIDAD"):0);
						listadoEmpleadoPuesto.setCidUnidad(map.get("COD_UNIDAD")!=null ? map.get("COD_UNIDAD").toString() : "");
						
						listadoEmpleadoPuesto.setNomUnidad(map.get("NOM_UNIDAD")!=null ? map.get("NOM_UNIDAD").toString() : "");
						listadoEmpleadoPuesto.setIdUTerritorial(map.get("ID_UTERRITORIAL")!=null ? (long)map.get("ID_UTERRITORIAL"):0);
						listadoEmpleadoPuesto.setNomUTerritorial(map.get("NOMBRE_UT")!=null ? map.get("NOMBRE_UT").toString() : "");
						listadoEmpleadoPuesto.setIdPlataforma(map.get("ID_PLAT")!=null ? (long)map.get("ID_PLAT"):0);
						listadoEmpleadoPuesto.setNomPlataforma(map.get("NOMBRE_PLAT")!=null ? map.get("NOMBRE_PLAT").toString() : "");
						listadoEmpleadoPuesto.setIdUTPlataforma(map.get("ID_UTERRITORIALP")!=null ? (long)map.get("ID_UTERRITORIALP"):0);
						listadoEmpleadoPuesto.setNomUTPlataforma(map.get("NOMBRE_UTP")!=null ? map.get("NOMBRE_UTP").toString() : "");
					
						listadoEmpleadoPuestos.add(listadoEmpleadoPuesto);
					}
					outResponse.setResponse(listadoEmpleadoPuestos);
					outResponse.setCodResultado( results.get("COD_RESULTADO")!=null ? Integer.parseInt(results.get("COD_RESULTADO").toString()) : 500);
					outResponse.setMsgResultado( results.get("MSG_RESULTADO")!=null ? results.get("MSG_RESULTADO").toString() : "-" );
				}else {
					outResponse.setCodResultado(2);//Codigo para indicar que no existen registros en la BD
					outResponse.setTotal(0);
					outResponse.setResponse("");
					outResponse.setMsgResultado("¡No existen registros!");
				}
			}
			
		} catch (Exception e) {
			log.error("DAO buscarEmpleadoPuesto>>>>"+this.getClass().getName(), e);
			outResponse.setCodResultado(500);
			outResponse.setMsgResultado( "Error en la Base de datos!.");
		}
			
		log.info("[DAO buscarEmpleadoPuesto] FIN  ");
		return outResponse;
	}
	
	/*
	@Override
	public ApiOutResponse buscarGrupoElectrogeno(BuscaGElectrogenoRequest objeto) {
		log.info("[DAO buscarGrupoElectrogeno] INICIO  ");	
		ApiOutResponse outResponse = new ApiOutResponse();
		List<DenominacionBienPorPlataformaResonse> denominaciones = new ArrayList<>(); // LISTA DE DENOMINACIONES PARA PLATAFORMAS U OFICINAS RELACIONADAS A UNIDAD TERRITORIAL
		
		int codigoRpta = 0, flgResult1 = 0, flgResult2 = 0;
		String mensajeRpta="";
		
		try {
			SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate.getDataSource());
	
			jdbcCall.withSchemaName("UADM")
			.withProcedureName("buscarGrupoElectrogenoBP")
			.declareParameters(
					new SqlParameter("ID_OPCION", 		Types.VARCHAR),
					new SqlParameter("ID_PLATAFORMA", 		Types.BIGINT),
					new SqlParameter("ID_UNIDAD_TERRITORIAL", 		Types.BIGINT),
					new SqlParameter("CAD_LIKE", 		Types.VARCHAR),
					new SqlOutParameter("COD_RESULTADO", 	Types.INTEGER),
					new SqlOutParameter("MSG_RESULTADO", 	Types.VARCHAR)
				);
	
			MapSqlParameterSource parametros = new MapSqlParameterSource();
			String txtCaso = "";
			Long idCodigo = (long)0;
			
			if (objeto.getIdUTerritorial() == 0) {
				txtCaso = "LISTA_POR__OFCNA_PLAT";
			}else {
				if (objeto.getIdUTerritorial() > 0 && objeto.getIdPlataforma() == 0) {
					txtCaso = "LISTA_POR__OFCNA_PLAT";
				}
				if (objeto.getIdUTerritorial() > 0 && objeto.getIdPlataforma() == -1) {
					txtCaso = "LISTA_POR_OFICINA";
				}
				if (objeto.getIdUTerritorial() > 0 && objeto.getIdPlataforma() > 0) {
					txtCaso = "LISTA_POR_PLATAFORMA";
				}
			}
			
			String cadGrupoClase = "%" + objeto.getCadGrupo()+objeto.getCadClase() + "%";
			parametros.addValue("ID_OPCION",txtCaso);
			parametros.addValue("ID_PLATAFORMA",objeto.getIdPlataforma());
			parametros.addValue("ID_UNIDAD_TERRITORIAL",objeto.getIdUTerritorial());
			parametros.addValue("CAD_LIKE",cadGrupoClase);
			
			Map<String, Object> results = jdbcCall.execute(parametros);
			List<Map<String, Object>> rs = (List<Map<String, Object>>) results.get(Constantes.RETURN_RESULT_SET_PREFIX);
				
			codigoRpta=Integer.parseInt(results.get("COD_RESULTADO").toString());
			mensajeRpta=results.get("MSG_RESULTADO").toString();

			if (results.isEmpty()||results.size()<1) {
				log.info("Sin registros, verificar el procedimiento listaDenominacionPlataforma>>");
				return  new ApiOutResponse();
			}
			
			if (codigoRpta!=1) {//VERIFICACION DE ERROR EN LA BD
				log.error("[buscarGrupoElectrogeno] Ocurrio un error en la operacion del Procedimiento almacenado listaDenominacionPlataforma : "+mensajeRpta);
				outResponse.setCodResultado(codigoRpta);
				outResponse.setTotal(0);
				outResponse.setResponse("Error en la BD");
				outResponse.setMsgResultado(mensajeRpta);
				return outResponse;
			}

			if (txtCaso.equalsIgnoreCase("LISTA_POR__OFCNA_PLAT")) {
				List<Map<String, Object>> rs2 = (List<Map<String, Object>>) results.get("#result-set-2");
				if(rs2!=null) {
					if (rs2.size() > 0) {
						for (Map<String, Object> mapeo : rs2) {
							DenominacionBienPorPlataformaResonse item = new DenominacionBienPorPlataformaResonse();
							item.setIdDenominacion(mapeo.get("ID_CODIGO")!=null ? (long)mapeo.get("ID_CODIGO"):0);
							item.setNomDenominacion(mapeo.get("CID_NOMBRE")!=null ? mapeo.get("CID_NOMBRE").toString() : "" );
							
							denominaciones.add(item);
						}
						flgResult1 = 1;
					}else {
						flgResult1 = 0;
					}
				}
			}
			
			if(rs!=null) {
				if (rs.size() > 0) {
					for (Map<String, Object> map : rs) {
						DenominacionBienPorPlataformaResonse item = new DenominacionBienPorPlataformaResonse();
						item.setIdDenominacion(map.get("ID_CODIGO")!=null ? (long)map.get("ID_CODIGO"):0);
						item.setNomDenominacion(map.get("CID_NOMBRE")!=null ? map.get("CID_NOMBRE").toString() : "" );
						
						denominaciones.add(item);
					}
					flgResult2 = 1;
				}else {
					if (flgResult1 == 0) {
						flgResult2 = 0;
					}
				}
			}
			if (flgResult1 == 1 || flgResult2 == 1) {
				outResponse.setResponse(denominaciones);
				outResponse.setCodResultado( results.get("COD_RESULTADO")!=null ? Integer.parseInt(results.get("COD_RESULTADO").toString()) : 500);
				outResponse.setMsgResultado( results.get("MSG_RESULTADO")!=null ? results.get("MSG_RESULTADO").toString() : "-" );
			}else {
				outResponse.setCodResultado(2);//Codigo para indicar que no existen registros en la BD
				outResponse.setTotal(0);
				outResponse.setResponse("");
				outResponse.setMsgResultado("¡No existen registros!");
			}
		} catch (Exception e) {
			log.error("DAO buscarGrupoElectrogeno>>>>"+this.getClass().getName(), e);
			outResponse.setCodResultado(500);
			outResponse.setMsgResultado( "Error en la Base de datos!.");
		}
			
		log.info("[DAO buscarGrupoElectrogeno] FIN  ");
		return outResponse;
	}*/
}
