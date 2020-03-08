package ttps.spring.rest.services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ttps.spring.dao.IEventoDAO;
import ttps.spring.model.Evento;

@Service
public class EventoService {

	@Autowired
    private IEventoDAO eventoRepository;

	//retorna un Evento por id
	public Evento encontrar(long id) {
        return eventoRepository.encontrar(id);
    }

	//retorna un Evento por slug
	public Evento encontrar(String slug) {
        return eventoRepository.encontrar(slug);
    }

	//comprueba si existe un Evento
	public Boolean existe(Evento e) {
        return eventoRepository.existe(e);
    }

	//guarda un Evento nuevo
	public Evento guardar(Evento e) {
        return eventoRepository.guardar(e);
    }

	//actualiza un Evento existente
	public Evento actualizar(Evento e) {
        return eventoRepository.actualizar(e);
    }

	//borra un Evento existente por id
	public void eliminar(long id) {
		eventoRepository.eliminar(id);
    }

	//retorna una lista de todos los eventos
	public List<Evento> listar() {
        return eventoRepository.recuperarEventos();
    }

	//retorna una lista de todos los eventos
	//para un dia dado
	public List<Evento> listarXFecha(LocalDate fecha) {
        return eventoRepository.recuperarEventosXFecha(fecha);
    }

}
