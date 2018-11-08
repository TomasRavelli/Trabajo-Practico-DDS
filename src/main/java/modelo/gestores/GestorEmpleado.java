package modelo.gestores;

import modelo.entidades.Empleado;

public class GestorEmpleado {
	private GestorBD gestorBD;
	
	public GestorEmpleado(GestorBD gBD) {
		gestorBD = gBD;
	}
	public Empleado getEmpleado (Integer legajo) {
		Empleado emp = gestorBD.getEmpleado(legajo);
		return emp;
	}
	
	

}
