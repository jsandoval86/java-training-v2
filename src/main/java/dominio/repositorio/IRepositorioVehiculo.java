package dominio.repositorio;

import dominio.Vehiculo;

public interface IRepositorioVehiculo {

	void agregar(Vehiculo vehiculo);
	
	Vehiculo obtenerPorPlaca(String placa);
}
