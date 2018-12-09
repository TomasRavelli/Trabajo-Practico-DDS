package modelo.aplicacion;

import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.WindowConstants;
import modelo.entidades.Usuario;
import modelo.gestores.*;


public class Principal extends JFrame{
	
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
				gestorBD = new GestorBD();
				gestorUsuario = new GestorUsuario();
				gestorEmpleado = new GestorEmpleado(gestorBD);
				gestorIntervencion = new GestorIntervencion(gestorBD, gestorUsuario, gestorEmpleado);
				gestorClasificacion = new GestorClasificacion(gestorBD);
				gestorGrupo = new GestorGrupo(gestorBD);
				gestorTicket = new GestorTicket(gestorBD,gestorEmpleado, gestorIntervencion, gestorUsuario, gestorClasificacion);
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
		
		this.setContentPane(new LogIn(this));
		this.pack();
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


	public static void setGestorUsuario(GestorUsuario gestorUsuario) {
		Principal.gestorUsuario = gestorUsuario;
	}


	public static void setGestorTicket(GestorTicket gestorTicket) {
		Principal.gestorTicket = gestorTicket;
	}


	public static void setGestorClasificacion(GestorClasificacion gestorClasificacion) {
		Principal.gestorClasificacion = gestorClasificacion;
	}


	public static void setGestorBD(GestorBD gestorBD) {
		Principal.gestorBD = gestorBD;
	}


	public static void setGestorEmpleado(GestorEmpleado gestorEmpleado) {
		Principal.gestorEmpleado = gestorEmpleado;
	}


	public static void setGestorIntervencion(GestorIntervencion gestorIntervencion) {
		Principal.gestorIntervencion = gestorIntervencion;
	}


	public static void setGestorGrupo(GestorGrupo gestorGrupo) {
		Principal.gestorGrupo = gestorGrupo;
	}


	public void setUsuario(Usuario u) {
		gestorUsuario.setUsuarioActual(u);
	}
}