package modelo.gestores;

import java.util.List;

import interfacesGraficas.InterfazRegistrarTicket1;
import interfacesGraficas.InterfazRegistrarTicket2;
import modelo.entidades.Empleado;

public class GestorEmpleado {
	
	private GestorBD gestorBD;
	
	
	public GestorEmpleado(GestorBD gBD) {
		gestorBD = gBD;
	}
	

	public Empleado getEmpleado (Integer legajo) {
		Empleado empleado = new Empleado();
		for (Empleado e : this.getEmpleados()) {
			if (e.getNumeroLegajo().equals(legajo)) {
				empleado = e;
			}
		}
		return empleado;
	}
	
	public List<Empleado> getEmpleados (){
		List<Empleado> empleados = gestorBD.getEmpleados();
		return empleados;
	}
	
	
	public String validarLegajo (String legajo) {
		for (Empleado e : this.getEmpleados()) {
			if (e.getNumeroLegajo().toString().equals(legajo)) {
				return e.getNombre();
			}
		}
		return null;	
	}
}
