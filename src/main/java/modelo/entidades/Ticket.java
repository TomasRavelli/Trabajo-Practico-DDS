package modelo.entidades;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
	@Column (name = "NUMERO_TICKET")
	private Integer numeroTicket;
	

	@OneToMany (cascade = {CascadeType.ALL}, mappedBy = "ticket")
	private List<DuracionEstado> duracionEstado;
	
	@OneToOne (cascade = {CascadeType.ALL})
	@JoinColumn (name = "ID_DURACION_ESTADO")
	private DuracionEstado duracionEstadoActual;
	

	@OneToMany (cascade = {CascadeType.ALL}, mappedBy = "ticket")
	private List<Intervencion> intervenciones;
	
	@ManyToOne (cascade = {CascadeType.ALL})
	@JoinColumn (name = "NUMERO_LEGAJO")
	private Empleado empleado;
		
	@OneToOne (cascade = {CascadeType.ALL})
	@JoinColumn (name= "ID_DURACION_CLASIFICACION")
	private DuracionClasificacion duracionClasificacionActual;
	
	@ManyToOne  (cascade = {CascadeType.ALL})
	@JoinColumn (name = "NUMERO_LEGAJO_Usuario")
	private Usuario usuario;
	
	@OneToMany (fetch = FetchType.LAZY, mappedBy = "ticket", cascade = {CascadeType.ALL} )
	private List<DuracionClasificacion> historialClasificacion;
	

	@Column (name = "FECHA_APERTURA")
	private LocalDate fechaApertura;
	@Column (name = "FECHA_FIN")
	private LocalDate fechaFin;
	@Column (name = "HORA_APERTURA")
	private LocalTime horaApertura;
	@Column (name = "HORA_FIN")
	private LocalTime horaFin;
	@Column (name = "DESCRIPCION_PROBLEMA")
	private String descripcion;
	
	
	public Ticket() {		
	}
	
	public Ticket(Integer nro, LocalDate fechaAp, LocalTime horaAp) {
		this.numeroTicket = nro;
		this.fechaApertura = fechaAp;
		this.horaApertura = horaAp;
	}
	
	
	public Ticket(TicketDTO t) {
		historialClasificacion = new ArrayList<>();
		duracionEstado = new ArrayList<>();
		intervenciones = new ArrayList<>();
		this.numeroTicket = t.getNumero();
		this.fechaApertura = t.getFechaApertura();
		this.fechaFin = null;
		this.horaApertura = t.getHoraApertura();
		this.horaFin = null;
		this.descripcion = t.getDescripcion();
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
	
	public DuracionClasificacion getDuracionClasificacionActual(){
		return duracionClasificacionActual;
	}
	
	public void setDuracionClasificacionActual(DuracionClasificacion dc) {
		duracionClasificacionActual = dc;
	}
	
	public List<DuracionClasificacion> getClasificaciones(){
		return historialClasificacion;
	}
	
	public void add(DuracionClasificacion durClasif) {
		historialClasificacion.add(durClasif);
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public Integer getIntervencionesAbiertas() {
		Integer i = 0;
		for (Intervencion intervencion : this.getIntervenciones()) {
			if (intervencion.getFechaFinAsignacion()==null) {
				i++;
			}
		}
		return i;
	}

	public void setFechaFinDurClasifActual(LocalDate fecha) {
		duracionClasificacionActual.setFechaFin(fecha);
	}
}