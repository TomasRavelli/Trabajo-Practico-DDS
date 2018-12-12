package modelo.aplicacion;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import interfacesGraficas.HomeGrupoResolucion;
import interfacesGraficas.HomeMesaAyuda;
import modelo.entidades.Usuario;


public class LogIn extends JPanel{
	
	private Principal ventana;
	private JLabel errorLegajoVacio;
	private JLabel errorLegajoExistente;
	private JTextField numeroLegajo;
	private JTextField txtContrasenia;
	//private JPasswordField txtContrasenia;
	

	public LogIn(Principal frame) {
		this.ventana=frame;
		ventana.setContentPane(this);
		this.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setPreferredSize(new Dimension(1366, 768));
		this.setBackground(new Color(230, 230, 250));
		this.setLayout(null);
		
		JLabel lblRegistrarTicket = new JLabel("La llamita: Log In");
		lblRegistrarTicket.setFont(new Font("Segoe UI Symbol", Font.BOLD, 40));
		lblRegistrarTicket.setBounds(511, 18, 344, 59);
		this.add(lblRegistrarTicket);
			
		
		JSeparator separator = new JSeparator();
		separator.setForeground(Color.GRAY);
		separator.setBounds(303, 90, 760, 2);
		this.add(separator);
		
		JLabel lblLegajo = new JLabel("Numero de Legajo:");
		lblLegajo.setFont(new Font("Segoe UI Symbol", Font.BOLD, 18));
		lblLegajo.setBounds(448, 180, 219, 37);
		this.add(lblLegajo);
		
		JLabel lblContrasenia = new JLabel("Password:");
		lblContrasenia.setFont(new Font("Segoe UI Symbol", Font.BOLD, 18));
		lblContrasenia.setBounds(448, 306, 219, 37);
		this.add(lblContrasenia);
		
		errorLegajoVacio = new JLabel("* Este campo no puede estar vacio.");
		errorLegajoVacio.setForeground(Color.RED);
		errorLegajoVacio.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 14));
		errorLegajoVacio.setBounds(934, 188, 219, 24);
		this.add(errorLegajoVacio);
		errorLegajoVacio.setVisible(false);
		
		JLabel errorContraseniaVacia = new JLabel("* Este campo no puede estar vacio.");
		errorContraseniaVacia.setForeground(Color.RED);
		errorContraseniaVacia.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 14));
		errorContraseniaVacia.setBounds(934, 314, 219, 24);
		this.add(errorContraseniaVacia);
		errorContraseniaVacia.setVisible(false);
		
		errorLegajoExistente = new JLabel("* Este numero de legajo no se encuentra en el sistema.");
		errorLegajoExistente.setForeground(Color.RED);
		errorLegajoExistente.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 14));
		errorLegajoExistente.setBounds(934, 188, 339, 24);
		this.add(errorLegajoExistente);
		errorLegajoExistente.setVisible(false);

		
		numeroLegajo = new JTextField();
		numeroLegajo.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 16));
		numeroLegajo.setBounds(679, 187, 219, 24);
		add(numeroLegajo);
		numeroLegajo.setColumns(10);
		numeroLegajo.setVisible(true);
		this.add(numeroLegajo);
		numeroLegajo.addKeyListener(new KeyListener(){	 
			public void keyTyped(KeyEvent e){
				if (numeroLegajo.getText().length()== 5)
			     e.consume();
			}
			public void keyPressed(KeyEvent arg0) {
			}
			public void keyReleased(KeyEvent arg0) {
			}
		});
		
		
		txtContrasenia = new JTextField();
		txtContrasenia.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 16));
		txtContrasenia.setBounds(679, 313, 219, 24);
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
				int dialogResult = JOptionPane.showConfirmDialog (null, "�Desea salir?","Warning",dialogButton);
				if(dialogResult == JOptionPane.YES_OPTION){
					System.exit(0);
				}
			}
		});
		
		
		btnIngresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			if(numeroLegajo.getText().isEmpty()) {
				errorLegajoVacio.setVisible(true);
				errorContraseniaVacia.setVisible(false);
			}
			else if(txtContrasenia.getText().isEmpty()) {
				errorContraseniaVacia.setVisible(true);
				errorLegajoVacio.setVisible(false);
			}
			else {
				errorLegajoVacio.setVisible(false);
				errorContraseniaVacia.setVisible(false);
				
				try{
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
						JOptionPane.showMessageDialog(ventana, "Password incorrecta");
					}
				}
				
				catch (Exception e) {
					JOptionPane.showMessageDialog(ventana, "Usuario no existente");
				}				
			}
			}
		});
	}
}