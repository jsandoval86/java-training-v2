package dominio;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tipo_vehiculo")
public class Tipo {
	
	@Id
	@Column(name="id", nullable=false)
	private long id;
	
	@Column(name="nombre", nullable=false)
	private String nombre;
	
	public Tipo() {
	}

	public Tipo(long id) {
		this.id = id;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	
}
