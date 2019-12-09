package ttps.spring.implementation;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ttps.spring.dao.IMascotaDAO;
import ttps.spring.model.Duenio;
import ttps.spring.model.Mascota;
import ttps.spring.model.Veterinario;

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
		Query consulta = this.getEntityManager()
				.createQuery("SELECT m FROM Mascota m");
		return (List<Mascota>) consulta.getResultList();
	}

	@Override
	public List<Mascota> recuperarMascotasPorDuenio(long id) {
		Query consulta = this.getEntityManager()
				.createQuery("SELECT m from Mascota m WHERE m.duenio.id = :idDuenio");
		consulta.setParameter("idDuenio", id);
		return (List<Mascota>) consulta.getResultList();
	}

	@Override
	public List<Mascota> recuperarMascotasPorVeterinario(long id) {
		Query consulta = this.getEntityManager()
				.createQuery("SELECT m from Mascota m WHERE m.veterinario.id = :idVet");
		consulta.setParameter("idVet", id);
		return (List<Mascota>) consulta.getResultList();
	}

}
