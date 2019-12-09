package ttps.spring.implementation;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import ttps.spring.dao.IVeterinarioDAO;
import ttps.spring.model.Veterinario;

@Repository
public class VeterinarioDAOHibJPA extends GenericDAOHibJPA<Veterinario>
								  implements IVeterinarioDAO {

	/**
	 * Veterinario DAO Hibernate JPA Implementation
	 */

	private static final long serialVersionUID = 1L;

	public VeterinarioDAOHibJPA() {
		super(Veterinario.class);
	}

	@Override
	public List<Veterinario> recuperarVeterinario(String userName) {
		Query consulta = this.getEntityManager()
				.createQuery("SELECT v from Veterinario v WHERE v.usuario = :nombreUsu");
		consulta.setParameter("nombreUsu", userName);
		return (List<Veterinario>) consulta.getResultList();
	}

	@Override
	public List<Veterinario> recuperarVeterinarios() {
		Query consulta = this.getEntityManager()
				.createQuery("select v from Veterinario v");
		return (List<Veterinario>) consulta.getResultList();
	}

}
