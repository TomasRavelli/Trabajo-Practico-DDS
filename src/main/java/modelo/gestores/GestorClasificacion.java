package modelo.gestores;

import java.util.List;

import modelo.entidades.ClasificacionTicket;

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
	
	
}
