package ttps.spring.implementation;

import java.util.List;

import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import ttps.spring.dao.IMascotaDAO;
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
		TypedQuery<Mascota> consulta = this.getEntityManager()
				.createQuery("SELECT m FROM Mascota m", Mascota.class );
		return (List<Mascota>) consulta.getResultList();
	}

	@Override
	public List<Mascota> recuperarMascotasExtraviadas() {
		TypedQuery<Mascota> consulta = this.getEntityManager()
				.createQuery("SELECT m FROM Mascota m WHERE m.extraviada = 1", Mascota.class );
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
		Mascota m = null;
		TypedQuery<Mascota> consulta = this.getEntityManager()
				.createQuery("SELECT m from Mascota m WHERE m.slug = :slug", Mascota.class);
		consulta.setParameter("slug", slug);
		List<Mascota> mList = consulta.getResultList();
		if (!mList.isEmpty()) {
			m = mList.get(0);
		}
		return m;
	}

	@Override
	public void actualizarMascota(Mascota mascota, String imagen) {
		Query consulta = this.getEntityManager()
				.createQuery("UPDATE Mascota m SET m.imagen = :img WHERE m.id = :idMasc");
		consulta.setParameter("idMasc", mascota.getId());
		consulta.setParameter("img", imagen);
		consulta.executeUpdate();
	}

	@Override
	public void agregarVeterinario(Mascota mascota, Veterinario veterinario) {
		Query consulta = this.getEntityManager()
				.createQuery("UPDATE Mascota m SET m.veterinario.id = :idVet WHERE m.id = :idMasc");
		consulta.setParameter("idMasc", mascota.getId());
		consulta.setParameter("idVet", veterinario.getId());
		consulta.executeUpdate();
	}

	@Override
	public void removerVeterinario(Mascota mascota) {
		Query consulta = this.getEntityManager()
				.createQuery("UPDATE Mascota m SET m.veterinario = NULL WHERE m.id = :idMasc");
		consulta.setParameter("idMasc", mascota.getId());
		consulta.executeUpdate();
	}

}
