package ttps.spring.rest.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ttps.spring.dao.IVeterinarioDAO;
import ttps.spring.model.Veterinario;

@Service
public class VeterinarioService {

	@Autowired
    private IVeterinarioDAO veterinarioRepository;

	//retorna un Veterinario por id
	public Veterinario encontrar(long id) {
        return veterinarioRepository.encontrar(id);
    }

	//comprueba si existe un Veterinario
	public Boolean existe(Veterinario mascota) {
        return veterinarioRepository.existe(mascota);
    }

	//guarda un Veterinario nueva
	public Veterinario guardar(Veterinario mascota) {
        return veterinarioRepository.guardar(mascota);
    }

	//actualiza un Veterinario existente
	public Veterinario actualizar(Veterinario mascota) {
        return veterinarioRepository.actualizar(mascota);
    }

	//borra un Veterinario existente por id
	public void eliminar(long id) {
		veterinarioRepository.eliminar(id);
    }
	
	//retorna una lista de todos los Veterinario
	public List<Veterinario> listar() {
        return veterinarioRepository.recuperarVeterinarios();
    }

}
