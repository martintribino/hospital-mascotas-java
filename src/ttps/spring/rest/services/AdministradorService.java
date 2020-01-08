package ttps.spring.rest.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ttps.spring.dao.IAdministradorDAO;
import ttps.spring.model.Administrador;

@Service
public class AdministradorService {

	@Autowired
    private IAdministradorDAO adminRepository;

	//retorna un Administrador por id
	public Administrador encontrar(long id) {
        return adminRepository.encontrar(id);
    }

	//comprueba si existe un Administrador
	public Boolean existe(Administrador admin) {
        return adminRepository.existe(admin);
    }

	//guarda un Administrador nuevo
	public Administrador guardar(Administrador admin) {
        return adminRepository.guardar(admin);
    }

	//actualiza un Administrador existente
	public Administrador actualizar(Administrador admin) {
        return adminRepository.actualizar(admin);
    }

	//borra un Administrador existente por id
	public void eliminar(long id) {
		adminRepository.eliminar(id);
    }
	
	//retorna una lista de todos los Administradores
	public List<Administrador> listar() {
        return adminRepository.recuperarAdministradores();
    }

}
