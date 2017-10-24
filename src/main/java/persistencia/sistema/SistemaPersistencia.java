package persistencia.sistema;


import javax.persistence.EntityManager;

import dominio.repositorio.IRepositorioRegistro;
import dominio.repositorio.IRepositorioVehiculo;
import persistencia.conexion.ConexionJPA;
import persistencia.repositorio.RepositorioRegistro;
import persistencia.repositorio.RepositorioVehiculo;

public class SistemaPersistencia {
	
	private EntityManager entityManager;

	public SistemaPersistencia() {
		this.entityManager = new ConexionJPA().crearEntityManager();
	}
	
	public IRepositorioVehiculo repositorioVehiculo() {
		return new RepositorioVehiculo(this.entityManager);
	}
	
	public IRepositorioRegistro repositorioRegistro() {
		return new RepositorioRegistro(this.entityManager);
	}
	
	public void iniciar() {
		this.entityManager.getTransaction().begin();
	}
	
	public void terminar() {
		this.entityManager.getTransaction().commit();
	}
}