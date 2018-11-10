package modelo.gestores;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import modelo.entidades.ClasificacionTicket;
import modelo.entidades.Empleado;
import modelo.entidades.Estado;
import modelo.entidades.GrupoDeResolucion;
import modelo.entidades.Intervencion;
import modelo.entidades.Ticket;

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
}