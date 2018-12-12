package modelo.aplicacion;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.time.LocalDate;
import java.time.LocalTime;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import infoDTO.TicketDTO;
import interfacesGraficas.HomeGrupoResolucion;
import interfacesGraficas.HomeMesaAyuda;
import interfacesGraficas.InterfazRegistrarTicket2;
import modelo.entidades.ClasificacionTicket;
import modelo.entidades.Usuario;

public class LogIn extends JPanel{
	
	private Principal ventana;
	private JLabel errorLegajoVacio;
	private JLabel errorLegajoExistente;
	private JTextField numeroLegajo;
	private JTextField txtContrasenia;
	
	public LogIn(Principal frame) {
	
		this.ventana=frame;
		ventana.setContentPane(this);
		this.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setPreferredSize(new Dimension(1366, 768));
		this.setBackground(new Color(230, 230, 250));
		this.setLayout(null);
		
		JLabel lblRegistrarTicket = new JLabel("Log In");
		lblRegistrarTicket.setFont(new Font("Segoe UI Symbol", Font.BOLD, 40));
		lblRegistrarTicket.setBounds(526, 20, 298, 59);
		this.add(lblRegistrarTicket);
			
		
		JSeparator separator = new JSeparator();
		separator.setForeground(Color.GRAY);
		separator.setBounds(295, 90, 760, 2);
		this.add(separator);
		
		JLabel lblLegajo = new JLabel("Numero de Legajo:");
		lblLegajo.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 16));
		lblLegajo.setBounds(379, 180, 156, 28);
		this.add(lblLegajo);
		
		JLabel lblContrasenia = new JLabel("Contrasenia:");
		lblContrasenia.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 16));
		lblContrasenia.setBounds(379, 303, 156, 23);
		this.add(lblContrasenia);
		
		errorLegajoVacio = new JLabel("* Este campo no puede estar vacio.");
		errorLegajoVacio.setForeground(Color.RED);
		errorLegajoVacio.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 14));
		errorLegajoVacio.setBounds(850, 303, 219, 24);
		this.add(errorLegajoVacio);
		errorLegajoVacio.setVisible(false);
		
		JLabel errorContraseniaVacia = new JLabel("* Este campo no puede estar vacio.");
		errorContraseniaVacia.setForeground(Color.RED);
		errorContraseniaVacia.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 14));
		errorContraseniaVacia.setBounds(850, 180, 219, 24);
		this.add(errorContraseniaVacia);
		errorContraseniaVacia.setVisible(false);
		
		errorLegajoExistente = new JLabel("* Este numero de legajo no se encuentra en el sistema.");
		errorLegajoExistente.setForeground(Color.RED);
		errorLegajoExistente.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 14));
		errorLegajoExistente.setBounds(988, 303, 339, 24);
		this.add(errorLegajoExistente);
		errorLegajoExistente.setVisible(false);

		
		numeroLegajo = new JTextField();
		numeroLegajo.setBounds(600, 180, 200, 22);
		add(numeroLegajo);
		numeroLegajo.setColumns(10);
		numeroLegajo.setVisible(true);
		this.add(numeroLegajo);
		
		txtContrasenia = new JTextField();
		txtContrasenia.setBounds(600, 303, 200, 22);
		add(txtContrasenia);
		txtContrasenia.setColumns(10);
		txtContrasenia.setVisible(true);
		this.add(txtContrasenia);
	
		JButton btnSalir = new JButton("Salir");
		btnSalir.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 16));
		btnSalir.setBounds(1207, 655, 133, 37);
		this.add(btnSalir);
		
		JButton btnIngresar = new JButton("Ingresar");
		btnIngresar.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 16));
		btnIngresar.setBounds(1020, 655, 133, 37);
		this.add(btnIngresar);
		
		
		
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int dialogButton = JOptionPane.YES_NO_OPTION;
				int dialogResult = JOptionPane.showConfirmDialog (null, "¿Desea salir?","Warning",dialogButton);
				if(dialogResult == JOptionPane.YES_OPTION){
					System.exit(0);
				}
			}
		});
		
		
		btnIngresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			 if (numeroLegajo.getText().isEmpty() || txtContrasenia.getText().isEmpty()) {
					if (numeroLegajo.getText().isEmpty()) {
						errorLegajoVacio.setVisible(true);
					}
					if (txtContrasenia.getText().isEmpty()) {
						errorContraseniaVacia.setVisible(true);
					}
			}
			else {
				errorLegajoVacio.setVisible(false);
				errorLegajoVacio.setVisible(false);
				if(ventana.getGestorBD().getUsuario(Integer.valueOf(numeroLegajo.getText())) != null){
					
					Usuario u = ventana.getGestorBD().getUsuario(Integer.valueOf(numeroLegajo.getText()));
				
			
				if (u.getPassword().intValue() == Integer.valueOf(txtContrasenia.getText())) {
					ventana.setUsuario(u);
						if(u.getEmpleado().getGrupo().getNombre().equalsIgnoreCase("Mesa de Ayuda")) {
						ventana.setContentPane(new HomeMesaAyuda(ventana));
						ventana.pack();
					}
					else {
						ventana.setContentPane(new HomeGrupoResolucion(ventana));
						ventana.pack();
					}
			
			}
					
					else {
						//int dialogButton = JOptionPane.ERROR_MESSAGE;
						JOptionPane.showMessageDialog(ventana, "Contrasenia incorrecta");
					}
				}
				else {
					JOptionPane.showMessageDialog(ventana, "Usuario no existente");
				}
			}
			}
		});
	}
}
	
	

