package modelo.gestores;

import java.time.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import infoDTO.IntervencionBusquedaDTO;
import infoDTO.IntervencionResultadoDTO;
import infoDTO.TicketDTO;
import modelo.entidades.EstadoIntervencion;
import modelo.entidades.GrupoDeResolucion;
import modelo.entidades.Intervencion;
import modelo.entidades.Ticket;
import modelo.entidades.Usuario;


public class GestorIntervencion {
	GestorBD gestorBD;
	GestorUsuario gestorUsuario;
	GestorEmpleado gestorEmpleado;
	private Integer MESADEAYUDA = 1;
	private String TERMINADA = "Terminada";
	private String ENESPERA = "En espera";
	private String TRABAJANDO = "Trabajando";
	private String ASIGNADA = "Asignada";
	
	
	public GestorIntervencion(GestorBD gBD, GestorUsuario gUsu, GestorEmpleado gE){
		gestorBD = gBD;
		gestorUsuario = gUsu;
		this.gestorEmpleado = gE;
	}

	
	public Intervencion crearIntervencion(LocalDate fechaActual, LocalTime horaActual, Ticket ticket, Usuario u) {
		Intervencion interv = new Intervencion(fechaActual, horaActual,ticket);
		interv.setGrupoResolucion(gestorBD.getGrupoResolucion(MESADEAYUDA));
		EstadoIntervencion estadoInterv = new EstadoIntervencion(TRABAJANDO,fechaActual, horaActual,interv);
		estadoInterv.setUsuario(u);
		interv.add(estadoInterv);
		interv.setEstadoIntervencionActual(estadoInterv);
		return interv;
	}
	
	
	public void actualizarEstadoIntervencion (Integer numeroTicket, String observaciones, Usuario usuario) {

		Intervencion intervencionMDA = gestorBD.getIntervencionMDA(numeroTicket);
		EstadoIntervencion estadoIntervencion = intervencionMDA.getEstadoIntervencionActual();
		LocalDate fecha = LocalDate.now();
		LocalTime hora = LocalTime.now();
		
		estadoIntervencion.setFechaFin(fecha);
		estadoIntervencion.setHoraFin(hora);
		
		EstadoIntervencion nuevoEstadoIntervencion = new EstadoIntervencion(TERMINADA,fecha,hora,intervencionMDA);
		nuevoEstadoIntervencion.setObservaciones(observaciones);
		nuevoEstadoIntervencion.setUsuario(usuario);
		nuevoEstadoIntervencion.setHoraFin(hora);
		nuevoEstadoIntervencion.setFechaFin(fecha);
		
		intervencionMDA.setEstadoIntervencionActual(nuevoEstadoIntervencion);
		intervencionMDA.add(nuevoEstadoIntervencion);
		intervencionMDA.setFechaFinAsignacion(fecha);
		intervencionMDA.setHoraFinAsignacion(hora);	
	}
	
	
	public Intervencion actualizarIntervenciones(Integer numeroTicket, String observaciones, GrupoDeResolucion grupo, Usuario usuario) {
		LocalDate fecha = LocalDate.now();
		LocalTime hora = LocalTime.now();
		Intervencion intervencion = gestorBD.getIntervencionMDA(numeroTicket);
		
		intervencion.getEstadoIntervencionActual().setFechaFin(fecha);
		intervencion.getEstadoIntervencionActual().setHoraFin(hora);
		
		EstadoIntervencion nuevoEstadoIntervencion = new EstadoIntervencion(ENESPERA, fecha, hora, intervencion);
		nuevoEstadoIntervencion.setUsuario(usuario);
		intervencion.setEstadoIntervencionActual(nuevoEstadoIntervencion);
		intervencion.add(nuevoEstadoIntervencion);
		gestorBD.guardarIntervencion(intervencion);
		
		Intervencion intervencionRetorno;
		
		Intervencion intervencionGrupoNueva = gestorBD.getUltimaIntervencion(numeroTicket,grupo);
		EstadoIntervencion estadoIntervencionGrupo = new EstadoIntervencion(fecha, hora);
		estadoIntervencionGrupo.setEstado(ASIGNADA);
		estadoIntervencionGrupo.setObservaciones(observaciones);
	
		
		if(intervencionGrupoNueva==null || intervencionGrupoNueva.getEstadoIntervencionActual().getEstado().equalsIgnoreCase(TERMINADA)) {
			Intervencion intervencionGrupo = new Intervencion();
			intervencionGrupo.setFechaAsignacion(fecha);
			intervencionGrupo.setHoraAsignacion(hora);
			intervencionGrupo.setGrupoResolucion(grupo);
			estadoIntervencionGrupo.setIntervencion(intervencionGrupo);
			estadoIntervencionGrupo.setUsuario(usuario);
			intervencionGrupo.setEstadoIntervencionActual(estadoIntervencionGrupo);
			intervencionGrupo.add(estadoIntervencionGrupo);
			intervencionRetorno = intervencionGrupo;
		}
		
		else {
			LocalTime horaFin = LocalTime.now();
			LocalDate fechaFin = LocalDate.now();
			
			intervencionGrupoNueva.getEstadoIntervencionActual().setFechaFin(fechaFin);
			intervencionGrupoNueva.getEstadoIntervencionActual().setHoraFin(horaFin);
			intervencionGrupoNueva.setEstadoIntervencionActual(estadoIntervencionGrupo);
			intervencionGrupoNueva.add(estadoIntervencionGrupo);
			estadoIntervencionGrupo.setIntervencion(intervencionGrupoNueva);
			estadoIntervencionGrupo.setUsuario(usuario);
			gestorBD.actualizarIntervencion(intervencionGrupoNueva);
			intervencionRetorno = null;
		}
		
		return intervencionRetorno;
	}
	
	
	public void actualizarEstado(Integer numeroTicket) {
		
		LocalDate fechaFin = LocalDate.now();
		LocalTime horaFin = LocalTime.now();
		
		Intervencion intervencionMDA = gestorBD.getIntervencionMDA(numeroTicket);
		intervencionMDA.getEstadoIntervencionActual().setFechaFin(fechaFin);
		intervencionMDA.getEstadoIntervencionActual().setHoraFin(horaFin);
		Usuario usuario = gestorUsuario.getUsuarioActual();
		
		EstadoIntervencion nuevoEstadoIntervencion = new EstadoIntervencion();
		nuevoEstadoIntervencion.setEstado(TRABAJANDO);
		nuevoEstadoIntervencion.setUsuario(usuario);
		nuevoEstadoIntervencion.setFechaInicio(fechaFin);
		nuevoEstadoIntervencion.setHoraInicio(horaFin);
		nuevoEstadoIntervencion.setIntervencion(intervencionMDA);
		intervencionMDA.setEstadoIntervencionActual(nuevoEstadoIntervencion);
		intervencionMDA.add(nuevoEstadoIntervencion);
	}
	
	
	
