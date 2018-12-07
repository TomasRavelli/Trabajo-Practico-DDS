package infoDTO;

import java.time.LocalDate;

public class DatosDTO {
	
	private Integer numeroTicket;
	private Integer numeroLegajo;
	private LocalDate fechaApertura;
	private LocalDate fechaUltimoCambioEstado;
	private String clasificacion;
	private String estado;
	private String grupo;
	
	
	public DatosDTO() {}

	public DatosDTO (String clasificacion, String estado, String grupo) {
		this.clasificacion = clasificacion;
		this.estado = estado;
		this.grupo = grupo;
	}
	
	public DatosDTO (String clasificacion, String estado, String grupo, Integer numeroTicket, Integer numeroLegajo, LocalDate fechaApertura, LocalDate fechaUltimoCambioEstado) {
		this.clasificacion = clasificacion;
		this.estado = estado;
		this.grupo = grupo;
		this.numeroTicket = numeroTicket;
		this.numeroLegajo = numeroLegajo;
		this.fechaApertura = fechaApertura;
		this.fechaUltimoCambioEstado = fechaUltimoCambioEstado;
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

	public LocalDate getFechaApertura() {
		return fechaApertura;
	}

	public void setFechaApertura(LocalDate fechaApertura) {
		this.fechaApertura = fechaApertura;
	}

	public LocalDate getFechaUltimoCambioEstado() {
		return fechaUltimoCambioEstado;
	}

	public void setFechaUltimoCambioEstado(LocalDate fechaUltimoCambioEstado) {
		this.fechaUltimoCambioEstado = fechaUltimoCambioEstado;
	}

	public String getClasificacion() {
		return clasificacion;
	}

	public void setClasificacion(String clasificacion) {
		this.clasificacion = clasificacion;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getGrupo() {
		return grupo;
	}

	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}

}
