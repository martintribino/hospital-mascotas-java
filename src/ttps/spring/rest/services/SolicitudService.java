package ttps.spring.rest.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ttps.spring.dao.ISolicitudDAO;
import ttps.spring.model.Mascota;
import ttps.spring.model.Solicitud;
import ttps.spring.model.Veterinario;

@Service
public class SolicitudService {

	@Autowired
    private ISolicitudDAO solicitudRepo;

	//retorna una Solicitud por id
	public Solicitud encontrar(long id) {
        return solicitudRepo.encontrar(id);
    }

	//retorna una Solicitud por slug
	public Solicitud encontrar(String slug) {
        return solicitudRepo.encontrar(slug);
    }

	//retorna una Solicitud por mascota id y veterinario id
	public Solicitud encontrar(long mascId, long vetId) {
        return solicitudRepo.encontrar(mascId, vetId);
    }

	//comprueba si existe una Solicitud
	public Boolean existe(Solicitud solicitud) {
        return solicitudRepo.existe(solicitud);
    }

	//guarda una Solicitud nueva
	public Solicitud guardar(Solicitud solicitud) {
        return solicitudRepo.guardar(solicitud);
    }

	//actualiza una Solicitud existente
	public Solicitud actualizar(Solicitud solicitud) {
        return solicitudRepo.actualizar(solicitud);
    }

	//borra una Solicitud existente por id
	public void eliminar(long id) {
		solicitudRepo.eliminar(id);
    }

	//borra solicitud para un determinado veterinario
	public void eliminarXVeterinario(Veterinario v) {
		solicitudRepo.eliminarXVeterinario(v);
    }

	//borra solicitud para un determinado veterinario
	public void eliminarXMascota(Mascota m) {
		solicitudRepo.eliminarXMascota(m);
    }
	
	//retorna una lista de todos las Solicitudes
	public List<Solicitud> listar() {
        return solicitudRepo.recuperarSolicitudes();
    }
	
	//retorna una lista de todos las Solicitudes de una Mascota
	public List<Solicitud> listarXMascota(Mascota m) {
        return solicitudRepo.recuperarSolicitudesPorMascota(m);
    }
	
	//retorna una lista de todos las Solicitudes de un Veterinario
	public List<Solicitud> listarXVeterinario(Veterinario v) {
        return solicitudRepo.recuperarSolicitudesPorVeterinario(v);
    }
	
	//retorna una lista de todos las Solicitudes de un Veterinario
	public List<Solicitud> listarXVeterinarioYEstado(Veterinario v, Solicitud.Estados e) {
        return solicitudRepo.recuperarSolicitudesPorVeterinarioYEstado(v, e);
    }

}
