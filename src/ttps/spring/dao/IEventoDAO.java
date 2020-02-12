package ttps.spring.dao;

import java.util.List;

import ttps.spring.dao.IGenericDAO;
import ttps.spring.model.Desparasitacion;
import ttps.spring.model.Enfermedad;
import ttps.spring.model.Evento;
import ttps.spring.model.Intervencion;
import ttps.spring.model.Mascota;
import ttps.spring.model.Reproduccion;
import ttps.spring.model.Vacuna;
import ttps.spring.model.Veterinario;
import ttps.spring.model.Visita;

public interface IEventoDAO extends IGenericDAO<Evento> {
	public Evento encontrar(String slug);
	public List<Visita> recuperarVisitas();
	public List<Visita> recuperarVisitasPorVeterinario(Veterinario v);
	public List<Visita> recuperarVisitasPorMascota(Mascota m);
	public List<Vacuna> recuperarVacunas();
	public List<Vacuna> recuperarVacunasPorMascota(Mascota m);
	public List<Vacuna> recuperarVacunasPorVeterinario(Veterinario v);
	public List<Reproduccion> recuperarReproducciones();
	public List<Reproduccion> recuperarReproduccionesPorMascota(Mascota m);
	public List<Reproduccion> recuperarReproduccionesPorVeterinario(Veterinario v);
	public List<Intervencion> recuperarIntervenciones();
	public List<Intervencion> recuperarIntervencionesPorMascota(Mascota m);
	public List<Intervencion> recuperarIntervencionesPorVeterinario(Veterinario v);
	public List<Desparasitacion> recuperarDesparasitaciones();
	public List<Desparasitacion> recuperarDesparasitacionesPorMascota(Mascota m);
	public List<Desparasitacion> recuperarDesparasitacionesPorVeterinario(Veterinario v);
	public List<Evento> recuperarEventos();
	public List<Enfermedad> recuperarEnfermedades();
	public List<Enfermedad> recuperarEnfermedadesPorMascota(Mascota m);
	public List<Enfermedad> recuperarEnfermedadesPorVeterinario(Veterinario v);
	public Mascota recuperarMascota(Evento e);
	public Veterinario recuperarVeterinario(Evento e);
}
