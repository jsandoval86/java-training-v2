package dominio;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="parqueadero")
public class Parqueadero {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(name="num_carros_permitidos", nullable=false)
	private int numCarrosPermitidos;
	
	@Column(name="num_motos_permitidos", nullable=false)
	private int numeroMotosPermitidos;
	
	private transient List<Integer> diasHabiles;
	
	public Parqueadero() {
	}
		
	public Parqueadero(int numCarrosPermitidos, int numeroMotosPermitidos, List<Integer> diasHabiles) {
		this.numCarrosPermitidos = numCarrosPermitidos;
		this.numeroMotosPermitidos = numeroMotosPermitidos;
		this.diasHabiles = diasHabiles;
	}

	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getNumCarrosPermitidos() {
		return numCarrosPermitidos;
	}
	public void setNumCarrosPermitidos(int numCarrosPermitidos) {
		this.numCarrosPermitidos = numCarrosPermitidos;
	}
	public int getNumeroMotosPermitidos() {
		return numeroMotosPermitidos;
	}
	public void setNumeroMotosPermitidos(int numeroMotosPermitidos) {
		this.numeroMotosPermitidos = numeroMotosPermitidos;
	}
	public List<Integer> getDiasHabiles() {
		return diasHabiles;
	}
	public void setDiasHabiles(List<Integer> diasHabiles) {
		this.diasHabiles = diasHabiles;
	}

	public int getCapacidadPorTipo(Vehiculo vehiculo) {
		return 20;
	}
	
	

}
