package interfacesGraficas;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import modelo.aplicacion.Principal;
import modelo.entidades.ClasificacionTicket;
import modelo.entidades.DuracionClasificacion;
import modelo.entidades.DuracionEstado;
import modelo.entidades.Ticket;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;
import modelo.entidades.Intervencion;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;


public class InterfazVisualizacionTicket extends JPanel {

	private Principal ventana;
	private JTextField txtDescripcionCargo;
	private JTextField txtLegajo;
	private JTextField txtApellidoNombre;
	private JTextField txtTelefonoInterno;
	private JTextField txtTelefonoDirecto;
	private JTextField txtCalle;
	private JTextField txtNumero;
	private JTextField txtPiso;
	private JTextField txtOficina;
	private JTable table;
	private DefaultTableModel modeloTabla;
	private Ticket ticketParaMostrar;


	public InterfazVisualizacionTicket(Principal frame, Integer numeroTicket) {

		this.ventana=frame;
		ventana.setContentPane(this);
		this.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setPreferredSize(new Dimension(1366, 768));
		this.setBackground(new Color(230, 230, 250));
		this.setLayout(null);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(275, 90, 800, 2);
		this.add(separator);
		
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(701, 182, 578, 223);
		this.add(scrollPane_1);
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(820, 438, 458, 170);
		this.add(scrollPane);
		
		JTextArea textAreaObservaciones = new JTextArea();
		textAreaObservaciones.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 15));
		textAreaObservaciones.setBackground(SystemColor.menu);
		textAreaObservaciones.setEditable(false);
		scrollPane.setViewportView(textAreaObservaciones);
		
		JLabel lblVisualizacionTicket = new JLabel("Visualizacion ticket");
		lblVisualizacionTicket.setFont(new Font("Segoe UI Symbol", Font.BOLD, 40));
		lblVisualizacionTicket.setBounds(481, 20, 385, 48);
		this.add(lblVisualizacionTicket);
		
		JLabel lblDatosUsuario = new JLabel("Datos usuario: ");
		lblDatosUsuario.setFont(new Font("Segoe UI Symbol", Font.BOLD, 20));
		lblDatosUsuario.setBounds(190, 135, 156, 26);
		this.add(lblDatosUsuario);
		
		JLabel lblHistorialTicket = new JLabel("Historial ticket: ");
		lblHistorialTicket.setFont(new Font("Segoe UI Symbol", Font.BOLD, 20));
		lblHistorialTicket.setBounds(679, 136, 172, 24);
		this.add(lblHistorialTicket);
		
		JLabel lblLegajo = new JLabel("Legajo: ");
		lblLegajo.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 15));
		lblLegajo.setBounds(220, 184, 156, 24);
		this.add(lblLegajo);
		
		JLabel lblNombreYApellido = new JLabel("Apellido y nombre: ");
		lblNombreYApellido.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 15));
		lblNombreYApellido.setBounds(220, 221, 156, 24);
		this.add(lblNombreYApellido);
		
		JLabel lblTelefonoInterno = new JLabel("Telefono interno: ");
		lblTelefonoInterno.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 15));
		lblTelefonoInterno.setBounds(220, 258, 156, 24);
		this.add(lblTelefonoInterno);
		
		JLabel lblTelefonoDirecto = new JLabel("Telefono directo: ");
		lblTelefonoDirecto.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 15));
		lblTelefonoDirecto.setBounds(220, 295, 156, 24);
		this.add(lblTelefonoDirecto);
		
		JLabel lblDescripcionCargo = new JLabel("Descripcion de cargo: ");
		lblDescripcionCargo.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 15));
		lblDescripcionCargo.setBounds(220, 332, 148, 24);
		this.add(lblDescripcionCargo);
		
		JLabel lblDireccion = new JLabel("Direccion: ");
		lblDireccion.setFont(new Font("Segoe UI Symbol", Font.BOLD, 15));
		lblDireccion.setBounds(220, 434, 156, 24);
		this.add(lblDireccion);
		
		JLabel lblCalle = new JLabel("Calle: ");
		lblCalle.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 15));
		lblCalle.setBounds(220, 471, 72, 24);
		this.add(lblCalle);
		
		JLabel lblNumero = new JLabel("Numero: ");
		lblNumero.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 15));
		lblNumero.setBounds(220, 510, 72, 24);
		this.add(lblNumero);
		
		JLabel lblPiso = new JLabel("Piso: ");
		lblPiso.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 15));
		lblPiso.setBounds(220, 547, 72, 24);
		this.add(lblPiso);
		
		JLabel lblOficina = new JLabel("Oficina: ");
		lblOficina.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 15));
		lblOficina.setBounds(220, 584, 72, 24);
		this.add(lblOficina);
		
		JLabel label_6 = new JLabel((String) null);
		label_6.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 15));
		label_6.setBounds(780, 550, 123, 24);
		this.add(label_6);
		
		JLabel lblObservaciones = new JLabel("Observaciones: ");
		lblObservaciones.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 15));
		lblObservaciones.setBounds(701, 434, 115, 24);
		this.add(lblObservaciones);		
		
		txtDescripcionCargo = new JTextField();
		txtDescripcionCargo.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 15));
		txtDescripcionCargo.setBackground(SystemColor.menu);
		txtDescripcionCargo.setBounds(380, 332, 245, 73);
		txtDescripcionCargo.setColumns(10);
		txtDescripcionCargo.setEditable(false);
		this.add(txtDescripcionCargo);
		
		txtLegajo = new JTextField();
		txtLegajo.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 15));
		txtLegajo.setColumns(10);
		txtLegajo.setBackground(SystemColor.menu);
		txtLegajo.setBounds(380, 184, 245, 24);
		txtLegajo.setEditable(false);
		this.add(txtLegajo);
		
		txtApellidoNombre = new JTextField();
		txtApellidoNombre.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 15));
		txtApellidoNombre.setColumns(10);
		txtApellidoNombre.setBackground(SystemColor.menu);
		txtApellidoNombre.setBounds(380, 221, 245, 24);
		txtApellidoNombre.setEditable(false);
		this.add(txtApellidoNombre);
		
		txtTelefonoInterno = new JTextField();
		txtTelefonoInterno.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 15));
		txtTelefonoInterno.setColumns(10);
		txtTelefonoInterno.setBackground(SystemColor.menu);
		txtTelefonoInterno.setBounds(380, 258, 245, 24);
		txtTelefonoInterno.setEditable(false);
		this.add(txtTelefonoInterno);
		
		txtTelefonoDirecto = new JTextField();
		txtTelefonoDirecto.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 15));
		txtTelefonoDirecto.setColumns(10);
		txtTelefonoDirecto.setBackground(SystemColor.menu);
		txtTelefonoDirecto.setBounds(380, 295, 245, 24);
		txtTelefonoDirecto.setEditable(false);
		this.add(txtTelefonoDirecto);
		
		txtCalle = new JTextField();
		txtCalle.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 15));
		txtCalle.setColumns(10);
		txtCalle.setBackground(SystemColor.menu);
		txtCalle.setBounds(327, 471, 180, 24);
		txtCalle.setEditable(false);
		this.add(txtCalle);
		
		txtNumero = new JTextField();
		txtNumero.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 15));
		txtNumero.setColumns(10);
		txtNumero.setBackground(SystemColor.menu);
		txtNumero.setBounds(327, 510, 180, 24);
		txtNumero.setEditable(false);
		this.add(txtNumero);
		
		txtPiso = new JTextField();
		txtPiso.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 15));
		txtPiso.setColumns(10);
		txtPiso.setBackground(SystemColor.menu);
		txtPiso.setBounds(327, 547, 180, 24);
		txtPiso.setEditable(false);
		this.add(txtPiso);
		
		txtOficina = new JTextField();
		txtOficina.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 15));
		txtOficina.setColumns(10);
		txtOficina.setBackground(SystemColor.menu);
		txtOficina.setBounds(327, 584, 180, 24);
		txtOficina.setEditable(false);
		this.add(txtOficina);
		
		
		table = new JTable();
		modeloTabla = new DefaultTableModel(
				new Object[][] {
					
				},
				new String[] {"Fecha", "Hora", "Operador", "Estado", "Grupo de resol.", "Clasif. ticket"}
			);
		table.setModel(modeloTabla);
		table.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 11));
		scrollPane_1.setViewportView(table);
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 14));
		btnAceptar.setBounds(1207, 655, 133, 37);
		this.add(btnAceptar);
		
		
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				modeloTabla.setRowCount(0);
				ventana.setContentPane(new InterfazConsultarTicket(ventana));
				ventana.pack();
			}
		});
		

		ticketParaMostrar = ventana.getGestorTicket().getTicket(numeroTicket);
		txtLegajo.setText(ticketParaMostrar.getEmpleado().getNumeroLegajo().toString());
		txtApellidoNombre.setText(ticketParaMostrar.getEmpleado().getNombre());
		txtTelefonoInterno.setText(ticketParaMostrar.getEmpleado().getTelefonoInterno());
		txtTelefonoDirecto.setText(ticketParaMostrar.getEmpleado().getTelefonoDirecto());
		txtDescripcionCargo.setText(ticketParaMostrar.getEmpleado().getDescripcionCargo());
		cargarTabla();
		
		
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		ListSelectionModel rowSM = table.getSelectionModel();
		rowSM.addListSelectionListener(new ListSelectionListener() {

		    public void valueChanged(ListSelectionEvent e) {
		        if (e.getValueIsAdjusting()) return;

		        ListSelectionModel lsm =
		            (ListSelectionModel)e.getSource();

		        if (lsm.isSelectionEmpty()) {
		        } 
		        else {
		            int selectedRow = lsm.getMinSelectionIndex();
		            textAreaObservaciones.setText(obtenerObservacionesFecha(table.getSelectedRow()));
		        }
		    }
		});
	}

	
	private void cargarTabla() {
		ArrayList<DuracionEstado> duraciones = new ArrayList<>();
		for(DuracionEstado d:  ticketParaMostrar.getDuracionEstado()) {
			duraciones.add(d);
		}

		if(duraciones.size() != 1) {
			duraciones.sort((c1,c2)->compararFechasCambioEstado(c1,c2));
		}
		
		for(DuracionEstado de: duraciones) {
			modeloTabla.addRow(new String[]{de.getFechaInicio().toString(), de.getHoraInicio().toString(),de.getUsuario().getNombre(),de.getEstado().getNombre(),de.getUsuario().getEmpleado().getGrupo().getNombre(),buscarClasificacionTicketDeLaFecha(de.getFechaInicio()).getNombre()});
		}		
	}


	private Integer compararFechasCambioEstado(DuracionEstado c1, DuracionEstado c2) {
		
		Integer retorno;
		if(c1.getFechaInicio().isEqual((c2.getFechaInicio()))) {
				if(c1.getHoraInicio().getHour() == c2.getHoraInicio().getHour()) {
					if(c1.getHoraInicio().getMinute() == c2.getHoraInicio().getMinute()) {
						retorno = (((Integer)c2.getHoraInicio().getSecond()).compareTo(c1.getHoraInicio().getSecond()));  
					}
					else {
						retorno = (((Integer)c2.getHoraInicio().getMinute()).compareTo((Integer)c1.getHoraInicio().getMinute()));  
					}
				}
				else {
					retorno = (((Integer)c2.getHoraInicio().getHour()).compareTo((Integer)c2.getHoraInicio().getHour()));  
				}
			}
		else {
			retorno = (c2.getFechaInicio().compareTo(c1.getFechaInicio()));  
		}
		return retorno;
	}
	
	
	private ClasificacionTicket buscarClasificacionTicketDeLaFecha(LocalDate fechaDada){
		ArrayList<DuracionClasificacion> dc = new ArrayList<>(); 
		for(DuracionClasificacion d: ticketParaMostrar.getClasificaciones()) {
			dc.add((DuracionClasificacion)d);
		}
		
		int i=0;
		boolean flag = true;
		ClasificacionTicket retorno = new ClasificacionTicket();
		if(dc.size() == 1) {
			retorno = dc.get(0).getClasificacion();
		}else {

			while(i<dc.size() && flag) {
			if(i+1 == dc.size()) {
				if(dc.get(i).getFechaInicio().isAfter(fechaDada)){	
					flag = false;
					retorno = dc.get(i-1).getClasificacion();
				}
				else {
					retorno = dc.get(i).getClasificacion();
				}
			}
			else {
			if(dc.get(i).getFechaInicio().isAfter(fechaDada)){	
				flag = false;
				if(i==0) {
					retorno = dc.get(i).getClasificacion();
				}
				else {
					retorno = dc.get(i-1).getClasificacion();
				}
			}
			}
			i++;
		}
		}		
		return retorno;
	}
	
	
	private String obtenerObservacionesFecha(int selectedRow) {
		ArrayList<String> aux = new ArrayList<>(); 
		ArrayList<DuracionEstado> de = new ArrayList<>(); 
		for(DuracionEstado d: ticketParaMostrar.getDuracionEstado()) {
			de.add((DuracionEstado)d);
		}
		de.sort((c1,c2) -> compararFechasCambioEstado(c1, c2));
		
		DuracionEstado duracionMostrada = (DuracionEstado)de.get(selectedRow);
		
		ArrayList<Intervencion> intervencionesTicket = new ArrayList<>();
		for(Intervencion i: duracionMostrada.getTicket().getIntervenciones()) {
			intervencionesTicket.add(i);
		}
		
		Intervencion intervencion = new Intervencion();
		int i = 0;
		boolean flag = true;
		String observaciones = new String();
		
		if(!duracionMostrada.getEstado().getNombre().equalsIgnoreCase("Abierto Derivado")) {
			for(Intervencion interv: intervencionesTicket) {
				if(interv.getGrupoResolucion().getNombre().equalsIgnoreCase("Mesa de ayuda")) {
					intervencion = interv;
				}
			}
		}
		
		else {
			ArrayList<Intervencion> auxIntervenciones = new ArrayList<>();
			for(Intervencion interv: intervencionesTicket) {
				if(!interv.getGrupoResolucion().getNombre().equalsIgnoreCase("Mesa de ayuda")) {
					auxIntervenciones.add(interv);
				}
			}
			intervencionesTicket.removeAll(intervencionesTicket);
			intervencionesTicket.addAll(auxIntervenciones);
			while(i < intervencionesTicket.size() && flag) {
				if(intervencionesTicket.get(i).getFechaAsignacion().isEqual(duracionMostrada.getFechaInicio())) {
					if(intervencionesTicket.get(i).getHoraAsignacion().equals(duracionMostrada.getHoraInicio())) {
						flag = false;
						intervencion = intervencionesTicket.get(i);
				}
					else {
						if(intervencionesTicket.get(i).getHoraAsignacion().isAfter(duracionMostrada.getHoraInicio()))
						flag = false;
						intervencion = intervencionesTicket.get(i);
					}
			
				}
				else {
					if(intervencionesTicket.get(i).getFechaAsignacion().isAfter(duracionMostrada.getFechaInicio())) {
						flag = false;
						intervencion = intervencionesTicket.get(i);
					}
				}
				i++;
			}
		}
		
		i=0;
		flag = true;
		
		while(i < intervencion.getHistorialEstadoIntervenciones().size() && flag) {
			if(intervencion.getHistorialEstadoIntervenciones().get(i).getFechaInicio().equals(duracionMostrada.getFechaInicio())){ 
			if(intervencion.getHistorialEstadoIntervenciones().get(i).getHoraInicio().equals(duracionMostrada.getHoraInicio())){
				flag = false;
				observaciones = intervencion.getHistorialEstadoIntervenciones().get(i).getObservaciones();
				
			}else {
				if(intervencion.getHistorialEstadoIntervenciones().get(i).getHoraInicio().isAfter(duracionMostrada.getHoraInicio())) {
					if(i>1) {
					flag = false;
					observaciones = intervencion.getHistorialEstadoIntervenciones().get(i-1).getObservaciones();
				}}}
			}
			i++;
		}
		
		flag = true;
		i=0;
		
		
	
		return observaciones;
	}
}	