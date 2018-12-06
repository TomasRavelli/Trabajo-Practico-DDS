package interfacesGraficas;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import infoDTO.DerivarDTO;
import modelo.aplicacion.Principal;
import modelo.entidades.ClasificacionTicket;
import modelo.entidades.GrupoDeResolucion;

import javax.swing.JSeparator;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;

public class InterfazDerivarTicket1 extends JPanel {

	private Principal ventana;
	private JTextField txtNumeroDeTicket;
	private JTextField txtNumeroDeLegajo;
	private JTextField txtClasificacionDeTicket;
	private GrupoDeResolucion[] grupos;
	

	public InterfazDerivarTicket1(Principal frame, DerivarDTO derivarDTO) {
		
		
		this.ventana=frame;
		ventana.setContentPane(this);
		this.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setPreferredSize(new Dimension(1366, 768));
		this.setBackground(new Color(230, 230, 250));
		this.setLayout(null);
		
		grupos = cargarGrupos(derivarDTO.getClasificacion());
		
		JSeparator separator = new JSeparator();
		separator.setForeground(Color.GRAY);
		separator.setBounds(301, 90, 760, 2);
		this.add(separator);
		
		
		JLabel lblDerivarTicket = new JLabel("Derivar ticket");
		lblDerivarTicket.setFont(new Font("Segoe UI Symbol", Font.BOLD, 40));
		lblDerivarTicket.setBounds(550, 20, 262, 54);
		this.add(lblDerivarTicket);
		
		JLabel lblSeleccione = new JLabel("Seleccione grupo de resolucion:");
		lblSeleccione.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 18));
		lblSeleccione.setBounds(346, 304, 262, 31);
		this.add(lblSeleccione);
		
		
		JLabel lblNumeroDeTicket = new JLabel("Numero de ticket:");
		lblNumeroDeTicket.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 18));
		lblNumeroDeTicket.setBounds(346, 143, 262, 31);
		add(lblNumeroDeTicket);
		
		JLabel lblNumeroDeLegajo = new JLabel("Numero de legajo:");
		lblNumeroDeLegajo.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 18));
		lblNumeroDeLegajo.setBounds(346, 187, 262, 31);
		add(lblNumeroDeLegajo);
		
		JLabel lblNewLabel = new JLabel("Clasificacion de ticket:");
		lblNewLabel.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 18));
		lblNewLabel.setBounds(346, 231, 262, 31);
		add(lblNewLabel);
		
		
		
		JComboBox<GrupoDeResolucion> comboBox = new JComboBox<GrupoDeResolucion>();
		comboBox.setBackground(Color.WHITE);
		comboBox.setModel(new DefaultComboBoxModel<GrupoDeResolucion>(grupos));
		comboBox.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 15));
		comboBox.setBounds(620, 308, 407, 25);
		add(comboBox);
		

		
		txtNumeroDeTicket = new JTextField();
		txtNumeroDeTicket.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 15));
		txtNumeroDeTicket.setEditable(false);
		txtNumeroDeTicket.setBounds(620, 150, 407, 24);
		add(txtNumeroDeTicket);
		txtNumeroDeTicket.setColumns(10);
		txtNumeroDeTicket.setText(derivarDTO.getNumeroTicket().toString());
		
		txtNumeroDeLegajo = new JTextField();
		txtNumeroDeLegajo.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 15));
		txtNumeroDeLegajo.setEditable(false);
		txtNumeroDeLegajo.setColumns(10);
		txtNumeroDeLegajo.setBounds(620, 194, 407, 24);
		add(txtNumeroDeLegajo);
		txtNumeroDeLegajo.setText(derivarDTO.getNumeroLegajo().toString());
		
		txtClasificacionDeTicket = new JTextField();
		txtClasificacionDeTicket.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 15));
		txtClasificacionDeTicket.setEditable(false);
		txtClasificacionDeTicket.setColumns(10);
		txtClasificacionDeTicket.setBounds(620, 238, 407, 24);
		add(txtClasificacionDeTicket);
		txtClasificacionDeTicket.setText(derivarDTO.getClasificacion().toString());
		
		JButton btnDerivarTicket = new JButton("Derivar Ticket");
		btnDerivarTicket.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 16));
		btnDerivarTicket.setBounds(1020, 655, 133, 37);
		this.add(btnDerivarTicket);
		
		JButton btnAtras = new JButton("Atras");
		btnAtras.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 16));
		btnAtras.setBounds(1207, 655, 133, 37);
		this.add(btnAtras);
		
		
		btnDerivarTicket.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int dialogButton = JOptionPane.YES_NO_OPTION;
				int dialogResult = JOptionPane.showConfirmDialog (null, "Desea derivar el ticket?","Warning",dialogButton);
				if(dialogResult == JOptionPane.YES_OPTION){
					ventana.getGestorTicket().derivarTicket(derivarDTO, false, (GrupoDeResolucion)comboBox.getSelectedItem(), derivarDTO.getObservaciones());
					ventana.setContentPane(new HomeMesaAyuda(ventana));
					ventana.pack();
				}
			}
		});
		

		btnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ventana.setContentPane(new HomeMesaAyuda(ventana));
				ventana.pack();
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
