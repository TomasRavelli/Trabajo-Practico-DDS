package modelo.aplicacion;

import java.awt.Toolkit;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JFrame;
import javax.swing.WindowConstants;
import interfacesGraficas.HomeMesaAyuda;
import modelo.entidades.Usuario;
import modelo.gestores.*;


public class Principal extends JFrame{
	
	//private static EntityManager manager;
	//private static EntityManagerFactory emf;
	private static GestorUsuario gestorUsuario;
	private static GestorTicket gestorTicket;
	private static GestorClasificacion gestorClasificacion;
	private static GestorBD gestorBD;
	private static GestorEmpleado gestorEmpleado;
	private static GestorIntervencion gestorIntervencion;
	private static GestorGrupo gestorGrupo;
	
	
	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				gestorUsuario = new GestorUsuario(new Usuario(123, "Tomas",11111));
				gestorBD = new GestorBD();
				gestorEmpleado = new GestorEmpleado(gestorBD);
				gestorIntervencion = new GestorIntervencion(gestorBD);
				gestorTicket = new GestorTicket(gestorBD,gestorEmpleado, gestorIntervencion, gestorUsuario);
				gestorClasificacion = new GestorClasificacion(gestorBD);
				new Principal();
			}
		});
	}
	
	
	public Principal() {
		
		this.setTitle("La llamita");
		this.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Malena Moix\\eclipse-workspace\\TPDS\\cool-flame-icon.png"));
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.setSize(800,600);
		this.setBounds(0, 0, 1366, 768);
		//setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
		this.setVisible(true);
		
		
		//DEPENDIENDO SI SE REGISTRE COMO MESA DE AYUDA O GRUPO DE RESOLUCION
		if (true) {
			this.setContentPane(new HomeMesaAyuda(this));
			this.pack();
		}
		/*else {
			this.setContentPane(new HomeGrupoResolucion(this));
			this.pack();
		}*/
		
		
	}


	public GestorUsuario getGestorUsuario() {
		return gestorUsuario;
	}


	public GestorTicket getGestorTicket() {
		return gestorTicket;
	}


	public GestorClasificacion getGestorClasificacion() {
		return gestorClasificacion;
	}


	public GestorBD getGestorBD() {
		return gestorBD;
	}


	public GestorEmpleado getGestorEmpleado() {
		return gestorEmpleado;
	}


	public GestorIntervencion getGestorIntervencion() {
		return gestorIntervencion;
	}


	public GestorGrupo getGestorGrupo() {
		return gestorGrupo;
	}
	
	
	
//	public static void main(String[] args) {

		//emf = Persistence.createEntityManagerFactory("Persistencia");
		//manager = emf.createEntityManager();
		
		
		
		//Estado estado = new Estado ("estado1","hola");
		
		//manager.getTransaction().begin();
		//manager.persist(estado);
		//manager.getTransaction().commit();
	//}

}
