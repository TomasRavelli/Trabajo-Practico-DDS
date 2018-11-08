package modelo.entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table (name = "CLASIFICACION_TICKET")
public class ClasificacionTicket implements Serializable {
	
	private static final long serialVersionUID = 1L;

	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column (name ="ID_CLASIFICACION")
	private Integer id_ClasificacionTicket;
	
	@Column (name ="NOMBRE")
	private String nombre;
	
	
	@OneToMany (fetch = FetchType.LAZY, mappedBy = "clasificacion")
	private List<Ticket> tickets;
	
	@ManyToMany (mappedBy = "clasificaciones")
	private List<GrupoDeResolucion> grupos;
	
	@ManyToMany (mappedBy = "clasificaciones")
	private List<Ticket> ticketsClasif;
	

	
	public ClasificacionTicket() {}
	
	public ClasificacionTicket(String n) {
		this.nombre = n;
	}

	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public Integer getId_ClasificacionTicket() {
		return id_ClasificacionTicket;
	}

	public void setId_ClasificacionTicket(Integer id_ClasificacionTicket) {
		this.id_ClasificacionTicket = id_ClasificacionTicket;
	}
	
	@Override
	public String toString() {
		return nombre;
	}

}