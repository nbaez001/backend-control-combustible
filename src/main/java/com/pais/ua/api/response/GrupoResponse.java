package com.pais.ua.api.response;

import java.io.Serializable;

public class GrupoResponse implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Long idGrupo;
	private String nomGrupo;
	
	public Long getIdGrupo() {
		return idGrupo;
	}
	public void setIdGrupo(Long idGrupo) {
		this.idGrupo = idGrupo;
	}
	public String getNomGrupo() {
		return nomGrupo;
	}
	public void setNomGrupo(String nomGrupo) {
		this.nomGrupo = nomGrupo;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
}
