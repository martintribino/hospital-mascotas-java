package ttps.spring.implementation;

import java.util.List;

import javax.persistence.Query;

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
		Query consulta = this.getEntityManager()
				.createQuery("select s from Solicitud s");
		return (List<Solicitud>) consulta.getResultList();
	}

	@Override
	public List<Solicitud> recuperarSolicitudesPorMascota(Mascota m) {
		Query consulta = this.getEntityManager()
				.createQuery("SELECT s from Solicitud s WHERE s.mascota.id = :idMascota");
		consulta.setParameter("idMascota", m.getId());
		return (List<Solicitud>) consulta.getResultList();
	}

	@Override
	public List<Solicitud> recuperarSolicitudesPorVeterinario(Veterinario v) {
		Query consulta = this.getEntityManager()
				.createQuery("SELECT s from Solicitud s WHERE s.veterinario.id = :idVet");
		consulta.setParameter("idVet", v.getId());
		return (List<Solicitud>) consulta.getResultList();
	}

}
