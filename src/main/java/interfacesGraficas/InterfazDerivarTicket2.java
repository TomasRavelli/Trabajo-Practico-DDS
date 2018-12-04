package interfacesGraficas;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import infoDTO.DerivarDTO;
import modelo.aplicacion.Principal;
import modelo.entidades.ClasificacionTicket;
import modelo.entidades.GrupoDeResolucion;
import modelo.entidades.Ticket;
import modelo.entidades.Usuario;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import java.awt.SystemColor;

public class InterfazDerivarTicket2 extends JPanel {

	private Principal ventana;
	private JTextField txtNumeroTicket;
	private JTextField txtLegajo;
	private JTextField txtEstadoActual;
	private JTextField txtNuevoEstado;
	private JTextField txtClasificacion;
	private GrupoDeResolucion[] grupos;
	
	public InterfazDerivarTicket2(Principal frame, DerivarDTO derivarDTO) {
		
		//SI EL GRUPO AL CUAL SE ESTA DERIVANDO EL TICKET TIENE UNA INTERVENCION EN ESPERA SE LLEVA A CABO UNA REASIGNACION DE DICHA INTERVENCION (NO SE CREA UNA NUEVA)
		//TODO FILTRAR GRUPOS EN EL COMBO BOX
		
		this.ventana=frame;
		ventana.setContentPane(this);
		this.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setPreferredSize(new Dimension(1366, 768));
		this.setBackground(new Color(230, 230, 250));
		this.setLayout(null);
		
		grupos = cargarGrupos(derivarDTO.getClasificacion());
		Ticket ticket = ventana.getGestorTicket().getTicket(Integer.valueOf(derivarDTO.getNumeroTicket()));
		
			
		JSeparator separator = new JSeparator();
		separator.setForeground(Color.GRAY);
		separator.setBounds(295, 80, 760, 2);
		this.add(separator);
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(692, 535, 252, 95);
		this.add(scrollPane);
		
		JTextArea txtAreaObservaciones = new JTextArea();
		scrollPane.setViewportView(txtAreaObservaciones);
		
		
		JScrollPane scrollPane2 = new JScrollPane();
		scrollPane2.setBounds(692, 280, 252, 95);
		this.add(scrollPane2);
		
		JTextArea txtDescripcion = new JTextArea();
		txtDescripcion.setBackground(SystemColor.controlHighlight);
		txtDescripcion.setEditable(false);
		txtDescripcion.setText(ticket.getDescripcion());
		scrollPane2.setViewportView(txtDescripcion);
		
		
		
		JLabel lblDerivarTicket = new JLabel("Derivar ticket");
		lblDerivarTicket.setFont(new Font("Segoe UI Symbol", Font.BOLD, 40));
		lblDerivarTicket.setBounds(535, 20, 280, 43);
		this.add(lblDerivarTicket);
		
		JLabel lblNroTicket = new JLabel("Numero Ticket: ");
		lblNroTicket.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 16));
		lblNroTicket.setBounds(465, 129, 149, 37);
		this.add(lblNroTicket);
		
		JLabel lblLegajo = new JLabel("Legajo: ");
		lblLegajo.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 16));
		lblLegajo.setBounds(465, 179, 100, 37);
		this.add(lblLegajo);
		
		JLabel lblEstadoActual = new JLabel("Estado actual: ");
		lblEstadoActual.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 16));
		lblEstadoActual.setBounds(465, 229, 149, 37);
		this.add(lblEstadoActual);
		
		JLabel lblDescripcion = new JLabel("Descripcion del problema: ");
		lblDescripcion.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 16));
		lblDescripcion.setBounds(465, 280, 187, 37);
		this.add(lblDescripcion);
		
		JLabel lblNuevoEstado = new JLabel("Nuevo estado: ");
		lblNuevoEstado.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 16));
		lblNuevoEstado.setBounds(465, 382, 149, 37);
		this.add(lblNuevoEstado);
		
		JLabel lblClasificacinTicket = new JLabel("Clasificacion del ticket: ");
		lblClasificacinTicket.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 16));
		lblClasificacinTicket.setBounds(465, 432, 170, 37);
		this.add(lblClasificacinTicket);
		
		JLabel lblGrupoResolucion = new JLabel("Grupo de resolucion: ");
		lblGrupoResolucion.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 16));
		lblGrupoResolucion.setBounds(465, 482, 149, 37);
		this.add(lblGrupoResolucion);
		
		JLabel lblObservaciones = new JLabel("Observaciones: ");
		lblObservaciones.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 16));
		lblObservaciones.setBounds(465, 532, 149, 30);
		this.add(lblObservaciones);
		
		
		JLabel errorGrupo = new JLabel("* Debe seleccionar una opcion.");
		errorGrupo.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 13));
		errorGrupo.setForeground(Color.RED);
		errorGrupo.setBounds(970, 492, 400, 20);
		errorGrupo.setVisible(false);
		this.add(errorGrupo);
		
		JLabel errorObsVacio = new JLabel("* Este campo no puede estar vacio.");
		errorObsVacio.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 13));
		errorObsVacio.setForeground(Color.RED);
		errorObsVacio.setBounds(970, 537, 400, 20);
		errorObsVacio.setVisible(false);
		this.add(errorObsVacio);
		
		
		txtNumeroTicket = new JTextField();
		txtNumeroTicket.setEditable(false);
		txtNumeroTicket.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 14));
		txtNumeroTicket.setBounds(692, 137, 252, 22);
		txtNumeroTicket.setColumns(10);
		txtNumeroTicket.setText(derivarDTO.getNumeroTicket().toString());
		this.add(txtNumeroTicket);
		
		
		txtLegajo = new JTextField();
		txtLegajo.setEditable(false);
		txtLegajo.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 14));
		txtLegajo.setColumns(10);
		txtLegajo.setBounds(692, 187, 252, 22);
		txtLegajo.setText(derivarDTO.getNumeroLegajo().toString());
		this.add(txtLegajo);
		
		txtEstadoActual = new JTextField();
		txtEstadoActual.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 14));
		txtEstadoActual.setEditable(false);
		txtEstadoActual.setBorder(new LineBorder(Color.gray));
		txtEstadoActual.setBackground(new Color(220, 220, 220));
		txtEstadoActual.setColumns(10);
		txtEstadoActual.setBounds(692, 237, 252, 22);
		txtEstadoActual.setText(ticket.getDuracionEstadoActual().getEstado().getNombre());
		this.add(txtEstadoActual);
		
		txtNuevoEstado = new JTextField();
		txtNuevoEstado.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 14));
		txtNuevoEstado.setEditable(false);
		txtNuevoEstado.setBackground(new Color(220, 220, 220));
		txtNuevoEstado.setBounds(692, 390, 252, 22);
		txtNuevoEstado.setText("Abierto derivado");
		txtNuevoEstado.setBorder(new LineBorder(Color.gray));
		txtNuevoEstado.setText("Abierto derivado");
		this.add(txtNuevoEstado);
		txtNuevoEstado.setColumns(10);
		
		txtClasificacion = new JTextField();
		txtClasificacion.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 14));
		txtClasificacion.setEditable(false);
		txtClasificacion.setBounds(692, 441, 252, 22);
		txtClasificacion.setText(derivarDTO.getClasificacion().toString());
		add(txtClasificacion);
		txtClasificacion.setColumns(10);
		

		
		JComboBox<GrupoDeResolucion> comboBoxGrupo = new JComboBox<GrupoDeResolucion>();
		comboBoxGrupo.setModel(new DefaultComboBoxModel<GrupoDeResolucion>(grupos));
		comboBoxGrupo.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 14));
		comboBoxGrupo.setBounds(692, 490, 252, 22);
		this.add(comboBoxGrupo);
		
		
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 14));
		btnCancelar.setBounds(1207, 650, 133, 37);
		this.add(btnCancelar);
		
		JButton btnDerivar = new JButton("Derivar ticket");
		btnDerivar.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 14));
		btnDerivar.setBounds(1020, 650, 133, 37);
		this.add(btnDerivar);
		

		btnDerivar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (txtAreaObservaciones.getText().isEmpty()) {
					errorObsVacio.setVisible(true);
				}
				else if(comboBoxGrupo.getSelectedIndex() == 0) {
					errorGrupo.setVisible(true);
				}
				else {
					
					ventana.getGestorTicket().derivarTicket(derivarDTO, (GrupoDeResolucion)comboBoxGrupo.getSelectedItem(), txtAreaObservaciones.getText());
					
					ventana.setContentPane(new HomeMesaAyuda(ventana));
					ventana.pack();
				}
			}
		});	
		
		
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//VUELVE
			}
		});
	}
	
	
	private GrupoDeResolucion[] cargarGrupos(ClasificacionTicket clasifTicket) {
		int n = 0;
		GrupoDeResolucion[] grupos = new GrupoDeResolucion[n];
		if(clasifTicket.getNombre().equalsIgnoreCase("Cambios de Configuracion de Sistema Operativo de PC") || clasifTicket.getNombre().equalsIgnoreCase("Problemas en el funcionamiento del SO de PC y utilitarios") ) {
			n = 4;
			grupos = new GrupoDeResolucion[n+1];
			grupos[1] = ventana.getGestorGrupo().getGrupo("'Mesa de Ayuda'");
			grupos[2] = ventana.getGestorGrupo().getGrupo("'Unidades de soporte'");
			grupos[3] = ventana.getGestorGrupo().getGrupo("'Administrador SUSE Linux'");
			grupos[4] = ventana.getGestorGrupo().getGrupo("'Administrador DEBIAN'");
		}
		
		else {
				if (clasifTicket.getNombre().equalsIgnoreCase("Solicitud de usuarios para Sistemas informaticos que utiliza la empresa")) {
					n = 7;
					grupos = new GrupoDeResolucion[n+1];
					grupos[1] = ventana.getGestorGrupo().getGrupo("'Mesa de Ayuda'");
					grupos[2] = ventana.getGestorGrupo().getGrupo("'Unidades de soporte'");
					grupos[3] = ventana.getGestorGrupo().getGrupo("'Administrador SUSE Linux'");
					grupos[4] = ventana.getGestorGrupo().getGrupo("'Administrador de Base de Datos'");			
					grupos[5] = ventana.getGestorGrupo().getGrupo("'Desarrollo Sistema Comercial'");
					grupos[6] = ventana.getGestorGrupo().getGrupo("'Desarrollo Sistema RRHH'");
					grupos[7] = ventana.getGestorGrupo().getGrupo("'Desarrollo Sistema de Reclamos'");
				}
				else {
					if(clasifTicket.getNombre().equalsIgnoreCase("Solicitud de Cambio de Contrasenias") || clasifTicket.getNombre().equalsIgnoreCase("Solicitud de instalacion de aplicaciones") || clasifTicket.getNombre().equalsIgnoreCase("Modificacion en los perfiles de usuarios")) {
						n = 7;
						grupos = new GrupoDeResolucion[n+1];
						grupos[1] = ventana.getGestorGrupo().getGrupo("'Mesa de Ayuda'");
						grupos[2] = ventana.getGestorGrupo().getGrupo("'Unidades de soporte'");
						grupos[3] = ventana.getGestorGrupo().getGrupo("'Administrador SUSE Linux'");
						grupos[4] = ventana.getGestorGrupo().getGrupo("'Administrador Proxy y correo electronico'");
						grupos[5] = ventana.getGestorGrupo().getGrupo("'Desarrollo Sistema Comercial'");
						grupos[6] = ventana.getGestorGrupo().getGrupo("'Desarrollo Sistema RRHH'");
						grupos[7] = ventana.getGestorGrupo().getGrupo("'Desarrollo Sistema de Reclamos'");
					}
				
				else {
					if(clasifTicket.getNombre().equalsIgnoreCase("Mal funcionamiento de Hardware")) {
						n =7;
						grupos = new GrupoDeResolucion[n+1];
						grupos[1] = ventana.getGestorGrupo().getGrupo("'Mesa de Ayuda'");
						grupos[2] = ventana.getGestorGrupo().getGrupo("'Unidades de soporte'");
						grupos[3] = ventana.getGestorGrupo().getGrupo("'Servicio tecnico'");
						grupos[4] = ventana.getGestorGrupo().getGrupo("'Administrador SUSE Linux'");
						grupos[5] = ventana.getGestorGrupo().getGrupo("'Administrador DEBIAN'");
						grupos[6] = ventana.getGestorGrupo().getGrupo("'Redes LAN'");
						grupos[7] = ventana.getGestorGrupo().getGrupo("'Comunicaciones'");
						
					}
					else {
						if(clasifTicket.getNombre().equalsIgnoreCase("Problemas en la autenticacion en los distintos sistemas")) {
							n = 6;
							grupos = new GrupoDeResolucion[n+1];
							grupos[1] = ventana.getGestorGrupo().getGrupo("'Mesa de Ayuda'");
							grupos[2] = ventana.getGestorGrupo().getGrupo("'Unidades de soporte'");
							grupos[3] = ventana.getGestorGrupo().getGrupo("'Administrador de Base de Datos'");							
							grupos[4] = ventana.getGestorGrupo().getGrupo("'Desarrollo Sistema Comercial'");
							grupos[5] = ventana.getGestorGrupo().getGrupo("'Desarrollo Sistema RRHH'");
							grupos[6] = ventana.getGestorGrupo().getGrupo("'Desarrollo Sistema de Reclamos'");
						}
						else {
							if(clasifTicket.getNombre().equalsIgnoreCase("Problemas de acceso a la red local o remota")){
								n = 6;
								grupos = new GrupoDeResolucion[n+1];
								grupos[1] = ventana.getGestorGrupo().getGrupo("'Mesa de Ayuda'");
								grupos[2] = ventana.getGestorGrupo().getGrupo("'Unidades de soporte'");
								grupos[3] = ventana.getGestorGrupo().getGrupo("'Servicio tecnico'");
								grupos[4] = ventana.getGestorGrupo().getGrupo("'Administrador Proxy y correo electronico'");
								grupos[5] = ventana.getGestorGrupo().getGrupo("'Redes LAN'");				
								grupos[5] = ventana.getGestorGrupo().getGrupo("'Comunicaciones'");		
							}
							else {
								if(clasifTicket.getNombre().equalsIgnoreCase("Solicitud de usuarios de red")){
									n = 4;
									grupos = new GrupoDeResolucion[n+1];
									grupos[1] = ventana.getGestorGrupo().getGrupo("'Mesa de Ayuda'");
									grupos[2] = ventana.getGestorGrupo().getGrupo("'Unidades de soporte'");
									grupos[3] = ventana.getGestorGrupo().getGrupo("'Administrador SUSE Linux'");
									grupos[4] = ventana.getGestorGrupo().getGrupo("'Administrador Proxy y correo electronico'");
								}
								else {
										if(clasifTicket.getNombre().equalsIgnoreCase("Problemas con el correo electronico")) {
											n = 3;
											grupos = new GrupoDeResolucion[n+1];
											grupos[1] = ventana.getGestorGrupo().getGrupo("'Mesa de Ayuda'");
											grupos[2] = ventana.getGestorGrupo().getGrupo("'Unidades de soporte'");
											grupos[3] = ventana.getGestorGrupo().getGrupo("'Administrador Proxy y correo electronico'");
										}
										else {
											if(clasifTicket.getNombre().equalsIgnoreCase("Solicitud de cuentas de correo electronico")) {
												n = 2;
												grupos[1] = ventana.getGestorGrupo().getGrupo("'Mesa de Ayuda'");
												grupos[2] = ventana.getGestorGrupo().getGrupo("'Administrador Proxy y correo electronico'");
											}
											else {
												if(clasifTicket.getNombre().equalsIgnoreCase("Solicitud de nuevos puestos de trabajo")) {
													n = 2;
													grupos = new GrupoDeResolucion[n+1];
													grupos[1] = ventana.getGestorGrupo().getGrupo("'Mesa de Ayuda'");
													grupos[2] = ventana.getGestorGrupo().getGrupo("'Unidades de soporte'");
												}
												else {
													if(clasifTicket.getNombre().equalsIgnoreCase("Solicitud de soporte en el uso de alguna aplicacion o sistema")) {
														n = 8;
														grupos = new GrupoDeResolucion[n+1];
														grupos[1] = ventana.getGestorGrupo().getGrupo("'Mesa de Ayuda'");
														grupos[2] = ventana.getGestorGrupo().getGrupo("'Unidades de soporte'");
														grupos[3] = ventana.getGestorGrupo().getGrupo("'Administrador de Base de Datos'");
														grupos[4] = ventana.getGestorGrupo().getGrupo("'Administrador SUSE Linux'");
														grupos[5] = ventana.getGestorGrupo().getGrupo("'Administrador Proxy y correo electronico'");
														grupos[6] = ventana.getGestorGrupo().getGrupo("'Desarrollo Sistema Comercial'");
														grupos[7] = ventana.getGestorGrupo().getGrupo("'Desarrollo Sistema RRHH'");
														grupos[8] = ventana.getGestorGrupo().getGrupo("'Desarrollo Sistema de Reclamos'");
													}
													
														else {
															 grupos = new GrupoDeResolucion[ventana.getGestorGrupo().getGrupos().size()+1];
																for(int i=0; i < ventana.getGestorGrupo().getGrupos().size(); i++) {
																	grupos[i+1] = ventana.getGestorGrupo().getGrupos().get(i);
																}																		
														}
													
														
													
														}
												}
											}
										}
									}
										
								}
								
							}
						}
					}
				}
		
		grupos[0] = new GrupoDeResolucion("Seleccione una opcion...");
		return grupos;
	}
}
