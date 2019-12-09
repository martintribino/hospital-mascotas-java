package ttps.spring.implementation;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ttps.spring.dao.IAdministradorDAO;
import ttps.spring.model.Administrador;

@Repository
@Transactional
public class AdministradorDAOHibJPA extends GenericDAOHibJPA<Administrador>
									implements IAdministradorDAO {

	/**
	 * Administrador DAO Hibernate JPA Implementation
	 */
	
	private static final long serialVersionUID = 1L;

	public AdministradorDAOHibJPA() {
		super(Administrador.class);
	}

	@Override
	public List<Administrador> recuperarAdministrador(String userName) {
		Query consulta = this.getEntityManager()
				.createQuery("SELECT a FROM Administrador a WHERE a.usuario.nombre_usuario = :nombreUsu");
		consulta.setParameter("nombreUsu", userName);
		return (List<Administrador>) consulta.getResultList();
	}

	@Override
	public List<Administrador> recuperarAdministradores() {
		return (List<Administrador>) this.listar("id", "desc");
	}
}
