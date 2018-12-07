package modelo.entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table (name = "USUARIO")
public class Usuario implements Serializable {
	
	private static final long serialVersionUID = 1L;
	//TAMBIEN ES LA FK
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column (name = "NUMERO_LEGAJO")
	private Integer numeroLegajo;
	
	@OneToOne (cascade = {CascadeType.ALL})
	@JoinColumn (name = "NUMERO_LEGAJO")
	private Empleado empleado;
	
	@OneToMany (cascade = {CascadeType.ALL}, mappedBy = "usuario")
	private List<DuracionEstado> duracionEstado;
	
	@OneToMany (cascade = {CascadeType.ALL}, mappedBy = "usuario")
	private List<EstadoIntervencion> estadoIntervencion;
	
	
	
	@Column (name = "PASSWORD")
	private Integer password;
	@Column (name = "NOMBRE")
	private String nombre;
	
	
	
	public Usuario() {}
	
	public Usuario(Integer passw, String n, Integer legajo) {
		this.password = passw;
		this.nombre = n;
		this.numeroLegajo = legajo;
	}



	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Integer getNumeroLegajo() {
		return numeroLegajo;
	}

	public void setNumeroLegajo(Integer numeroLegajo) {
		this.numeroLegajo = numeroLegajo;
	}

	public Integer getPassword() {
		return password;
	}

	public void setPassword(Integer password) {
		this.password = password;
	}

	public Empleado getEmpleado() {
		return empleado;
	}

	public void setEmpleado(Empleado empleado) {
		this.empleado = empleado;
	}

	public List<DuracionEstado> getDuracionEstado() {
		return duracionEstado;
	}

	public void setDuracionEstado(List<DuracionEstado> duracionEstado) {
		this.duracionEstado = duracionEstado;
	}

	public List<EstadoIntervencion> getEstadoIntervencion() {
		return estadoIntervencion;
	}

	public void setEstadoIntervencion(List<EstadoIntervencion> estadoIntervencion) {
		this.estadoIntervencion = estadoIntervencion;
	}
	
	

}