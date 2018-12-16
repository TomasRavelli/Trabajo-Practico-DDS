package interfacesGraficas;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import infoDTO.TicketDTO;
import modelo.aplicacion.Principal;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.TextArea;
import javax.swing.JSeparator;


public class InterfazCerrarTicket extends JPanel {

	private Principal ventana;
	private JTextField textFieldTicket;
	private JTextField textFieldLegajo;
	private JTextField textFieldNuevoEstado;

	public InterfazCerrarTicket(Principal frame, TicketDTO ticketSeleccionado) {

		
		this.ventana=frame;
		ventana.setContentPane(this);
		this.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setPreferredSize(new Dimension(1366, 768));
		this.setBackground(new Color(230, 230, 250));
		this.setLayout(null);
		
		
		
		JSeparator separator = new JSeparator();
		separator.setForeground(Color.GRAY);
		separator.setBounds(275, 90, 800, 2);
		this.add(separator);
		
		TextArea textAreaObservaciones = new TextArea();
		textAreaObservaciones.setBounds(701, 420, 345, 160);
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
		this.add(textAreaObservaciones);
		
		
		JLabel lblCerrarTicket = new JLabel("Cerrar ticket\r\n");
		lblCerrarTicket.setFont(new Font("Segoe UI Symbol", Font.BOLD, 40));
		lblCerrarTicket.setToolTipText("");
		lblCerrarTicket.setBounds(547, 20, 255, 47);
		this.add(lblCerrarTicket);
		
		JLabel lblTicket = new JLabel("Ticket: ");
		lblTicket.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 20));
		lblTicket.setBounds(498, 180, 148, 26);
		this.add(lblTicket);
		
		JLabel lblLegajo = new JLabel("Legajo: ");
		lblLegajo.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 20));
		lblLegajo.setBounds(498, 260, 148, 30);
		this.add(lblLegajo);
		
		JLabel lblNuevoEstado = new JLabel("Nuevo estado: ");
		lblNuevoEstado.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 20));
		lblNuevoEstado.setBounds(498, 340, 148, 29);
		this.add(lblNuevoEstado);
		
		JLabel lblObservaciones = new JLabel("Observaciones: ");
		lblObservaciones.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 20));
		lblObservaciones.setBounds(498, 420, 148, 26);
		this.add(lblObservaciones);
		
		JLabel errorObsVacio = new JLabel("* Este campo no puede estar vacio.");
		errorObsVacio.setBounds(1083, 423, 241, 26);
		errorObsVacio.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 14));
		errorObsVacio.setForeground(Color.RED);
		errorObsVacio.setVisible(false);
		this.add(errorObsVacio);
		
		
		textFieldTicket = new JTextField();
		textFieldTicket.setEditable(false);
		textFieldTicket.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 14));
		textFieldTicket.setBounds(701, 184, 200, 24);
		textFieldTicket.setColumns(10);
		this.add(textFieldTicket);
		textFieldTicket.setText(ticketSeleccionado.getNumero().toString());
		
		textFieldLegajo = new JTextField();
		textFieldLegajo.setEditable(false);
		textFieldLegajo.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 14));
		textFieldLegajo.setBounds(701, 266, 200, 24);
		textFieldLegajo.setColumns(10);
		this.add(textFieldLegajo);
		textFieldLegajo.setText(ticketSeleccionado.getLegajo().toString());
		
		textFieldNuevoEstado = new JTextField();
		textFieldNuevoEstado.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 15));
		textFieldNuevoEstado.setBounds(701, 345, 200, 24);
		textFieldNuevoEstado.setColumns(10);
		textFieldNuevoEstado.setEditable(false);
		textFieldNuevoEstado.setText("Cerrado");
		this.add(textFieldNuevoEstado);
		
		
		
		JButton btnCerrarTicket = new JButton("Cerrar ticket");
		btnCerrarTicket.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 14));
		btnCerrarTicket.setBounds(1020, 650, 133, 37);
		this.add(btnCerrarTicket);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 14));
		btnCancelar.setBounds(1207, 650, 133, 37);
		this.add(btnCancelar);
		
		ventana.getGestorIntervencion().actualizarEstado(ticketSeleccionado.getNumero());
		
		
		btnCerrarTicket.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			if(textAreaObservaciones.getText().length()<=255) {
				if(textAreaObservaciones.getText().isEmpty()) {
					errorObsVacio.setVisible(true);
				}
				
				int dialogButton = JOptionPane.YES_NO_OPTION;
				int dialogResult = JOptionPane.showConfirmDialog (null, "Desea cerrar el ticket?","Warning",dialogButton);
				if(dialogResult == JOptionPane.YES_OPTION){
				  ventana.getGestorTicket().cerrarTicket(ticketSeleccionado.getNumero(), textAreaObservaciones.getText());
				  ventana.setContentPane(new InterfazConsultarTicket(ventana));
				  ventana.pack();
				}
			}
			else {
				JOptionPane p = new JOptionPane();
				p.showMessageDialog(ventana, "Revisar campos. Longitud no valida.", "Error", p.ERROR_MESSAGE);
				}
			}
		});
		
		
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int dialogButton = JOptionPane.YES_NO_OPTION;
				int dialogResult = JOptionPane.showConfirmDialog (null, "Desea cancelar el cierre del ticket? los cambios no seran guardados.","Warning",dialogButton);
				if(dialogResult == JOptionPane.YES_OPTION){
				  ventana.setContentPane(new HomeMesaAyuda(ventana));
				  ventana.pack();
				}
			}
		});
	}
}
