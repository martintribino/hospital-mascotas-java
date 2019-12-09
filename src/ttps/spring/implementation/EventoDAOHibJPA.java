package ttps.spring.implementation;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import ttps.spring.dao.IEventoDAO;
import ttps.spring.model.Desparasitacion;
import ttps.spring.model.Enfermedad;
import ttps.spring.model.Evento;
import ttps.spring.model.Intervencion;
import ttps.spring.model.Mascota;
import ttps.spring.model.Reproduccion;
import ttps.spring.model.Vacuna;
import ttps.spring.model.Veterinario;
import ttps.spring.model.Visita;

@Repository
public class EventoDAOHibJPA extends GenericDAOHibJPA<Evento>
							 implements IEventoDAO {

	/**
	 * Mascota DAO Hibernate JPA Implementation
	 */

	private static final long serialVersionUID = 1L;

	public EventoDAOHibJPA() {
		super(Evento.class);
	}

	@Override
	public List<Visita> recuperarVisitas() {
		Query consulta = this.getEntityManager()
				.createQuery("SELECT m FROM Evento m WHERE m.tipoEvento = 'Visita'");
		return (List<Visita>) consulta.getResultList();
	}

	@Override
	public List<Visita> recuperarVisitasPorVeterinario(Veterinario v) {
		Query consulta = this.getEntityManager()
				.createQuery("SELECT e FROM Evento e WHERE e.tipoEvento = 'Visita' and e.veterinario.id = :veterinarioId");
		consulta.setParameter("veterinarioId", v.getId());
		return (List<Visita>) consulta.getResultList();
	}

	@Override
	public List<Visita> recuperarVisitasPorMascota(Mascota m) {
		Query consulta = this.getEntityManager()
				.createQuery("SELECT e FROM Evento e WHERE e.tipoEvento = 'Visita' and e.mascota.id = :mascotaId");
		consulta.setParameter("mascotaId", m.getId());
		return (List<Visita>) consulta.getResultList();
	}

	@Override
	public List<Vacuna> recuperarVacunas() {
		Query consulta = this.getEntityManager()
				.createQuery("SELECT e FROM Evento e WHERE e.tipoEvento = 'Vacuna'");
		return (List<Vacuna>) consulta.getResultList();
	}

	@Override
	public List<Vacuna> recuperarVacunasPorMascota(Mascota m) {
		Query consulta = this.getEntityManager()
				.createQuery("SELECT e FROM Evento e WHERE e.tipoEvento = 'Vacuna' and e.mascota.id = :mascotaId");
		consulta.setParameter("mascotaId", m.getId());
		return (List<Vacuna>) consulta.getResultList();
	}

	@Override
	public List<Vacuna> recuperarVacunasPorVeterinario(Veterinario v) {
		Query consulta = this.getEntityManager()
				.createQuery("SELECT e FROM Evento e WHERE e.tipoEvento = 'Vacuna' and e.veterinario.id = :mascotaId");
		consulta.setParameter("mascotaId", v.getId());
		return (List<Vacuna>) consulta.getResultList();
	}

	@Override
	public List<Reproduccion> recuperarReproducciones() {
		Query consulta = this.getEntityManager()
				.createQuery("SELECT m FROM Evento m WHERE m.tipoEvento = 'Reproduccion'");
		return (List<Reproduccion>) consulta.getResultList();
	}

	@Override
	public List<Reproduccion> recuperarReproduccionesPorMascota(Mascota m) {
		Query consulta = this.getEntityManager()
				.createQuery("SELECT e FROM Evento e WHERE e.tipoEvento = 'Reproduccion' and e.mascota.id = :mascotaId");
		consulta.setParameter("mascotaId", m.getId());
		return (List<Reproduccion>) consulta.getResultList();
	}

	@Override
	public List<Reproduccion> recuperarReproduccionesPorVeterinario(Veterinario v) {
		Query consulta = this.getEntityManager()
				.createQuery("SELECT e FROM Evento e WHERE e.tipoEvento = 'Reproduccion' and e.veterinario.id = :veterinarioId");
		consulta.setParameter("veterinarioId", v.getId());
		return (List<Reproduccion>) consulta.getResultList();
	}

	@Override
	public List<Intervencion> recuperarIntervenciones() {
		Query consulta = this.getEntityManager()
				.createQuery("SELECT m FROM Evento m WHERE m.tipoEvento = 'Intervencion'");
		return (List<Intervencion>) consulta.getResultList();
	}

	@Override
	public List<Intervencion> recuperarIntervencionesPorMascota(Mascota m) {
		Query consulta = this.getEntityManager()
				.createQuery("SELECT e FROM Evento e WHERE e.tipoEvento = 'Intervencion' and e.mascota.id = :mascotaId");
		consulta.setParameter("mascotaId", m.getId());
		return (List<Intervencion>) consulta.getResultList();
	}

	@Override
	public List<Intervencion> recuperarIntervencionesPorVeterinario(Veterinario v) {
		Query consulta = this.getEntityManager()
				.createQuery("SELECT e FROM Evento e WHERE e.tipoEvento = 'Intervencion' and e.veterinario.id = :veterinarioId");
		consulta.setParameter("veterinarioId", v.getId());
		return (List<Intervencion>) consulta.getResultList();
	}

	@Override
	public List<Desparasitacion> recuperarDesparasitaciones() {
		Query consulta = this.getEntityManager()
				.createQuery("SELECT e FROM Evento e WHERE e.tipoEvento = 'Desparasitacion'");
		return (List<Desparasitacion>) consulta.getResultList();
	}

	@Override
	public List<Desparasitacion> recuperarDesparasitacionesPorMascota(Mascota m) {
		Query consulta = this.getEntityManager()
				.createQuery("SELECT e FROM Evento e WHERE e.tipoEvento = 'Desparasitacion' and e.mascota.id = :mascotaId");
		consulta.setParameter("mascotaId", m.getId());
		return (List<Desparasitacion>) consulta.getResultList();
	}

	@Override
	public List<Desparasitacion> recuperarDesparasitacionesPorVeterinario(Veterinario v) {
		Query consulta = this.getEntityManager()
				.createQuery("SELECT e FROM Evento e WHERE e.tipoEvento = 'Desparasitacion' and e.veterinario.id = :veterinarioId");
		consulta.setParameter("veterinarioId", v.getId());
		return (List<Desparasitacion>) consulta.getResultList();
	}

	@Override
	public List<Evento> recuperarEventos() {
		Query consulta = this.getEntityManager()
				.createQuery("SELECT e FROM Evento e");
		return (List<Evento>) consulta.getResultList();
	}

	@Override
	public List<Enfermedad> recuperarEnfermedades() {
		Query consulta = this.getEntityManager()
				.createQuery("SELECT e FROM Evento e WHERE e.tipoEvento = 'Enfermedad'");
		return (List<Enfermedad>) consulta.getResultList();
	}

	@Override
	public List<Enfermedad> recuperarEnfermedadesPorMascota(Mascota m) {
		Query consulta = this.getEntityManager()
				.createQuery("SELECT e FROM Evento e WHERE e.tipoEvento = 'Enfermedad' and e.mascota.id = :mascotaId");
		consulta.setParameter("mascotaId", m.getId());
		return (List<Enfermedad>) consulta.getResultList();
	}

	@Override
	public List<Enfermedad> recuperarEnfermedadesPorVeterinario(Veterinario v) {
		Query consulta = this.getEntityManager()
				.createQuery("SELECT e FROM Evento e WHERE e.tipoEvento = 'Enfermedad' and e.veterinario.id = :veterinarioId");
		consulta.setParameter("veterinarioId", v.getId());
		return (List<Enfermedad>) consulta.getResultList();
	}

	@Override
	public Mascota recuperarMascota(Evento e) {
		Mascota m = null;
		if(e.getMascota() != null) {
			Query consulta = this.getEntityManager()
					.createQuery("SELECT m FROM Mascota m WHERE m.id = :mascotaId");
			consulta.setParameter("mascotaId", e.getMascota().getId());
			List<Mascota> mList = (List<Mascota>) consulta.getResultList();
			if (mList.size() == 1) {
				m = mList.get(0);
			}
		}
		return m;
	}

	@Override
	public Veterinario recuperarVeterinario(Evento e) {
		Veterinario v = null;
		if(e.getVeterinario() != null) {
			Query consulta = this.getEntityManager()
					.createQuery("SELECT v FROM Veterinario v WHERE v.id = :veterinarioId");
			consulta.setParameter("veterinarioId", e.getVeterinario().getId());
			List<Veterinario> vList = (List<Veterinario>) consulta.getResultList();
			if (vList.size() == 1) {
				v = vList.get(0);
			}
		}
		return v;
	}

}
