package modelo.gestores;

import java.time.*;
import modelo.entidades.EstadoIntervencion;
import modelo.entidades.GrupoDeResolucion;
import modelo.entidades.Intervencion;
import modelo.entidades.Ticket;

public class GestorIntervencion {
	GestorBD gestorBD;
	GestorUsuario gestorUsuario;
	
	public GestorIntervencion(GestorBD gBD, GestorUsuario gUsu){
		gestorBD=gBD;
		gestorUsuario = gUsu;
	}

	
	public Intervencion crearIntervencion(LocalDate fechaActual, LocalTime horaActual, Ticket ticket) {
		Intervencion interv = new Intervencion(fechaActual, horaActual,ticket);
		//CREAR CONSTANTE GLOBAL PARA EL GET(1)
		interv.setGrupoResolucion(gestorBD.getGrupoResolucion(1));
		EstadoIntervencion estadoInterv = new EstadoIntervencion("Trabajando",fechaActual, horaActual,interv);
		interv.add(estadoInterv);
		interv.setEstadoIntervencionActual(estadoInterv);
		return interv;
	}
	
	
	public void actualizarEstadoIntervencion (Integer numeroTicket, String observaciones) {
		//NO INICIALIZAR ASI, YA DARLE EL VALOR QUE VA A TENER
		Intervencion intervencion = new Intervencion();
		EstadoIntervencion estadoIntervencion = new EstadoIntervencion();
		intervencion = gestorBD.getIntervencionMDA(numeroTicket);
		estadoIntervencion = intervencion.getEstadoIntervencionActual();
		LocalDate fecha = LocalDate.now();
		LocalTime hora = LocalTime.now();
		
		estadoIntervencion.setFechaFin(fecha);
		estadoIntervencion.setHoraFin(hora);
		
		
		EstadoIntervencion nuevoEstadoIntervencion = new EstadoIntervencion("Terminada",fecha,hora,intervencion);
		nuevoEstadoIntervencion.setHoraFin(hora);
		nuevoEstadoIntervencion.setFechaFin(fecha);
		
		
		intervencion.setEstadoIntervencionActual(nuevoEstadoIntervencion);
		intervencion.add(nuevoEstadoIntervencion);
		intervencion.setFechaFinAsignacion(fecha);
		intervencion.setHoraFinAsignacion(hora);
		
	}
	
	public Intervencion actualizarIntervenciones(Integer numeroTicket, String observaciones, GrupoDeResolucion grupo, String observacionesNueva) {
		LocalDate fecha = LocalDate.now();
		LocalTime hora = LocalTime.now();
		Intervencion intervencion = gestorBD.getIntervencionMDA(numeroTicket);
		
		intervencion.getEstadoIntervencionActual().setObservaciones(observaciones);
		intervencion.getEstadoIntervencionActual().setUsuario(gestorUsuario.getUsuarioActual());
		intervencion.getEstadoIntervencionActual().setFechaFin(fecha);
		intervencion.getEstadoIntervencionActual().setHoraFin(hora);
		
		EstadoIntervencion nuevoEstadoIntervencion = new EstadoIntervencion("En espera", fecha, hora, intervencion);
		nuevoEstadoIntervencion.setObservaciones(observacionesNueva);
		nuevoEstadoIntervencion.setUsuario(gestorUsuario.getUsuarioActual());
		intervencion.setEstadoIntervencionActual(nuevoEstadoIntervencion);
		intervencion.add(nuevoEstadoIntervencion);
		gestorBD.guardarIntervencion(intervencion);
		
		
		Intervencion intervencionGrupo = new Intervencion(fecha, hora, gestorBD.getTicket(numeroTicket));
		intervencionGrupo.setGrupoResolucion(grupo);
		EstadoIntervencion estadoIntervencionGrupo = new EstadoIntervencion("Asignada", fecha, hora, intervencionGrupo);
		estadoIntervencionGrupo.setUsuario(gestorUsuario.getUsuarioActual());
		intervencionGrupo.setEstadoIntervencionActual(estadoIntervencionGrupo);
		intervencionGrupo.add(estadoIntervencionGrupo);
		gestorBD.guardarIntervencion(intervencionGrupo);
		
		return intervencionGrupo;
	}
}
