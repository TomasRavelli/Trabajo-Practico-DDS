package modelo.entidades;

import java.io.Serializable;

import java.time.LocalDate;
import java.time.LocalTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table (name = "ESTADO_INTERVENCION")
public class EstadoIntervencion implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column (name = "ID_ESTADO_INTERVENCION")
	private Integer id_EstadoIntervencion;
	
	//ID_INTERVENCION ES LA FK
	//INSERTAR LEGAJO SIEMPRE?
	
	
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
	@Column (name = "OBSERVACIONES")
	private String observaciones;
	

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public EstadoIntervencion() {
		
	}
	
	public EstadoIntervencion(String estado, LocalDate fInicio, LocalTime hInicio, Intervencion i) {
		this.estado = estado;
		this.fechaInicio = fInicio;
		this.horaInicio = hInicio;
		this.intervencionMuchos=i;
	}
	
	public EstadoIntervencion(LocalDate fInicio, LocalTime hInicio) {
		this.fechaInicio = fInicio;
		this.horaInicio = hInicio;
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
	
	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	public Intervencion getIntervencion() {
		return intervencionMuchos;
	}

	public void setIntervencion(Intervencion interv) {
		this.intervencionMuchos = interv;
	}
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario u) {
		this.usuario = u;
	
	}
}