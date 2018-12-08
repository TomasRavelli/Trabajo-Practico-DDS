package modelo.entidades;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
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
	
	@ManyToOne  (cascade = {CascadeType.ALL})
	@JoinColumn (name = "NUMERO_LEGAJO")
	private Usuario usuario;
	
	@ManyToOne  (cascade = {CascadeType.ALL})
	@JoinColumn (name = "ID_ESTADO")
	private Estado estado;
	
	@ManyToOne (cascade = {CascadeType.ALL})
	@JoinColumn (name = "NUMERO_TICKET")
	private Ticket ticket;
	
	@Column (name = "FECHA_INICIO")
	private LocalDate fechaInicio;
	@Column (name = "HORA_INICIO")
	private LocalTime horaInicio;
	@Column (name = "FECHA_FIN")
	private LocalDate fechaFin;
	@Column (name = "HORA_FIN")
	private LocalTime horaFin;
	
	
	public DuracionEstado() {}
	
	public DuracionEstado(LocalDate fInicio,LocalTime horaIn, Usuario u, Ticket t) {
		this.fechaInicio = fInicio;
		this.usuario = u;
		this.ticket=t;
		this.horaInicio = horaIn;
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
	
	
	public Integer getIdDuracionEstado() {
		return idDuracionEstado;
	}

	public void setIdDuracionEstado(Integer idDuracionEstado) {
		this.idDuracionEstado = idDuracionEstado;
	}

	public Ticket getTicket() {
		return ticket;
	}

	public void setTicket(Ticket ticket) {
		this.ticket = ticket;
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
