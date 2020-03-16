package com.pais.ua.api.response;

import java.io.Serializable;
import java.util.List;

import com.pais.ua.entity.TipoLbcteVhcloEntity;

public class TipoLbcteVhcloResponse implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long klmtrje;
	private List<TipoLbcteVhcloEntity> tipoLbcteVhclo;
	
	public Long getKlmtrje() {
		return klmtrje;
	}
	public void setKlmtrje(Long klmtrje) {
		this.klmtrje = klmtrje;
	}
	public List<TipoLbcteVhcloEntity> getTipoLbcteVhclo() {
		return tipoLbcteVhclo;
	}
	public void setTipoLbcteVhclo(List<TipoLbcteVhcloEntity> tipoLbcteVhclo) {
		this.tipoLbcteVhclo = tipoLbcteVhclo;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}


}
