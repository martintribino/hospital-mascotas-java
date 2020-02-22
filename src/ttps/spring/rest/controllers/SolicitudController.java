package ttps.spring.rest.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ttps.spring.exceptions.BadRequestException;
import ttps.spring.exceptions.DuplicateEntityException;
import ttps.spring.exceptions.MascotaNotFoundException;
import ttps.spring.exceptions.SolicitudNotFoundException;
import ttps.spring.exceptions.UserNotFoundException;
import ttps.spring.model.Mascota;
import ttps.spring.model.Persona;
import ttps.spring.model.Solicitud;
import ttps.spring.model.Usuario;
import ttps.spring.model.Veterinario;
import ttps.spring.requests.SolicitudReqBody;
import ttps.spring.rest.services.MascotaService;
import ttps.spring.rest.services.SolicitudService;
import ttps.spring.rest.services.UsuarioService;
import ttps.spring.rest.services.VeterinarioService;

@RestController
@RequestMapping(value="/api/solicitud/", produces={MediaType.APPLICATION_JSON_VALUE})
public class SolicitudController {

	@Autowired
	private UsuarioService usuService;
	@Autowired
	private MascotaService mascotaService;
	@Autowired
	private VeterinarioService vetService;
	@Autowired
	private SolicitudService solicitudService;

	//obtiene las solicitudes para un veterinario dado un veterinario
	@GetMapping
	public ResponseEntity<List<Solicitud>> listarXUsuario(
			@RequestParam("username") String userName
	) {
		Usuario usu = usuService.recuperarUsuarioPorNombre(userName);
		if(usu == null) {
	         throw new UserNotFoundException("Usuario no válido: " + userName);
	    }
		Persona perfil = usu.getPersona();
		if(perfil == null) {
	         throw new UserNotFoundException("Perfil no encontrado." );
	    }
		Veterinario v = vetService.encontrar(perfil.getId());
		if(v == null) {
	         throw new UserNotFoundException("Veterinario no encontrado." );
	    }
		try
		{
			List<Solicitud> s = solicitudService.listarXVeterinario(v);
			return new ResponseEntity<List<Solicitud>>(s, HttpStatus.OK);
		}
		catch(Exception ex)
		{
			throw ex;
		}
	}

	//obtiene las solicitudes para un veterinario dado un veterinario
	// y filtrado por estado
	@GetMapping("/{estado}")
	public ResponseEntity<List<Solicitud>> listarXUsuarioYEstado(
			@PathVariable Solicitud.Estados estado,
			@RequestParam("username") String userName
	) {
		Usuario usu = usuService.recuperarUsuarioPorNombre(userName);
		if(usu == null) {
	         throw new UserNotFoundException("Usuario no válido: " + userName);
	    }
		Persona perfil = usu.getPersona();
		if(perfil == null) {
	         throw new UserNotFoundException("Perfil no encontrado." );
	    }
		Veterinario v = vetService.encontrar(perfil.getId());
		if(v == null) {
	         throw new UserNotFoundException("Veterinario no encontrado." );
	    }
		try
		{
			List<Solicitud> s = solicitudService.listarXVeterinarioYEstado(v, estado);
			return new ResponseEntity<List<Solicitud>>(s, HttpStatus.OK);
		}
		catch(Exception ex)
		{
			throw ex;
		}
	}

	//guarda una Solicitud de una mascota de un duenio para un veterinario
	@PostMapping
	public ResponseEntity<Solicitud> guardar(
			@RequestParam("username") String userName,
			@RequestParam("slg") String slug,
			@RequestParam("vusername") String vUserName
			) {
		Usuario usu = usuService.recuperarUsuarioPorNombre(userName);
		if(usu == null) {
	         throw new UserNotFoundException("Usuario no válido: " + userName);
	    }
		Mascota mascota = mascotaService.encontrar(slug);
		if (mascota == null) {
	         throw new MascotaNotFoundException("No se encontró mascota.");
		}
		Usuario vUsu = usuService.recuperarUsuarioPorNombre(vUserName);
		if(vUsu == null) {
	         throw new UserNotFoundException("Veterinario no válido: " + vUserName);
	    }
		Persona perfil = vUsu.getPersona();
		if(perfil == null) {
	         throw new UserNotFoundException("Perfil no encontrado." );
	    }
		Veterinario v = vetService.encontrar(perfil.getId());
		if(v == null) {
	         throw new UserNotFoundException("Veterinario no encontrado." );
	    }
		if (mascota.getVeterinario() != null &&
				mascota.getVeterinario().getId() == v.getId()) {
			throw new DuplicateEntityException("Actualmente este es su veterinario." );
		}
		try
		{
			switch (perfil.getRole()) {
				case "administrador":
			        throw new BadRequestException("Access denied." );
				case "duenio":
			        throw new BadRequestException("Access denied." );
				case "veterinario":
					Solicitud s = solicitudService.encontrar(mascota.getId(), v.getId());
					if (s != null) {
						throw new DuplicateEntityException("Solicitud duplicada." );
					}
					s = new Solicitud();
					s.setMascota(mascota);
					s.setVeterinario(v);
					s.setEstado(Solicitud.Estados.ESPERA);
					solicitudService.guardar(s);
					return new ResponseEntity<Solicitud>(s, HttpStatus.OK);
				default:
			        throw new BadRequestException("No role." );
			}
		}
		catch(Exception ex)
		{
			throw ex;
		}
	}

	//actualiza una Solicitud de una mascota de un duenio para un veterinario
	@PutMapping
	public ResponseEntity<Solicitud> actualizar(
			@Valid @RequestBody SolicitudReqBody solicitudParam
	) {
		Solicitud solicitud = solicitudService.encontrar(solicitudParam.getSlug());
		if (solicitud == null) {
	         throw new SolicitudNotFoundException("No se encontró solicitud.");
		}
		Veterinario veterinario = solicitud.getVeterinario();
		if (veterinario == null) {
	         throw new UserNotFoundException("Veterinario no encontrado." );
		}
		Mascota mascota = solicitud.getMascota();
		if (mascota == null) {
	         throw new MascotaNotFoundException("No se encontró mascota.");
		}
		try
		{
			solicitud.setEstado(solicitudParam.getEstado());
			switch (solicitud.getEstado()) {
			case APROBADO:
				mascotaService.agregarVeterinario(mascota, veterinario);
				solicitudService.eliminarXMascota(mascota);
				break;
			case ESPERA:
				solicitudService.actualizar(solicitud);
				break;
			case RECHAZADO:
				solicitudService.eliminar(solicitud.getId());
				break;
			default:
				solicitudService.actualizar(solicitud);
				break;
			}
			return new ResponseEntity<Solicitud>(solicitud, HttpStatus.NO_CONTENT);
		}
		catch (Exception ex) {
			throw ex;
		}
	}

}
