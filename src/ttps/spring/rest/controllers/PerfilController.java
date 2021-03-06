package ttps.spring.rest.controllers;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ttps.spring.exceptions.BadRequestException;
import ttps.spring.exceptions.UnauthorizedException;
import ttps.spring.exceptions.UserNotFoundException;
import ttps.spring.model.Administrador;
import ttps.spring.model.Duenio;
import ttps.spring.model.Mascota;
import ttps.spring.model.Persona;
import ttps.spring.model.Usuario;
import ttps.spring.model.Veterinario;
import ttps.spring.requests.PerfilReqBody;
import ttps.spring.requests.PersonaReqBody;
import ttps.spring.responses.DefaultResponse;
import ttps.spring.rest.services.DuenioService;
import ttps.spring.rest.services.UsuarioService;
import ttps.spring.rest.services.VeterinarioService;

@RestController
public class PerfilController {

	@Autowired
	private AdministradorController adminCont;
	@Autowired
	private UsuarioService usuService;
	@Autowired
	private DuenioController duenioCont;
	@Autowired
	private DuenioService duenioService;
	@Autowired
	private VeterinarioController vetCont;
	@Autowired
	private VeterinarioService vetService;
	
	//guarda un Perfil
	@PostMapping(value="/usuario/profile", produces={MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<?> guardar(@Valid @RequestBody PersonaReqBody p) {
		try
		{
			switch (p.getRole()) {
				case "administrador":
					Administrador a = new Administrador(p);
					return adminCont.guardar(a);
				case "duenio":
					Duenio d = new Duenio(p);
					return duenioCont.guardar(d);
				case "veterinario":
					Veterinario v = new Veterinario(p);
					return vetCont.guardar(v);
				default:
			        throw new BadRequestException("No role." );
			}
		}
		catch(Exception ex)
		{
		    throw ex;
		}
	}
	
	//lista un Perfil
	@GetMapping(value="/api/usuario/profile", produces={MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Persona> listar(@RequestParam("username") String userName, HttpServletRequest request) {
		Usuario usu = usuService.recuperarUsuarioPorNombre(userName);
		if(usu == null) {
	         throw new UserNotFoundException("Usuario no válido: " + userName);
	    }
		Persona perfil = usu.getPersona();
		if(perfil == null) {
	         throw new UserNotFoundException("Perfil no encontrado." );
	    }
		return new ResponseEntity<Persona>(perfil, HttpStatus.OK);
	}
	
	//actualiza un Perfil
	@PutMapping(value="/api/usuario/profile", produces={MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<?> actualizar(
			@RequestParam("username") String userName, 
			@Valid @RequestBody PerfilReqBody perfReq,
			HttpServletRequest request) {
		Usuario usu = usuService.recuperarUsuarioPorNombre(userName);
		if(usu == null) {
	         throw new UserNotFoundException("Usuario no válido: " + userName);
	    }
		Persona perfil = usu.getPersona();
		if(perfil == null) {
	         throw new UserNotFoundException("Perfil no encontrado." );
	    }
		try
		{
			switch (perfil.getRole()) {
				case "administrador":
					Administrador a = new Administrador(
							perfReq.getNombre(),
							perfReq.getApellido(),
							perfReq.getEmail(),
							perfReq.getDni(),
							perfReq.getTelefono(),
							perfReq.getDomicilio(),
							usu);
					return adminCont.actualizar(perfil.getId(), a);
				case "duenio":
					Duenio d = new Duenio(
							perfReq.getNombre(),
							perfReq.getApellido(),
							perfReq.getEmail(),
							perfReq.getDni(),
							perfReq.getTelefono(),
							perfReq.getDomicilio(),
							usu,
							new ArrayList<Mascota>());
					return duenioCont.actualizar(perfil.getId(), d);
				case "veterinario":
					Veterinario v = new Veterinario(
							perfReq.getNombre(),
							perfReq.getApellido(),
							perfReq.getEmail(),
							perfReq.getDni(),
							perfReq.getTelefono(),
							perfReq.getDomicilio(),
							usu,
							perfReq.getNombreClinica(),
							perfReq.getDomicilioClinica(),
							perfReq.getValidado());
					return vetCont.actualizar(perfil.getId(), v);
				default:
					DefaultResponse resp = new DefaultResponse();
					resp.setStatus(HttpStatus.BAD_REQUEST);
					resp.setStatusCode(HttpStatus.BAD_REQUEST.value());
					resp.setStatusText("No role");
					return new ResponseEntity<DefaultResponse>(resp, HttpStatus.BAD_REQUEST);
			}
		}
		catch(Exception ex)
		{
		    throw ex;
		}
	}
	
	//borra un perfil
	//solo se permite para un usuario administrador
	@DeleteMapping(value="/api/usuario/profile", produces={MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Persona> eliminar(
			@RequestParam("username") String userName,
			@RequestParam("owner") String owner
			) {
		Usuario usu = usuService.recuperarUsuarioPorNombre(userName);
		if(usu == null) {
	         throw new UserNotFoundException("Usuario no válido: " + userName);
	    }
		Persona perfil = usu.getPersona();
		if(perfil == null) {
	         throw new UserNotFoundException("Perfil no encontrado." );
	    }
		Usuario admin = usuService.recuperarUsuarioPorNombre(owner);
		if(admin == null) {
	         throw new UnauthorizedException("Administrador no válido.");
	    }
		Persona perfilAdmin = admin.getPersona();
		if(perfilAdmin == null) {
	         throw new UnauthorizedException("Administrador no encontrado." );
	    }
		if(!perfilAdmin.getRole().contains("administrador")) {
	         throw new UnauthorizedException("Usuario no tiene permisos." );
	    }
		try
		{
			switch (perfil.getRole()) {
				case "duenio":
					duenioService.eliminar(perfil.getId());
					return new ResponseEntity<Persona>(HttpStatus.NO_CONTENT);
				case "veterinario":
					vetService.eliminar(perfil.getId());
					return new ResponseEntity<Persona>(HttpStatus.NO_CONTENT);
				default:
			        throw new BadRequestException("No role." );
			}
		}
		catch(Exception ex)
		{
		    throw ex;
		}
	}
	
	//valida un Perfil
	@PutMapping(value="/api/usuario/profile/validar", produces={MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Persona> validarVeterinario(
			@RequestParam("username") String userName,
			@RequestParam("owner") String owner) {
		Usuario usu = usuService.recuperarUsuarioPorNombre(userName);
		if(usu == null) {
	         throw new UserNotFoundException("Usuario no válido: " + userName);
	    }
		Persona perfil = usu.getPersona();
		if(perfil == null) {
	         throw new UserNotFoundException("Perfil no encontrado." );
	    }
		Usuario admin = usuService.recuperarUsuarioPorNombre(owner);
		if(admin == null) {
	         throw new UnauthorizedException("Administrador no válido.");
	    }
		Persona perfilAdmin = admin.getPersona();
		if(perfilAdmin == null) {
	         throw new UnauthorizedException("Administrador no encontrado." );
	    }
		if(!perfilAdmin.getRole().contains("administrador")) {
	         throw new UnauthorizedException("Usuario no tiene permiso." );
	    }
		try
		{
			switch (perfil.getRole()) {
				case "veterinario":
					Veterinario v = vetService.encontrar(perfil.getId());
					if (v == null)
				        throw new UserNotFoundException("Veterinario not found." );
					v.setValidado(true);
					Veterinario vResp = vetService.actualizar(v);
					return new ResponseEntity<Persona>(vResp, HttpStatus.OK);
				default:
			        throw new BadRequestException("No veterinario role." );
			}
		}
		catch(Exception ex)
		{
		    throw ex;
		}
	}
	
	//chequea que exista el token y sea correcto
	//Se hace con el filtro en /api/
	@GetMapping()
	@RequestMapping(value="/api/usuario/check-token", produces={MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Persona> checkToken(HttpServletRequest request) {
		return new ResponseEntity<Persona>(HttpStatus.NO_CONTENT);
	}

}
