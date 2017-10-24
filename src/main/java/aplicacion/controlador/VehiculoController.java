package aplicacion.controlador;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import dominio.Vehiculo;
import persistencia.sistema.SistemaPersistencia;

@Controller
public class VehiculoController {

	@RequestMapping(value="/vehiculos", method=RequestMethod.GET)
	public ResponseEntity<List<Vehiculo>> listaVehiculos() {
		
		SistemaPersistencia sistemaPersistencia = new SistemaPersistencia();
		List<Vehiculo> vehiculosLista = sistemaPersistencia.repositorioVehiculo().listaVehiculos();
		if(vehiculosLista.isEmpty())
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		
		return new ResponseEntity<List<Vehiculo>>(vehiculosLista,HttpStatus.OK);
		
	}
	
}
