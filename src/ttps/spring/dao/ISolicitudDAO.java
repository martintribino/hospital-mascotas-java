package ttps.spring.dao;

import java.util.List;

import ttps.spring.dao.IGenericDAO;
import ttps.spring.model.Mascota;
import ttps.spring.model.Solicitud;
import ttps.spring.model.Veterinario;

public interface ISolicitudDAO extends IGenericDAO<Solicitud> {
	public Solicitud encontrar(String slug);
	public Solicitud encontrar(Long mascId, Long vetId);
	public List<Solicitud> recuperarSolicitudes();
	public List<Solicitud> recuperarSolicitudesPorMascota(Mascota m);
	public List<Solicitud> recuperarSolicitudesPorVeterinario(Veterinario v);
}
