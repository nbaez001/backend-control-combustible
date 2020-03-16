package com.pais.ua.model.seguridad;

public class DatoGeneral {

	long idUsuario;
	long idPerfil;
	long idProyecto;
	long idMovimientoProyecto;
	String ip;
	String nombreArchivo;
	String rutaArchivo;
	
	public long getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(long idUsuario) {
		this.idUsuario = idUsuario;
	}
	public long getIdPerfil() {
		return idPerfil;
	}
	public void setIdPerfil(long idPerfil) {
		this.idPerfil = idPerfil;
	}
	public long getIdProyecto() {
		return idProyecto;
	}
	public void setIdProyecto(long idProyecto) {
		this.idProyecto = idProyecto;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public long getIdMovimientoProyecto() {
		return idMovimientoProyecto;
	}
	public void setIdMovimientoProyecto(long idMovimientoProyecto) {
		this.idMovimientoProyecto = idMovimientoProyecto;
	}
	public String getNombreArchivo() {
		return nombreArchivo;
	}
	public void setNombreArchivo(String nombreArchivo) {
		this.nombreArchivo = nombreArchivo;
	}
	public String getRutaArchivo() {
		return rutaArchivo;
	}
	public void setRutaArchivo(String rutaArchivo) {
		this.rutaArchivo = rutaArchivo;
	}
	
	
}
