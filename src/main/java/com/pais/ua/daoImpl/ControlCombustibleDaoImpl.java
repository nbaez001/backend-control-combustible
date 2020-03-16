package com.pais.ua.daoImpl;

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

import com.pais.ua.api.ApiOutResponse;
import com.pais.ua.api.Item;
import com.pais.ua.api.request.KilometrajeRequest;
import com.pais.ua.api.response.KilometrajeResponse;
import com.pais.ua.dao.ControlCombustibleDao;
import com.pais.ua.util.Constantes;
import com.pais.ua.util.Util;

@Repository
public class ControlCombustibleDaoImpl implements ControlCombustibleDao {
	
	private  final Log log = LogFactory.getLog(getClass());
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	private SimpleJdbcCall jdbcCall;
	
	@Override
	public ApiOutResponse ListadoKiometraje(KilometrajeRequest objeto) {
		
		log.info("[DAO ListadoKiometraje] INICIO  ");	
		ApiOutResponse outResponse = new ApiOutResponse();
		SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate.getDataSource());
		List<KilometrajeResponse> kilometrajes = new ArrayList<>();
		
		int codigoRpta=0;
		String mensajeRpta="";
		
		try {
			
			jdbcCall.withSchemaName("UADM")
			.withProcedureName("ListadoDeKiometraje")
			.declareParameters(
					new SqlParameter("ID_UNIDAD", 			Types.BIGINT),
					new SqlParameter("ID_TAMBO", 			Types.BIGINT),
					new SqlParameter("ID_VEHICULO", 		Types.BIGINT),
					new SqlParameter("FEC_INICIO", 			Types.VARCHAR),
					new SqlParameter("FEC_FIN", 			Types.VARCHAR),
					new SqlOutParameter("COD_RESULTADO", 	Types.INTEGER),
					new SqlOutParameter("MSG_RESULTADO", 	Types.VARCHAR)
				);
	
			MapSqlParameterSource parametros = new MapSqlParameterSource();
			parametros.addValue("ID_UNIDAD",	objeto.getIdUnidad());
			parametros.addValue("ID_TAMBO",		objeto.getIdPlataforma());
			parametros.addValue("ID_VEHICULO",	objeto.getIdVehiculo());
			parametros.addValue("FEC_INICIO",	Util.getDateyyyyMMdd(objeto.getFecInicio()));
			parametros.addValue("FEC_FIN",		Util.getDateyyyyMMdd(objeto.getFecFin()));
			
			Map<String, Object> results = jdbcCall.execute(parametros);
			
			List<Map<String, Object>> rs = (List<Map<String, Object>>) results.get(Constantes.RETURN_RESULT_SET_PREFIX);
			
			
			System.out.println("=====================================");
			//System.out.println(Constantes.RETURN_RESULT_SET_PREFIX);
			System.out.println(results);						
			System.out.println(rs);
			System.out.println("=====================================");
			
			codigoRpta=Integer.parseInt(results.get("COD_RESULTADO").toString());
			mensajeRpta=results.get("MSG_RESULTADO").toString();
			
			if (results.isEmpty()||results.size()<1) {
				log.info("Sin registros, verificar el procedimiento ListadoDeKiometraje>>");
				return  new ApiOutResponse();
			}
			
			if (codigoRpta!=1) {//VERIFICACION DE ERROR EN LA BD
				log.error("[ListadoKiometraje] Ocurrio un error en la operacion del Procedimiento almacenado ListadoDeKiometraje : "+mensajeRpta);
				outResponse.setCodResultado(codigoRpta);
				outResponse.setTotal(0);
				outResponse.setResponse("Error en la BD");
				outResponse.setMsgResultado(mensajeRpta);
				return outResponse;
			}
			  
			if(rs!=null) {
				if (rs.size() > 0) {
					for (Map<String, Object> map : rs) {
						KilometrajeResponse item = new KilometrajeResponse();
						item.setIdCtlKlmtje(map.get("ID_CODIGO")!=null ? (long)map.get("ID_CODIGO"):0);
						item.setIdUnidad(map.get("ID_UNIDAD")!=null ? (long)map.get("ID_UNIDAD"):0);
						item.setNomUnidad(map.get("NOMBRE_UNIDAD")!=null ? map.get("NOMBRE_UNIDAD").toString() : "" );
						item.setIdPlataforma(map.get("ID_PTA")!=null ? (long)map.get("ID_PTA"):0);
						item.setNomPlataforma(map.get("NOMBRE_PLATAFORMA")!=null ? map.get("NOMBRE_PLATAFORMA").toString() : "" );
						item.setIdDenominacion(map.get("ID_DEN")!=null ? (long)map.get("ID_DEN"):0);
						item.setNomDenminacion(map.get("NOMBRE_DENOMINACION")!=null ? map.get("NOMBRE_DENOMINACION").toString() : "" );
						item.setIdVehiculo(map.get("ID_VHCLO")!=null ? (long)map.get("ID_VHCLO"):0);
						item.setNomMarca(map.get("NOMBRE_MARCA")!=null ? map.get("NOMBRE_MARCA").toString() : "" );
						item.setNumPlaca(map.get("CID_PLACA")!=null ? map.get("CID_PLACA").toString() : "" );
						item.setHorSalida(map.get("HOR_SALIDA")!=null ? map.get("HOR_SALIDA").toString() : "" );
						item.setHorLlegada(map.get("HOR_LLEGADA")!=null ? map.get("HOR_LLEGADA").toString() : "" );
						item.setNumKlmtjeSalida(map.get("NUM_KLMTJE_SLDA")!=null ? (long)map.get("NUM_KLMTJE_SLDA"):0);
						item.setNumKlmtjeLlegada(map.get("NUM_KLMTJE_LGDA")!=null ? (long)map.get("NUM_KLMTJE_LGDA"):0);
						item.setIdLugarOrigen(map.get("ID_ORI")!=null ? (long)map.get("ID_ORI"):0);
						item.setNomLugarOrigen(map.get("NOMBRE_ORI")!=null ? map.get("NOMBRE_ORI").toString() : "" );
						item.setIdLugarDestino(map.get("ID_DES")!=null ? (long)map.get("ID_DES"):0);
						item.setNomLugarDestino(map.get("NOMBRE_DEST")!=null ? map.get("NOMBRE_DEST").toString() : "" );
						item.setTxtObservacion(map.get("TXT_OBSERVACION")!=null ? map.get("TXT_OBSERVACION").toString() : "" );
						item.setFecRegCtlKmtje(map.get("FEC_REGI")!=null ? map.get("FEC_REGI").toString() : "" );
						
						
						kilometrajes.add(item);
					}
					outResponse.setResponse(kilometrajes);
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
			log.error("DAO ListadoKiometraje>>>>"+this.getClass().getName(), e);
			outResponse.setCodResultado(500);
			outResponse.setMsgResultado( "Error en la Base de datos!.");
		}
			
		log.info("[DAO ListadoKiometraje] FIN  ");
		return outResponse;
	}

}
