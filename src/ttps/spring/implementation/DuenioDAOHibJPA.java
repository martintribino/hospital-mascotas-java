package ttps.spring.implementation;

import java.util.List;

import javax.persistence.Query;

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
		Query consulta = this.getEntityManager()
				.createQuery("SELECT d FROM Duenio d");
		return (List<Duenio>) consulta.getResultList();
	}

	@Override
	public List<Duenio> recuperarDuenio(String userName) {
		Query consulta = this.getEntityManager()
				.createQuery("SELECT d FROM Duenio d WHERE d.usuario = :nombreUsu");
		consulta.setParameter("nombreUsu", userName);
		return (List<Duenio>) consulta.getResultList();
	}

}
