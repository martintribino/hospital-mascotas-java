package ttps.spring.implementation;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ttps.spring.dao.ITurnoDAO;
import ttps.spring.exceptions.TurnoNoValidoException;
import ttps.spring.model.Turno;

@Repository
@Transactional
public class TurnoDAOHibJPA extends GenericDAOHibJPA<Turno>
							implements ITurnoDAO {

	/**
	 * TurnoDAOHibJPA
	 */
	private static final long serialVersionUID = -4509649832370406540L;

	public TurnoDAOHibJPA() {
		super(Turno.class);
	}

	@Override
	public Turno encontrar(LocalDate fecha, LocalTime inicio, LocalTime fin) {
		Turno trn = null;
		TypedQuery<Turno> consulta = this.getEntityManager()
				.createQuery("SELECT t from Turno t WHERE (t.fecha = :tfecha and t.inicio = :tinicio and t.fin = :tfin)", Turno.class);
		consulta.setParameter("tfecha", fecha);
		consulta.setParameter("tinicio", inicio);
		consulta.setParameter("tfin", fin);
		try
		{
			trn = consulta.getSingleResult();
			return trn;
			
		}
		catch (Exception e)
		{
			return trn;
		}
	}

	@Override
	public Boolean estaDisponible(Turno turno) {
		TypedQuery<Turno> consulta = this.getEntityManager()
				.createQuery("SELECT t from Turno t WHERE (t.fecha = :tfecha and NOT (t.inicio > :tfin) and NOT (:tinicio > t.fin))", Turno.class);
		consulta.setParameter("tfecha", turno.getFecha());
		consulta.setParameter("tinicio", turno.getInicio());
		consulta.setParameter("tfin", turno.getFin());
		List<Turno> trns = consulta.getResultList();
		return trns.isEmpty();
	}

	@Override
	public Turno guardar(Turno turno) {
		if (turno.isValid() && this.estaDisponible(turno)) {
			return this.guardar(turno);
		} else
	        throw new TurnoNoValidoException("Turno no válido." );
	}

	@Override
	public List<Turno> listarXFecha(LocalDate fecha) {
		TypedQuery<Turno> consulta = this.getEntityManager()
				.createQuery("SELECT t from Turno t WHERE (t.fecha = :tfecha)", Turno.class);
		consulta.setParameter("tfecha", fecha);
		List<Turno> trns = consulta.getResultList();
		return trns;
	}

}
