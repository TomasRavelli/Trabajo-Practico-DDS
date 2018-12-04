package modelo.gestores;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import infoDTO.DatosDTO;
import infoDTO.DerivarDTO;
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
	
	
	public Ticket getTicket(Integer numero) {
		return gestorBD.getTicket(numero);
	}
	
	public void derivarTicket (DerivarDTO derivarDTO, GrupoDeResolucion grupo, String observacionesNueva) {
		Ticket ticket = this.getTicket(derivarDTO.getNumeroTicket());
		LocalDate fecha= LocalDate.now();
		Usuario usuario = gestorUsuario.getUsuarioActual();
		Intervencion nuevaIntervencion = gestorIntervencion.actualizarIntervenciones(derivarDTO.getNumeroTicket(), derivarDTO.getObservaciones(), grupo, observacionesNueva);
		
		ticket.add(nuevaIntervencion);
		ticket.getDuracionEstadoActual().setFechaFin(fecha);
		
		DuracionEstado nuevaDuracionEstado = new DuracionEstado(fecha, usuario, ticket);
		nuevaDuracionEstado.setEstado(gestorBD.getEstado(2));
		ticket.setDuracionEstadoActual(nuevaDuracionEstado);
		ticket.add(nuevaDuracionEstado);
		gestorBD.guardarTicket(ticket);
	}
	
	
	
	public List<TicketDTO> getTickets(DatosDTO datosDTO){
		List<TicketDTO> encontrados = new ArrayList<>();
		List<Ticket> encontradosAux = gestorBD.getTickets(datosDTO);
		
		for(Ticket t: encontradosAux) {
			TicketDTO auxDTO = new TicketDTO(t.getNumero(), t.getEmpleado().getNumeroLegajo(), t.getDuracionClasificacionActual().getClasificacion(), t.getFechaApertura(), t.getIntervenciones().get(t.getIntervenciones().size()-1).getGrupoResolucion(), t.getDuracionEstadoActual().getFechaInicio(), t.getDuracionEstadoActual().getEstado());
			encontrados.add(auxDTO);
		}
		
		return encontrados;
	}
}