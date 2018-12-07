package modelo.gestores;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import infoDTO.DatosDTO;
import infoDTO.DerivarDTO;
import infoDTO.IntervencionResultadoDTO;
import infoDTO.TicketDTO;
import modelo.entidades.ClasificacionTicket;
import modelo.entidades.DuracionClasificacion;
import modelo.entidades.DuracionEstado;
import modelo.entidades.GrupoDeResolucion;
import modelo.entidades.Intervencion;
import modelo.entidades.Ticket;
import modelo.entidades.Usuario;

public class GestorTicket {
	
	private GestorBD gestorBD;
	private GestorEmpleado gestorEmpleado;
	private GestorUsuario gestorUsuario;
	private GestorIntervencion gestorIntervencion;
	private GestorClasificacion gestorClasificacion;
	
	
	public GestorTicket(GestorBD gBD, GestorEmpleado gestorE, GestorIntervencion gestorI, GestorUsuario u, GestorClasificacion gC) {
		gestorBD = gBD;
		gestorEmpleado = gestorE;
		gestorIntervencion = gestorI;
		gestorUsuario = u;
		gestorClasificacion = gC;
	}
	
	
	public Ticket crearTicket() {
		Ticket ticket = new Ticket();
		Ticket nuevoTicket = gestorBD.guardarTicket(ticket);
		return nuevoTicket;
	}
	
	
	public Ticket crearTicket(TicketDTO ticketDTO) {
		Ticket ticket = new Ticket(ticketDTO);
		ticket.setEmpleado(gestorEmpleado.getEmpleado(ticketDTO.getLegajo()));

		ClasificacionTicket clasificacion = gestorClasificacion.getClasificacion(ticketDTO.getClasificacion().toString());
		DuracionClasificacion nuevaDuracionClasificacion = gestorClasificacion.crearDuracionClasificacion(clasificacion,ticketDTO.getFechaApertura(),ticket);//Adentro ya se inserta la clasificacion
		ticket.setDuracionClasificacionActual(nuevaDuracionClasificacion);
		ticket.add(nuevaDuracionClasificacion);
		ticket.setUsuario(gestorUsuario.getUsuarioActual());
		DuracionEstado durEstado = new DuracionEstado(ticketDTO.getFechaApertura(), ticketDTO.getHoraApertura(), gestorUsuario.getUsuarioActual(),ticket);

		//TODO crear constante global
		durEstado.setEstado(gestorBD.getEstado(1));
		durEstado.setUsuario(gestorUsuario.getUsuarioActual());
		ticket.setDuracionEstadoActual(durEstado);
		ticket.add(durEstado);
		ticket.add(gestorIntervencion.crearIntervencion(LocalDate.now(),LocalTime.now(),ticket));
		return gestorBD.actualizarTicket(ticket);
	}
	
	
	public void cerrarTicket (Integer numeroTicket, String observaciones) {
		Ticket ticket = new Ticket();
		LocalDate fecha= LocalDate.now();
		LocalTime hora= LocalTime.now();
		
		gestorIntervencion.actualizarEstadoIntervencion(numeroTicket, observaciones);
		ticket = gestorBD.getTicket(numeroTicket);
		
		DuracionEstado durEstado= new DuracionEstado();
		durEstado= ticket.getDuracionEstadoActual();
		durEstado.setFechaFin(fecha);
		durEstado.setHoraFin(hora);
		
		DuracionEstado durEstadoNueva= new DuracionEstado(fecha,hora, gestorUsuario.getUsuarioActual(),ticket);
		durEstadoNueva.setEstado(gestorBD.getEstado(3));
		durEstadoNueva.setUsuario(gestorUsuario.getUsuarioActual());
		durEstadoNueva.setFechaFin(fecha);
		ticket.setDuracionEstadoActual(durEstadoNueva);
		ticket.add(durEstadoNueva);
		ticket.setFechaFin(fecha);
		ticket.setHoraFin(hora);
		gestorBD.actualizarTicket(ticket);
	}
	
	
	public void eliminarTicket (String numeroTicket) {
		gestorBD.eliminarTicket(numeroTicket);
	}
	
	
	public Ticket getTicket(Integer numero) {
		return gestorBD.getTicket(numero);
	}
	
	
	public void derivarTicket (DerivarDTO derivarDTO, boolean cambioClasificacion, GrupoDeResolucion grupo) {
		Ticket ticket = this.getTicket(derivarDTO.getNumeroTicket());
		LocalDate fecha= LocalDate.now();
		LocalTime hora= LocalTime.now();
		Usuario usuario = gestorUsuario.getUsuarioActual();
		Intervencion nuevaIntervencion = gestorIntervencion.actualizarIntervenciones(derivarDTO.getNumeroTicket(), derivarDTO.getObservaciones(), grupo);
		
		if(nuevaIntervencion!=null) {
			nuevaIntervencion.setTicket(ticket);
			ticket.add(nuevaIntervencion);
			ticket.getDuracionEstadoActual().setFechaFin(fecha);
			ticket.getDuracionEstadoActual().setHoraFin(hora);
		}		
		
		DuracionEstado nuevaDuracionEstado = new DuracionEstado(fecha, hora, usuario, ticket);
		nuevaDuracionEstado.setEstado(gestorBD.getEstado(2));
		nuevaDuracionEstado.setUsuario(usuario);
		ticket.add(nuevaDuracionEstado);
		ticket.setDuracionEstadoActual(nuevaDuracionEstado);
		if(cambioClasificacion) {
			ClasificacionTicket clasificacion = gestorClasificacion.getClasificacion(derivarDTO.getClasificacion().getNombre());
			DuracionClasificacion nuevaDuracionClasificacion = gestorClasificacion.crearDuracionClasificacion(clasificacion,fecha,ticket);
			ticket.setDuracionClasificacionActual(nuevaDuracionClasificacion);
			ticket.add(nuevaDuracionClasificacion);	
		}
		gestorBD.actualizarTicket(ticket);
	}
	
	
	
