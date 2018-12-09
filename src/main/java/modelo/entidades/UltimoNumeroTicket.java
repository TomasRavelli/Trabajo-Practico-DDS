package modelo.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table (name = "Ultimo_Numero_Ticket")
public class UltimoNumeroTicket implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column (name = "ID")
	private Integer id;
	
	@Column (name = "NUMERO_TICKET")
	private Integer numeroTicket;

	public UltimoNumeroTicket() { 
	}
	
	
	public Integer getNumeroTicket() {
		return numeroTicket;
	}
	
	public void setNumeroTicket(Integer numeroTicket) {
		this.numeroTicket = numeroTicket;
	}
}