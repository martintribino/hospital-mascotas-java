package ttps.spring.implementation;

import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import ttps.spring.dao.ISolicitudDAO;
import ttps.spring.model.Mascota;
import ttps.spring.model.Solicitud;
import ttps.spring.model.Veterinario;

@Repository
public class SolicitudDAOHibJPA extends GenericDAOHibJPA<Solicitud>
								implements ISolicitudDAO {

	/**
	 * Duenio DAO Hibernate JPA Implementation
	 */

	private static final long serialVersionUID = 1L;

	public SolicitudDAOHibJPA() {
		super(Solicitud.class);
	}

	@Override
	public List<Solicitud> recuperarSolicitudes() {
		TypedQuery<Solicitud> consulta = this.getEntityManager()
				.createQuery("select s from Solicitud s", Solicitud.class);
		return consulta.getResultList();
	}

	@Override
	public List<Solicitud> recuperarSolicitudesPorMascota(Mascota m) {
		TypedQuery<Solicitud> consulta = this.getEntityManager()
				.createQuery("SELECT s from Solicitud s WHERE s.mascota.id = :idMascota", Solicitud.class);
		consulta.setParameter("idMascota", m.getId());
		return consulta.getResultList();
	}

	@Override
	public List<Solicitud> recuperarSolicitudesPorVeterinario(Veterinario v) {
		TypedQuery<Solicitud> consulta = this.getEntityManager()
				.createQuery("SELECT s from Solicitud s WHERE s.veterinario.id = :idVet", Solicitud.class);
		consulta.setParameter("idVet", v.getId());
		return consulta.getResultList();
	}

	@Override
	public Solicitud encontrar(String slug) {
		Solicitud s = this.getEntityManager().unwrap(Session.class)
				.bySimpleNaturalId(Solicitud.class)
				.load(slug);
		return s;
	}

	@Override
	public Solicitud encontrar(Long mascId, Long vetId) {
		TypedQuery<Solicitud> consulta = this.getEntityManager()
				.createQuery("SELECT s from Solicitud s WHERE s.veterinario.id = :idVet and s.mascota.id = :idMasc", Solicitud.class);
		consulta.setParameter("idVet", vetId);
		consulta.setParameter("idMasc", mascId);
		return consulta.getSingleResult();
	}

}
