package modelo.gestores;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import infoDTO.DatosDTO;
import modelo.entidades.ClasificacionTicket;
import modelo.entidades.Empleado;
import modelo.entidades.Estado;
import modelo.entidades.GrupoDeResolucion;
import modelo.entidades.Intervencion;
import modelo.entidades.Ticket;
import modelo.entidades.Usuario;

public class GestorBD {

	private  EntityManager manager;
	private  EntityManagerFactory emf;
	
	public GestorBD() {
		emf = Persistence.createEntityManagerFactory("Persistencia");
		manager = emf.createEntityManager();
	}
	
	
	public List<Empleado> getEmpleados (){
		List<Empleado> empleados = manager.createQuery("from Empleado").getResultList();
		return empleados;
	}
	

	public Ticket guardarTicket (Ticket ticket) {
		manager.getTransaction().begin();
		manager.persist(ticket);
		manager.getTransaction().commit();
		return ticket;
	}
	
	
	public void guardarIntervencion(Intervencion interv) {
		manager.getTransaction().begin();
		manager.persist(interv);
		manager.getTransaction().commit();
	}
	
	
	public Ticket actualizarTicket (Ticket ticket) {
		manager.getTransaction().begin();
		ticket = manager.merge(ticket);
		manager.persist(ticket);
		manager.getTransaction().commit();
		return ticket;
	}
	
	
	public List<ClasificacionTicket> getClasificaciones() {
		List<ClasificacionTicket> clasificaciones;
		manager.getTransaction().begin();
		clasificaciones = (List<ClasificacionTicket>) manager.createQuery("FROM ClasificacionTicket").getResultList();
		manager.getTransaction().commit();
		return clasificaciones;
	}
	
	
	public List<GrupoDeResolucion> getGrupos() {
		List<GrupoDeResolucion> grupos;
		manager.getTransaction().begin();
		grupos = (List<GrupoDeResolucion>) manager.createQuery("FROM GrupoDeResolucion").getResultList();
		manager.getTransaction().commit();
		return grupos;
	}
	
	
	public Estado getEstado(Integer id_estado) {
		Estado estadoEncontrado;
		String consulta = "FROM Estado where id_estado = " + id_estado;
		manager.getTransaction().begin();
		estadoEncontrado = (Estado) manager.createQuery(consulta).getSingleResult();
		manager.getTransaction().commit();
		return estadoEncontrado;
	}
	
	
	public GrupoDeResolucion getGrupoResolucion (Integer id) {
		GrupoDeResolucion grupoEncontrado;
		String consulta = "FROM GrupoDeResolucion where id_grupo = " + id;
		manager.getTransaction().begin();
		grupoEncontrado = (GrupoDeResolucion) manager.createQuery(consulta).getSingleResult();
		manager.getTransaction().commit();
		return grupoEncontrado;
	}
	
	
	public Intervencion getIntervencionMDA (Integer numeroTicket) {
		Intervencion intervencionEncontrada;
		String consulta = "FROM Intervencion WHERE numero_ticket = " + numeroTicket;
		manager.getTransaction().begin();
		intervencionEncontrada = (Intervencion) manager.createQuery(consulta).getSingleResult();
		manager.getTransaction().commit();
		return intervencionEncontrada;
	}
	
	
	public void eliminarTicket (String numeroTicket) {
		Integer numero = Integer.valueOf(numeroTicket);
		manager.getTransaction().begin();
		String consulta = "delete Ticket where id = " + numero;
		manager.createQuery(consulta).executeUpdate();
		manager.getTransaction().commit();
	}
	
	
	public Ticket getTicket(Integer numeroTicket) {
		Ticket ticket;
		String consulta = "FROM Ticket WHERE numero_ticket = " + numeroTicket;
		manager.getTransaction().begin();
		ticket = (Ticket) manager.createQuery(consulta).getSingleResult();
		manager.getTransaction().commit();
		return ticket;
	}


	public Usuario getUsuario(int i) {
		Usuario u;
		manager.getTransaction().begin();
		u = (Usuario) manager.createQuery("from Usuario where NUMERO_LEGAJO = " + i).getSingleResult();
		manager.getTransaction().commit();
		return u;
	}


	public GrupoDeResolucion getGrupoResolucion(String nombre) {
		GrupoDeResolucion grupo;
		manager.getTransaction().begin();
		grupo = (GrupoDeResolucion) manager.createQuery("FROM GrupoDeResolucion WHERE nombre = " + nombre ).getSingleResult();
		manager.getTransaction().commit();
		return grupo;
	}


	public List<Ticket> getTickets(DatosDTO datosDTO) {
		List<Ticket> encontrados = new ArrayList<>();
		
		String consulta1 = "Select distinct t FROM Ticket t, DuracionClasificacion dc, DuracionEstado de, ClasificacionTicket ct, Estado e, Intervencion i, GrupoDeResolucion gr  "
				+ " where t.duracionClasificacionActual = dc and dc.clasificacion = ct and t.duracionEstadoActual = de and de.estado = e and t = i.ticket and i.grupo = gr";
		
		if(!(datosDTO.getNumeroTicket()==null)) {
			consulta1+=" and t.numeroTicket = " + datosDTO.getNumeroTicket();
		}
		
		if(!(datosDTO.getNumeroLegajo()==null)) {
			consulta1+=" and t.empleado = " + datosDTO.getNumeroLegajo();
		}
		
		if(!(datosDTO.getFechaApertura()==null)) {
			consulta1+= " and t.fechaApertura = '" + datosDTO.getFechaApertura() + "'";
		}
		
		if(!(datosDTO.getFechaUltimoCambioEstado()==null)) {
			consulta1+=" and de.fechaInicio = '" + datosDTO.getFechaUltimoCambioEstado() + "'";
		}
		
		if(!datosDTO.getClasificacion().equalsIgnoreCase("Todas")) {
			consulta1+=" and ct.nombre = '" + datosDTO.getClasificacion() + "'";
		}
		if(!datosDTO.getEstado().equalsIgnoreCase("Todos")) {
			consulta1+=" and e.nombre = '" + datosDTO.getEstado() + "'";
		}
		if(!datosDTO.getGrupo().equalsIgnoreCase("Todos")) {
			consulta1+="  and gr.nombre = '" + datosDTO.getGrupo() + "'";
		}
		
		
		manager.getTransaction().begin();
		encontrados = (List<Ticket>) manager.createQuery(consulta1).getResultList();
		manager.getTransaction().commit();

		
		return encontrados;
	}
}