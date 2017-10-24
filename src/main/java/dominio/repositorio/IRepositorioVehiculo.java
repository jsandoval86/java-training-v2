package dominio.repositorio;

import java.util.List;

import dominio.Vehiculo;

public interface IRepositorioVehiculo {

	void agregar(Vehiculo vehiculo);
	
	Vehiculo obtenerPorPlaca(String placa);
	
	List<Vehiculo> listaVehiculos();
}
