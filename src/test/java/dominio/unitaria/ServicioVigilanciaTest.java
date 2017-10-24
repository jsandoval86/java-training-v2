package dominio.unitaria;

import org.junit.Test;

import dominio.Parqueadero;
import dominio.Registro;
import dominio.ServicioVigilancia;
import dominio.Tipo;
import dominio.Vehiculo;
import dominio.excepcion.IngresoExcepcion;
import dominio.repositorio.IRepositorioRegistro;
import junit.framework.Assert;
import persistencia.repositorio.RepositorioRegistro;
import persistencia.sistema.SistemaPersistencia;
import testdatabuilder.ParqueaderoTestDataBuilder;
import testdatabuilder.RegistroTestDataBuilder;
import testdatabuilder.VehiculoTestDataBuilder;

import static org.junit.Assert.fail;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ServicioVigilanciaTest {

	
	@Test
	public void DenegarIngresoAVehiculoConPlacaIniciadaConAYDiaNoHabil() {
		
		// arrange
		SistemaPersistencia sistemaPersistencia = mock(SistemaPersistencia.class);
		List<Integer> diasHabiles = new ArrayList<Integer>();
		diasHabiles.add(new Integer(Calendar.SUNDAY));
		diasHabiles.add(new Integer(Calendar.MONDAY));
		
		Parqueadero parqueadero = new ParqueaderoTestDataBuilder()
				.conDiasHabiles(diasHabiles )
				.build();
		
		int diaHoy = Calendar.WEDNESDAY;
		ServicioVigilancia vigilancia = new ServicioVigilancia(
				sistemaPersistencia , parqueadero);
		
		vigilancia.setDiaHoy(diaHoy);
		
		Vehiculo vehiculo = new VehiculoTestDataBuilder()
				.conPlaca("AAA-123")
				.build();
		// act
		try {
			vigilancia.registrarIngresoVehiculo(vehiculo);
		}
		catch(IngresoExcepcion e) {
			// assert
			Assert.assertEquals(ServicioVigilancia.NO_INGRESO_POR_PLACA, e.getMessage());
			return;
		}
		
		fail();
	}

	@Test
	public void DenegarIngresoAVehiculoPorCupoLleno() {
		
		// arrange
		
		SistemaPersistencia sistemaPersistencia = mock(SistemaPersistencia.class);
		IRepositorioRegistro repositorioRegistro = mock(IRepositorioRegistro.class);
		when(sistemaPersistencia .repositorioRegistro()).thenReturn(repositorioRegistro);
		
		
		int numeroCarrosPermitido = 20;
		Parqueadero parqueadero = new ParqueaderoTestDataBuilder()
				.conNumeroCarrosPermitido(numeroCarrosPermitido)
				.build();
		ServicioVigilancia vigilancia = new ServicioVigilancia(sistemaPersistencia, parqueadero);
		Tipo tipo = new Tipo();
		tipo.setId(1);
		
		Vehiculo vehiculo = new VehiculoTestDataBuilder()
				.conPlaca("HUD-120")
				.conTipo(tipo)
				.build();
		
		when(repositorioRegistro.obtenerCupoPorTipo(vehiculo)).thenReturn(numeroCarrosPermitido);
		
		// act
		try {
			vigilancia.registrarIngresoVehiculo(vehiculo);
		}
		catch(IngresoExcepcion e) {
			// assert
			Assert.assertEquals(ServicioVigilancia.NO_INGRESO_CUPO_LLENO, e.getMessage());
			return;
		}
		
		fail();
		
	}
	
	@Test
	public void DenegarIngresoSiVehiculoYaFueIngresado() {
		// arrange
		String placa = "RFO-123";
		
		Vehiculo vehiculo = new VehiculoTestDataBuilder()
				.conPlaca(placa).build();
		Parqueadero parqueadero = new ParqueaderoTestDataBuilder().build();
		
		SistemaPersistencia sistemaPersistencia = mock(SistemaPersistencia.class);
		IRepositorioRegistro repositorioRegistro = mock(RepositorioRegistro.class);
		when(sistemaPersistencia.repositorioRegistro()).thenReturn(repositorioRegistro);
			
		Registro registro = new RegistroTestDataBuilder()
				.conVehiculo(vehiculo)
				.conFechaSalida(null)
				.build();
		
		when(repositorioRegistro.obtenerPorPlaca(placa)).thenReturn(registro);
		
		
		ServicioVigilancia vigilancia = new ServicioVigilancia(sistemaPersistencia, parqueadero);
		
		// act
		try {
			vigilancia.registrarIngresoVehiculo(vehiculo);
		}
		catch(IngresoExcepcion e) {
			// assert
			Assert.assertEquals(ServicioVigilancia.EL_VEHICULO_YA_FUE_INGRESADO, e.getMessage());
			return;
		}
		
		fail();
	}
	
	//@Test
	public void IngresarVehiculo() {
		// arrange
			
		String placa = "KOD-230";
		Vehiculo vehiculo = new VehiculoTestDataBuilder().conPlaca(placa).build();
		Parqueadero parqueadero = new ParqueaderoTestDataBuilder().build();
		
		SistemaPersistencia sistemaPersistencia = mock(SistemaPersistencia.class);
		IRepositorioRegistro repositorioRegistro = mock(IRepositorioRegistro.class);
		when(sistemaPersistencia.repositorioRegistro()).thenReturn(repositorioRegistro);
		
		Registro registro = new RegistroTestDataBuilder()
				.conParqueadero(parqueadero)
				.conVehiculo(vehiculo)
				.build();
		
		//when(repositorioRegistro.agregar(registro)).thenReturn();
				
		ServicioVigilancia vigilancia = new ServicioVigilancia(sistemaPersistencia , parqueadero);
		
		// act
		try {
			vigilancia.registrarIngresoVehiculo(vehiculo);
		}
		catch(IngresoExcepcion e) {
			fail();
		}
		catch(Exception e) {
			fail();
		}
		// assert
	}
}
