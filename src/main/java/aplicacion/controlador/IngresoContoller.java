package aplicacion.controlador;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import dominio.Registro;

@Controller
public class IngresoContoller {
	
	@RequestMapping(value="/registros", method=RequestMethod.POST)
	public ResponseEntity<Registro> IngresarVehiculo(@RequestBody Registro registro) {
		
		System.out.println(registro.getFechaIngreso());
		return null;		
	}
}
