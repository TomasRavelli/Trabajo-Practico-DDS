package modelo.gestores;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import infoDTO.DatosDTO;
import infoDTO.IntervencionBusquedaDTO;
import modelo.entidades.ClasificacionTicket;
import modelo.entidades.Empleado;
import modelo.entidades.Estado;
import modelo.entidades.GrupoDeResolucion;
import modelo.entidades.Intervencion;
import modelo.entidades.Ticket;
import modelo.entidades.UltimoNumeroTicket;
import modelo.entidades.Usuario;

public class GestorBD {

	private  EntityManager manager;
	private  EntityManagerFactory emf;
	
	public GestorBD() {
		emf = Persistence.createEntityManagerFactory("Persistencia");
		manager = emf.createEntityManager();
	}
	
	
	
	public List<Empleado> getEmpleados (){
		manager.getTransaction().begin();
		List<Empleado> empleados = manager.createQuery("from Empleado").getResultList();
		manager.getTransaction().commit();	
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
	
	public Intervencion actualizarIntervencion (Intervencion intervencion) {
		manager.getTransaction().begin();
		intervencion = manager.merge(intervencion);
		manager.persist(intervencion);
		manager.getTransaction().commit();
		return intervencion;
	}
	
	
	public List<ClasificacionTicket> getClasificaciones() {
		manager.getTransaction().begin();
		List<ClasificacionTicket> clasificaciones = (List<ClasificacionTicket>) manager.createQuery("FROM ClasificacionTicket").getResultList();
		manager.getTransaction().commit();
		return clasificaciones;
	}
	
	
	public List<GrupoDeResolucion> getGrupos() {
		manager.getTransaction().begin();
		List<GrupoDeResolucion> grupos = (List<GrupoDeResolucion>) manager.createQuery("FROM GrupoDeResolucion").getResultList();
		manager.getTransaction().commit();
		return grupos;
	}
	
	
	public Estado getEstado(Integer id_estado) {
		String consulta = "FROM Estado where id_estado = " + id_estado;
		manager.getTransaction().begin();
		Estado estadoEncontrado = (Estado) manager.createQuery(consulta).getSingleResult();
		manager.getTransaction().commit();
		return estadoEncontrado;
	}
	
	
	public GrupoDeResolucion getGrupoResolucion (Integer id) {
		String consulta = "FROM GrupoDeResolucion where id_grupo = " + id;
		manager.getTransaction().begin();
		GrupoDeResolucion grupoEncontrado = (GrupoDeResolucion) manager.createQuery(consulta).getSingleResult();
		manager.getTransaction().commit();
		return grupoEncontrado;
	}
	
	
	public Intervencion getIntervencionMDA (Integer numeroTicket) {
		Intervencion intervencionEncontrada;
		String consulta = "Select i FROM Intervencion i, GrupoDeResolucion gr WHERE i.grupo = gr and numero_ticket = " + numeroTicket + " and gr.nombre = 'Mesa de Ayuda'";
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
		String consulta = "FROM Ticket WHERE numero_ticket = " + numeroTicket;
		manager.getTransaction().begin();
		Ticket ticket = (Ticket) manager.createQuery(consulta).getSingleResult();
		manager.getTransaction().commit();
		return ticket;
	}


	public Usuario getUsuario(int i) {
		Usuario u;
		manager.getTransaction().begin();
		try {
			u = (Usuario) manager.createQuery("from Usuario where NUMERO_LEGAJO = " + i).getSingleResult();
		} 
		catch (Exception e) {
			u = null;
		}
		manager.getTransaction().commit();
		return u;
	}


	public GrupoDeResolucion getGrupoResolucion(String nombre) {
		manager.getTransaction().begin();
		GrupoDeResolucion grupo = (GrupoDeResolucion) manager.createQuery("FROM GrupoDeResolucion WHERE nombre = " + nombre ).getSingleResult();
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
	
	
	public List<Intervencion> getIntervenciones(IntervencionBusquedaDTO criteriosBusqueda, Integer idGrupo){
		String consulta = "Select i FROM Intervencion i, Ticket t, EstadoIntervencion ei where i.ticket = t and i.estadoIntervencion1 = ei and i.grupo = " + idGrupo;
		
		if (criteriosBusqueda.getNumeroTicket()!=null) {
			consulta += " and t.numeroTicket = " + criteriosBusqueda.getNumeroTicket();
		}
		
		if (!(criteriosBusqueda.getNumeroLegajo()==null)) {
			consulta += " and t.empleado = " + criteriosBusqueda.getNumeroLegajo();
		}
		
		if (!criteriosBusqueda.getEstado().equalsIgnoreCase("Todos")) {
			consulta += " and ei.estado = '" + criteriosBusqueda.getEstado() + "'";
		}
		
		if (criteriosBusqueda.getFechaDesde()!=null && criteriosBusqueda.getFechaHasta()!=null) {
			consulta += " and ei.fechaInicio between " + criteriosBusqueda.getFechaDesde() + " and " + criteriosBusqueda.getFechaHasta();
		}		
		
		manager.getTransaction().begin();
		List<Intervencion> intervenciones = (List<Intervencion>) manager.createQuery(consulta).getResultList();
		manager.getTransaction().commit();
		return intervenciones;
	}
	

	public Intervencion getUltimaIntervencion(Integer numeroTicket, GrupoDeResolucion grupo) {
		Intervencion resultado;
		List<Intervencion> intervencionesTicket;
		String consulta = "from Intervencion where ticket = " + numeroTicket + " and grupo = " + grupo.getId_Grupo();
		
		manager.getTransaction().begin();
		intervencionesTicket = (List<Intervencion>) manager.createQuery(consulta).getResultList();
		manager.getTransaction().commit();
		if(intervencionesTicket.size() == 0) {
			resultado = null;
		}
		else {
			resultado = intervencionesTicket.get(intervencionesTicket.size());
		}
		return resultado;
	}
	
	
	public Intervencion getIntervencion (Integer idIntevercion) {
		String consulta = "FROM Intervencion where id_Intervencion = " + idIntevercion;
		manager.getTransaction().begin();
		Intervencion intervencionEncontrada = (Intervencion) manager.createQuery(consulta).getSingleResult();
		manager.getTransaction().commit();
		return intervencionEncontrada;
	}
	
	
	public UltimoNumeroTicket getUltimoNumeroTicket() {
		manager.getTransaction().begin();
		UltimoNumeroTicket n = (UltimoNumeroTicket) manager.createQuery("FROM UltimoNumeroTicket").getSingleResult();
		n.setNumeroTicket(n.getNumeroTicket()+1);
		n = manager.merge(n);
		manager.persist(n);
		manager.getTransaction().commit();
		return n;
	}
}