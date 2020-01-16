package ttps.spring.implementation;

import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import ttps.spring.implementation.GenericDAOHibJPA;
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
	public List<Veterinario> recuperarVeterinarios() {
		TypedQuery<Veterinario> consulta = this.getEntityManager()
				.createQuery("select v from Veterinario v", Veterinario.class);
		return (List<Veterinario>) consulta.getResultList();
	}

	@Override
	public List<Veterinario> recuperarVeterinariosXValidacion(Boolean validados) {
		TypedQuery<Veterinario> consulta = this.getEntityManager()
				.createQuery("select v from Veterinario v where v.validado = :validados", Veterinario.class);
		consulta.setParameter("validados", validados);
		return (List<Veterinario>) consulta.getResultList();
	}

}
