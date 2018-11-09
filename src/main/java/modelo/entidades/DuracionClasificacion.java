package modelo.entidades;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

import javax.persistence.CascadeType;
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
@Table (name = "DURACION_CLASIFICACION")
public class DuracionClasificacion implements Serializable {

	private static final long serialVersionUID = 1L;
		
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column (name = "ID_DURACION_CLASIFICACION")
	private Integer idDuracionClasificacion;
	
	@Column (name = "FECHA_INICIO")
	private LocalDate fechaInicio;
	
	@Column (name = "FECHA_FIN")
	private Date fechaFin;
		
	@OneToOne (cascade = {CascadeType.ALL})
	@JoinColumn (name = "ID_DURACION_CLASIFICACION")
	private Ticket t;
	
	@ManyToOne  (cascade = {CascadeType.ALL})
	@JoinColumn (name = "NUMERO_TICKET")
	private Ticket ticket;
	
	@OneToOne (cascade = {CascadeType.ALL})
	@JoinColumn (name = "ID_DURACION_CLASIFICACION")
	private ClasificacionTicket cActual;
	
	@ManyToOne  (cascade = {CascadeType.ALL})
	@JoinColumn (name = "ID_CLASIFICACION")
	private ClasificacionTicket clasificacion;

	public DuracionClasificacion() {
		
	}
	
	public DuracionClasificacion(LocalDate localDate) {
		fechaInicio = localDate;
	}

	
	public LocalDate getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(LocalDate fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Date getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	public void setClasificacion(ClasificacionTicket clasificacion2) {
		// TODO Auto-generated method stub
		cActual = clasificacion2; 
	}

	public void setTicket(Ticket ticket2) {
		// TODO Auto-generated method stub
		t = ticket2;
		
	}

}
