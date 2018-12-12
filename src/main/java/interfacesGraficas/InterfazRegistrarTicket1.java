package interfacesGraficas;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import infoDTO.TicketDTO;
import modelo.aplicacion.Principal;
import modelo.entidades.ClasificacionTicket;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.SystemColor;

public class InterfazRegistrarTicket1 extends JPanel{
	
	private ClasificacionTicket[] clasificaciones;
	private TicketDTO ticketDTO;
	private Principal ventana;
	private JTextField txtFechaApertura;
	private JTextField txtHoraApertura;
	private JTextField txtNumeroTicket;
	private JTextField txtNumeroLegajo;
	private JLabel errorLegajoExistente;
	private JLabel errorLegajoVacio;
	private JTextField nombre;	

	
	public InterfazRegistrarTicket1(Principal frame) {

		this.ventana=frame;
		ventana.setContentPane(this);
		this.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setPreferredSize(new Dimension(1366, 768));
		this.setBackground(new Color(230, 230, 250));
		this.setLayout(null);
		
		clasificaciones = cargarClasificaciones();
		
		JSeparator separator = new JSeparator();
		separator.setForeground(Color.GRAY);
		separator.setBounds(295, 90, 760, 2);
		this.add(separator);
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(611, 310, 365, 280);
		this.add(scrollPane);
		
		JTextArea textAreaDescripcion = new JTextArea();
		scrollPane.setViewportView(textAreaDescripcion);
		textAreaDescripcion.setLineWrap(true);
		
		
		JLabel lblNumeroTicket = new JLabel("Numero de Ticket:");
		lblNumeroTicket.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 16));
		lblNumeroTicket.setBounds(379, 130, 156, 28);
		this.add(lblNumeroTicket);
		
		JLabel lblNumeroLegajo = new JLabel("Numero de Legajo:");
		lblNumeroLegajo.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 16));
		lblNumeroLegajo.setBounds(379, 253, 156, 23);
		this.add(lblNumeroLegajo);
		
		JLabel lblClasificacionTicket = new JLabel("Clasificacion del Ticket:");
		lblClasificacionTicket.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 16));
		lblClasificacionTicket.setBounds(379, 598, 172, 36);
		this.add(lblClasificacionTicket);
		
		JLabel lblDescripcion = new JLabel("Descripcion del problema:");
		lblDescripcion.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 16));
		lblDescripcion.setBounds(379, 305, 229, 29);
		this.add(lblDescripcion);
		
		JLabel lblFechaApertura = new JLabel("Fecha de apertura:");
		lblFechaApertura.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 16));
		lblFechaApertura.setBounds(379, 171, 142, 28);
		this.add(lblFechaApertura);
		
		JLabel lblHoraApertura = new JLabel("Hora de apertura:");
		lblHoraApertura.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 16));
		lblHoraApertura.setBounds(379, 212, 133, 28);
		this.add(lblHoraApertura);
		
		JLabel lblRegistrarTicket = new JLabel("Registrar ticket");
		lblRegistrarTicket.setFont(new Font("Segoe UI Symbol", Font.BOLD, 40));
		lblRegistrarTicket.setBounds(526, 20, 298, 59);
		this.add(lblRegistrarTicket);
		
		errorLegajoVacio = new JLabel("* Este campo no puede estar vacio.");
		errorLegajoVacio.setForeground(Color.RED);
		errorLegajoVacio.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 14));
		errorLegajoVacio.setBounds(988, 253, 219, 24);
		this.add(errorLegajoVacio);
		errorLegajoVacio.setVisible(false);
		
		JLabel errorDescripcionVacio = new JLabel("* Este campo no puede estar vacio.");
		errorDescripcionVacio.setForeground(Color.RED);
		errorDescripcionVacio.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 14));
		errorDescripcionVacio.setBounds(988, 308, 219, 24);
		this.add(errorDescripcionVacio);
		errorDescripcionVacio.setVisible(false);
		
		JLabel errorDebeElegir = new JLabel("* Debe elegir una opcion.");
		errorDebeElegir.setForeground(Color.RED);
		errorDebeElegir.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 14));
		errorDebeElegir.setBounds(988, 605, 219, 24);
		this.add(errorDebeElegir);
		errorDebeElegir.setVisible(false);
		
		errorLegajoExistente = new JLabel("* Este numero de legajo no se encuentra en el sistema.");
		errorLegajoExistente.setForeground(Color.RED);
		errorLegajoExistente.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 14));
		errorLegajoExistente.setBounds(988, 253, 339, 24);
		this.add(errorLegajoExistente);
		errorLegajoExistente.setVisible(false);

		
		nombre = new JTextField();
		nombre.setBounds(988, 255, 200, 22);
		add(nombre);
		nombre.setColumns(10);
		nombre.setVisible(false);
		nombre.setEditable(false);
		
		txtFechaApertura = new JTextField();
		txtFechaApertura.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 14));
		txtFechaApertura.setBackground(SystemColor.menu);
		txtFechaApertura.setBounds(611, 174, 365, 24);
		txtFechaApertura.setColumns(10);
		txtFechaApertura.setEditable(false);
		txtFechaApertura.setFocusable(false);
		this.add(txtFechaApertura);
		LocalDate fechaApertura = LocalDate.now();
		txtFechaApertura.setText(fechaApertura.toString());
		
		
		txtHoraApertura = new JTextField();
		txtHoraApertura.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 14));
		txtHoraApertura.setBackground(SystemColor.menu);
		txtHoraApertura.setColumns(10);
		txtHoraApertura.setBounds(611, 215, 365, 24);
		txtHoraApertura.setEditable(false);
		txtHoraApertura.setFocusable(false);
		this.add(txtHoraApertura);
		LocalTime horaApertura = LocalTime.now();
		txtHoraApertura.setText(horaApertura.toString());
		
		
		txtNumeroTicket = new JTextField();
		txtNumeroTicket.setEditable(false);
		txtNumeroTicket.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 14));
		txtNumeroTicket.setColumns(10);
		txtNumeroTicket.setBackground(SystemColor.menu);
		txtNumeroTicket.setBounds(611, 133, 365, 24);
		txtNumeroTicket.setFocusable(false);
		this.add(txtNumeroTicket);
		txtNumeroTicket.setText(obtenerNumeroTicketNuevo());
		
		txtNumeroLegajo = new JTextField();
		txtNumeroLegajo.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 14));
		txtNumeroLegajo.setColumns(10);
		txtNumeroLegajo.setBackground(Color.WHITE);
		txtNumeroLegajo.setBounds(611, 253, 365, 24);
		this.add(txtNumeroLegajo);
		
		txtNumeroLegajo.addFocusListener(new FocusListener() {
			String nombreEmpleado;
	        public void focusLost(FocusEvent arg0) { 
	        	if(txtNumeroLegajo.getText().isEmpty()) {
	    			errorLegajoExistente.setVisible(false);
	    			errorLegajoVacio.setVisible(true);
	    			nombre.setVisible(false);
	    		}
	        	else {
	    			errorLegajoVacio.setVisible(false);
	    		}
	        	if (!txtNumeroLegajo.getText().isEmpty()) {
	        		nombreEmpleado = ventana.getGestorEmpleado().validarLegajo(Integer.valueOf(txtNumeroLegajo.getText()));
	        		if (nombreEmpleado == null) {
	        			errorLegajoExistente.setVisible(true);
	        			nombre.setVisible(false);
	        			errorLegajoVacio.setVisible(false);
	        		}
	        		else {
	        			errorLegajoVacio.setVisible(false);
	        			errorLegajoExistente.setVisible(false);
	        			nombre.setText(nombreEmpleado);
	        			nombre.setVisible(true);
	        		}
	        	}
	        }  
			@Override
			public void focusGained(FocusEvent e) {
			}
		});
				
		
		JComboBox<ClasificacionTicket> comboBoxClasificacionTicket = new JComboBox<ClasificacionTicket>();
		comboBoxClasificacionTicket.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 14));
		comboBoxClasificacionTicket.setModel(new DefaultComboBoxModel<ClasificacionTicket>(clasificaciones));
		comboBoxClasificacionTicket.setBounds(611, 605, 365, 24);
		this.add(comboBoxClasificacionTicket);
		
	
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 16));
		btnCancelar.setBounds(1207, 655, 133, 37);
		this.add(btnCancelar);
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 16));
		btnAceptar.setBounds(1020, 655, 133, 37);
		this.add(btnAceptar);
		
		
		
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int dialogButton = JOptionPane.YES_NO_OPTION;
				int dialogResult = JOptionPane.showConfirmDialog (null, "Desea cancelar el registro del ticket? Los cambios no seran guardados.","Warning",dialogButton);
				if(dialogResult == JOptionPane.YES_OPTION){
					ventana.getGestorTicket().eliminarTicket(txtNumeroTicket.getText());
					ventana.setContentPane(new HomeMesaAyuda(ventana));
					ventana.pack();
				}
			}
		});
		
		
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			 if (textAreaDescripcion.getText().isEmpty() || (comboBoxClasificacionTicket.getSelectedIndex()==0)) {
					if (textAreaDescripcion.getText().isEmpty()) {
						errorDescripcionVacio.setVisible(true);
					}
					if (comboBoxClasificacionTicket.getSelectedIndex()==0) {
						errorDebeElegir.setVisible(true);
					}
			}
			else {
				ticketDTO = new TicketDTO(Integer.valueOf(txtNumeroTicket.getText()),Integer.valueOf(txtNumeroLegajo.getText()), (ClasificacionTicket) comboBoxClasificacionTicket.getSelectedItem(), textAreaDescripcion.getText(), fechaApertura, horaApertura, null, null);
				ventana.getGestorTicket().crearTicket(ticketDTO);
				ventana.setContentPane(new InterfazRegistrarTicket2(ventana,ticketDTO));
				ventana.pack();
			}
			}
		});
	}
	
	
	private ClasificacionTicket[] cargarClasificaciones() {
		ClasificacionTicket[] clasificaciones = new ClasificacionTicket[ventana.getGestorClasificacion().getClasificaciones().size()+1];
		for(int i=0; i < ventana.getGestorClasificacion().getClasificaciones().size(); i++) {
			clasificaciones[i+1] = ventana.getGestorClasificacion().getClasificaciones().get(i);
		}
		clasificaciones[0] = new ClasificacionTicket("Seleccione una opcion...");
		
		return ordenarVector(clasificaciones);
	}
	
	
	private ClasificacionTicket[] ordenarVector(ClasificacionTicket[] clasificaciones2) {
		ArrayList<ClasificacionTicket> aux = new ArrayList<>();
		for(int i = 1; i < clasificaciones2.length ;i++) {
			aux.add(clasificaciones2[i]);
		}
		
		aux.sort((c1,c2) -> c1.getNombre().compareTo(c2.getNombre()));
		
		for(int i = 1; i < clasificaciones2.length ;i++) {
			clasificaciones2[i] = aux.get(i-1);
		}
		return clasificaciones2;
	}


	private String obtenerNumeroTicketNuevo() {
		return ventana.getGestorTicket().getNext();
	}
}