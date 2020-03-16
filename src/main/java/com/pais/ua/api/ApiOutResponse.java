package com.pais.ua.api;

public class ApiOutResponse {


    private Integer codResultado;
    private String msgResultado;
    private Integer total;
    private Object response;

    private Integer idPerfil;//agregado para excel 

    public Integer getCodResultado() {
        return codResultado;
    }

    public void setCodResultado(Integer codResultado) {
        this.codResultado = codResultado;
    }

    public String getMsgResultado() {
        return msgResultado;
    }

    public void setMsgResultado(String msgResultado) {
        this.msgResultado = msgResultado;
    }

    public Object getResponse() {
        return response;
    }

    public void setResponse(Object response) {
        this.response = response;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "ApiOutResponse [codResultado=" + codResultado + ", msgResultado=" + msgResultado + ", response="
                + response + "]";
    }

	public Integer getIdPerfil() {
		return idPerfil;
	}

	public void setIdPerfil(Integer idPerfil) {
		this.idPerfil = idPerfil;
	}
}
