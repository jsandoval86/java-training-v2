package dominio.integracion;

import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import dominio.Parqueadero;
import dominio.ServicioVigilancia;
import dominio.Vehiculo;
import dominio.excepcion.IngresoExcepcion;
import persistencia.sistema.SistemaPersistencia;
import testdatabuilder.ParqueaderoTestDataBuilder;
import testdatabuilder.VehiculoTestDataBuilder;

public class ServicioVigilanciaTest {

	private SistemaPersistencia sistemaPersistencia;

	@Before
	public void setup() {
		sistemaPersistencia = new SistemaPersistencia();
		sistemaPersistencia.iniciar();
	}
	
	@After
	public void tearDown() {
		sistemaPersistencia.terminar();
	}
	
	@Ignore
	public void registrarUnVehiculo () {
		// arrange
		Vehiculo vehiculo = new VehiculoTestDataBuilder()
				.conPlaca("GDH-120")
				.conCilindraje(500)
				.build();
		
		Parqueadero parqueadero = new ParqueaderoTestDataBuilder()
				.conIdentificador(1)
				.conNumeroCarrosPermitido(20)
				.conNumeroMotosPermitido(10)
				.build();
		
		ServicioVigilancia vigilancia = new ServicioVigilancia(sistemaPersistencia, parqueadero);
		
		// act 
		try {
			vigilancia.registrarIngresoVehiculo(vehiculo);
		}
		catch(IngresoExcepcion e) {
			fail(e.getMessage());
		}
		catch(Exception e) {
			fail(e.getMessage());
		}
		
		// assert
		Assert.assertNotNull(
			sistemaPersistencia.repositorioRegistro().obtenerPorPlaca(vehiculo.getPlaca())
		);
		
	}

	@Ignore
	public void registrarUnVehiculoConPlacaIniciadaConAEnDiaHabil() {
		// arrange
		Vehiculo vehiculo = new VehiculoTestDataBuilder()
				.conPlaca("AAA-245")
				.conCilindraje(500)
				.build();
		
		
		List<Integer> diasHabiles = new ArrayList<Integer>();
		diasHabiles.add(new Integer(Calendar.SUNDAY));
		diasHabiles.add(new Integer(Calendar.MONDAY));
		
		Parqueadero parqueadero = new ParqueaderoTestDataBuilder()
				.conIdentificador(1)
				.conNumeroCarrosPermitido(20)
				.conNumeroMotosPermitido(10)
				.conDiasHabiles(diasHabiles )
				.build();
		
		ServicioVigilancia vigilancia = new ServicioVigilancia(sistemaPersistencia, parqueadero);
		vigilancia.setDiaHoy(Calendar.MONDAY);
		
		try {
			vigilancia.registrarIngresoVehiculo(vehiculo);
		}
		catch(IngresoExcepcion e) {
			fail(e.getMessage());
		}
		catch(Exception e) {
			fail(e.getMessage());
		}
		
		// assert
		Assert.assertNotNull(
			sistemaPersistencia.repositorioRegistro().obtenerPorPlaca(vehiculo.getPlaca())
		);
				
	}
}
