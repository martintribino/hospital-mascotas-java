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
	public Boolean existe(Veterinario vet) {
        return veterinarioRepository.existe(vet);
    }

	//guarda un Veterinario nuevo
	public Veterinario guardar(Veterinario vet) {
        return veterinarioRepository.guardar(vet);
    }

	//actualiza un Veterinario existente
	public Veterinario actualizar(Veterinario vet) {
        return veterinarioRepository.actualizar(vet);
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
