package persistencia.repositorio;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import dominio.Vehiculo;
import dominio.repositorio.IRepositorioVehiculo;

public class RepositorioVehiculo implements IRepositorioVehiculo{

	private EntityManager entityManager;

	public RepositorioVehiculo(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	public void agregar(Vehiculo vehiculo) {
		this.entityManager.persist(vehiculo);
	}

	public Vehiculo obtenerPorPlaca(String placa) {
		Vehiculo vehiculo = null;
		
		TypedQuery<Vehiculo> query = this.entityManager
			.createQuery("SELECT vehiculo FROM Vehiculo vehiculo WHERE vehiculo.placa = :placa", Vehiculo.class)
			.setParameter("placa", placa);
	
		List<Vehiculo> vehiculoLista = query.getResultList();
		if(!vehiculoLista.isEmpty())
			vehiculo = vehiculoLista.get(0);
		
		return vehiculo;
	}

	public List<Vehiculo> listaVehiculos() {
		List<Vehiculo> vehiculos = new ArrayList<Vehiculo>();
		
		TypedQuery<Vehiculo> query = this.entityManager
			.createQuery("SELECT vehiculo FROM Vehiculo vehiculo", Vehiculo.class);
	
		vehiculos = query.getResultList();
		return vehiculos;
	}

}