	public List<TicketDTO> getTickets(DatosDTO datosDTO){
		List<TicketDTO> encontrados = new ArrayList<>();
		List<Ticket> encontradosAux = gestorBD.getTickets(datosDTO);
		for(Ticket t: encontradosAux) {
			TicketDTO auxDTO = new TicketDTO(t.getNumero(), t.getEmpleado().getNumeroLegajo(), t.getDuracionClasificacionActual().getClasificacion(), t.getFechaApertura(),t.getHoraApertura(), t.getIntervenciones().get(t.getIntervenciones().size()-1).getGrupoResolucion(), t.getDuracionEstadoActual().getFechaInicio(), t.getDuracionEstadoActual().getEstado(), t.getUsuario());
			encontrados.add(auxDTO);
		}
		return encontrados;
	}
	
	
	
	public void actualizarEstadoIntervencion (IntervencionResultadoDTO intervencion, String nuevoEstado, String observaciones, Boolean asignacionIncorrecta, ClasificacionTicket clasificacionNueva) {
		//TODO ticket no recibe los cambios porque se pasa por referencia
		Ticket ticket = this.getTicket(intervencion.getNumeroTicket());
		ticket = gestorIntervencion.actualizarIntervencion(intervencion, nuevoEstado, observaciones, ticket);
		
		if (ticket!=null) {
			DuracionEstado nuevaDuracionEstado = new DuracionEstado();
			nuevaDuracionEstado.setFechaInicio(ticket.getDuracionEstado().get(ticket.getDuracionEstado().size()-1).getFechaFin());
			nuevaDuracionEstado.setHoraInicio(ticket.getDuracionEstado().get(ticket.getDuracionEstado().size()-1).getHoraFin());
			nuevaDuracionEstado.setUsuario(gestorUsuario.getUsuarioActual());
			
			if (nuevoEstado.equalsIgnoreCase("En espera")) {
				nuevaDuracionEstado.getEstado().setNombre("Abierto en Mesa de Ayuda");
			}
			
			if (nuevoEstado.equalsIgnoreCase("Terminada")) {
				if (asignacionIncorrecta || ticket.getIntervencionesAbiertas()>1) {
					nuevaDuracionEstado.getEstado().setNombre("Abierto en Mesa de Ayuda");
				}			
				else {
					nuevaDuracionEstado.getEstado().setNombre("Solucionado a la espera OK");
				}
			}
				
			ticket.setDuracionEstadoActual(nuevaDuracionEstado);
			ticket.add(nuevaDuracionEstado);
			
			if (!clasificacionNueva.getNombre().equalsIgnoreCase(ticket.getDuracionClasificacionActual().getClasificacion().getNombre())) {
				ticket.getDuracionClasificacionActual().setFechaFin(ticket.getDuracionEstado().get(ticket.getDuracionEstado().size()-1).getFechaFin());
				DuracionClasificacion duracionClasificacionNueva = new DuracionClasificacion(ticket.getDuracionEstado().get(ticket.getDuracionEstado().size()-1).getFechaFin(), ticket);
				ticket.getClasificaciones().add(duracionClasificacionNueva);
				ticket.setDuracionClasificacionActual(duracionClasificacionNueva);
			}
			
			gestorBD.actualizarTicket(ticket);
		}
	}	
}