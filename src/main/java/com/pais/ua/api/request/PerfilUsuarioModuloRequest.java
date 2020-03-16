package com.pais.ua.api.request;

import java.io.Serializable;

public class PerfilUsuarioModuloRequest implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Long idUsuario;
	private Long idModulo;
	
	public Long getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}
	public Long getIdModulo() {
		return idModulo;
	}
	public void setIdModulo(Long idModulo) {
		this.idModulo = idModulo;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	

}
