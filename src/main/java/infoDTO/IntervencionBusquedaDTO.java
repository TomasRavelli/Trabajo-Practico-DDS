package infoDTO;

import java.time.LocalDate;

public class IntervencionBusquedaDTO {
	
	private String estado;
	private LocalDate fechaDesde;
	private LocalDate fechaHasta;
	private Integer numeroTicket;
	private Integer numeroLegajo;
	
	public IntervencionBusquedaDTO() {
		
	}
	
	public IntervencionBusquedaDTO(String e, String numTicket, String legajo) {
		this.estado = e;
		
		if (!numTicket.isEmpty()) {
			try {
				this.numeroTicket = Integer.valueOf(numTicket);
			} catch (NumberFormatException e1) {
				e1.printStackTrace();
			}
		}
		
		if (!legajo.isEmpty()) {
			try {
				this.numeroLegajo = Integer.valueOf(legajo);
			} catch (NumberFormatException e1) {
				e1.printStackTrace();
			}
		}		
	}
	
	public IntervencionBusquedaDTO(String e, LocalDate fd, LocalDate fh, String numTicket, String legajo) {
		this.estado = e;
		this.fechaDesde = fd;
		this.fechaHasta = fh;
		
		if (!numTicket.isEmpty()) {
			try {
				this.numeroTicket = Integer.valueOf(numTicket);
			} catch (NumberFormatException e1) {
				e1.printStackTrace();
			}
		}
		
		if (!legajo.isEmpty()) {
			try {
				this.numeroLegajo = Integer.valueOf(legajo);
			} catch (NumberFormatException e1) {
				e1.printStackTrace();
			}
		}

	}


	
	public String getEstado() {
		return estado;
	}
	
	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	public LocalDate getFechaDesde() {
		return fechaDesde;
	}
	
	public void setFechaDesde(LocalDate fechaDesde) {
		this.fechaDesde = fechaDesde;
	}
	
	public LocalDate getFechaHasta() {
		return fechaHasta;
	}
	
	public void setFechaHasta(LocalDate fechaHasta) {
		this.fechaHasta = fechaHasta;
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
}