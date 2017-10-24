package dominio.repositorio;

import dominio.Registro;
import dominio.Vehiculo;

public interface IRepositorioRegistro {

	void agregar(Registro registro);
	
	Registro obtenerPorPlaca(String placa);
	
	int obtenerCupoPorTipo(Vehiculo vehiculo);
}
