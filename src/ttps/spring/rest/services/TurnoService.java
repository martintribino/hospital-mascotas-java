package ttps.spring.rest.services;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ttps.spring.dao.ITurnoDAO;
import ttps.spring.model.Mascota;
import ttps.spring.model.Turno;

@Service
public class TurnoService {

	@Autowired
    private ITurnoDAO turnoRepository;

	//retorna una mascota por el indice (fecha,inicio,fin)
	public Turno encontrar(LocalDate fecha, LocalTime inicio, LocalTime fin) {
        return turnoRepository.encontrar(fecha, inicio, fin);
    }

	//retorna si esta disponible
	public Boolean estaDisponible(Turno t) {
        return turnoRepository.estaDisponible(t);
    }

	//efectua una reserva, si esta disponible
	public Turno guardar(Turno t) {
        return turnoRepository.guardar(t);
    }

	//efectua una reserva, si esta disponible
	public List<Turno> listarXFecha(LocalDate f) {
        return turnoRepository.listarXFecha(f);
    }

	//efectua una reserva, si esta disponible x fecha y mascotas
	public List<Turno> listarXFechaYMascotas(LocalDate f, List<Mascota> mascotas) {
        return turnoRepository.listarXFechaYMascotas(f, mascotas);
    }

}
