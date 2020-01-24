package ttps.spring.implementation;

import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ttps.spring.dao.IFichaPublicaDAO;
import ttps.spring.model.FichaPublica;

@Repository
@Transactional
public class FichaPublicaDAOHibJPA extends GenericDAOHibJPA<FichaPublica>
									implements IFichaPublicaDAO {

	/**
	 * FichaPublicaDAOHibJPA
	 */
	private static final long serialVersionUID = 3077635270714857657L;

	public FichaPublicaDAOHibJPA() {
		super(FichaPublica.class);
	}

	@Override
	public FichaPublica encontrar(String slug) {
		FichaPublica f = null;
		TypedQuery<FichaPublica> consulta = this.getEntityManager()
				.createQuery("SELECT f from FichaPublica f WHERE f.slug = :slug", FichaPublica.class);
		consulta.setParameter("slug", slug);
		List<FichaPublica> mList = consulta.getResultList();
		if (!mList.isEmpty()) {
			f = mList.get(0);
		}
		return f;
	}

}
