package modelo.gestores;

import java.time.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import infoDTO.IntervencionBusquedaDTO;
import infoDTO.IntervencionResultadoDTO;
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
	
	public GestorIntervencion(GestorBD gBD, GestorUsuario gUsu, GestorEmpleado gE){
		gestorBD = gBD;
		gestorUsuario = gUsu;
		this.gestorEmpleado = gE;
	}

	
	public Intervencion crearIntervencion(LocalDate fechaActual, LocalTime horaActual, Ticket ticket) {
		Intervencion interv = new Intervencion(fechaActual, horaActual,ticket);
		//TODO CREAR CONSTANTE GLOBAL PARA EL GET(1)
		interv.setGrupoResolucion(gestorBD.getGrupoResolucion(MESADEAYUDA));
		EstadoIntervencion estadoInterv = new EstadoIntervencion("Trabajando",fechaActual, horaActual,interv);
		interv.add(estadoInterv);
		interv.setEstadoIntervencionActual(estadoInterv);
		return interv;
	}
	
	
	public void actualizarEstadoIntervencion (Integer numeroTicket, String observaciones) {

		Intervencion intervencion = gestorBD.getIntervencionMDA(numeroTicket);
		EstadoIntervencion estadoIntervencion = intervencion.getEstadoIntervencionActual();

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
	
	
	
	public List<IntervencionResultadoDTO> getIntervenciones(IntervencionBusquedaDTO intervencionDTO, Integer legajo) {
		List<IntervencionResultadoDTO> encontradas = new ArrayList<>();
		Integer idGrupo = gestorEmpleado.getGrupoId(legajo);
		List<Intervencion> encontradasAux = gestorBD.getIntervenciones(intervencionDTO, idGrupo);
		if (encontradasAux.size()>0) {
			System.out.println("Entra al fin");
			for(Intervencion i: encontradasAux) {
				System.out.println("Entra al for");
				Ticket t1 = i.getTicket();
				
				//EL ERROR ES ESTE PUTOOOOOOOOOOOOOOO
				//System.out.println(t1.getDuracionEstadoActual().getEstado().getNombre());
				
				IntervencionResultadoDTO auxDTO = new IntervencionResultadoDTO(t1.getNumero(), t1.getEmpleado().getNumeroLegajo(), t1.getDuracionClasificacionActual().getClasificacion().getNombre(), t1.getDuracionEstadoActual().getEstado().getNombre(), t1.getFechaApertura(), i.getFechaAsignacion(), i.getEstadoIntervencionActual().getEstado(), i.getGrupoResolucion().getNombre(), i.getEstadoIntervencionActual().getObservaciones(), i.getId_Intervencion());
				
				encontradas.add(auxDTO);
			}
		}
		
		return encontradas;
	}
	
	
	public Ticket actualizarIntervencion(IntervencionResultadoDTO i, String nuevoEstado, String observaciones, Ticket ticket) {
		Ticket resultado;
		Usuario usuario = gestorUsuario.getUsuarioActual();
		Intervencion intervencion = gestorBD.getIntervencion(i.getIdIntervencion());
		LocalDate fechaFin = LocalDate.now();
		LocalTime horaFin = LocalTime.now();
		
		if ((intervencion.getEstadoIntervencionActual().getEstado().equalsIgnoreCase("Trabajando") && nuevoEstado.equalsIgnoreCase("En espera")) || (intervencion.getEstadoIntervencionActual().getEstado().equalsIgnoreCase("Trabajando") && nuevoEstado.equalsIgnoreCase("Terminada")) || (intervencion.getEstadoIntervencionActual().getEstado().equalsIgnoreCase("Asignada") && nuevoEstado.equalsIgnoreCase("Trabajando"))) {
			intervencion.getEstadoIntervencionActual().setFechaFin(fechaFin);
			intervencion.getEstadoIntervencionActual().setHoraFin(horaFin);
			
			EstadoIntervencion nuevoEstadoIntervencion = new EstadoIntervencion(fechaFin, horaFin);
			nuevoEstadoIntervencion.setUsuario(usuario);
			nuevoEstadoIntervencion.setObservaciones(observaciones);
			
			if (intervencion.getEstadoIntervencionActual().getEstado().equalsIgnoreCase("Asignada") && nuevoEstado.equalsIgnoreCase("Trabajando")) {
				nuevoEstadoIntervencion.setEstado(nuevoEstado);
			}
			
			else {			
				ticket.getDuracionEstadoActual().setFechaFin(fechaFin);
				ticket.getDuracionEstadoActual().setHoraFin(horaFin);
				
				if (intervencion.getEstadoIntervencionActual().getEstado().equalsIgnoreCase("Trabajando") && nuevoEstado.equalsIgnoreCase("En espera")) {
					nuevoEstadoIntervencion.setEstado(nuevoEstado);
				}
				
				if (intervencion.getEstadoIntervencionActual().getEstado().equalsIgnoreCase("Trabajando") && nuevoEstado.equalsIgnoreCase("Terminada")) {
					nuevoEstadoIntervencion.setEstado(nuevoEstado);
				}
			}
			
			intervencion.setTicket(ticket);
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