package ttps.spring.rest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ttps.spring.exceptions.BadRequestException;
import ttps.spring.exceptions.DuplicateEntityException;
import ttps.spring.exceptions.MascotaNotFoundException;
import ttps.spring.exceptions.UserNotFoundException;
import ttps.spring.model.Mascota;
import ttps.spring.model.Persona;
import ttps.spring.model.Solicitud;
import ttps.spring.model.Usuario;
import ttps.spring.model.Veterinario;
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
	private VeterinarioService vetServ;
	@Autowired
	private SolicitudService solicitudService;
	
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
	         throw new UserNotFoundException("Veterinario no válido: " + userName);
	    }
		Persona perfil = vUsu.getPersona();
		if(perfil == null) {
	         throw new UserNotFoundException("Perfil no encontrado." );
	    }
		Veterinario v = vetServ.encontrar(perfil.getId());
		if(v == null) {
	         throw new UserNotFoundException("Veterinario no encontrado." );
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
					if (s == null) {
						s = new Solicitud();
						s.setMascota(mascota);
						s.setVeterinario(v);
						s.setEstado(Solicitud.Estados.ESPERA);
						solicitudService.guardar(s);
					} else {
						if (s.getEstado() == Solicitud.Estados.APROBADO || s.getEstado() == Solicitud.Estados.ESPERA)
					         throw new DuplicateEntityException("Solicitud duplicada." );
						s.setEstado(Solicitud.Estados.ESPERA);
						solicitudService.actualizar(s);
					}
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

}
