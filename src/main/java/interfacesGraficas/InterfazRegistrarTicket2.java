package interfacesGraficas;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import infoDTO.DerivarDTO;
import infoDTO.TicketDTO;
import modelo.aplicacion.Principal;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import java.awt.SystemColor;

public class InterfazRegistrarTicket2 extends JPanel {

	private Principal ventana;
	private JTextField txtNumeroTicket;
	private JTextField txtNumeroLegajo;
	private JTextField txtClasificacion;

	public InterfazRegistrarTicket2(Principal frame,TicketDTO ticketDTO) {

		this.ventana = frame;
		ventana.setContentPane(this);
		this.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setPreferredSize(new Dimension(1366, 768));
		this.setBackground(new Color(230, 230, 250));
		this.setLayout(null);
	
		JSeparator separator = new JSeparator();
		separator.setForeground(Color.GRAY);
		separator.setBounds(301, 90, 760, 2);
		this.add(separator);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(503, 295, 520, 280);
		this.add(scrollPane);
		
		JTextArea textAreaObservaciones = new JTextArea();
		textAreaObservaciones.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 18));
		scrollPane.setViewportView(textAreaObservaciones);
		textAreaObservaciones.addKeyListener(new KeyListener(){	 
			public void keyTyped(KeyEvent e){
				if (textAreaObservaciones.getText().length() >= 255)
			     e.consume();
			}
			public void keyPressed(KeyEvent arg0) {
			}
			public void keyReleased(KeyEvent arg0) {
			}
		});
		textAreaObservaciones.setLineWrap(true);
		
		JLabel errorObsVacio = new JLabel("* Este campo no puede estar vacio.");
		errorObsVacio.setForeground(Color.RED);
		errorObsVacio.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 14));
		errorObsVacio.setBounds(1057, 300, 219, 24);
		this.add(errorObsVacio);
		errorObsVacio.setVisible(false);
		
		JLabel lblRegistrarTicket = new JLabel("Registrar ticket");
		lblRegistrarTicket.setFont(new Font("Segoe UI Symbol", Font.BOLD, 40));
		lblRegistrarTicket.setBounds(532, 20, 298, 54);
		this.add(lblRegistrarTicket);
		
		JLabel lblObservaciones = new JLabel("Observaciones:");
		lblObservaciones.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 20));
		lblObservaciones.setBounds(319, 288, 172, 27);
		this.add(lblObservaciones);
		
		JLabel lblNumeroTicket = new JLabel("Numero de ticket:");
		lblNumeroTicket.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 20));
		lblNumeroTicket.setBounds(319, 152, 175, 27);
		this.add(lblNumeroTicket);
		
		JLabel lblNumeroLegajo = new JLabel("Numero de legajo:");
		lblNumeroLegajo.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 20));
		lblNumeroLegajo.setBounds(319, 192, 176, 27);
		this.add(lblNumeroLegajo);
		
		JLabel lblClasificacion = new JLabel("Clasificacion ticket:");
		lblClasificacion.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 20));
		lblClasificacion.setBounds(319, 232, 172, 27);
		add(lblClasificacion);
				
		txtNumeroTicket = new JTextField();
		txtNumeroTicket.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 18));
		txtNumeroTicket.setBackground(SystemColor.menu);
		txtNumeroTicket.setBounds(503, 154, 520, 25);
		txtNumeroTicket.setColumns(10);
		txtNumeroTicket.setEditable(false);
		this.add(txtNumeroTicket);
		txtNumeroTicket.setText(ticketDTO.getNumero().toString());
		
		txtNumeroLegajo = new JTextField();
		txtNumeroLegajo.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 18));
		txtNumeroLegajo.setColumns(10);
		txtNumeroLegajo.setBackground(SystemColor.menu);
		txtNumeroLegajo.setBounds(503, 194, 520, 25);
		txtNumeroLegajo.setEditable(false);
		this.add(txtNumeroLegajo);
		txtNumeroLegajo.setText(ticketDTO.getLegajo().toString());
		
		txtClasificacion = new JTextField();
		txtClasificacion.setBackground(SystemColor.menu);
		txtClasificacion.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 18));
		txtClasificacion.setEditable(false);
		txtClasificacion.setBounds(503, 234, 520, 25);
		add(txtClasificacion);
		txtClasificacion.setColumns(10);
		txtClasificacion.setText(ticketDTO.getClasificacion().toString());
			
		JButton btnDerivarTicket = new JButton("Derivar ticket");
		btnDerivarTicket.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 16));
		btnDerivarTicket.setBounds(1020, 655, 133, 37);
		this.add(btnDerivarTicket);
		
		JButton btnCerrarTicket = new JButton("Cerrar ticket");
		btnCerrarTicket.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 16));
		btnCerrarTicket.setBounds(1207, 655, 133, 37);
		this.add(btnCerrarTicket);
		
		
		btnDerivarTicket.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			if(textAreaObservaciones.getText().length() <= 255) {	
				if (textAreaObservaciones.getText().isEmpty()) {
					errorObsVacio.setVisible(true);
				}
				else {
					errorObsVacio.setVisible(false);
					int dialogButton = JOptionPane.YES_NO_OPTION;
					int dialogResult = JOptionPane.showConfirmDialog (null, "¿Desea derivar el ticket a otro Grupo de Resolucion?","Warning",dialogButton);
					if(dialogResult == JOptionPane.YES_OPTION){
						ventana.setContentPane(new InterfazDerivarTicket1(ventana, new DerivarDTO(Integer.valueOf(txtNumeroTicket.getText()), Integer.valueOf(txtNumeroLegajo.getText()), ticketDTO.getClasificacion(), textAreaObservaciones.getText())));
						ventana.pack();
					}
				}
			}
			else{
				JOptionPane p = new JOptionPane();
				p.showMessageDialog(ventana, "Revisar campos. Longitud no valida.", "Error", p.ERROR_MESSAGE);
			}}
		});
		

		btnCerrarTicket.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
			if(textAreaObservaciones.getText().length() <= 255) {
				
				if (textAreaObservaciones.getText().isEmpty()) {
					errorObsVacio.setVisible(true);
				}
				else {
					errorObsVacio.setVisible(false);
					ventana.getGestorTicket().cerrarTicket(Integer.valueOf(txtNumeroTicket.getText()),textAreaObservaciones.getText());
					ventana.setContentPane(new HomeMesaAyuda(ventana));
					ventana.pack();
				}
			}
			
			else {
				JOptionPane p = new JOptionPane();
				p.showMessageDialog(ventana, "Revisar campos. Longitud no valida.", "Error", p.ERROR_MESSAGE);
				}
			}
		});	
	}
}
