package modelo.gestores;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

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
	private Integer ABIERTOENMESADEAYUDA = 1;
	private Integer ABIERTODERIVADO = 2;
	private Integer CERRADO = 3;
	private Integer SOLUCIONADOOK = 4;
	private String TERMINADA = "Terminada";
	private String ENESPERA = "En espera";
	private String TRABAJANDO = "Trabajando";
	private String ASIGNADA = "Asignada";
	private String ABIERTOENMESAAYUDA = "Abierto en Mesa de Ayuda";
	
	
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
		Usuario usuario = gestorUsuario.getUsuarioActual();
		ticket.setEmpleado(gestorEmpleado.getEmpleado(ticketDTO.getLegajo()));

		ClasificacionTicket clasificacion = gestorClasificacion.getClasificacion(ticketDTO.getClasificacion().toString());
		DuracionClasificacion nuevaDuracionClasificacion = gestorClasificacion.crearDuracionClasificacion(clasificacion,ticketDTO.getFechaApertura(),ticket);
		ticket.setDuracionClasificacionActual(nuevaDuracionClasificacion);
		ticket.add(nuevaDuracionClasificacion);
		ticket.setUsuario(usuario);
		DuracionEstado durEstado = new DuracionEstado(ticketDTO.getFechaApertura(), ticketDTO.getHoraApertura(), usuario,ticket);

		durEstado.setEstado(gestorBD.getEstado(ABIERTOENMESADEAYUDA));
		durEstado.setUsuario(usuario);
		ticket.setDuracionEstadoActual(durEstado);
		ticket.add(durEstado);
		ticket.add(gestorIntervencion.crearIntervencion(LocalDate.now(), LocalTime.now(), ticket, usuario));
		
		return gestorBD.guardarTicket(ticket);
	}
	
	
	public void cerrarTicket (Integer numeroTicket, String observaciones) {
		Ticket ticket = new Ticket();
		Usuario usuario = gestorUsuario.getUsuarioActual();
		LocalDate fecha= LocalDate.now();
		LocalTime hora= LocalTime.now();
		
		gestorIntervencion.actualizarEstadoIntervencion(numeroTicket, observaciones, usuario);
		ticket = gestorBD.getTicket(numeroTicket);
		
		DuracionEstado durEstado = ticket.getDuracionEstadoActual();
		durEstado.setFechaFin(fecha);
		durEstado.setHoraFin(hora);
		
		DuracionEstado durEstadoNueva= new DuracionEstado(fecha,hora, usuario,ticket);
		durEstadoNueva.setEstado(gestorBD.getEstado(CERRADO));
		durEstadoNueva.setUsuario(usuario);
		durEstadoNueva.setFechaFin(fecha);
		durEstadoNueva.setHoraFin(hora);
		ticket.setDuracionEstadoActual(durEstadoNueva);
		ticket.add(durEstadoNueva);
		ticket.setFechaFin(fecha);
		ticket.setHoraFin(hora);
		ticket.getDuracionClasificacionActual().setFechaFin(fecha);
		
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
		Intervencion nuevaIntervencion = gestorIntervencion.actualizarIntervenciones(derivarDTO.getNumeroTicket(), derivarDTO.getObservaciones(), grupo, usuario);
		
		if(nuevaIntervencion!=null) {
			nuevaIntervencion.setTicket(ticket);
			ticket.add(nuevaIntervencion);
		}		
		
		ticket.getDuracionEstadoActual().setFechaFin(fecha);
		ticket.getDuracionEstadoActual().setHoraFin(hora);
		DuracionEstado nuevaDuracionEstado = new DuracionEstado(fecha, hora, usuario, ticket);
		nuevaDuracionEstado.setEstado(gestorBD.getEstado(ABIERTODERIVADO));
		nuevaDuracionEstado.setUsuario(usuario);
		ticket.add(nuevaDuracionEstado);
		ticket.setDuracionEstadoActual(nuevaDuracionEstado);
		
		if(cambioClasificacion) {
			ClasificacionTicket clasificacion = gestorClasificacion.getClasificacion(derivarDTO.getClasificacion().getNombre());
			DuracionClasificacion nuevaDuracionClasificacion = gestorClasificacion.crearDuracionClasificacion(clasificacion,fecha,ticket);
			ticket.setFechaFinDurClasifActual(fecha);
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
	
	
	
	public void actualizarEstadoIntervencion (IntervencionResultadoDTO intervencion, String nuevoEstado, String observaciones, ClasificacionTicket clasificacionNueva) {
		Ticket ticket = gestorBD.getTicket(intervencion.getNumeroTicket());
		LocalDate fecha = LocalDate.now();
		LocalTime hora = LocalTime.now();
		ticket = gestorIntervencion.actualizarIntervencion(fecha, hora, intervencion, nuevoEstado, observaciones, ticket);
		
		
		if (ticket!=null) {
			if(!(intervencion.getEstadoIntervencion().equalsIgnoreCase(ASIGNADA) && nuevoEstado.equalsIgnoreCase(TRABAJANDO))) {
		
			DuracionEstado nuevaDuracionEstado = new DuracionEstado();
			nuevaDuracionEstado.setFechaInicio(fecha);
			nuevaDuracionEstado.setHoraInicio(hora);
			nuevaDuracionEstado.setUsuario(gestorUsuario.getUsuarioActual());
			
			if (nuevoEstado.equalsIgnoreCase(ENESPERA)) {
				nuevaDuracionEstado.setEstado(gestorBD.getEstado(ABIERTOENMESADEAYUDA));
			}
			
			if (nuevoEstado.equalsIgnoreCase(TERMINADA)) {
				Boolean asignacionIncorrecta;
				Boolean necesitaOtroGrupo = false;
				
				int dialogButton = JOptionPane.YES_NO_OPTION;
				int dialogResult = JOptionPane.showConfirmDialog (null, "Esta terminando la intervencion por una asignacion incorrecta?","Warning",dialogButton);
				if(dialogResult == JOptionPane.YES_OPTION){
					asignacionIncorrecta=true;
				}
				else {
					int dialogButton2 = JOptionPane.YES_NO_OPTION;
					int dialogResult2 = JOptionPane.showConfirmDialog (null, "Necesita de otro Grupo de Resolucion?","Warning",dialogButton2);
					if(dialogResult2 == JOptionPane.YES_OPTION){
						necesitaOtroGrupo = true;
					}
					else {
						necesitaOtroGrupo = false;
					}
					asignacionIncorrecta = false;
				}
				
				
				if (asignacionIncorrecta || ticket.getIntervencionesAbiertas()>1 || necesitaOtroGrupo) {
					nuevaDuracionEstado.setEstado(gestorBD.getEstado(ABIERTOENMESADEAYUDA));
				}			
				else {
					nuevaDuracionEstado.setEstado(gestorBD.getEstado(SOLUCIONADOOK));
				}
				
				ticket.getIntervenciones().get(ticket.getIntervenciones().size()-1).getEstadoIntervencionActual().setFechaFin(fecha);
				ticket.getIntervenciones().get(ticket.getIntervenciones().size()-1).getEstadoIntervencionActual().setHoraFin(hora);
			}
		
			nuevaDuracionEstado.setTicket(ticket);	
			ticket.setDuracionEstadoActual(nuevaDuracionEstado);
			ticket.add(nuevaDuracionEstado);
			ticket.getIntervenciones().get(ticket.getIntervenciones().size()-1).setTicket(ticket);
			
			}
			if (!clasificacionNueva.getNombre().equalsIgnoreCase(ticket.getDuracionClasificacionActual().getClasificacion().getNombre())) {
				ticket.getDuracionClasificacionActual().setFechaFin(fecha);//(ticket.getDuracionEstado().get(ticket.getDuracionEstado().size()-1).getFechaFin()));
				DuracionClasificacion duracionClasificacionNueva = gestorClasificacion.crearDuracionClasificacion(clasificacionNueva, fecha, ticket);
				ticket.getClasificaciones().add(duracionClasificacionNueva);
				ticket.setDuracionClasificacionActual(duracionClasificacionNueva);
			}
			
			gestorBD.actualizarTicket(ticket);
		}
	}

	
	public String getNext() {
		return gestorBD.getUltimoNumeroTicket().getNumeroTicket().toString();
	}
}