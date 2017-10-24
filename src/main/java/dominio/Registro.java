package dominio;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="registro")
public class Registro {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@ManyToOne
	@JoinColumn(name="parqueadero_id", referencedColumnName="id", nullable=false)
	private Parqueadero parqueadero;
	
	@ManyToOne
	@JoinColumn(name="vehiculo_placa", referencedColumnName="placa", nullable=false)
	private Vehiculo vehiculo;
	
	@Column(name="fecha_ingreso", nullable=false)
	private Date fechaIngreso;
	
	@Column(name="fecha_salida", nullable=true)
	private Date fechaSalida;
	
	public Registro() {
	}

	public Registro(Parqueadero parqueadero, Vehiculo vehiculo) {
		this.parqueadero = parqueadero;
		this.vehiculo = vehiculo;
		this.fechaIngreso = new Date();
		this.fechaSalida = null;
	}

	public Registro(Parqueadero parqueadero, Vehiculo vehiculo, Date fechaIngreso) {
		this.parqueadero = parqueadero;
		this.vehiculo = vehiculo;
		this.fechaIngreso = fechaIngreso;
		this.fechaSalida = null;
	}
	
	public Parqueadero getParqueadero() {
		return parqueadero;
	}
	public void setParqueadero(Parqueadero parqueadero) {
		this.parqueadero = parqueadero;
	}
	public Vehiculo getVehiculo() {
		return vehiculo;
	}
	public void setVehiculo(Vehiculo vehiculo) {
		this.vehiculo = vehiculo;
	}
	public Date getFechaIngreso() {
		return fechaIngreso;
	}
	public void setFechaIngreso(Date fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}
	public Date getFechaSalida() {
		return fechaSalida;
	}
	public void setFechaSalida(Date fechaSalida) {
		this.fechaSalida = fechaSalida;
	}
	
	
}
