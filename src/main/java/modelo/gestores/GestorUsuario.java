package modelo.gestores;

import modelo.entidades.Usuario;

public class GestorUsuario {
	
	private Usuario usuarioActual;
	
	public GestorUsuario () {}
	

	public GestorUsuario(Usuario usuarioActual) {
		super();
		this.usuarioActual = usuarioActual;
	}

	
	public Usuario getUsuarioActual() {
		return usuarioActual;
	}
	
	
	public Integer getNumeroLegajo() {
		return this.getUsuarioActual().getNumeroLegajo();
	}

	
	public void setUsuarioActual(Usuario usuarioActual) {
		this.usuarioActual = usuarioActual;
	}
}