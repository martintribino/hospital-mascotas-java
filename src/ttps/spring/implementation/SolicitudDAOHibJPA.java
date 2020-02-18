package ttps.spring.implementation;

import java.util.List;

import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ttps.spring.dao.ISolicitudDAO;
import ttps.spring.model.Mascota;
import ttps.spring.model.Solicitud;
import ttps.spring.model.Veterinario;

@Repository
@Transactional
public class SolicitudDAOHibJPA extends GenericDAOHibJPA<Solicitud>
								implements ISolicitudDAO {

	/**
	 * SolicitudDAOHibJPA
	 */
	private static final long serialVersionUID = 74631803819126428L;

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
		Solicitud s = null;
		TypedQuery<Solicitud> consulta = this.getEntityManager()
				.createQuery("SELECT s from Solicitud s WHERE s.slug = :slug", Solicitud.class);
		consulta.setParameter("slug", slug);
		List<Solicitud> sList = consulta.getResultList();
		if (!sList.isEmpty()) {
			s = sList.get(0);
		}
		return s;
	}

	@Override
	public Solicitud encontrar(Long mascId, Long vetId) {
		Solicitud s = null;
		TypedQuery<Solicitud> consulta = this.getEntityManager()
				.createQuery("SELECT s from Solicitud s WHERE s.veterinario.id = :idVet and s.mascota.id = :idMasc", Solicitud.class);
		consulta.setParameter("idVet", vetId);
		consulta.setParameter("idMasc", mascId);
		List<Solicitud> sList = consulta.getResultList();
		if (!sList.isEmpty()) {
			s = sList.get(0);
		}
		return s;
	}

	@Override
	public List<Solicitud> recuperarSolicitudesPorVeterinarioYEstado(
			Veterinario v, Solicitud.Estados estado) {
		TypedQuery<Solicitud> consulta = this.getEntityManager()
				.createQuery(
					"SELECT s from Solicitud s WHERE s.veterinario.id = :idVet and  s.estado = :idEst",
					Solicitud.class);
		consulta.setParameter("idVet", v.getId());
		consulta.setParameter("idEst", estado);
		return consulta.getResultList();
	}

	@Override
	public void eliminarXVeterinario(Veterinario v) {
		Query consulta = this.getEntityManager()
				.createQuery("DELETE from Solicitud s WHERE s.veterinario.id = :idVet");
		consulta.setParameter("idVet", v.getId());
		consulta.executeUpdate();
	}

	@Override
	public void eliminarXMascota(Mascota m) {
		Query consulta = this.getEntityManager()
				.createQuery("DELETE from Solicitud s WHERE s.mascota.id = :idMasc");
		consulta.setParameter("idMasc", m.getId());
		consulta.executeUpdate();
	}

}
