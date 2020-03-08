package ttps.spring.rest.controllers;

import java.time.LocalDate;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ttps.spring.exceptions.BadRequestException;
import ttps.spring.exceptions.EventoNotFound;
import ttps.spring.exceptions.MascotaNotFoundException;
import ttps.spring.exceptions.TurnoNoValidoException;
import ttps.spring.exceptions.UserNotFoundException;
import ttps.spring.model.Duenio;
import ttps.spring.model.Evento;
import ttps.spring.model.Mascota;
import ttps.spring.model.Persona;
import ttps.spring.model.Turno;
import ttps.spring.model.Usuario;
import ttps.spring.model.Veterinario;
import ttps.spring.requests.EventoRequBody;
import ttps.spring.responses.HorariosResponse;
import ttps.spring.rest.services.DuenioService;
import ttps.spring.rest.services.EventoService;
import ttps.spring.rest.services.MascotaService;
import ttps.spring.rest.services.TurnoService;
import ttps.spring.rest.services.UsuarioService;
import ttps.spring.rest.services.VeterinarioService;

@RestController
@RequestMapping(value="/api/eventos", produces={MediaType.APPLICATION_JSON_VALUE})
public class EventoController {

	@Autowired
	private EventoService eventoService;
	@Autowired
	private TurnoService turnoService;
	@Autowired
	private UsuarioService usuService;
	@Autowired
	private MascotaService mascotaService;
	@Autowired
	private DuenioService duenioService;
	@Autowired
	private VeterinarioService vetService;

