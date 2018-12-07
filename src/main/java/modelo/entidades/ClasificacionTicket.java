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
	
	@ManyToMany (mappedBy = "clasificaciones")
	private List<GrupoDeResolucion> grupos;
	
	@OneToMany (fetch = FetchType.LAZY, mappedBy = "clasificacion")
	private List<DuracionClasificacion> clasificaciones;
	
	
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
	
	public List<GrupoDeResolucion> getGrupos() {
		return grupos;
	}


	public void setGrupos(List<GrupoDeResolucion> grupos) {
		this.grupos = grupos;
	}


	@Override
	public String toString() {
		return nombre;
	}
}