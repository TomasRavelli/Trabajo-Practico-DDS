package modelo.gestores;

import java.time.LocalDate;
import java.time.LocalTime;

import infoDTO.TicketDTO;
import modelo.entidades.ClasificacionTicket;
import modelo.entidades.DuracionClasificacion;
import modelo.entidades.DuracionEstado;
import modelo.entidades.Ticket;

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
		//HACER ESTO EN EL GESTOR CLASIFICACION Y VER DIAGRAMA
		ClasificacionTicket clasificacion = gestorClasificacion.getClasificacion(ticketDTO.getClasificacion().toString());
		DuracionClasificacion nuevaDuracionClasificacion = gestorClasificacion.crearDuracionClasificacion(clasificacion,ticketDTO.getFechaApertura(),ticket);//Adentro ya se inserta la clasificacion
		ticket.setDuracionClasificacionActual(nuevaDuracionClasificacion);
		ticket.add(nuevaDuracionClasificacion);
		ticket.setUsuario(gestorUsuario.getUsuarioActual());
		ticket.setDescripcion(ticketDTO.getDescripcion());
		DuracionEstado durEstado = new DuracionEstado(ticketDTO.getFechaApertura(), gestorUsuario.getUsuarioActual(),ticket);
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
		DuracionEstado durEstadoNueva= new DuracionEstado(fecha,gestorUsuario.getUsuarioActual(),ticket);
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
}