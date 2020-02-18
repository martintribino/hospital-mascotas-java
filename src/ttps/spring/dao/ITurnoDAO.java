package ttps.spring.dao;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import ttps.spring.model.Turno;

public interface ITurnoDAO extends IGenericDAO<Turno> {
	public Turno encontrar(LocalDate fecha, LocalTime inicio, LocalTime fin);
	public Boolean estaDisponible(Turno turno);
	public List<Turno> listarXFecha(LocalDate fecha);
}
