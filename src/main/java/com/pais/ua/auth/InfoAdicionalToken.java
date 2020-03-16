/*
 * 
 */
package com.pais.ua.auth;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import com.pais.ua.api.AuthUsuario;
import com.pais.ua.bus.AccesoBus;


@Component
public class InfoAdicionalToken implements TokenEnhancer{
	
	@Autowired
	private AccesoBus accesoBus;
	
	@Override
	public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
		
		AuthUsuario usu = accesoBus.findByUsername(authentication.getName());
		
		Map<String, Object> info = new HashMap<>();
		
		info.put("id_usuario", 			usu.getIdCodigo());
		info.put("username", 		usu.getCidUsuario());
		info.put("nombres", 			usu.getCidNombre());
		info.put("perfil", 			usu.getCidNombrePerfil());
		info.put("codPerfil", 		usu.getCidCodigoPerfil());
		info.put("id_perfil", usu.getIdCodigoPerfil());
		
		((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(info);
		
		return accessToken;
	}

}
