package infoDTO;

import java.time.LocalDate;

public class IntervencionResultadoDTO {
	
	private Integer numeroTicket;
	private Integer numeroLegajo;
	private String clasificacion;
	private String estadoTicket;
	private LocalDate fechaApertura;
	private LocalDate fechaAsignacionIntervencion;
	private String estadoIntervencion;
	private String grupo;
	private String observacionIntervencion;
	
	public IntervencionResultadoDTO() {
		
	}
	
	public IntervencionResultadoDTO(Integer numt, Integer legajo, String clas, String estadoT, LocalDate fechaAp, LocalDate fechaAsig, String estadoInt, String g, String obsInt) {
		this.numeroTicket = numt;
		this.numeroLegajo = legajo;
		this.clasificacion = clas;
		this.estadoTicket = estadoT;
		this.fechaApertura = fechaAp;
		this.fechaAsignacionIntervencion = fechaAsig;
		this.estadoIntervencion = estadoInt;
		this.grupo = g;
		this.observacionIntervencion = obsInt;
	}
	
	
	public Integer getNumeroTicket() {
		return numeroTicket;
	}
	
	public void setNumeroTicket(Integer numeroTicket) {
		this.numeroTicket = numeroTicket;
	}
	
	public Integer getNumeroLegajo() {
		return numeroLegajo;
	}
	
	public void setNumeroLegajo(Integer numeroLegajo) {
		this.numeroLegajo = numeroLegajo;
	}
	
	public String getClasificacion() {
		return clasificacion;
	}
	
	public void setClasificacion(String clasificacion) {
		this.clasificacion = clasificacion;
	}
	
	public String getEstadoTicket() {
		return estadoTicket;
	}
	
	public void setEstadoTicket(String estadoTicket) {
		this.estadoTicket = estadoTicket;
	}
	
	public LocalDate getFechaApertura() {
		return fechaApertura;
	}
	
	public void setFechaApertura(LocalDate fechaApertura) {
		this.fechaApertura = fechaApertura;
	}
	
	public LocalDate getFechaAsignacionIntervencion() {
		return fechaAsignacionIntervencion;
	}
	
	public void setFechaAsignacionIntervencion(LocalDate fechaAsignacionIntervencion) {
		this.fechaAsignacionIntervencion = fechaAsignacionIntervencion;
	}
	
	public String getEstadoIntervencion() {
		return estadoIntervencion;
	}
	
	public void setEstadoIntervencion(String estadoIntervencion) {
		this.estadoIntervencion = estadoIntervencion;
	}
	
	public String getGrupo() {
		return grupo;
	}
	
	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}
	
	public String getObservacionIntervencion() {
		return observacionIntervencion;
	}
	
	public void setObservacionIntervencion(String observacionIntervencion) {
		this.observacionIntervencion = observacionIntervencion;
	}
}