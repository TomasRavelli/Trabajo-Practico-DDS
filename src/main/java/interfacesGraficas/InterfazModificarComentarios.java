package interfacesGraficas;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import infoDTO.IntervencionResultadoDTO;
import modelo.aplicacion.Principal;

import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.JSeparator;
import javax.swing.JTextField;

public class InterfazModificarComentarios extends JPanel {

	private Principal ventana;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;

	public InterfazModificarComentarios(Principal frame, IntervencionResultadoDTO intervencion) {
		
		//ACTOR : GRUPO DE RESOLUCION
		//INGRESA INFO ADICIONAL O MODIFICA LO QUE YA ESTABA
		
		this.ventana=frame;
		ventana.setContentPane(this);
		this.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setPreferredSize(new Dimension(1366, 768));
		this.setBackground(new Color(230, 230, 250));
		this.setLayout(null);
		
		
		
		JSeparator separator = new JSeparator();
		separator.setForeground(Color.GRAY);
		separator.setBounds(274, 90, 800, 2);
		this.add(separator);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(455, 338, 525, 245);
		this.add(scrollPane);
		
		JTextArea textAreaObservaciones = new JTextArea();
		textAreaObservaciones.setBackground(new Color(220, 220, 220));
		textAreaObservaciones.setEditable(true);
		scrollPane.setColumnHeaderView(textAreaObservaciones);
		
		
		
		JLabel lblModificarComentarios = new JLabel("Modificar comentarios");
		lblModificarComentarios.setFont(new Font("Segoe UI Symbol", Font.BOLD, 40));
		lblModificarComentarios.setBounds(455, 20, 438, 45);
		this.add(lblModificarComentarios);
		
		JLabel lblObservaciones = new JLabel("Observaciones:");
		lblObservaciones.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 20));
		lblObservaciones.setBounds(274, 334, 143, 25);
		this.add(lblObservaciones);
		
		
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 14));
		btnAceptar.setBounds(1020, 655, 133, 37);
		this.add(btnAceptar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 14));
		btnCancelar.setBounds(1207, 655, 133, 37);
		this.add(btnCancelar);
		
		JLabel lblNumeroDeTicket = new JLabel("Numero de Ticket:");
		lblNumeroDeTicket.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 16));
		lblNumeroDeTicket.setBounds(274, 133, 143, 25);
		add(lblNumeroDeTicket);
		
		JLabel lblLegajo = new JLabel("Legajo:");
		lblLegajo.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 16));
		lblLegajo.setBounds(274, 194, 143, 25);
		add(lblLegajo);
		
		JLabel lblClasificacionTicket = new JLabel("Clasificacion ticket:");
		lblClasificacionTicket.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 16));
		lblClasificacionTicket.setBounds(274, 254, 143, 25);
		add(lblClasificacionTicket);
		
		textField = new JTextField();
		textField.setEditable(false);
		textField.setBounds(455, 138, 525, 20);
		add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setEditable(false);
		textField_1.setColumns(10);
		textField_1.setBounds(455, 194, 525, 20);
		add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setEditable(false);
		textField_2.setColumns(10);
		textField_2.setBounds(455, 259, 525, 20);
		add(textField_2);
		
		
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//PASA ALGO
			}
		});
	}
}