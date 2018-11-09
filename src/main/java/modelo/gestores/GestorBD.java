package modelo.gestores;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import modelo.entidades.ClasificacionTicket;
import modelo.entidades.Empleado;
import modelo.entidades.Estado;
import modelo.entidades.GrupoDeResolucion;
import modelo.entidades.Ticket;

public class GestorBD {

	private  EntityManager manager;
	private  EntityManagerFactory emf;
	
	public GestorBD() {
		emf = Persistence.createEntityManagerFactory("Persistencia");
		manager = emf.createEntityManager();
	}
	
	
	public List<Empleado> getEmpleados (){
		System.out.println("Male");
		List<Empleado> empleados = manager.createQuery("from Empleado").getResultList();
		System.out.println("Male - segunda salida");
		return empleados;
	}
	

	/*public Empleado getEmpleado (Integer legajo) {
		Empleado emp = new Empleado();
		String consulta = "select e from Empleado e where e.numeroLegajo = " + legajo;
		manager.getTransaction().begin();
		try {
			
			emp = (Empleado) manager.createQuery(consulta).getSingleResult();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			emp.setNumeroLegajo(null);
			//e.printStackTrace();
		}
		manager.getTransaction().commit();
		System.out.print(emp.getNumeroLegajo());
		
		return emp;
	}*/
	
	
	public Ticket guardarTicket (Ticket ticket) {
		manager.getTransaction().begin();
		manager.persist(ticket);
		manager.getTransaction().commit();
		return ticket;
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
		System.out.println(estadoEncontrado.getNombre());
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
	
}