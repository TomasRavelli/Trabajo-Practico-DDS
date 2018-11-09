package modelo.gestores;


import java.time.LocalDate;
import java.time.LocalTime;

import infoDTO.TicketDTO;
import modelo.entidades.DuracionEstado;
import modelo.entidades.Ticket;

public class GestorTicket {
	
	private GestorBD gestorBD;
	private GestorEmpleado gestorEmpleado;
	private GestorUsuario gestorUsuario;
	private GestorIntervencion gestorIntervencion;
	
	public GestorTicket(GestorBD gBD, GestorEmpleado gestorE, GestorIntervencion gestorI, GestorUsuario u) {
		gestorBD = gBD;
		gestorEmpleado = gestorE;
		gestorIntervencion = gestorI;
		gestorUsuario = u;
	}
	
	public Ticket crearTicket() {
		Ticket ticket = new Ticket();
		Ticket nuevoTicket = gestorBD.guardarTicket(ticket);
		return nuevoTicket;
	}
	
	public Ticket crearTicket(TicketDTO ticketDTO) {
		Ticket ticket = new Ticket(ticketDTO);
		ticket.setEmpleado(gestorEmpleado.getEmpleado(ticketDTO.getLegajo()));
		//ticket.add(new DuracionClasificacion(ticketDTO.getFechaApertura())); Crear Clase.
		ticket.setUsuario(gestorUsuario.getUsuarioActual());
		DuracionEstado durEstado = new DuracionEstado(ticketDTO.getFechaApertura(), gestorUsuario.getUsuarioActual());
		durEstado.setEstado(gestorBD.getEstado(1));
		durEstado.setUsuario(gestorUsuario.getUsuarioActual());
		ticket.setDuracionEstadoActual(durEstado);
		ticket.add(durEstado);
		ticket.add(gestorIntervencion.crearIntervencion(LocalDate.now(),LocalTime.now()));
		return gestorBD.actualizarTicket(ticket);
	}

}
