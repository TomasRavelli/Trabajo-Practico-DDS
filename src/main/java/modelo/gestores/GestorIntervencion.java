package modelo.gestores;

import java.time.*;

import modelo.entidades.EstadoIntervencion;
import modelo.entidades.Intervencion;

public class GestorIntervencion {
	GestorBD gestorBD;
	public GestorIntervencion(GestorBD gBD){
		gestorBD=gBD;
	}

	public Intervencion crearIntervencion(LocalDate fechaActual, LocalTime horaActual) {
		Intervencion interv = new Intervencion(fechaActual, horaActual);
		interv.setGrupoResolucion(gestorBD.getGrupoResolucion(1));
		EstadoIntervencion estadoInterv = new EstadoIntervencion("Trabajando",fechaActual, horaActual);
		interv.add(estadoInterv);
		interv.setEstadoIntervencionActual(estadoInterv);
		gestorBD.guardarIntervencion(interv);
		return interv;
	}
	
	
	public void actualizarEstadoIntervencion (Integer numeroTicket, String observaciones) {
		
		Intervencion intervencion = new Intervencion();
		EstadoIntervencion estadoIntervencion = new EstadoIntervencion();
		intervencion = gestorBD.getIntervencionMDA(numeroTicket);
		estadoIntervencion = intervencion.getEstadoIntervencionActual();
		
		//CREAR UNA ACA ARRIBA ASI A TODAS LES MANDO EL MISMO PARAMETRO PARA QUE SEAN IGUALES
		//VER HORA ACTUAL Y SETEARLA
		estadoIntervencion.setFechaFin(null);
		estadoIntervencion.setHoraFin(null);
		
		//MANDAR FECHA Y HORA ACTUAL
		EstadoIntervencion nuevoEstadoIntervencion = new EstadoIntervencion();
		nuevoEstadoIntervencion.setEstado("TERMINADA");
		nuevoEstadoIntervencion.setObservaciones(observaciones);
		
		
		intervencion.setEstadoIntervencionActual(nuevoEstadoIntervencion);
		intervencion.add(nuevoEstadoIntervencion);
		intervencion.setFechaFinAsignacion(null);
		intervencion.setHoraFinAsignacion(null);
		
	}
}
