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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table (name = "INTERVENCION")
public class Intervencion implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column (name = "ID_INTERVENCION")
	private Integer id_Intervencion;
	
	//ID_GRUPO, NUMERO_TICKET Y ID_INTERVALO_TRABAJO SON LAS FK
	
	@ManyToOne
	@JoinColumn (name = "NUMERO_TICKET")
	private Ticket ticket;
	
	@ManyToOne
	@JoinColumn (name = "ID_GRUPO")
	private GrupoDeResolucion grupo;
	
	@OneToOne (cascade = {CascadeType.ALL})
	@JoinColumn (name = "ID_ESTADO_INTERVENCION")
	private EstadoIntervencion estadoIntervencion1;
	

	@OneToMany (cascade = {CascadeType.ALL}, mappedBy = "intervencionMuchos")
	private List<EstadoIntervencion> estadoIntervencionMuchos;
	
	
	
	@Column (name = "FECHA_ASIGNACION")
	private LocalDate fechaAsignacion;
	@Column (name = "FECHA_FIN_ASIGNACION")
	private LocalDate fechaFinAsignacion;
	@Column (name = "HORA_ASIGNACION")
	private LocalTime horaAsignacion;
	@Column (name = "HORA_FIN_ASIGNACION")
	private LocalTime horaFinAsignacion;
	
	
	
	public Intervencion() {}
	
	public Intervencion(LocalDate fechaAsig, LocalTime horaAsig, Ticket t) {
		estadoIntervencionMuchos = new ArrayList<>();
		this.fechaAsignacion = fechaAsig;
		this.horaAsignacion = horaAsig;
		this.ticket = t;
	}

	

	public LocalDate getFechaAsignacion() {
		return fechaAsignacion;
	}

	public void setFechaAsignacion(LocalDate fechaAsignacion) {
		this.fechaAsignacion = fechaAsignacion;
	}

	public LocalDate getFechaFinAsignacion() {
		return fechaFinAsignacion;
	}

	public void setFechaFinAsignacion(LocalDate fechaFinAsignacion) {
		this.fechaFinAsignacion = fechaFinAsignacion;
	}

	public LocalTime getHoraAsignacion() {
		return horaAsignacion;
	}

	public void setHoraAsignacion(LocalTime horaAsignacion) {
		this.horaAsignacion = horaAsignacion;
	}

	public LocalTime getHoraFinAsignacion() {
		return horaFinAsignacion;
	}

	public void setHoraFinAsignacion(LocalTime horaFinAsignacion) {
		this.horaFinAsignacion = horaFinAsignacion;
	}
	
	public Integer getId_Intervencion() {
		return id_Intervencion;
	}

	public void setId_Intervencion(Integer id_Intervencion) {
		this.id_Intervencion = id_Intervencion;
	}
	
	public GrupoDeResolucion getGrupoResolucion() {
		return grupo;
	}

	public void setGrupoResolucion(GrupoDeResolucion grupoRes) {
		this.grupo = grupoRes;
	}
	
	public List<EstadoIntervencion> getHistorialEstadoIntervenciones(){
		return estadoIntervencionMuchos;
	}
	
	public void setEstadoIntervencionActual(EstadoIntervencion ei){
		estadoIntervencion1=ei;
	}
	
	public EstadoIntervencion getEstadoIntervencionActual(){
		return estadoIntervencion1;
	}
	
	public void add(EstadoIntervencion ei){
		estadoIntervencionMuchos.add(ei);
	}
}