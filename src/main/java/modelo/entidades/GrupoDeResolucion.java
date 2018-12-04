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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table (name = "GRUPO_DE_RESOLUCION")
public class GrupoDeResolucion implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column (name = "ID_GRUPO")
	private Integer id_Grupo;
	
	
	@OneToMany (cascade = {CascadeType.ALL}, mappedBy = "grupo")
	private List<Empleado> empleados;
	
	@OneToMany (cascade = {CascadeType.ALL}, mappedBy = "grupo")
	private List<Intervencion> intervenciones;
	
	@ManyToMany
	@JoinTable (name = "MODIFICADO_POR", joinColumns = { @JoinColumn (name = "ID_GRUPO")}, inverseJoinColumns = {@JoinColumn (name = "ID_CLASIFICACION")})
	private List<ClasificacionTicket> clasificaciones;
	
	
	@Column (name = "NOMBRE")
	private String nombre;
	
	
	public GrupoDeResolucion() {}
	
	public GrupoDeResolucion(String n) {
		this.nombre = n;
	}

	
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public Integer getId_Grupo() {
		return id_Grupo;
	}

	public void setId_Grupo(Integer id_Grupo) {
		this.id_Grupo = id_Grupo;
	}
	
	@Override
	public String toString() {
		return this.getNombre();
	}	
}