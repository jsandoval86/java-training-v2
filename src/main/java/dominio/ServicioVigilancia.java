package dominio;

import java.util.Calendar;
import java.util.Date;

import dominio.excepcion.IngresoExcepcion;
import persistencia.sistema.SistemaPersistencia;

public class ServicioVigilancia {
	
	private static final char LETRA_INICIAL_PLACA_RESTRICCION = 'A';
	public static final String EL_VEHICULO_YA_FUE_INGRESADO = "El vehiculo ya fue ingresado";
	public static final String NO_INGRESO_POR_PLACA = "No puede ingresar porque no está en un día hábil";
	public static final String NO_INGRESO_CUPO_LLENO = "No puede Ingresar porque no hay cupos disponibles";
	
	private SistemaPersistencia sistemaPersistencia;
	private Parqueadero parqueadero;
	private int diaHoy;
	private Calendar cal = Calendar.getInstance();
	
	public ServicioVigilancia(SistemaPersistencia sistemaPersistencia, Parqueadero parqueadero) {
		this.sistemaPersistencia = sistemaPersistencia;
		this.parqueadero = parqueadero;
		this.diaHoy = cal.get(Calendar.DAY_OF_WEEK);
	}
	
	public void registrarIngresoVehiculo(Vehiculo vehiculo) {
		if(placaInicaConA(vehiculo.getPlaca()) && (!esDiaHabil()))
			throw new IngresoExcepcion(NO_INGRESO_POR_PLACA);
		
		if(!capacidadParqueaderoDisponible(vehiculo))
			throw new IngresoExcepcion(NO_INGRESO_CUPO_LLENO);
		
		if(yaEstaIngresado(vehiculo))
			throw new IngresoExcepcion(EL_VEHICULO_YA_FUE_INGRESADO);
		
		Registro registro = new Registro();
		registro.setParqueadero(parqueadero);
		registro.setVehiculo(vehiculo);
		registro.setFechaIngreso(new Date());
		
		ingresarVehiculoSiNoExiste(vehiculo);
		sistemaPersistencia.repositorioRegistro().agregar(registro);
	}

	
	// agregar vehiculo a persistencia si no existe
	private void ingresarVehiculoSiNoExiste(Vehiculo vehiculo) {

		Vehiculo vehiculoBuscado = sistemaPersistencia
				.repositorioVehiculo().obtenerPorPlaca(vehiculo.getPlaca());

		if(vehiculoBuscado == null) {
			sistemaPersistencia.repositorioVehiculo().agregar(vehiculo);
		}
	}
	
	private  boolean yaEstaIngresado(Vehiculo vehiculo) {
		Registro registro = sistemaPersistencia.repositorioRegistro()
				.obtenerPorPlaca(vehiculo.getPlaca());
		
		if(registro != null ) {
			if (registro.getFechaSalida() == null)
				return true;
		}
		
		return false;
	}
	
	private boolean capacidadParqueaderoDisponible(Vehiculo vehiculo) {
		int numeroDeVehiculosRegistrados = sistemaPersistencia
				.repositorioRegistro().obtenerCupoPorTipo(vehiculo);

		if (this.parqueadero.getCapacidadPorTipo(vehiculo) > 
			numeroDeVehiculosRegistrados) {
			return true;
		}
		
		return false;
	}

	private boolean esDiaHabil() {
		for(Integer i : this.parqueadero.getDiasHabiles()) {
			if(this.getDiaHoy() == i.intValue())
				return true;
		}
		
		return false;
	}

	private boolean placaInicaConA(String placa) {
		if(placa == null)
			return false;
		
		if (placa.charAt(0) == LETRA_INICIAL_PLACA_RESTRICCION)
			return true;
		
		return false;
	}

	public int getDiaHoy() {
		return diaHoy;
	}

	public void setDiaHoy(int diaHoy) {
		this.diaHoy = diaHoy;
	}
	
	

}
