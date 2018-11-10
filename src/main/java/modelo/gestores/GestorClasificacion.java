package modelo.gestores;

import java.time.LocalDate;
import java.util.List;

import modelo.entidades.ClasificacionTicket;
import modelo.entidades.DuracionClasificacion;
import modelo.entidades.Ticket;

public class GestorClasificacion {
	
	private List<ClasificacionTicket> clasificaciones;
	private GestorBD gestorBD;
	
	
	public GestorClasificacion(GestorBD gBD) {
		gestorBD = gBD;
		clasificaciones = gestorBD.getClasificaciones();
	}
	
	
	public List<ClasificacionTicket> getClasificaciones() {
		return clasificaciones;
	}
	
	
	public ClasificacionTicket getClasificacion(String nombre) {
		ClasificacionTicket clasificacion = new ClasificacionTicket();
		for(ClasificacionTicket c: clasificaciones) {
			if (c.getNombre().equals(nombre)) {
				clasificacion = c;
			}
		}
		return clasificacion;
	}
	
	
	public DuracionClasificacion crearDuracionClasificacion(ClasificacionTicket clasificacion, LocalDate fechaAp, Ticket t) {
		DuracionClasificacion nuevaDuracion= new DuracionClasificacion(fechaAp, t);
		nuevaDuracion.setClasificacion(clasificacion);
		return nuevaDuracion;
	}
}