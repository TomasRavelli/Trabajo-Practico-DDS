package modelo.gestores;

import java.util.List;

import modelo.entidades.GrupoDeResolucion;


public class GestorGrupo {
	
	private List<GrupoDeResolucion> grupos;
	private GestorBD gestorBD;
	
	
	public GestorGrupo(GestorBD gBD) {
		gestorBD = gBD;
		grupos = gestorBD.getGrupos();
	}


	public List<GrupoDeResolucion> getGrupos() {
		return grupos;
	}


	public void setGrupos(List<GrupoDeResolucion> grupos) {
		this.grupos = grupos;
	}


	public GestorBD getGestorBD() {
		return gestorBD;
	}


	public void setGestorBD(GestorBD gestorBD) {
		this.gestorBD = gestorBD;
	}

}
