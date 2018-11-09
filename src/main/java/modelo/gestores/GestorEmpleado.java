package modelo.gestores;

import java.util.List;

import modelo.entidades.Empleado;

public class GestorEmpleado {
	
	private GestorBD gestorBD;
	
	
	public GestorEmpleado(GestorBD gBD) {
		gestorBD = gBD;
	}
	

	public Empleado getEmpleado (Integer legajo) {
		System.out.println("GestorEmpleado-getEmpleado - 0");
		Empleado empleado = new Empleado();
		System.out.println("GestorEmpleado-getEmpleado - primera vez");
		for (Empleado e : this.getEmpleados()) {
			if (e.getNumeroLegajo().equals(legajo)) {
				empleado = e;
			}
		}
		System.out.println("GestorEmpleado-getEmpleado - segunda vez");
		return empleado;
	}
	
	public List<Empleado> getEmpleados (){
		System.out.println("esto es getEmpleados - GestorEmpleado");
		List<Empleado> empleados = gestorBD.getEmpleados();
		System.out.println("esto es getEmpleados - GestorEmpleado - segunda salida");
		return empleados;
	}

}
