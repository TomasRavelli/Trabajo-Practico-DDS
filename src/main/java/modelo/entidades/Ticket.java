package modelo.entidades;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import infoDTO.TicketDTO;

@Entity
@Table (name = "TICKET")
public class Ticket implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column (name = "NUMERO_TICKET")
	private Integer numeroTicket;
	
	//NUMERO_LEGAJO Y ID_CLASIFICACION SON FK
	
	@OneToMany (fetch = FetchType.LAZY, mappedBy = "ticket")
	private List<DuracionEstado> duracionEstado;
	
	@OneToOne (fetch = FetchType.LAZY)
	@JoinColumn (name = "ID_DURACION_ESTADO")
	private DuracionEstado duracionEstadoActual;
	
	@OneToMany (fetch = FetchType.LAZY, mappedBy = "ticket")
	private List<Intervencion> intervenciones;
	
	@ManyToOne
	@JoinColumn (name = "NUMERO_LEGAJO")
	private Empleado empleado;
	
	
	@ManyToOne
	@JoinColumn (name = "ID_CLASIFICACION")
	private ClasificacionTicket clasificacion;
	
	@ManyToOne
	@JoinColumn (name = "NUMERO_LEGAJO_Usuario")
	private Usuario usuario;
	
	@ManyToMany
	@JoinTable (name = "HISTORIAL_CT", joinColumns = {@JoinColumn (name = "NUMERO_TICKET")}, inverseJoinColumns = {@JoinColumn (name = "ID_CLASIFICACION")})
	private List<ClasificacionTicket> clasificaciones;
	

	@Column (name = "FECHA_APERTURA")
	private LocalDate fechaApertura;
	@Column (name = "FECHA_FIN")
	private LocalDate fechaFin;
	@Column (name = "HORA_APERTURA")
	private LocalTime horaApertura;
	@Column (name = "HORA_FIN")
	private LocalTime horaFin;
	
	
	
	public Ticket() {
		
	}
	
	public Ticket(Integer nro, LocalDate fechaAp, LocalTime horaAp) {
		this.numeroTicket = nro;
		this.fechaApertura = fechaAp;
		this.horaApertura = horaAp;
	}
	
	public Ticket(TicketDTO t) {
		this.numeroTicket = t.getNumero();
		this.clasificacion = t.getClasificacion();
		this.clasificaciones.add(t.getClasificacion());
		this.fechaApertura = t.getFechaApertura();
		this.fechaFin = null;
		this.horaApertura = t.getHoraApertura();
		this.horaFin = null;
	}

	public Integer getNumero() {
		return numeroTicket;
	}

	public void setNumero(Integer numero) {
		this.numeroTicket = numero;
	}

	public LocalDate getFechaApertura() {
		return fechaApertura;
	}

	public void setFechaApertura(LocalDate fechaApertura) {
		this.fechaApertura = fechaApertura;
	}

	public LocalDate getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(LocalDate fechaFin) {
		this.fechaFin = fechaFin;
	}

	public LocalTime getHoraApertura() {
		return horaApertura;
	}

	public void setHoraApertura(LocalTime horaApertura) {
		this.horaApertura = horaApertura;
	}
	
	
	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public LocalTime getHoraFin() {
		return horaFin;
	}

	public void setHoraFin(LocalTime horaFin) {
		this.horaFin = horaFin;
	}
	
	public Empleado getEmpleado() {
		return this.empleado;
	}

	public void setEmpleado(Empleado e) {
		this.empleado = e;
	}

	public List<DuracionEstado> getDuracionEstado() {
		return duracionEstado;
	}
	public void add(DuracionEstado duracionEstado2) {
		duracionEstado.add(duracionEstado2);
	}

	public DuracionEstado getDuracionEstadoActual(){
		return duracionEstadoActual;
	}
	
	public void setDuracionEstadoActual(DuracionEstado dea) {
		duracionEstadoActual = dea;
	}
	
	public List<Intervencion> getIntervenciones(){
		return intervenciones;
	}
	
	public void add(Intervencion interv) {
		intervenciones.add(interv);
	}
	

}