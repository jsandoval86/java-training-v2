package testdatabuilder;

import java.util.Date;

import dominio.Parqueadero;
import dominio.Registro;
import dominio.Vehiculo;

public class RegistroTestDataBuilder {
	
	private static Date FECHA_INGRESO = new Date();
	private static Date FECHA_SALIDA = null;
	private static Vehiculo VEHICULO = new VehiculoTestDataBuilder().build();
	private static Parqueadero PARQUEADERO = new ParqueaderoTestDataBuilder().build();
	
	private Vehiculo vehiculo;
	private Parqueadero parqueadero;
	private Date fechaIngreso;
	private Date fechaSalida;
	
	public RegistroTestDataBuilder() {
		this.vehiculo = VEHICULO;
		this.parqueadero = PARQUEADERO;
		this.fechaIngreso = FECHA_INGRESO;
		this.fechaSalida = FECHA_SALIDA;
	}

	public RegistroTestDataBuilder conVehiculo(Vehiculo vehiculo) {
		this.vehiculo = vehiculo;
		return this;
	}

	public RegistroTestDataBuilder conParqueadero(Parqueadero parqueadero) {
		this.parqueadero = parqueadero;
		return this;
	}
	
	public RegistroTestDataBuilder conFechaIngreso(Date fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
		return this;
	}
	
	public RegistroTestDataBuilder conFechaSalida(Date fechaSalida) {
		this.fechaSalida = fechaSalida;
		return this;
	}	

	public Registro build() {
		Registro registro = new Registro();
		registro.setVehiculo(this.vehiculo);
		registro.setParqueadero(this.parqueadero);
		registro.setFechaIngreso(this.fechaIngreso);
		registro.setFechaSalida(this.fechaSalida);
		return registro;
	}
}
