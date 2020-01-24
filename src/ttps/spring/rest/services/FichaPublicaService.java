package ttps.spring.rest.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ttps.spring.dao.IFichaPublicaDAO;
import ttps.spring.model.FichaPublica;

@Service
public class FichaPublicaService {

	@Autowired
    private IFichaPublicaDAO fichaRepository;

	//retorna una mascota por slug
	public FichaPublica encontrar(String slug) {
        return fichaRepository.encontrar(slug);
    }

	//actualiza una Ficha Publica existente
	public FichaPublica actualizar(FichaPublica ficha) {
        return fichaRepository.actualizar(ficha);
    }

}
