package interfacesGraficas;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import infoDTO.DatosDTO;
import infoDTO.DerivarDTO;
import infoDTO.TicketDTO;
import modelo.aplicacion.Principal;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JSeparator;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;


public class InterfazConsultarTicket extends JPanel {
	
	private Principal ventana;
	private JTable table_1;
	private JTextField txtNumeroTicket;
	private JTextField txtNumeroLegajo;
	DefaultTableModel modeloTablaTicket;
	List<TicketDTO> ticketsEncontrados;
	TicketDTO ticketSeleccionado;

	public InterfazConsultarTicket(Principal frame) {

		
		this.ventana=frame;
		ventana.setContentPane(this);
		this.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setPreferredSize(new Dimension(1366, 768));
		this.setBackground(new Color(230, 230, 250));
		this.setLayout(null);
		
		
		
		JSeparator separator = new JSeparator();
		separator.setBounds(275, 90, 800, 2);
		this.add(separator);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(122, 280, 1059, 314);
		this.add(scrollPane);
		
		
		JLabel lblConsultarTickets = new JLabel("Consultar ticket");
		lblConsultarTickets.setFont(new Font("Segoe UI Symbol", Font.BOLD, 40));
		lblConsultarTickets.setBounds(514, 20, 319, 46);
		this.add(lblConsultarTickets);
		
		JLabel lblCriterios = new JLabel("Criterios de busqueda:");
		lblCriterios.setFont(new Font("Segoe UI Symbol", Font.BOLD, 20));
		lblCriterios.setBounds(36, 97, 261, 26);
		this.add(lblCriterios);
		
		JLabel lblNumeroTicket = new JLabel("-Numero de ticket: ");
		lblNumeroTicket.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 15));
		lblNumeroTicket.setBounds(46, 128, 134, 21);
		this.add(lblNumeroTicket);
		
		JLabel lblNumeroLegajo = new JLabel("-Numero de legajo:");
		lblNumeroLegajo.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 15));
		lblNumeroLegajo.setBounds(46, 153, 135, 21);
		this.add(lblNumeroLegajo);
		
		JLabel lblClasificacionActual = new JLabel("-Clasificacion actual del ticket:  ");
		lblClasificacionActual.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 15));
		lblClasificacionActual.setBounds(402, 128, 209, 21);
		this.add(lblClasificacionActual);
		
		JLabel lblEstadoActual = new JLabel("-Estado actual del ticket: ");
		lblEstadoActual.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 15));
		lblEstadoActual.setBounds(402, 153, 167, 21);
		this.add(lblEstadoActual);
		
		JLabel lblUltimoGrupo = new JLabel("-Utimo grupo de resolucion asignado: ");
		lblUltimoGrupo.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 15));
		lblUltimoGrupo.setBounds(870, 128, 261, 21);
		this.add(lblUltimoGrupo);
		
		JLabel lblFechaUltimoCambio = new JLabel("-Fecha ultimo cambio de estado: ");
		lblFechaUltimoCambio.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 15));
		lblFechaUltimoCambio.setBounds(870, 153, 222, 21);
		this.add(lblFechaUltimoCambio);
		
		JLabel label_13 = new JLabel("/");
		label_13.setBounds(1189, 158, 13, 15);
		this.add(label_13);
		
		JLabel label_17 = new JLabel("/");
		label_17.setBounds(244, 183, 8, 15);
		this.add(label_17);
		
		JLabel label_18 = new JLabel("/");
		label_18.setBounds(306, 183, 13, 14);
		this.add(label_18);
		
		JLabel label_14 = new JLabel("/");
		label_14.setBounds(1252, 158, 13, 14);
		this.add(label_14);
		
		JLabel lblFechaApertura = new JLabel("-Fecha Apertura: ");
		lblFechaApertura.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 15));
		lblFechaApertura.setBounds(46, 178, 119, 21);
		this.add(lblFechaApertura);
		
		modeloTablaTicket = new DefaultTableModel(
				new Object[][] {
					
				},
				new String[] {"Ticket", "Legajo", "Fecha apertura", "Hora apertura", "Operador", "Clasificacion actual", "Estado actual", "Ultimo cambio estado"}
			);
		
		
		table_1 = new JTable();
		table_1.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 11));
		table_1.setModel(modeloTablaTicket);
		scrollPane.setViewportView(table_1);
		
		
		txtNumeroTicket = new JTextField();
		txtNumeroTicket.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 14));
		txtNumeroTicket.setColumns(10);
		txtNumeroTicket.setBounds(190, 131, 185, 20);
		this.add(txtNumeroTicket);
		
		txtNumeroLegajo = new JTextField();
		txtNumeroLegajo.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 14));
		txtNumeroLegajo.setColumns(10);
		txtNumeroLegajo.setBounds(190, 156, 185, 20);
		this.add(txtNumeroLegajo);
		
		
		JComboBox<String> comboBoxClasificacionActual = new JComboBox<String>();
		comboBoxClasificacionActual.setModel(new DefaultComboBoxModel<String>(new String[] {"Todas", "Configuracion de Sistema Operativo", "Mal funcionamiento de Hardware", "Modificaci\u00F3n en los perfiles de usuarios", "Problemas con el correo electr\u00F3nico", "Problemas de acceso a la red local o remota", "Problemas en el funcionamiento del Sistema Operativo", "Problemas en la autenticaci\u00F3n", "Problemas en los sistemas de la empresa", "Solicitud de cambio de contrase\u00F1as", "Solicitud de instalaci\u00F3n de aplicaciones", "Solicitud de nuevos puestos de trabajo", "Solicitud de usuarios de red", "Solicitud de usuarios de Sistemas informaticos", "Solicitud soporte en el uso de alguna aplicaci\u00F3n o sistema", "Otros"}));
		comboBoxClasificacionActual.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 14));
		comboBoxClasificacionActual.setEditable(true);
		comboBoxClasificacionActual.setBounds(611, 129, 230, 20);
		this.add(comboBoxClasificacionActual);
		
		JComboBox<String> comboBoxEstadoActual = new JComboBox<String>();
		comboBoxEstadoActual.setModel(new DefaultComboBoxModel(new String[] {"Abierto en Mesa de Ayuda", "Abierto derivado", "Cerrado", "Solucionado en la espera de OK", "Todos"}));
		comboBoxEstadoActual.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 14));
		comboBoxEstadoActual.setEditable(true);
		comboBoxEstadoActual.setBounds(611, 156, 230, 20);
		this.add(comboBoxEstadoActual);
		
		JComboBox<String> comboBoxUltimoGrupo = new JComboBox<String>();
		comboBoxUltimoGrupo.setModel(new DefaultComboBoxModel<String>(new String[] {"Todos", "Administrador DEBIAN", "Administrador de Base de Datos", "Administrador LAN", "Administrador Proxy y correo electr\u00F3nico", "Administrador SUSE Linux", "Comunicaciones", "Desarrollo Sistema Comercial", "Desarrollo Sistema de Reclamos", "Desarrollo Sistema RRHH", "Mesa de ayuda", "Servicio t\u00E9cnico", "Unidades de soporte"}));
		comboBoxUltimoGrupo.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 14));
		comboBoxUltimoGrupo.setEditable(true);
		comboBoxUltimoGrupo.setBounds(1135, 129, 185, 20);
		this.add(comboBoxUltimoGrupo);
		
		JComboBox<String> comboBoxDiaCambio = new JComboBox<String>();
		comboBoxDiaCambio.setModel(new DefaultComboBoxModel<String>(new String[] {"DD", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"}));
		comboBoxDiaCambio.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 14));
		comboBoxDiaCambio.setEditable(true);
		comboBoxDiaCambio.setBounds(1135, 155, 52, 22);
		this.add(comboBoxDiaCambio);
		
		JComboBox<String> comboBoxMesCambio = new JComboBox<String>();
		comboBoxMesCambio.setModel(new DefaultComboBoxModel<String>(new String[] {"MM", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"}));
		comboBoxMesCambio.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 14));
		comboBoxMesCambio.setEditable(true);
		comboBoxMesCambio.setBounds(1197, 155, 52, 22);
		this.add(comboBoxMesCambio);
		
		JComboBox<String> comboBoxAnioCambio = new JComboBox<String>();
		comboBoxAnioCambio.setModel(new DefaultComboBoxModel<String>(new String[] {"AAAA", "2018", "2019", "2020", "2021", "2022", "2023", "2024", "2025", "2026", "2017", "2028"}));
		comboBoxAnioCambio.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 14));
		comboBoxAnioCambio.setEditable(true);
		comboBoxAnioCambio.setBounds(1258, 155, 62, 22);
		this.add(comboBoxAnioCambio);
		
		JComboBox<String> comboBoxDiaApertura = new JComboBox<String>();
		comboBoxDiaApertura.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 14));
		comboBoxDiaApertura.setModel(new DefaultComboBoxModel<String>(new String[] {"DD", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"}));
		comboBoxDiaApertura.setEditable(true);
		comboBoxDiaApertura.setBounds(190, 179, 52, 22);
		this.add(comboBoxDiaApertura);
		
		JComboBox<String> comboBoxMesApertura = new JComboBox<String>();
		comboBoxMesApertura.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 14));
		comboBoxMesApertura.setModel(new DefaultComboBoxModel<String>(new String[] {"MM", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"}));
		comboBoxMesApertura.setEditable(true);
		comboBoxMesApertura.setBounds(252, 179, 52, 22);
		this.add(comboBoxMesApertura);
		
		JComboBox<String> comboBoxAnioApertura = new JComboBox<String>();
		comboBoxAnioApertura.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 14));
		comboBoxAnioApertura.setModel(new DefaultComboBoxModel<String>(new String[] {"AAAA", "2018", "2019", "2020", "2021", "2022", "2023", "2024", "2025", "2026", "2017", "2028"}));
		comboBoxAnioApertura.setEditable(true);
		comboBoxAnioApertura.setBounds(313, 179, 62, 22);
		this.add(comboBoxAnioApertura);
		
		
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 14));
		btnCancelar.setBounds(1207, 650, 133, 37);
		this.add(btnCancelar);
		
		JButton btnImprimir = new JButton("Imprimir");
		btnImprimir.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 14));
		btnImprimir.setBounds(455, 650, 133, 37);
		this.add(btnImprimir);
		
		JButton btnDerivar = new JButton("Derivar ticket");
		btnDerivar.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 14));
		btnDerivar.setBounds(1020, 650, 133, 37);
		this.add(btnDerivar);
		
		JButton btnCerrarTicket = new JButton("Cerrar ticket");
		btnCerrarTicket.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 14));
		btnCerrarTicket.setBounds(833, 650, 133, 37);
		this.add(btnCerrarTicket);
		
		JButton btnVetTicket = new JButton("Ver ticket");
		btnVetTicket.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 14));
		btnVetTicket.setBounds(646, 650, 133, 37);
		this.add(btnVetTicket);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 14));
		btnBuscar.setBounds(1119, 218, 133, 37);
		this.add(btnBuscar);
		

		/*btnImprimir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(table_1.getSelectedRow() != -1 || table_1.getSelectedRow() < ticketsEncontrados.size()) {
					ventana.setContentPane(new InterfazConfigurarReporte(ventana, ticketsEncontrados));
					ventana.pack();
			}}
		});*/
		
		
		
		btnCerrarTicket.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(table_1.getSelectedRow() != -1 || table_1.getSelectedRow() < ticketsEncontrados.size()) {

					Integer numeroTicketSeleccionado = Integer.valueOf(((Vector) modeloTablaTicket.getDataVector().elementAt(table_1.getSelectedRow())).elementAt(0).toString());
					ticketSeleccionado = buscarTicket(numeroTicketSeleccionado, ticketsEncontrados);
				
					if(ticketSeleccionado.getEstado().getNombre().equalsIgnoreCase("Solucionado a la espera OK")) {
						ventana.setContentPane(new InterfazCerrarTicket(ventana, ticketSeleccionado));
						ventana.pack();
					}
					else {
						JOptionPane.showMessageDialog(null, "ESTADO de ticket no permitido para cerrar ticket.");
					}
				}
			}

		});
		
		
		btnVetTicket.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(table_1.getSelectedRow() != -1 || table_1.getSelectedRow() < ticketsEncontrados.size()) {
					Integer numeroTicketSeleccionado = Integer.valueOf(((Vector) modeloTablaTicket.getDataVector().elementAt(table_1.getSelectedRow())).elementAt(0).toString());
					ticketSeleccionado = buscarTicket(numeroTicketSeleccionado, ticketsEncontrados);
					ventana.setContentPane(new InterfazVisualizacionTicket(ventana, ticketSeleccionado.getNumero()));
					ventana.pack();
				}
			}
		});
		
		
		btnDerivar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(table_1.getSelectedRow() != -1 && table_1.getSelectedRow() < ticketsEncontrados.size()) {
					Integer numeroTicketSeleccionado = Integer.valueOf(((Vector) modeloTablaTicket.getDataVector().elementAt(table_1.getSelectedRow())).elementAt(0).toString());
					ticketSeleccionado = buscarTicket(numeroTicketSeleccionado, ticketsEncontrados);

					
					if(ticketSeleccionado.getEstado().getNombre().equalsIgnoreCase("Solucionado a la espera OK") || ticketSeleccionado.getEstado().getNombre().equalsIgnoreCase("Abierto en Mesa de Ayuda")) {
						DerivarDTO derivarDTO1 = new DerivarDTO(ticketSeleccionado.getNumero(),ticketSeleccionado.getLegajo(),ticketSeleccionado.getClasificacion());
						ventana.setContentPane(new InterfazDerivarTicket2(ventana, derivarDTO1));
						ventana.pack();

					}
					else {
						JOptionPane.showMessageDialog(null, "ESTADO de ticket no permitido para derivar ticket.");
					}
				}
				
			}
		});
		
		
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				modeloTablaTicket.setRowCount(0);;
				DatosDTO datosDTO = new DatosDTO();
				
				if(!txtNumeroTicket.getText().isEmpty()) {
					datosDTO.setNumeroTicket(Integer.valueOf(txtNumeroTicket.getText()));
				}
				
				if(!txtNumeroLegajo.getText().isEmpty()) {
					datosDTO.setNumeroLegajo(Integer.valueOf(txtNumeroLegajo.getText()));
				}
				
				datosDTO.setClasificacion(comboBoxClasificacionActual.getSelectedItem().toString());
				datosDTO.setEstado(comboBoxEstadoActual.getSelectedItem().toString());
				datosDTO.setGrupo(comboBoxUltimoGrupo.getSelectedItem().toString());
				
				
				if(!comboBoxDiaApertura.getSelectedItem().toString().equals("DD") && !comboBoxMesApertura.getSelectedItem().toString().equals("MM") && !comboBoxAnioApertura.getSelectedItem().toString().equals("AAAA") && !comboBoxDiaCambio.getSelectedItem().toString().equals("DD") && !comboBoxMesCambio.getSelectedItem().toString().equals("MM") && !comboBoxAnioCambio.getSelectedItem().toString().equals("AAAA")) {
					
					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
					LocalDate fechaApertura = LocalDate.parse(new String(comboBoxDiaApertura.getSelectedItem().toString() + "/" + comboBoxMesApertura.getSelectedItem().toString() + "/" + comboBoxAnioApertura.getSelectedItem().toString()), formatter);
					LocalDate fechaUltimoCambioEstado = LocalDate.parse(new String(comboBoxDiaCambio.getSelectedItem().toString() + "/" + comboBoxMesCambio.getSelectedItem().toString() + "/" + comboBoxAnioCambio.getSelectedItem().toString()), formatter);
					
					if(fechaApertura.isAfter(LocalDate.now()) || fechaUltimoCambioEstado.isAfter(LocalDate.now())) {

						JOptionPane.showMessageDialog(null, "Fecha(s) no valida(s).");
					}
					
					else {
						datosDTO.setFechaApertura(fechaApertura);
						datosDTO.setFechaUltimoCambioEstado(fechaUltimoCambioEstado);
						ticketsEncontrados = ventana.getGestorTicket().getTickets(datosDTO);
					}
				}
				
				else {
					 ticketsEncontrados = ventana.getGestorTicket().getTickets(datosDTO);
				}

				
				if(ticketsEncontrados.size() > 0) {
					cargarTabla(ticketsEncontrados);
				}
				
				else {
					JOptionPane.showMessageDialog(null, "No existen tickets que cumplan con los criterios ingresados.");
				}

			}
		});
		
		
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ventana.setContentPane(new HomeMesaAyuda(ventana));
				ventana.pack();
			}
		});		
	}
	
	
	private TicketDTO buscarTicket (Integer numeroTicketSeleccionado, List<TicketDTO> ticketsEncontrados) {
		TicketDTO aux = new TicketDTO();
		for(TicketDTO t: ticketsEncontrados) {
			if(t.getNumero() == numeroTicketSeleccionado) {
				 aux = t;
			}
		}
		return aux;
	}


	private void cargarTabla(List<TicketDTO> ticketsEncontrados) {
		System.out.println(ticketsEncontrados);
		for(TicketDTO t: ticketsEncontrados) {
			modeloTablaTicket.addRow(new String[]{t.getNumero().toString(), t.getLegajo().toString(),t.getFechaApertura().toString(), t.getHoraApertura().toString(), t.getUsuario().getNombre(), t.getClasificacion().toString(), t.getEstado().getNombre(), t.getFechaUltimoCambioEstado().toString()});
		}
	}

	
}