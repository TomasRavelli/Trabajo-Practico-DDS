package modelo.gestores;

import java.util.List;
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
	
	
	public String validarLegajo (Integer legajo) {
		for (Empleado e : this.getEmpleados()) {
			if (e.getNumeroLegajo().intValue() == legajo.intValue()) {
				return e.getNombre();
			}
		}
		
		return null;	
	}
	
	
	public Integer getGrupoId (Integer legajo) {
		return this.getEmpleado(legajo).getGrupoId();
	}
}
