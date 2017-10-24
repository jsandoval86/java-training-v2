package persistencia.conexion;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ConexionJPA {
	
	private static EntityManagerFactory entityManagerFactory;
	private static final String UNIDAD_PERSISTENCIA = "parqueadero_v2";
	
	public ConexionJPA() {
		entityManagerFactory = Persistence.createEntityManagerFactory(UNIDAD_PERSISTENCIA);
	}
	
	public EntityManager crearEntityManager() {
		return entityManagerFactory.createEntityManager();
	}
}