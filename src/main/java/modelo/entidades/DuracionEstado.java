package modelo.entidades;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table (name = "DURACION_ESTADO")
public class DuracionEstado implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column (name = "ID_DURACION_ESTADO")
	private Integer idDuracionEstado;
	
	//NUMERO_TICKET , NUMERO_LEGAJO, ID_ESTADO SON FK
	
	@ManyToOne
	@JoinColumn (name = "NUMERO_LEGAJO")
	private Usuario usuario;
	
	@ManyToOne
	@JoinColumn (name = "ID_ESTADO")
	private Estado estado;
	
	@ManyToOne (cascade = {CascadeType.ALL})//fijarse su va
	@JoinColumn (name = "NUMERO_TICKET")
	private Ticket ticket;
	
	
	
	@Column (name = "FECHA_INICIO")
	private LocalDate fechaInicio;
	@Column (name = "FECHA_FIN")
	private LocalDate fechaFin;
	@Column (name = "OBSERVACIONES")
	private String observaciones;
	
	
	public DuracionEstado() {}
	
	public DuracionEstado(LocalDate fInicio, Usuario u) {
		this.fechaInicio = fInicio;
		this.usuario = u;
	}



	public LocalDate getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(LocalDate fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public LocalDate getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(LocalDate fechaFin) {
		this.fechaFin = fechaFin;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}
	
	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado2) {
		this.estado = estado2;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario2) {
		this.usuario = usuario2;
	}
}
