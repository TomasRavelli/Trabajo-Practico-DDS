package interfacesGraficas;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import modelo.aplicacion.Principal;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.JSeparator;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;

public class InterfazModificarClasificacionTicket extends JPanel {

	private Principal ventana;
	private JTextField txtCodigo;
	private JTextField txtNombre;
	private JTextField txtEstado;

	public InterfazModificarClasificacionTicket(Principal frame) {
		
		//NOMBRE EDITABLE SOLO SI LA CLASIFICACION JAMAS FUE USADA
		//DESCRIPCION EDITABLE POR LOS GRUPOS DE RESOLUCION ASOCIADOS A LA CLASIFICACION
		
		//SI EL ESTADO PASA DE ACTIVO A INACTIVO ADVERTIR QUE LA CLASIFICACION NO ESTARA DISPONIBLE PARA USARSE EN ADELANTE
		//SI VICEVERSA, LO CONTRARIO
		
		
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
		scrollPane.setBounds(615, 307, 292, 86);
		this.add(scrollPane);
		
		JTextArea textAreaDescripcion = new JTextArea();
		textAreaDescripcion.setBackground(new Color(220, 220, 220));
		textAreaDescripcion.setEditable(true);
		scrollPane.setViewportView(textAreaDescripcion);
		
		
		JScrollPane scrollPane2 = new JScrollPane();
		scrollPane2.setBounds(615, 500, 292, 95);
		this.add(scrollPane2);
		
		JTextArea txtListaGrupos = new JTextArea();
		txtListaGrupos.setBackground(new Color(220, 220, 220));
		txtListaGrupos.setEditable(true);
		scrollPane2.setViewportView(txtListaGrupos);
		
		
		
		JLabel lblModificarClasificacion = new JLabel("Modificar clasificacion de ticket");
		lblModificarClasificacion.setFont(new Font("Segoe UI Symbol", Font.BOLD, 40));
		lblModificarClasificacion.setBounds(361, 20, 625, 42);
		this.add(lblModificarClasificacion);
		
		JLabel lblCodigo = new JLabel("Codigo:");
		lblCodigo.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 18));
		lblCodigo.setBounds(430, 173, 173, 25);
		this.add(lblCodigo);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 18));
		lblNombre.setBounds(430, 240, 173, 25);
		this.add(lblNombre);
		
		JLabel lblDescripcion = new JLabel("Descripcion:");
		lblDescripcion.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 18));
		lblDescripcion.setBounds(430, 307, 173, 25);
		this.add(lblDescripcion);
		
		JLabel lblEstado = new JLabel("Estado:");
		lblEstado.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 18));
		lblEstado.setBounds(430, 436, 173, 25);
		this.add(lblEstado);
		
		JLabel lblGruposResolucion = new JLabel("Grupos de resolucion:");
		lblGruposResolucion.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 18));
		lblGruposResolucion.setBounds(430, 499, 173, 25);
		this.add(lblGruposResolucion);
		
		JLabel errorNombre = new JLabel("* Este campo no puede estar vacio.");
		errorNombre.setBackground(new Color(240, 240, 240));
		errorNombre.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 15));
		errorNombre.setForeground(Color.RED);
		errorNombre.setBounds(940, 240, 400, 20);
		errorNombre.setVisible(false);
		this.add(errorNombre);
		
		JLabel errorNombre2 = new JLabel("* Este nombre ya existe en el sistema.");
		errorNombre2.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 15));
		errorNombre2.setForeground(Color.RED);
		errorNombre2.setBounds(940, 240, 400, 20);
		errorNombre2.setVisible(false);
		this.add(errorNombre2);
		
		JLabel errorDescripcionVacio = new JLabel("* Este campo no puede estar vacio.");
		errorDescripcionVacio.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 15));
		errorDescripcionVacio.setForeground(Color.RED);
		errorDescripcionVacio.setBounds(940, 307, 400, 20);
		errorDescripcionVacio.setVisible(false);
		this.add(errorDescripcionVacio);
		
		JLabel errorGrupo = new JLabel("* No se puede eliminar este grupo de resolucion.");
		errorGrupo.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 15));
		errorGrupo.setForeground(Color.RED);
		errorGrupo.setBounds(940, 499, 400, 20);
		errorGrupo.setVisible(false);
		this.add(errorGrupo);
		
		JLabel errorGrupo2 = new JLabel("* Este campo no puede estar vacio.");
		errorGrupo2.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 15));
		errorGrupo2.setForeground(Color.RED);
		errorGrupo2.setBounds(940, 499, 400, 20);
		errorGrupo2.setVisible(false);
		this.add(errorGrupo2);
		
		JLabel errorClasificacion = new JLabel("* No se puede desactivar esta clasificacion.");
		errorClasificacion.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 15));
		errorClasificacion.setForeground(Color.RED);
		errorClasificacion.setBounds(940, 436, 400, 20);
		errorClasificacion.setVisible(false);
		this.add(errorClasificacion);
		
		

		txtCodigo = new JTextField();
		txtCodigo.setBackground(new Color(220, 220, 220));
		txtCodigo.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 15));
		txtCodigo.setBounds(615, 174, 292, 24);
		txtCodigo.setEditable(false);
		txtCodigo.setColumns(10);
		this.add(txtCodigo);
		
		txtNombre = new JTextField();
		txtNombre.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 15));
		txtNombre.setBounds(615, 241, 292, 24);
		txtNombre.setColumns(10);
		this.add(txtNombre);
		
		txtEstado = new JTextField();
		txtEstado.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 15));
		txtEstado.setBounds(615, 437, 292, 24);
		txtEstado.setColumns(10);
		this.add(txtEstado);
		
		
		
		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 14));
		btnGuardar.setBounds(1020, 655, 133, 37);
		this.add(btnGuardar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 14));
		btnCancelar.setBounds(1207, 655, 133, 37);
		this.add(btnCancelar);
		
		
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (txtNombre.getText().isEmpty()) {
					errorNombre.setVisible(true);
				}
				else if (textAreaDescripcion.getText().isEmpty()) {
					errorDescripcionVacio.setVisible(true);
				}
				else if (txtListaGrupos.getText().isEmpty()) {
					errorGrupo2.setVisible(true);
				}
				else {
					int dialogButton = JOptionPane.YES_NO_OPTION;
					//INFORMAR ADEMAS SI CAMBIA EL ESTADO
					int dialogResult = JOptionPane.showConfirmDialog (null, "Desea guardar los cambios realizados?","Warning",dialogButton);
					if(dialogResult == JOptionPane.YES_OPTION){
					  //PASA ALGO
					}
				}
			}
		});

		
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//VUELVE
			}
		});
	}
}