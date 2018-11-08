package modelo.entidades;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table (name = "INTERVALO_TRABAJO")
public class EstadoIntervencion implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column (name = "ID_ESTADO_INTERVENCION")
	private Integer id_EstadoIntervencion;
	
	//ID_INTERVENCION ES LA FK
	
	@OneToOne (fetch = FetchType.LAZY, mappedBy = "estadoIntervencion1")
	private Intervencion intervencion1;
	
	@ManyToOne
	@JoinColumn (name = "ID_INTERVENCION")
	private Intervencion intervencionMuchos;
	
	@ManyToOne
	@JoinColumn (name = "NUMERO_LEGAJO")
	private Usuario usuario;
	
	@Column (name = "ESTADO")
	private String estado;	
	@Column (name = "FECHA_INICIO")
	private LocalDate fechaInicio;
	@Column (name = "FECHA_FIN")
	private LocalDate fechaFin;
	@Column (name = "HORA_INICIO")
	private LocalTime horaInicio;
	@Column (name = "HORA_FIN")
	private LocalTime horaFin;
	
	

	public EstadoIntervencion() {}
	
	public EstadoIntervencion(String estado2, LocalDate fInicio, LocalTime hInicio) {
		this.fechaInicio = fInicio;
		this.horaInicio = hInicio;
		this.estado = estado2;
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

	public LocalTime getHoraInicio() {
		return horaInicio;
	}

	public void setHoraInicio(LocalTime horaInicio) {
		this.horaInicio = horaInicio;
	}

	public LocalTime getHoraFin() {
		return horaFin;
	}

	public void setHoraFin(LocalTime horaFin) {
		this.horaFin = horaFin;
	}
	
	public Integer getId_EstadoIntervencion() {
		return id_EstadoIntervencion;
	}

	public void setId_EstadoIntervencion(Integer id_EstadoIntervencion) {
		this.id_EstadoIntervencion = id_EstadoIntervencion;
	}

}