	 //Recupero todas los eventos
	@GetMapping
	public ResponseEntity<List<Evento>> listar() {
		List<Evento> eventos = eventoService.listar();
		if(eventos.isEmpty()){
			return new ResponseEntity<List<Evento>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Evento>>(eventos, HttpStatus.OK);
	}

	 //Recupero todas los eventos de un dia dado
	@GetMapping(params = "fecha")
	public ResponseEntity<HorariosResponse> listarXFecha(@RequestParam("fecha") String fechaP) {
		LocalDate fecha = LocalDate.parse(fechaP.toString());
		List<Turno> turnos = turnoService.listarXFecha(fecha);
		HorariosResponse hr = new HorariosResponse(fecha, turnos);
		return new ResponseEntity<HorariosResponse>(hr, HttpStatus.OK);
	}

	 //Recupero todas los eventos de un dia dado
	@GetMapping(params = {"fecha", "usuario"})
	public ResponseEntity<HorariosResponse> listarXFechaXUsuario(
			@RequestParam("fecha") String fechaP,
			@RequestParam("usuario") String usuario
		) {
		Usuario usu = usuService.recuperarUsuarioPorNombre(usuario);
		if(usu == null) {
	         throw new UserNotFoundException("Usuario no válido: " + usuario);
	    }
		Persona perfil = usu.getPersona();
		if(perfil == null) {
	         throw new UserNotFoundException("Perfil no encontrado." );
	    }
		try
		{
			LocalDate fecha = LocalDate.parse(fechaP.toString());
			List<Turno> turnos;
			HorariosResponse hr;
			switch (perfil.getRole()) {
				case "administrador":
					turnos = turnoService.listarXFecha(fecha);
					hr = new HorariosResponse(fecha, turnos);
					return new ResponseEntity<HorariosResponse>(hr, HttpStatus.OK);
				case "duenio":
					Duenio d = duenioService.encontrar(perfil.getId());
					if (d.getMascotas().isEmpty()) {
						return new ResponseEntity<HorariosResponse>(HttpStatus.NO_CONTENT);
					}
					turnos = turnoService.listarXFechaYMascotas(fecha, d.getMascotas());
					hr = new HorariosResponse(fecha, turnos);
					return new ResponseEntity<HorariosResponse>(hr, HttpStatus.OK);
				case "veterinario":
					Veterinario v = vetService.encontrar(perfil.getId());
					if (v.getMascotas().isEmpty()) {
						return new ResponseEntity<HorariosResponse>(HttpStatus.NO_CONTENT);
					}
					turnos = turnoService.listarXFechaYMascotas(fecha, v.getMascotas());
					hr = new HorariosResponse(fecha, turnos);
					return new ResponseEntity<HorariosResponse>(hr, HttpStatus.OK);
				default:
			        throw new BadRequestException("No existe ese rol." );
			}
		}
		catch(Exception ex)
		{
		    throw ex;
		}
	}

	//guarda una Solicitud de una mascota de un duenio para un veterinario
	@PostMapping
	public ResponseEntity<Evento> guardar(
			@Valid @RequestBody EventoRequBody ev
			) {
		Usuario usu = usuService.recuperarUsuarioPorNombre(ev.getUsername());
		if(usu == null) {
	         throw new UserNotFoundException("Usuario no válido: " + ev.getUsername());
	    }
		Persona perfil = usu.getPersona();
		if(perfil == null) {
	         throw new UserNotFoundException("Perfil no encontrado." );
	    }
		Mascota masc = mascotaService.encontrar(ev.getSlug());
		if(masc == null) {
	         throw new MascotaNotFoundException("Mascota no encontrada." );
	    }
		Evento e = ev.getEvento();
		if(e == null) {
	         throw new BadRequestException("Evento no válido." );
	    }
		try
		{
			Turno trn = new Turno(
					ev.getFecha(),
					ev.getInicio(),
					ev.getFin(),
					Turno.Estados.RESERVADO
				);
			if(!trn.isValid()) {
		         throw new TurnoNoValidoException("Turno no válido." );
			}
			e.setMascota(masc);
			e.setTurno(trn);
			Evento evento = null;
			switch (perfil.getRole()) {
				case "duenio":
					if(masc.getDuenio().getId() != perfil.getId()) {
				         throw new BadRequestException("Mascota no coincide con dueño." );
				    }
					//los dueños no pueden guardar fechas pasadas
					if(trn.getFecha().isBefore(LocalDate.now())) {
				         throw new TurnoNoValidoException("Turno no válido." );
					}
					evento = eventoService.guardar(e);
					return new ResponseEntity<Evento>(evento, HttpStatus.OK);
				case "veterinario":
					if(masc.getVeterinario().getId() != perfil.getId()) {
				         throw new BadRequestException("Mascota no tiene este veterinario." );
				    }
					e.getTurno().setEstado(ev.getEvento().getTurno().getEstado());
					evento = eventoService.guardar(e);
					return new ResponseEntity<Evento>(evento, HttpStatus.OK);
				default:
			        throw new BadRequestException("No existe ese rol." );
			}
		}
		catch(Exception ex)
		{
		    throw ex;
		}
	}

	//borra una mascota
	@DeleteMapping
	public ResponseEntity<Evento> eliminar(
			@RequestParam("username") String userName,
			@RequestParam("slg") String slug
			) {
		Usuario usu = usuService.recuperarUsuarioPorNombre(userName);
		if(usu == null) {
	         throw new UserNotFoundException("Usuario no válido: " + userName);
	    }
		Evento e = eventoService.encontrar(slug);
		if(e == null) {
	         throw new EventoNotFound("Evento no válido: " + userName);
	    }
		Persona perfil = usu.getPersona();
		if (perfil == null) {
	         throw new UserNotFoundException("Perfil no encontrado." );
	    }
		try
		{
			switch (perfil.getRole()) {
				case "administrador":
					eventoService.eliminar(e.getId());
					return new ResponseEntity<Evento>(HttpStatus.NO_CONTENT);
				case "duenio":
					if(
						e.getMascota() == null ||
						e.getMascota().getDuenio() == null ||
						e.getMascota().getDuenio().getId() != usu.getPersona().getId()) {
						throw new BadRequestException("Dueño no válido: " + userName);
					}
					eventoService.eliminar(e.getId());
					return new ResponseEntity<Evento>(HttpStatus.NO_CONTENT);
				case "veterinario":
					if(
						e.getMascota() == null ||
						e.getMascota().getVeterinario() == null ||
						e.getMascota().getVeterinario().getId() != usu.getPersona().getId()) {
						throw new BadRequestException("Veterinario no válido: " + userName);
					}
					eventoService.eliminar(e.getId());
					return new ResponseEntity<Evento>(HttpStatus.NO_CONTENT);
				default:
			        throw new BadRequestException("No role." );
			}
		}
		catch (Exception ex)
		{
			throw ex;
		}
	}

}
