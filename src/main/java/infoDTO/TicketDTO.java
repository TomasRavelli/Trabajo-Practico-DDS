package infoDTO;

import java.time.LocalDate;
import java.time.LocalTime;
import modelo.entidades.ClasificacionTicket;
import modelo.entidades.Estado;
import modelo.entidades.GrupoDeResolucion;
import modelo.entidades.Usuario;

public class TicketDTO {

	private Integer numero;
	private Integer legajo;
	private ClasificacionTicket clasificacion;
	private String descripcion;
	private LocalDate fechaApertura;
	private LocalTime horaApertura;
	private LocalDate fechaCierre;
	private LocalTime horaCierre;
	private GrupoDeResolucion grupo;
	private LocalDate fechaUltimoCambioEstado;
	private Estado estado;
	private Usuario usuario;

	
	public TicketDTO(Integer numero, Integer legajo, ClasificacionTicket clasificacion, String descripcion, LocalDate fechaApertura, LocalTime horaApertura, LocalDate fechaCierre, LocalTime horaCierre) {
		this.numero = numero;
		this.legajo = legajo;
		this.clasificacion = clasificacion;
		this.descripcion = descripcion;
		this.fechaApertura = fechaApertura;
		this.horaApertura = horaApertura;
		this.fechaCierre = fechaCierre;
		this.horaCierre = horaCierre;
	}
	
	public TicketDTO(Integer numero, Integer legajo, ClasificacionTicket clasificacion, LocalDate fechaApertura, LocalTime horaApertura, GrupoDeResolucion grupo, LocalDate fechaUltimo, Estado estado, Usuario u) {
		this.numero = numero;
		this.legajo = legajo;
		this.clasificacion = clasificacion;
		this.fechaApertura = fechaApertura;
		this.horaApertura = horaApertura;
		this.grupo = grupo;
		this.fechaUltimoCambioEstado = fechaUltimo;
		this.estado = estado;
		this.usuario = u;
	}
	
	
	public TicketDTO() {
		
	}

	public LocalDate getFechaApertura() {
		return fechaApertura;
	}
	
	public void setFechaApertura(LocalDate fechaApertura) {
		this.fechaApertura = fechaApertura;
	}
	
	public LocalTime getHoraApertura() {
		return horaApertura;
	}
	
	public void setHoraApertura(LocalTime horaApertura) {
		this.horaApertura = horaApertura;
	}
	
	public LocalDate getFechaCierre() {
		return fechaCierre;
	}
	
	public void setFechaCierre(LocalDate fechaCierre) {
		this.fechaCierre = fechaCierre;
	}
	
	public LocalTime getHoraCierre() {
		return horaCierre;
	}
	
	public void setHoraCierre(LocalTime horaCierre) {
		this.horaCierre = horaCierre;
	}
	
	public Integer getLegajo() {
		return legajo;
	}
	
	public void setLegajo(Integer legajo) {
		this.legajo = legajo;
	}
	
	public ClasificacionTicket getClasificacion() {
		return clasificacion;
	}
	
	public void setClasificacion(ClasificacionTicket clasificacion) {
		this.clasificacion = clasificacion;
	}
	
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}


	public GrupoDeResolucion getGrupo() {
		return grupo;
	}


	public void setGrupo(GrupoDeResolucion grupo) {
		this.grupo = grupo;
	}


	public LocalDate getFechaUltimoCambioEstado() {
		return fechaUltimoCambioEstado;
	}


	public void setFechaUltimoCambioEstado(LocalDate fechaUltimoCambioEstado) {
		this.fechaUltimoCambioEstado = fechaUltimoCambioEstado;
	}


	public Estado getEstado() {
		return estado;
	}


	public void setEstado(Estado estado) {
		this.estado = estado;
	}
	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}