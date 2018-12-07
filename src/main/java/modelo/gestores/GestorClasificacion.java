package modelo.gestores;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import modelo.entidades.ClasificacionTicket;
import modelo.entidades.DuracionClasificacion;
import modelo.entidades.GrupoDeResolucion;
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
	
	
	public List<ClasificacionTicket> getClasificaciones (String grupo){
		List<ClasificacionTicket> clasificacionesGrupo = gestorBD.getClasificaciones();
		System.out.println(clasificacionesGrupo.size());
		
		List<ClasificacionTicket> resultado = new ArrayList<>();
		for (ClasificacionTicket ct : clasificacionesGrupo) {
			for (GrupoDeResolucion g : ct.getGrupos()) {
				if(g.getNombre().equalsIgnoreCase(grupo)) {
					resultado.add(ct);
				}
			}
		}
		
		System.out.println(resultado.size());
		return resultado;
	}
}