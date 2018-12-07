package interfacesGraficas;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import infoDTO.IntervencionBusquedaDTO;
import infoDTO.IntervencionResultadoDTO;
import modelo.aplicacion.Principal;
import modelo.entidades.GrupoDeResolucion;
import modelo.entidades.Intervencion;
import modelo.entidades.Ticket;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class InterfazConsultarIntervencionesPaginacion extends JPanel {

	private Principal ventana;
	private JTextField txtNumeroTicket;
	private JTextField txtNumeroLegajo;
	private JTextField txtFechaApertura;
	private JTextField txtClasificacion;
	private JTextField txtEstadoTicket;
	private JTextField txtFechaAsignacion;
	private JTextField txtEstadoIntervencion;
	private JTextField txtGrupoResolucion;
	private JTextField txtNumeroPagina;
	private JTextField txtCantidad;
	private JTextField textFieldNumeroTicket;
	private JTextField textFieldEstado;
	private JTextField textFieldNumeroLegajo;
	private JTextField textFieldFechaDesde;
	private JTextField textFieldFechaHasta;
	private Integer i;

	public InterfazConsultarIntervencionesPaginacion(Principal frame, IntervencionBusquedaDTO criteriosBusqueda) {

		this.ventana=frame;
		ventana.setContentPane(this);
		this.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setPreferredSize(new Dimension(1366, 768));
		this.setBackground(new Color(230, 230, 250));
		this.setLayout(null);
		
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(976, 407, 202, 113);
		this.add(scrollPane);
		
		JTextArea textAreaObservaciones = new JTextArea();
		textAreaObservaciones.setBackground(new Color(220, 220, 220));
		textAreaObservaciones.setEditable(false);
		scrollPane.setViewportView(textAreaObservaciones);
		
		
		JLabel lblNumeroTicket = new JLabel("Numero ticket:");
		lblNumeroTicket.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 18));
		lblNumeroTicket.setBounds(189, 260, 235, 25);
		this.add(lblNumeroTicket);
		
		JLabel lblNumeroLegajo = new JLabel("Numero legajo:");
		lblNumeroLegajo.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 18));
		lblNumeroLegajo.setBounds(189, 309, 235, 25);
		this.add(lblNumeroLegajo);
		
		JLabel lblClasificacionActual = new JLabel("Clasificacion actual del ticket:");
		lblClasificacionActual.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 18));
		lblClasificacionActual.setBounds(189, 358, 235, 22);
		this.add(lblClasificacionActual);
		
		JLabel lblEstadoDelTicket = new JLabel("Estado del ticket:");
		lblEstadoDelTicket.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 18));
		lblEstadoDelTicket.setBounds(189, 407, 235, 22);
		this.add(lblEstadoDelTicket);
		
		JLabel lblFechaApertura = new JLabel("Fecha de apertura:");
		lblFechaApertura.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 18));
		lblFechaApertura.setBounds(189, 456, 235, 22);
		this.add(lblFechaApertura);
		
		JLabel lblFechaAsignacion = new JLabel("Fecha asignacion de intervencion:");
		lblFechaAsignacion.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 18));
		lblFechaAsignacion.setBounds(694, 260, 271, 25);
		this.add(lblFechaAsignacion);
		
		JLabel lblEstadoIntervencion = new JLabel("Estado intervencion:");
		lblEstadoIntervencion.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 18));
		lblEstadoIntervencion.setBounds(694, 310, 270, 22);
		this.add(lblEstadoIntervencion);
		
		JLabel lblGrupoResolucion = new JLabel("Grupo de resolucion:");
		lblGrupoResolucion.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 18));
		lblGrupoResolucion.setBounds(694, 357, 270, 25);
		this.add(lblGrupoResolucion);
		
		JLabel lblObservaciones = new JLabel("Observaciones de la intervencion:");
		lblObservaciones.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 18));
		lblObservaciones.setBounds(694, 406, 270, 25);
		this.add(lblObservaciones);
		
		JLabel lblCriteriosDeBusqueda = new JLabel("Criterios de busqueda de intervenciones asignadas:");
		lblCriteriosDeBusqueda.setFont(new Font("Segoe UI Symbol", Font.BOLD, 20));
		lblCriteriosDeBusqueda.setBounds(80, 35, 516, 31);
		this.add(lblCriteriosDeBusqueda);
		
		JLabel lblClasificacionDeTicket = new JLabel("Intervencion asignada        de ");
		lblClasificacionDeTicket.setFont(new Font("Segoe UI Symbol", Font.BOLD, 18));
		lblClasificacionDeTicket.setBounds(532, 200, 271, 22);
		this.add(lblClasificacionDeTicket);
		
		JLabel lblNumeroTicketDeArriba = new JLabel("-Numero ticket:");
		lblNumeroTicketDeArriba.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 15));
		lblNumeroTicketDeArriba.setBounds(169, 92, 116, 21);
		this.add(lblNumeroTicketDeArriba);
		
		JLabel lblNumeroLegajoDeArriba = new JLabel("-Numero legajo:");
		lblNumeroLegajoDeArriba.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 15));
		lblNumeroLegajoDeArriba.setBounds(169, 160, 116, 21);
		this.add(lblNumeroLegajoDeArriba);
		
		JLabel lblEstadoDearriba = new JLabel("-Estado:");
		lblEstadoDearriba.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 15));
		lblEstadoDearriba.setBounds(169, 126, 116, 21);
		this.add(lblEstadoDearriba);
		
		JLabel lblfechaDesdeDeArriba = new JLabel("-Fecha desde:");
		lblfechaDesdeDeArriba.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 15));
		lblfechaDesdeDeArriba.setBounds(502, 94, 102, 16);
		this.add(lblfechaDesdeDeArriba);
		
		JLabel lblfechaHastaDeArriba = new JLabel("-Fecha hasta:");
		lblfechaHastaDeArriba.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 15));
		lblfechaHastaDeArriba.setBounds(502, 128, 86, 16);
		this.add(lblfechaHastaDeArriba);
		
		
		
		txtNumeroTicket = new JTextField();
		txtNumeroTicket.setBackground(new Color(220, 220, 220));
		txtNumeroTicket.setBounds(436, 264, 202, 22);
		txtNumeroTicket.setColumns(10);
		txtNumeroTicket.setEditable(false);
		this.add(txtNumeroTicket);
		
		txtNumeroLegajo = new JTextField();
		txtNumeroLegajo.setBackground(new Color(220, 220, 220));
		txtNumeroLegajo.setBounds(436, 313, 202, 22);
		txtNumeroLegajo.setColumns(10);
		txtNumeroLegajo.setEditable(false);
		this.add(txtNumeroLegajo);
		
		txtFechaApertura = new JTextField();
		txtFechaApertura.setBackground(new Color(220, 220, 220));
		txtFechaApertura.setBounds(436, 459, 202, 22);
		txtFechaApertura.setColumns(10);
		txtFechaApertura.setEditable(false);
		this.add(txtFechaApertura);
		
		txtClasificacion = new JTextField();
		txtClasificacion.setBackground(new Color(220, 220, 220));
		txtClasificacion.setBounds(436, 361, 202, 22);
		txtClasificacion.setColumns(10);
		txtClasificacion.setEditable(false);
		this.add(txtClasificacion);
		
		txtEstadoTicket = new JTextField();
		txtEstadoTicket.setBackground(new Color(220, 220, 220));
		txtEstadoTicket.setBounds(436, 410, 202, 22);
		txtEstadoTicket.setColumns(10);
		txtEstadoTicket.setEditable(false);
		this.add(txtEstadoTicket);
		
		txtFechaAsignacion = new JTextField();
		txtFechaAsignacion.setBackground(new Color(220, 220, 220));
		txtFechaAsignacion.setBounds(977, 264, 202, 22);
		txtFechaAsignacion.setColumns(10);
		txtFechaAsignacion.setEditable(false);
		this.add(txtFechaAsignacion);
		
		txtEstadoIntervencion = new JTextField();
		txtEstadoIntervencion.setBackground(new Color(220, 220, 220));
		txtEstadoIntervencion.setBounds(976, 313, 202, 22);
		txtEstadoIntervencion.setColumns(10);
		txtEstadoIntervencion.setEditable(false);
		this.add(txtEstadoIntervencion);
		
		txtGrupoResolucion = new JTextField();
		txtGrupoResolucion.setBackground(new Color(220, 220, 220));
		txtGrupoResolucion.setBounds(976, 361, 202, 22);
		txtGrupoResolucion.setColumns(10);
		txtGrupoResolucion.setEditable(false);
		this.add(txtGrupoResolucion);
		
		txtNumeroPagina = new JTextField();
		txtNumeroPagina.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 18));
		txtNumeroPagina.setBounds(731, 203, 34, 22);
		txtNumeroPagina.setColumns(10);
		this.add(txtNumeroPagina);
		
		
		txtCantidad = new JTextField();
		txtCantidad.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 18));
		txtCantidad.setBounds(803, 203, 57, 22);
		txtCantidad.setColumns(10);
		txtCantidad.setEditable(false);
		this.add(txtCantidad);
		
		
		
		textFieldNumeroTicket = new JTextField();
		textFieldNumeroTicket.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 14));
		textFieldNumeroTicket.setBounds(297, 92, 130, 21);
		textFieldNumeroTicket.setColumns(10);
		textFieldNumeroTicket.setEditable(false);
		this.add(textFieldNumeroTicket);
		if(!(criteriosBusqueda.getNumeroTicket()==null)) {
			textFieldNumeroTicket.setText(criteriosBusqueda.getNumeroTicket().toString());
		}
		
		textFieldEstado = new JTextField();
		textFieldEstado.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 14));
		textFieldEstado.setColumns(10);
		textFieldEstado.setBounds(297, 126, 130, 21);
		textFieldEstado.setEditable(false);
		this.add(textFieldEstado);
		textFieldEstado.setText(criteriosBusqueda.getEstado());
		
		textFieldNumeroLegajo = new JTextField();
		textFieldNumeroLegajo.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 14));
		textFieldNumeroLegajo.setColumns(10);
		textFieldNumeroLegajo.setBounds(297, 160, 130, 21);
		textFieldNumeroLegajo.setEditable(false);
		this.add(textFieldNumeroLegajo);
		if(!(criteriosBusqueda.getNumeroLegajo()==null)) {
			textFieldNumeroLegajo.setText(criteriosBusqueda.getNumeroLegajo().toString());
		}
		
		
		textFieldFechaDesde = new JTextField();
		textFieldFechaDesde.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 14));
		textFieldFechaDesde.setColumns(10);
		textFieldFechaDesde.setBounds(616, 92, 116, 21);
		textFieldFechaDesde.setEditable(false);
		this.add(textFieldFechaDesde);
		if(!(criteriosBusqueda.getFechaDesde()==null)) {
			textFieldFechaDesde.setText(criteriosBusqueda.getFechaDesde().toString());
		}
			
		textFieldFechaHasta = new JTextField();
		textFieldFechaHasta.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 14));
		textFieldFechaHasta.setColumns(10);
		textFieldFechaHasta.setBounds(616, 126, 116, 21);
		textFieldFechaHasta.setEditable(false);
		this.add(textFieldFechaHasta);
		if(!(criteriosBusqueda.getFechaHasta()==null)) {
			textFieldFechaHasta.setText(criteriosBusqueda.getFechaHasta().toString());
		}
		
		
		
		
		JButton btnModificarEstado = new JButton("Modificar estado");
		btnModificarEstado.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 14));
		btnModificarEstado.setBounds(854, 650, 157, 37);
		this.add(btnModificarEstado);
		
		JButton btnIngresarComentario = new JButton("Ingresar comentario");
		btnIngresarComentario.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 14));
		btnIngresarComentario.setBounds(1020, 650, 157, 37);
		this.add(btnIngresarComentario);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 14));
		btnCancelar.setBounds(1207, 650, 133, 37);
		this.add(btnCancelar);
		
		JButton btnIzquierda = new JButton("<");
		btnIzquierda.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 12));
		btnIzquierda.setBounds(880, 202, 44, 25);
		this.add(btnIzquierda);
		
		JButton btnDerecha = new JButton(">");
		btnDerecha.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 12));
		btnDerecha.setBounds(926, 202, 42, 25);
		this.add(btnDerecha);
		
		//TODO ver que el campo de paginacion sea menor al size
		Integer numeroLegajo = ventana.getGestorUsuario().getNumeroLegajo();
		List<IntervencionResultadoDTO> intervenciones = ventana.getGestorIntervencion().getIntervenciones(criteriosBusqueda, numeroLegajo);
		
		txtCantidad.setText(((Integer)intervenciones.size()).toString());
		
		if (intervenciones.size()>0) {
			txtNumeroPagina.setText("1");
			txtNumeroTicket.setText(intervenciones.get(i-1).getNumeroTicket().toString());
			txtNumeroLegajo.setText(intervenciones.get(i-1).getNumeroLegajo().toString());
			txtClasificacion.setText(intervenciones.get(i-1).getClasificacion());
			txtEstadoTicket.setText(intervenciones.get(i-1).getEstadoTicket());
			txtFechaApertura.setText(intervenciones.get(i-1).getFechaApertura().toString());
			txtFechaAsignacion.setText(intervenciones.get(i-1).getFechaAsignacionIntervencion().toString());
			txtEstadoIntervencion.setText(intervenciones.get(i-1).getEstadoIntervencion());
			txtGrupoResolucion.setText(intervenciones.get(i-1).getGrupo());
			textAreaObservaciones.setText(intervenciones.get(i-1).getObservacionIntervencion());
		}
		
		else {
			txtNumeroPagina.setText("0");
			JOptionPane.showMessageDialog(null, "No existen intervenciones que cumplan con los criterios ingresados.");
		}
		
		if (Integer.valueOf(txtNumeroPagina.getText())<=intervenciones.size()) {
			i = Integer.valueOf(txtNumeroPagina.getText());
		}
		else {
			JOptionPane.showMessageDialog(null, "Numero ingresado no valido.");
		}
			
		
		btnDerecha.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				i++;
				if (i<=intervenciones.size()) {
					txtNumeroPagina.setText(i.toString());
					txtNumeroTicket.setText(intervenciones.get(i-1).getNumeroTicket().toString());
					txtNumeroLegajo.setText(intervenciones.get(i-1).getNumeroLegajo().toString());
					txtClasificacion.setText(intervenciones.get(i-1).getClasificacion());
					txtEstadoTicket.setText(intervenciones.get(i-1).getEstadoTicket());
					txtFechaApertura.setText(intervenciones.get(i-1).getFechaApertura().toString());
					txtFechaAsignacion.setText(intervenciones.get(i-1).getFechaAsignacionIntervencion().toString());
					txtEstadoIntervencion.setText(intervenciones.get(i-1).getEstadoIntervencion());
					txtGrupoResolucion.setText(intervenciones.get(i-1).getGrupo());
					textAreaObservaciones.setText(intervenciones.get(i-1).getObservacionIntervencion());
				}
			}
		});
		
		
		btnIzquierda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				i--;
				if (i>0) {
					txtNumeroPagina.setText(i.toString());
					Integer i = Integer.valueOf(txtNumeroPagina.getText());
					txtNumeroTicket.setText(intervenciones.get(i-1).getNumeroTicket().toString());
					txtNumeroLegajo.setText(intervenciones.get(i-1).getNumeroLegajo().toString());
					txtClasificacion.setText(intervenciones.get(i-1).getClasificacion());
					txtEstadoTicket.setText(intervenciones.get(i-1).getEstadoTicket());
					txtFechaApertura.setText(intervenciones.get(i-1).getFechaApertura().toString());
					txtFechaAsignacion.setText(intervenciones.get(i-1).getFechaAsignacionIntervencion().toString());
					txtEstadoIntervencion.setText(intervenciones.get(i-1).getEstadoIntervencion());
					txtGrupoResolucion.setText(intervenciones.get(i-1).getGrupo());
					textAreaObservaciones.setText(intervenciones.get(i-1).getObservacionIntervencion());
				}	
			}
		});
		
			
		btnModificarEstado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (intervenciones.size()>0) {
					ventana.setContentPane(new InterfazActualizarEstadoIntervencion(ventana, intervenciones.get(i-1)));
					ventana.pack();
				}
				else {
					JOptionPane.showMessageDialog(null, "No hay intervenciones para modificar su estado.");
				}
			}
		});
		
		
		btnIngresarComentario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (intervenciones.size()>0) {
					if (intervenciones.get(i-1).getEstadoIntervencion().equalsIgnoreCase("Trabajando")) {
						ventana.setContentPane(new InterfazModificarComentarios(ventana, intervenciones.get(i-1)));
						ventana.pack();
					}
					else {
						JOptionPane.showMessageDialog(null, "No se pueden modificar los comentarios de esta intervencion.");
					}
				}
				else {
					JOptionPane.showMessageDialog(null, "No hay intervenciones para ingresarle un comentario.");
				}
			}
		});
		
		
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ventana.setContentPane(new InterfazConsultarIntervenciones(ventana));
				ventana.pack();
			}
		});
	}
}
