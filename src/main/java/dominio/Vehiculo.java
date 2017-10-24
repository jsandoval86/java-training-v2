package dominio;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="vehiculo")
public class Vehiculo {
	
	@Id
	@Column(name="placa")
	private String placa;
	
	@Column(name="cilindraje", nullable=true)
	private int cilindraje;
	
	@OneToOne
	@JoinColumn(name="tipo_vehiculo_id", referencedColumnName="id", nullable=false)
	private Tipo tipo;
	
	public Vehiculo() {
	
	}
	
	public Vehiculo(String placa, int cilindraje, Tipo tipo) {
		this.placa = placa;
		this.cilindraje = cilindraje;
		this.tipo = tipo;
	}

	public String getPlaca() {
		return placa;
	}
	public void setPlaca(String placa) {
		this.placa = placa;
	}
	public int getCilindraje() {
		return cilindraje;
	}
	public void setCilindraje(int cilindraje) {
		this.cilindraje = cilindraje;
	}
	public Tipo getTipo() {
		return tipo;
	}
	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}

	
	
}
