package ttps.spring.rest.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ttps.spring.dao.IDuenioDAO;
import ttps.spring.model.Duenio;

@Service
public class DuenioService {

	@Autowired
    private IDuenioDAO duenioRepository;

	//retorna un Dueño por id
	public Duenio encontrar(long id) {
        return duenioRepository.encontrar(id);
    }

	//comprueba si existe un Dueño
	public Boolean existe(Duenio mascota) {
        return duenioRepository.existe(mascota);
    }

	//guarda un Dueño nueva
	public Duenio guardar(Duenio mascota) {
        return duenioRepository.guardar(mascota);
    }

	//actualiza un Dueño existente
	public Duenio actualizar(Duenio mascota) {
        return duenioRepository.actualizar(mascota);
    }

	//borra un Dueño existente por id
	public void eliminar(long id) {
		duenioRepository.eliminar(id);
    }
	
	//retorna una lista de todos los Dueños
	public List<Duenio> listar() {
        return duenioRepository.recuperarDuenios();
    }

}
