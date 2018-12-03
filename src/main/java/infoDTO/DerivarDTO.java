package infoDTO;

import modelo.entidades.ClasificacionTicket;

public class DerivarDTO {
	
	private Integer numeroLegajo;
	private Integer numeroTicket;
	private ClasificacionTicket clasificacion;
	private String observaciones;
	
	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public DerivarDTO () {
		
	}
	
	public DerivarDTO (Integer numeroTicket, Integer numeroLegajo, ClasificacionTicket clasificacion, String observaciones) {
		this.numeroLegajo = numeroLegajo;
		this.numeroTicket = numeroTicket;
		this.clasificacion = clasificacion;
		this.observaciones = observaciones;
	}

	public Integer getNumeroLegajo() {
		return numeroLegajo;
	}

	public void setNumeroLegajo(Integer numeroLegajo) {
		this.numeroLegajo = numeroLegajo;
	}

	public Integer getNumeroTicket() {
		return numeroTicket;
	}

	public void setNumeroTicket(Integer numeroTicket) {
		this.numeroTicket = numeroTicket;
	}

	public ClasificacionTicket getClasificacion() {
		return clasificacion;
	}

	public void setClasificacion(ClasificacionTicket clasificacion) {
		this.clasificacion = clasificacion;
	}

}
