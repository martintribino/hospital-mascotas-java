package ttps.spring.implementation;

import java.util.List;

import javax.persistence.TypedQuery;

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
		TypedQuery<Visita> consulta = this.getEntityManager()
				.createQuery("SELECT m FROM Evento m WHERE m.tipoEvento = 'Visita'", Visita.class);
		return (List<Visita>) consulta.getResultList();
	}

	@Override
	public List<Visita> recuperarVisitasPorVeterinario(Veterinario v) {
		TypedQuery<Visita> consulta = this.getEntityManager()
				.createQuery("SELECT e FROM Evento e WHERE e.tipoEvento = 'Visita' and e.veterinario.id = :veterinarioId", Visita.class);
		consulta.setParameter("veterinarioId", v.getId());
		return (List<Visita>) consulta.getResultList();
	}

	@Override
	public List<Visita> recuperarVisitasPorMascota(Mascota m) {
		TypedQuery<Visita> consulta = this.getEntityManager()
				.createQuery("SELECT e FROM Evento e WHERE e.tipoEvento = 'Visita' and e.mascota.id = :mascotaId", Visita.class);
		consulta.setParameter("mascotaId", m.getId());
		return (List<Visita>) consulta.getResultList();
	}

	@Override
	public List<Vacuna> recuperarVacunas() {
		TypedQuery<Vacuna> consulta = this.getEntityManager()
				.createQuery("SELECT e FROM Evento e WHERE e.tipoEvento = 'Vacuna'", Vacuna.class);
		return (List<Vacuna>) consulta.getResultList();
	}

	@Override
	public List<Vacuna> recuperarVacunasPorMascota(Mascota m) {
		TypedQuery<Vacuna> consulta = this.getEntityManager()
				.createQuery("SELECT e FROM Evento e WHERE e.tipoEvento = 'Vacuna' and e.mascota.id = :mascotaId", Vacuna.class);
		consulta.setParameter("mascotaId", m.getId());
		return (List<Vacuna>) consulta.getResultList();
	}

	@Override
	public List<Vacuna> recuperarVacunasPorVeterinario(Veterinario v) {
		TypedQuery<Vacuna> consulta = this.getEntityManager()
				.createQuery("SELECT e FROM Evento e WHERE e.tipoEvento = 'Vacuna' and e.veterinario.id = :mascotaId", Vacuna.class);
		consulta.setParameter("mascotaId", v.getId());
		return (List<Vacuna>) consulta.getResultList();
	}

	@Override
	public List<Reproduccion> recuperarReproducciones() {
		TypedQuery<Reproduccion> consulta = this.getEntityManager()
				.createQuery("SELECT m FROM Evento m WHERE m.tipoEvento = 'Reproduccion'", Reproduccion.class);
		return (List<Reproduccion>) consulta.getResultList();
	}

	@Override
	public List<Reproduccion> recuperarReproduccionesPorMascota(Mascota m) {
		TypedQuery<Reproduccion> consulta = this.getEntityManager()
				.createQuery("SELECT e FROM Evento e WHERE e.tipoEvento = 'Reproduccion' and e.mascota.id = :mascotaId", Reproduccion.class);
		consulta.setParameter("mascotaId", m.getId());
		return (List<Reproduccion>) consulta.getResultList();
	}

	@Override
	public List<Reproduccion> recuperarReproduccionesPorVeterinario(Veterinario v) {
		TypedQuery<Reproduccion> consulta = this.getEntityManager()
				.createQuery("SELECT e FROM Evento e WHERE e.tipoEvento = 'Reproduccion' and e.veterinario.id = :veterinarioId", Reproduccion.class);
		consulta.setParameter("veterinarioId", v.getId());
		return (List<Reproduccion>) consulta.getResultList();
	}

	@Override
	public List<Intervencion> recuperarIntervenciones() {
		TypedQuery<Intervencion> consulta = this.getEntityManager()
				.createQuery("SELECT m FROM Evento m WHERE m.tipoEvento = 'Intervencion'", Intervencion.class);
		return (List<Intervencion>) consulta.getResultList();
	}

	@Override
	public List<Intervencion> recuperarIntervencionesPorMascota(Mascota m) {
		TypedQuery<Intervencion> consulta = this.getEntityManager()
				.createQuery("SELECT e FROM Evento e WHERE e.tipoEvento = 'Intervencion' and e.mascota.id = :mascotaId", Intervencion.class);
		consulta.setParameter("mascotaId", m.getId());
		return (List<Intervencion>) consulta.getResultList();
	}

	@Override
	public List<Intervencion> recuperarIntervencionesPorVeterinario(Veterinario v) {
		TypedQuery<Intervencion> consulta = this.getEntityManager()
				.createQuery("SELECT e FROM Evento e WHERE e.tipoEvento = 'Intervencion' and e.veterinario.id = :veterinarioId", Intervencion.class);
		consulta.setParameter("veterinarioId", v.getId());
		return (List<Intervencion>) consulta.getResultList();
	}

	@Override
	public List<Desparasitacion> recuperarDesparasitaciones() {
		TypedQuery<Desparasitacion> consulta = this.getEntityManager()
				.createQuery("SELECT e FROM Evento e WHERE e.tipoEvento = 'Desparasitacion'", Desparasitacion.class);
		return (List<Desparasitacion>) consulta.getResultList();
	}

	@Override
	public List<Desparasitacion> recuperarDesparasitacionesPorMascota(Mascota m) {
		TypedQuery<Desparasitacion> consulta = this.getEntityManager()
				.createQuery("SELECT e FROM Evento e WHERE e.tipoEvento = 'Desparasitacion' and e.mascota.id = :mascotaId", Desparasitacion.class);
		consulta.setParameter("mascotaId", m.getId());
		return (List<Desparasitacion>) consulta.getResultList();
	}

	@Override
	public List<Desparasitacion> recuperarDesparasitacionesPorVeterinario(Veterinario v) {
		TypedQuery<Desparasitacion> consulta = this.getEntityManager()
				.createQuery("SELECT e FROM Evento e WHERE e.tipoEvento = 'Desparasitacion' and e.veterinario.id = :veterinarioId", Desparasitacion.class);
		consulta.setParameter("veterinarioId", v.getId());
		return (List<Desparasitacion>) consulta.getResultList();
	}

	@Override
	public List<Evento> recuperarEventos() {
		TypedQuery<Evento> consulta = this.getEntityManager()
				.createQuery("SELECT e FROM Evento e", Evento.class);
		return (List<Evento>) consulta.getResultList();
	}

	@Override
	public List<Enfermedad> recuperarEnfermedades() {
		TypedQuery<Enfermedad> consulta = this.getEntityManager()
				.createQuery("SELECT e FROM Evento e WHERE e.tipoEvento = 'Enfermedad'", Enfermedad.class);
		return (List<Enfermedad>) consulta.getResultList();
	}

	@Override
	public List<Enfermedad> recuperarEnfermedadesPorMascota(Mascota m) {
		TypedQuery<Enfermedad> consulta = this.getEntityManager()
				.createQuery("SELECT e FROM Evento e WHERE e.tipoEvento = 'Enfermedad' and e.mascota.id = :mascotaId", Enfermedad.class);
		consulta.setParameter("mascotaId", m.getId());
		return (List<Enfermedad>) consulta.getResultList();
	}

	@Override
	public List<Enfermedad> recuperarEnfermedadesPorVeterinario(Veterinario v) {
		TypedQuery<Enfermedad> consulta = this.getEntityManager()
				.createQuery("SELECT e FROM Evento e WHERE e.tipoEvento = 'Enfermedad' and e.veterinario.id = :veterinarioId", Enfermedad.class);
		consulta.setParameter("veterinarioId", v.getId());
		return (List<Enfermedad>) consulta.getResultList();
	}

	@Override
	public Mascota recuperarMascota(Evento e) {
		Mascota m = null;
		if(e.getMascota() != null) {
			TypedQuery<Mascota> consulta = this.getEntityManager()
					.createQuery("SELECT m FROM Mascota m WHERE m.id = :mascotaId", Mascota.class);
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
			TypedQuery<Veterinario> consulta = this.getEntityManager()
					.createQuery("SELECT v FROM Veterinario v WHERE v.id = :veterinarioId", Veterinario.class);
			consulta.setParameter("veterinarioId", e.getVeterinario().getId());
			List<Veterinario> vList = (List<Veterinario>) consulta.getResultList();
			if (vList.size() == 1) {
				v = vList.get(0);
			}
		}
		return v;
	}

}
