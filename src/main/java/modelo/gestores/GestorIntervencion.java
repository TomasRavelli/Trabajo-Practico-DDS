package modelo.gestores;

import java.time.*;
import modelo.entidades.EstadoIntervencion;
import modelo.entidades.Intervencion;
import modelo.entidades.Ticket;

public class GestorIntervencion {
	GestorBD gestorBD;
	
	public GestorIntervencion(GestorBD gBD){
		gestorBD=gBD;
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
		LocalDate fecha= LocalDate.now();
		LocalTime hora= LocalTime.now();

		estadoIntervencion.setFechaFin(fecha);
		estadoIntervencion.setHoraFin(hora);
		
		
		EstadoIntervencion nuevoEstadoIntervencion = new EstadoIntervencion("Terminada",fecha,hora,intervencion);
		nuevoEstadoIntervencion.setHoraFin(hora);
		nuevoEstadoIntervencion.setFechaFin(fecha);
		nuevoEstadoIntervencion.setObservaciones(observaciones);
		
		
		intervencion.setEstadoIntervencionActual(nuevoEstadoIntervencion);
		intervencion.add(nuevoEstadoIntervencion);
		intervencion.setFechaFinAsignacion(fecha);
		intervencion.setHoraFinAsignacion(hora);
		
	}
}
