package ttps.spring.implementation;

import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import ttps.spring.dao.IMascotaDAO;
import ttps.spring.model.Mascota;

@Repository
public class MascotaDAOHibJPA extends GenericDAOHibJPA<Mascota>
							  implements IMascotaDAO {

	/**
	 * Mascota DAO Hibernate JPA Implementation
	 */
	private static final long serialVersionUID = 1L;

	public MascotaDAOHibJPA() {
		super(Mascota.class);
	}

	@Override
	public List<Mascota> recuperarMascotas() {
		TypedQuery<Mascota> consulta = this.getEntityManager()
				.createQuery("SELECT m FROM Mascota m", Mascota.class );
		return (List<Mascota>) consulta.getResultList();
	}

	@Override
	public List<Mascota> recuperarMascotasPorDuenio(long id) {
		TypedQuery<Mascota> consulta = this.getEntityManager()
				.createQuery("SELECT m from Mascota m WHERE m.duenio.id = :idDuenio", Mascota.class);
		consulta.setParameter("idDuenio", id);
		return (List<Mascota>) consulta.getResultList();
	}

	@Override
	public List<Mascota> recuperarMascotasPorVeterinario(long id) {
		TypedQuery<Mascota> consulta = this.getEntityManager()
				.createQuery("SELECT m from Mascota m WHERE m.veterinario.id = :idVet", Mascota.class);
		consulta.setParameter("idVet", id);
		return (List<Mascota>) consulta.getResultList();
	}

	@Override
	public Mascota encontrar(String slug) {
		TypedQuery<Mascota> consulta = this.getEntityManager()
				.createQuery("SELECT m from Mascota m WHERE m.slug = :slug", Mascota.class);
		consulta.setParameter("slug", slug);
		Mascota mascota = consulta.getSingleResult();
		return mascota;
	}

}
