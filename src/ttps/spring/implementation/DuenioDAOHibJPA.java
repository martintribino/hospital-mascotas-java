package ttps.spring.implementation;

import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import ttps.spring.dao.IDuenioDAO;
import ttps.spring.model.Duenio;

@Repository
public class DuenioDAOHibJPA extends GenericDAOHibJPA<Duenio>
							 implements IDuenioDAO {

	/**
	 * Duenio DAO Hibernate JPA Implementation
	 */

	private static final long serialVersionUID = 1L;

	public DuenioDAOHibJPA() {
		super(Duenio.class);
	}

	@Override
	public List<Duenio> recuperarDuenios() {
		TypedQuery<Duenio> consulta = this.getEntityManager()
				.createQuery("SELECT d FROM Duenio d", Duenio.class);
		return (List<Duenio>) consulta.getResultList();
	}

	@Override
	public List<Duenio> recuperarDuenio(String userName) {
		TypedQuery<Duenio> consulta = this.getEntityManager()
				.createQuery("SELECT d FROM Duenio d WHERE d.usuario = :nombreUsu", Duenio.class);
		consulta.setParameter("nombreUsu", userName);
		return (List<Duenio>) consulta.getResultList();
	}

}
