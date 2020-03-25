package ttps.spring.rest.services;

import java.lang.reflect.Field;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ttps.spring.dao.IMascotaDAO;
import ttps.spring.exceptions.BadRequestException;
import ttps.spring.model.Mascota;
import ttps.spring.model.Veterinario;

@Service
public class MascotaService {

	@Autowired
    private IMascotaDAO mascotaRepository;

	//retorna una mascota por id
	public Mascota encontrar(long id) {
        return mascotaRepository.encontrar(id);
    }

	//retorna una mascota por slug
	public Mascota encontrar(String slug) {
        return mascotaRepository.encontrar(slug);
    }

	//comprueba si existe una mascota
	public Boolean existe(Mascota mascota) {
        return mascotaRepository.existe(mascota);
    }

	//guarda una mascota nueva
	public Mascota guardar(Mascota mascota) {
        return mascotaRepository.guardar(mascota);
    }

	//actualiza una mascota existente
	public Mascota actualizar(Mascota mascota) {
        return mascotaRepository.actualizar(mascota);
    }

	//borra una mascota existente por id
	public void eliminar(long id) {
        mascotaRepository.eliminar(id);
    }

	//aprueba una Solicitud existente
	public void agregarVeterinario(Mascota mascota, Veterinario veterinario) {
		mascotaRepository.agregarVeterinario(mascota, veterinario);
    }

	//actualiza una mascota
	public void actualizarMascota(Mascota mascota, String imagen) {
		mascotaRepository.actualizarMascota(mascota, imagen);
    }

	//aprueba una Solicitud existente
	public void removerVeterinario(Mascota mascota) {
		mascotaRepository.removerVeterinario(mascota);
    }

	//retorna una lista de todas las mascotas
	public List<Mascota> listar() {
        return mascotaRepository.recuperarMascotas();
    }

	//retorna una lista de todas las mascotas
	public List<Mascota> listarFiltro(String filtro) {
		if (filtro.equals("extraviadas"))
			return mascotaRepository.recuperarMascotasExtraviadas();
		else
	        return mascotaRepository.recuperarMascotas();
    }

	//retorna una lista de mascotas por dueno
	public List<Mascota> listarPorDuenio(long id) {
        return mascotaRepository.recuperarMascotasPorDuenio(id);
    }

	//retorna una lista de mascotas por veterinario
	public List<Mascota> listarPorVeterinario(long id) {
        return mascotaRepository.recuperarMascotasPorVeterinario(id);
    }

	//retorna una lista de mascotas por dueno y criterio
	public List<Mascota> listarPorDuenioCriteria(long id, String criteria, String search) {
		try
		{
			Field field = Mascota.class.getDeclaredField(criteria);
			return mascotaRepository.recuperarMascotasPorDuenioCriteria(id, field.getName(), search);
		}
		catch (Exception e)
		{
	        throw new BadRequestException("Invalid criteria." );
		}
    }

	//retorna una lista de mascotas por veterinario y criterio
	public List<Mascota> listarPorVeterinarioCriteria(long id, String criteria, String search) {
		try
		{
			Field field = Mascota.class.getField(criteria);
			return mascotaRepository.recuperarMascotasPorVeterinarioCriteria(id, field.getName(), search);
		}
		catch (Exception e)
		{
	        throw new BadRequestException("Invalid criteria." );
		}
    }

}
