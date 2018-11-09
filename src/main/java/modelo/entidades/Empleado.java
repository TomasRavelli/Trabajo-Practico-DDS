package modelo.entidades;

import java.io.Serializable;
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
@Table (name = "EMPLEADO")
public class Empleado implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column (name = "NUMERO_LEGAJO")
	private Integer numeroLegajo;
	
	@OneToOne (fetch = FetchType.LAZY, mappedBy="empleado")
	private Usuario usuario;
	
	//ID_GRUPO ES LA FK
	
	@ManyToOne
	@JoinColumn (name = "ID_GRUPO")
	private GrupoDeResolucion grupo;
	
	@OneToMany (cascade = {CascadeType.ALL}, mappedBy = "empleado")
	private List<Ticket> tickets;
	
	
	
	@Column (name = "NOMBRE")
	private String nombre;
	@Column (name = "TELEFONO_INTERNO")
	private String telefonoInterno;
	@Column (name = "TELEFONO_DIRECTO")
	private String telefonoDirecto;
	@Column (name = "DESCRIPCION_CARGO")
	private String descripcionCargo;
	
	
	public Empleado() {}
	
	public Empleado(String n, Integer legajo) {
		this.nombre = n;
		this.numeroLegajo = legajo;
	}
	
	
	
	public String getTelefonoInterno() {
		return telefonoInterno;
	}

	public void setTelefonoInterno(String telefonoInterno) {
		this.telefonoInterno = telefonoInterno;
	}

	public String getTelefonoDirecto() {
		return telefonoDirecto;
	}

	public void setTelefonoDirecto(String telefonoDirecto) {
		this.telefonoDirecto = telefonoDirecto;
	}

	public String getDescripcionCargo() {
		return descripcionCargo;
	}

	public void setDescripcionCargo(String descripcionCargo) {
		this.descripcionCargo = descripcionCargo;
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

}
