package persistencia.repositorio;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import dominio.Registro;
import dominio.Vehiculo;
import dominio.repositorio.IRepositorioRegistro;

public class RepositorioRegistro implements IRepositorioRegistro{

	private EntityManager entityManager;

	public RepositorioRegistro(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	public void agregar(Registro registro) {
		this.entityManager.persist(registro);
	}

	public Registro obtenerPorPlaca(String placa) {
		Registro registro = null;
		TypedQuery<Registro> query = this.entityManager
				.createQuery("SELECT registro FROM Registro registro WHERE registro.vehiculo.placa = :placa", Registro.class)
				.setParameter("placa", placa);
		
		List<Registro> vehiculoLista = query.getResultList();
		if(!vehiculoLista.isEmpty())
			registro = vehiculoLista.get(0);
		
		return registro;
	}

	public int obtenerCupoPorTipo(Vehiculo vehiculo) {
		long numeroDeVehiculosRegistrados = 0;
		Query query = this.entityManager
				.createQuery("SELECT COUNT(registro) FROM Registro registro WHERE registro.vehiculo.tipo.id = :tipo AND registro.fechaSalida IS NULL")
				.setParameter("tipo", vehiculo.getTipo().getId());
		 
		numeroDeVehiculosRegistrados = (Long) query.getSingleResult();
		
 		return (int) numeroDeVehiculosRegistrados;
	}

}
