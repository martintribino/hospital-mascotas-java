package ttps.spring.implementation;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ttps.spring.implementation.GenericDAOHibJPA;
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
	public List<Administrador> recuperarAdministradores() {
		return (List<Administrador>) this.listar("id", "desc");
	}
}
