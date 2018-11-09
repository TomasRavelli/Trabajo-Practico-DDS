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
		return interv;
	}
}