	public List<IntervencionResultadoDTO> getIntervenciones(IntervencionBusquedaDTO intervencionDTO, Integer legajo) {
		List<IntervencionResultadoDTO> encontradas = new ArrayList<>();
		Integer idGrupo = gestorEmpleado.getGrupoId(legajo);
		List<Intervencion> encontradasAux = gestorBD.getIntervenciones(intervencionDTO, idGrupo);
		if (encontradasAux.size()>0) {
			for(Intervencion i: encontradasAux) {
				Ticket t1 = i.getTicket();
				IntervencionResultadoDTO auxDTO = new IntervencionResultadoDTO(t1.getNumero(), t1.getEmpleado().getNumeroLegajo(), t1.getDuracionClasificacionActual().getClasificacion().getNombre(), t1.getDuracionEstadoActual().getEstado().getNombre(), t1.getFechaApertura(), i.getFechaAsignacion(), i.getEstadoIntervencionActual().getEstado(), i.getGrupoResolucion().getNombre(), i.getEstadoIntervencionActual().getObservaciones(), i.getId_Intervencion());
				encontradas.add(auxDTO);
			}
		}
		return encontradas;
	}
	
	
	public Ticket actualizarIntervencion(LocalDate fechaFin, LocalTime horaFin, IntervencionResultadoDTO i, String nuevoEstado, String observaciones, Ticket ticket) {
		Ticket resultado;
		Usuario usuario = gestorUsuario.getUsuarioActual();
		Intervencion intervencion = gestorBD.getIntervencion(i.getIdIntervencion());
		
		
		if ((intervencion.getEstadoIntervencionActual().getEstado().equalsIgnoreCase(TRABAJANDO) && nuevoEstado.equalsIgnoreCase(ENESPERA)) || (intervencion.getEstadoIntervencionActual().getEstado().equalsIgnoreCase(TRABAJANDO) && nuevoEstado.equalsIgnoreCase(TERMINADA)) || (intervencion.getEstadoIntervencionActual().getEstado().equalsIgnoreCase(ASIGNADA) && nuevoEstado.equalsIgnoreCase(TRABAJANDO))) {
			intervencion.getEstadoIntervencionActual().setFechaFin(fechaFin);
			intervencion.getEstadoIntervencionActual().setHoraFin(horaFin);
			
			EstadoIntervencion nuevoEstadoIntervencion = new EstadoIntervencion(fechaFin, horaFin);
			nuevoEstadoIntervencion.setUsuario(usuario);
			nuevoEstadoIntervencion.setObservaciones(observaciones);
			
			if (intervencion.getEstadoIntervencionActual().getEstado().equalsIgnoreCase(ASIGNADA) && nuevoEstado.equalsIgnoreCase(TRABAJANDO)) {
				nuevoEstadoIntervencion.setEstado(nuevoEstado);
			}
			
			else {			
				ticket.getDuracionEstadoActual().setFechaFin(fechaFin);
				ticket.getDuracionEstadoActual().setHoraFin(horaFin);
				
				if (intervencion.getEstadoIntervencionActual().getEstado().equalsIgnoreCase(TRABAJANDO) && nuevoEstado.equalsIgnoreCase(ENESPERA)) {
					nuevoEstadoIntervencion.setEstado(nuevoEstado);
				}
				
				if (intervencion.getEstadoIntervencionActual().getEstado().equalsIgnoreCase(TRABAJANDO) && nuevoEstado.equalsIgnoreCase(TERMINADA)) {
					nuevoEstadoIntervencion.setEstado(nuevoEstado);
					nuevoEstadoIntervencion.setFechaFin(fechaFin);
					nuevoEstadoIntervencion.setHoraFin(horaFin);
					intervencion.setFechaFinAsignacion(fechaFin);
					intervencion.setHoraFinAsignacion(horaFin);
				}
			}
			
			//intervencion.setTicket(ticket);
			intervencion.setEstadoIntervencionActual(nuevoEstadoIntervencion);
			intervencion.add(nuevoEstadoIntervencion);
			nuevoEstadoIntervencion.setIntervencion(intervencion);
			gestorBD.actualizarIntervencion(intervencion);
			resultado = ticket;
		}
		
		else {
			JOptionPane.showMessageDialog(null, "Nuevo estado de ticket no valido.");
			resultado = null;
		}
		return resultado;
	}
}