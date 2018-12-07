package modelo.gestores;

import java.time.*;
import java.util.ArrayList;
import java.util.List;

import infoDTO.IntervencionBusquedaDTO;
import infoDTO.IntervencionResultadoDTO;
import infoDTO.TicketDTO;
import modelo.entidades.EstadoIntervencion;
import modelo.entidades.GrupoDeResolucion;
import modelo.entidades.Intervencion;
import modelo.entidades.Ticket;

public class GestorIntervencion {
	GestorBD gestorBD;
	GestorUsuario gestorUsuario;
	
	public GestorIntervencion(GestorBD gBD, GestorUsuario gUsu){
		gestorBD = gBD;
		gestorUsuario = gUsu;
	}

	
	public Intervencion crearIntervencion(LocalDate fechaActual, LocalTime horaActual, Ticket ticket) {
		Intervencion interv = new Intervencion(fechaActual, horaActual,ticket);
		//CREAR CONSTANTE GLOBAL PARA EL GET(1)
		interv.setGrupoResolucion(gestorBD.getGrupoResolucion(1));
		EstadoIntervencion estadoInterv = new EstadoIntervencion("Trabajando",fechaActual, horaActual,interv);
		interv.add(estadoInterv);
		interv.setEstadoIntervencionActual(estadoInterv);
		return interv;
	}
	
	
	public void actualizarEstadoIntervencion (Integer numeroTicket, String observaciones) {
		//TODO NO INICIALIZAR ASI, YA DARLE EL VALOR QUE VA A TENER
		Intervencion intervencion = new Intervencion();
		EstadoIntervencion estadoIntervencion = new EstadoIntervencion();
		intervencion = gestorBD.getIntervencionMDA(numeroTicket);
		estadoIntervencion = intervencion.getEstadoIntervencionActual();
		LocalDate fecha = LocalDate.now();
		LocalTime hora = LocalTime.now();
		
		estadoIntervencion.setFechaFin(fecha);
		estadoIntervencion.setHoraFin(hora);
		
		EstadoIntervencion nuevoEstadoIntervencion = new EstadoIntervencion("Terminada",fecha,hora,intervencion);
		nuevoEstadoIntervencion.setHoraFin(hora);
		nuevoEstadoIntervencion.setFechaFin(fecha);
		
		intervencion.setEstadoIntervencionActual(nuevoEstadoIntervencion);
		intervencion.add(nuevoEstadoIntervencion);
		intervencion.setFechaFinAsignacion(fecha);
		intervencion.setHoraFinAsignacion(hora);
		
	}
	
	public Intervencion actualizarIntervenciones(Integer numeroTicket, String observaciones, GrupoDeResolucion grupo) {
		LocalDate fecha = LocalDate.now();
		LocalTime hora = LocalTime.now();
		Intervencion intervencion = gestorBD.getIntervencionMDA(numeroTicket);
		
		//intervencion.getEstadoIntervencionActual().setObservaciones(observaciones);
		intervencion.getEstadoIntervencionActual().setUsuario(gestorUsuario.getUsuarioActual());
		intervencion.getEstadoIntervencionActual().setFechaFin(fecha);
		intervencion.getEstadoIntervencionActual().setHoraFin(hora);
		
		EstadoIntervencion nuevoEstadoIntervencion = new EstadoIntervencion("En espera", fecha, hora, intervencion);
		nuevoEstadoIntervencion.setUsuario(gestorUsuario.getUsuarioActual());
		intervencion.setEstadoIntervencionActual(nuevoEstadoIntervencion);
		intervencion.add(nuevoEstadoIntervencion);
		gestorBD.guardarIntervencion(intervencion);
		
		Intervencion intervencionRetorno;
		
		Intervencion intervencionGrupoNueva = gestorBD.getUltimaIntervencion(numeroTicket,grupo);
		EstadoIntervencion estadoIntervencionGrupo = new EstadoIntervencion(fecha, hora);
		estadoIntervencionGrupo.setEstado("Asignada");
		estadoIntervencionGrupo.setObservaciones(observaciones);
	
		
		if(intervencionGrupoNueva==null || intervencionGrupoNueva.getEstadoIntervencionActual().getEstado().equalsIgnoreCase("Terminada")) {
			Intervencion intervencionGrupo = new Intervencion();
			intervencionGrupo.setFechaAsignacion(fecha);
			intervencionGrupo.setHoraAsignacion(hora);
			intervencionGrupo.setGrupoResolucion(grupo);
			estadoIntervencionGrupo.setIntervencion(intervencionGrupo);
			estadoIntervencionGrupo.setUsuario(gestorUsuario.getUsuarioActual());
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
			estadoIntervencionGrupo.setUsuario(gestorUsuario.getUsuarioActual());
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
		
		EstadoIntervencion nuevoEstadoIntervencion = new EstadoIntervencion();
		nuevoEstadoIntervencion.setEstado("Trabajando");
		intervencionMDA.setEstadoIntervencionActual(nuevoEstadoIntervencion);
		intervencionMDA.add(nuevoEstadoIntervencion);
	}
	
	
	
	public List<IntervencionResultadoDTO> getIntervenciones(IntervencionBusquedaDTO intervencionDTO, Integer idGrupo) {
		List<IntervencionResultadoDTO> encontradas = new ArrayList<>();
		List<Intervencion> encontradasAux = gestorBD.getIntervenciones(intervencionDTO, idGrupo);
		for(Intervencion i: encontradasAux) {
			Ticket t1 = i.getTicket();
			IntervencionResultadoDTO auxDTO = new IntervencionResultadoDTO(t1.getNumero(), t1.getEmpleado().getNumeroLegajo(), t1.getDuracionClasificacionActual().getClasificacion().getNombre(), t1.getDuracionEstadoActual().getEstado().getNombre(), t1.getFechaApertura(), i.getFechaAsignacion(), intervencionDTO.getEstado(), i.getGrupoResolucion().getNombre(), i.getEstadoIntervencionActual().getObservaciones());
			encontradas.add(auxDTO);
		}
		/*for(Intervencion i: encontradasAux) {
			IntervencionResultadoDTO auxDTO = new IntervencionBusquedaDTO(i.getEstadoIntervencionActual().getEstado(), intervencionDTO.getFechaDesde(), intervencionDTO.getFechaHasta(), i.getTicket().getNumero().toString(), i.getTicket().getEmpleado().getNumeroLegajo().toString());
			encontradas.add(auxDTO);
		}*/
		return encontradas;
	}
}
