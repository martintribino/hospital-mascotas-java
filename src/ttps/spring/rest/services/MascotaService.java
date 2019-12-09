package ttps.spring.rest.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ttps.spring.dao.IMascotaDAO;
import ttps.spring.model.Mascota;

@Service
public class MascotaService {

	@Autowired
    private IMascotaDAO mascotaRepository;

	//retorna una mascota por id
	public Mascota encontrar(long id) {
        return mascotaRepository.encontrar(id);
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
	
	//retorna una lista de todas las mascotas
	public List<Mascota> listar() {
        return mascotaRepository.recuperarMascotas();
    }
	
	//retorna una lista de mascotas por dueno 
	public List<Mascota> listarPorDuenio(long id) {
        return mascotaRepository.recuperarMascotasPorDuenio(id);
    }
	
	//retorna una lista de mascotas por dueno 
	public List<Mascota> listarPorVeterinario(long id) {
        return mascotaRepository.recuperarMascotasPorVeterinario(id);
    }

}