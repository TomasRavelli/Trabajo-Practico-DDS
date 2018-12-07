package interfacesGraficas;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import infoDTO.IntervencionResultadoDTO;
import modelo.aplicacion.Principal;
import modelo.entidades.ClasificacionTicket;
import modelo.entidades.Intervencion;
import modelo.entidades.Ticket;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JSeparator;
import java.awt.SystemColor;

public class InterfazActualizarEstadoIntervencion extends JPanel {

	private Principal ventana;
	private JTextField txtEstadoActual;
	private ClasificacionTicket[] clasificaciones;


	public InterfazActualizarEstadoIntervencion(Principal frame, IntervencionResultadoDTO intervencion) {

		this.ventana=frame;
		ventana.setContentPane(this);
		this.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setPreferredSize(new Dimension(1366, 768));
		this.setBackground(new Color(230, 230, 250));
		this.setLayout(null);
		
		clasificaciones = cargarClasificaciones(intervencion);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(Color.GRAY);
		separator.setBounds(274, 90, 800, 2);
		this.add(separator);
		
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(666, 246, 266, 80);
		this.add(scrollPane);
		
		JScrollPane scrollPane2 = new JScrollPane();
		scrollPane2.setBounds(666, 502, 266, 80);
		this.add(scrollPane2);
		
		
		Ticket ticket = ventana.getGestorTicket().getTicket(intervencion.getNumeroTicket());
		JTextArea textAreaDescripcion = new JTextArea();
		textAreaDescripcion.setEditable(false);
		textAreaDescripcion.setBackground(SystemColor.controlHighlight);
		scrollPane.setViewportView(textAreaDescripcion);
		textAreaDescripcion.setText(ticket.getDescripcion());
		
		
		
		JTextArea textAreaObservaciones = new JTextArea();
		textAreaObservaciones.setBackground(SystemColor.text);
		textAreaObservaciones.setEditable(true);
		scrollPane2.setViewportView(textAreaObservaciones);
		
		
		JLabel lblActualizarEstadoIntervencion = new JLabel("Actualizar estado intervencion");
		lblActualizarEstadoIntervencion.setFont(new Font("Segoe UI Symbol", Font.BOLD, 40));
		lblActualizarEstadoIntervencion.setBounds(377, 20, 593, 43);
		this.add(lblActualizarEstadoIntervencion);
		
		JLabel lblEstadoActual = new JLabel("Estado actual:");
		lblEstadoActual.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 18));
		lblEstadoActual.setBounds(446, 165, 208, 21);
		this.add(lblEstadoActual);
		
		JLabel lblDescripcionProblema = new JLabel("Descripcion del problema:");
		lblDescripcionProblema.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 18));
		lblDescripcionProblema.setBounds(446, 244, 208, 21);
		this.add(lblDescripcionProblema);
		
		JLabel lblNuevoEstado = new JLabel("Nuevo estado:");
		lblNuevoEstado.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 18));
		lblNuevoEstado.setBounds(446, 348, 208, 21);
		this.add(lblNuevoEstado);
		
		JLabel lblClasificacionTicket = new JLabel("Clasificacion del ticket:");
		lblClasificacionTicket.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 18));
		lblClasificacionTicket.setBounds(446, 424, 208, 21);
		this.add(lblClasificacionTicket);
		
		JLabel lblObservaciones = new JLabel("Observaciones:");
		lblObservaciones.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 18));
		lblObservaciones.setBounds(446, 500, 208, 21);
		this.add(lblObservaciones);
		
		JLabel errorEstadoVacio = new JLabel("* Debe seleccionar una opcion.");
		errorEstadoVacio.setBackground(new Color(240, 240, 240));
		errorEstadoVacio.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 15));
		errorEstadoVacio.setForeground(Color.RED);
		errorEstadoVacio.setBounds(970, 346, 400, 20);
		errorEstadoVacio.setVisible(false);
		this.add(errorEstadoVacio);
		
		JLabel errorObservacionesVacio = new JLabel("* Este campo no puede estar vacio.");
		errorObservacionesVacio.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 15));
		errorObservacionesVacio.setForeground(Color.RED);
		errorObservacionesVacio.setBounds(970, 500, 400, 20);
		errorObservacionesVacio.setVisible(false);
		this.add(errorObservacionesVacio);
		
		
		txtEstadoActual = new JTextField();
		txtEstadoActual.setBounds(666, 166, 266, 25);
		txtEstadoActual.setColumns(10);
		txtEstadoActual.setEditable(false);
		this.add(txtEstadoActual);
		txtEstadoActual.setText(intervencion.getEstadoIntervencion());
		
		
		
		JComboBox<String> comboBoxNuevoEstado = new JComboBox<String>();
		comboBoxNuevoEstado.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 15));
		comboBoxNuevoEstado.setModel(new DefaultComboBoxModel<String>(new String[] {"Seleccione una opcion...", "Asignada", "En espera", "Terminada", "Trabajando"}));
		comboBoxNuevoEstado.setBounds(666, 347, 266, 25);
		this.add(comboBoxNuevoEstado);
		
		JComboBox<ClasificacionTicket> comboBoxClasificacion = new JComboBox<ClasificacionTicket>();
		comboBoxClasificacion.setBackground(SystemColor.text);
		comboBoxClasificacion.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 14));
		comboBoxClasificacion.setModel(new DefaultComboBoxModel<ClasificacionTicket>(clasificaciones));
		comboBoxClasificacion.setBounds(666, 424, 266, 24);
		this.add(comboBoxClasificacion);
		
		
		
		JButton btnActualizar = new JButton("Actualizar");
		btnActualizar.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 14));
		btnActualizar.setBounds(1020, 655, 133, 37);
		this.add(btnActualizar);
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 14));
		btnSalir.setBounds(1207, 655, 133, 37);
		this.add(btnSalir);
		
		
		
		btnActualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (comboBoxNuevoEstado.getSelectedIndex() == 0){
					errorEstadoVacio.setVisible(true);
				}
				else if (textAreaObservaciones.getText().isEmpty()) {
					errorObservacionesVacio.setVisible(true);
				}
				else {
					errorObservacionesVacio.setVisible(false);
					errorEstadoVacio.setVisible(false);
					
					int dialogButton = JOptionPane.YES_NO_OPTION;
					int dialogResult = JOptionPane.showConfirmDialog (null, "¿Esta terminando la intervencion por una asignacion incorrecta?","Warning",dialogButton);
					if(dialogResult == JOptionPane.YES_OPTION){
						ventana.getGestorTicket().actualizarEstadoIntervencion(intervencion, comboBoxNuevoEstado.getSelectedItem().toString(), textAreaObservaciones.getText(), true, (ClasificacionTicket)comboBoxClasificacion.getSelectedItem());
					}
					else {
						ventana.getGestorTicket().actualizarEstadoIntervencion(intervencion, comboBoxNuevoEstado.getSelectedItem().toString(), textAreaObservaciones.getText(), false, (ClasificacionTicket)comboBoxClasificacion.getSelectedItem());					
					}
					
					ventana.setContentPane(new InterfazConsultarIntervenciones(ventana));
					ventana.pack();
				}
			}
		});
		
		
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int dialogButton = JOptionPane.YES_NO_OPTION;
				int dialogResult = JOptionPane.showConfirmDialog (null, "Desea cancelar la actualizacion del estado de la intervencion? Los cambios no seran guardados.","Warning",dialogButton);
				if(dialogResult == JOptionPane.YES_OPTION){
					ventana.setContentPane(new HomeGrupoResolucion(ventana));
					ventana.pack();
				}
			}
		});	
	}
	
	
	private ClasificacionTicket[] cargarClasificaciones(IntervencionResultadoDTO intervencion) {
		List<ClasificacionTicket> clasificacionesGrupo = ventana.getGestorClasificacion().getClasificaciones(intervencion.getGrupo());
		clasificaciones = new ClasificacionTicket[clasificacionesGrupo.size()];
		List<ClasificacionTicket> auxiliar = new ArrayList<>();		
		
		for (ClasificacionTicket ct : clasificacionesGrupo) {
			if (!ct.getNombre().equalsIgnoreCase(intervencion.getClasificacion())) {
				auxiliar.add(ct);
			}
		}
		
		clasificaciones[0] = new ClasificacionTicket(intervencion.getClasificacion());
		for(int i=0; i < auxiliar.size(); i++) {
			clasificaciones[i+1] = auxiliar.get(i);
		}
		
		return clasificaciones;
	}
}
