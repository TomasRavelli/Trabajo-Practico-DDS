package modelo.entidades;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table (name = "ESTADO")
public class Estado {
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column (name = "ID_ESTADO")
	private Integer id_estado;
	
	
	@Column (name = "NOMBRE")
	private String nombre;
	@Column (name = "DESCRIPCION")
	private String descripcion;
	
	@OneToMany (cascade = {CascadeType.ALL}, mappedBy = "estado")
	private List<DuracionEstado> duracionesEstado;
	
	
	public Estado() {}
	
	public Estado(String n, String desc) {
		this.nombre = n;
		this.descripcion = desc;
	}



	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Integer getId_estado() {
		return id_estado;
	}

	public void setId_estado(Integer id_estado) {
		this.id_estado = id_estado;
	}
}