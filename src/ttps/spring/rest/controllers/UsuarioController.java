package ttps.spring.rest.controllers;

import java.util.List;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import ttps.spring.exceptions.UnauthorizedException;
import ttps.spring.exceptions.UserInvalidKeyException;
import ttps.spring.exceptions.UserNotFoundException;
import ttps.spring.model.Encrypt;
import ttps.spring.model.Persona;
import ttps.spring.model.Usuario;
import ttps.spring.model.Veterinario;
import ttps.spring.requests.UsuarioReqBody;
import ttps.spring.responses.UsuarioResponse;
import ttps.spring.rest.services.UsuarioService;
import ttps.spring.rest.services.VeterinarioService;
import ttps.spring.security.JWToken;

@RestController
public class UsuarioController {

	public static final String LOGIN_URL = "/login";
	public static final String TOKEN_NAME = "X-JWToken";
	public static final String EXPOSE_HEADERS = "Access-Control-Expose-Headers";

	@Autowired
	private UsuarioService usuarioService;
	@Autowired
	private VeterinarioService vetService;

	//retorna un Usuario por username, mas conocido como login (:
	@RequestMapping(value=UsuarioController.LOGIN_URL, produces={MediaType.APPLICATION_JSON_VALUE})
	@PostMapping()
	public @ResponseBody ResponseEntity<UsuarioResponse> login(@Valid @RequestBody Usuario usu) {
		List<Usuario> listaUsuarios = usuarioService.recuperarUsuario(usu.getNombreUsuario());
		if(listaUsuarios.isEmpty()) {
			throw new UserNotFoundException("Usuario no válido : " + usu.getNombreUsuario());
	    }
		Usuario usuario = listaUsuarios.get(0);
		Persona perfil = usuario.getPersona();
		if(perfil == null) {
			throw new UserNotFoundException("Perfil no encontrado." );
	    }
		if(perfil.getRole().contains("veterinario")) {
			Veterinario v = vetService.encontrar(perfil.getId());
			if (v == null) {
				throw new UserNotFoundException("Veterinario no encontrado." );
			}
			if (!v.getValidado()) {
				throw new UnauthorizedException("Veterinario no validado." );
			}
	    }
        if (usuario.verificarClave(usu.getClave())) {
        	String token = JWToken.generar(usuario.getNombreUsuario());
			HttpHeaders headers = new HttpHeaders();
			UsuarioResponse resp = new UsuarioResponse();
			resp.setNombreUsuario(usuario.getNombreUsuario());
			resp.setRole(usuario.getRole());
			resp.setToken(token);
			resp.setImagen(usuario.getImagen());
    		return new ResponseEntity<UsuarioResponse>(resp, headers, HttpStatus.OK);
        } else {
        	throw new UnauthorizedException("Usuario no autorizado : " + usu.getNombreUsuario());
        }
	}

	//edita un usuario
	@RequestMapping(value="/api/usuario/editar", produces={MediaType.APPLICATION_JSON_VALUE})
	@PutMapping()
	public @ResponseBody ResponseEntity<UsuarioResponse> editarUsuario(
			@Valid @RequestBody UsuarioReqBody usu,
			ServletRequest request
			) throws UserNotFoundException, UserInvalidKeyException {
		Usuario usuario = usuarioService.recuperarUsuarioPorNombre(usu.getNombreUsuarioViejo());
		if(usuario == null) {
	         throw new UserNotFoundException("Usuario no válido : " + usu.getNombreUsuario());
	    }
		if(!usuario.getClave().isEmpty()) {
			//se queda con la que tiene
			if(usuario.getClave().equals(usu.getClave())) {
		         throw new UserInvalidKeyException("La clave actual debe ser distinta a la anterior.");
			}
			if(!usu.getClave().equals(usu.getConfirmarClave())) {
		         throw new UserInvalidKeyException("Las claves deben coincidir.");
			}
		}
		HttpHeaders headers = new HttpHeaders();
		try
		{
			if (usuario.getNombreUsuario() != usu.getNombreUsuario())
				usuario.setNombreUsuario(usu.getNombreUsuario());
			if(!usuario.getClave().isEmpty()) {
				String pass = Encrypt.encode(usu.getClave());
				usuario.setClave(pass);
			}
			this.usuarioService.actualizar(usuario);
			HttpServletRequest req = (HttpServletRequest) request;
	        String token = JWToken.getToken(req);
			UsuarioResponse usuResp = new UsuarioResponse();
			usuResp.setNombreUsuario(usuario.getNombreUsuario());
			usuResp.setImagen(usuario.getImagen());
			usuResp.setRole(usuario.getRole());
			usuResp.setToken(token);
    		return new ResponseEntity<UsuarioResponse>(usuResp, headers, HttpStatus.OK);
		}
		catch(Exception e) {
	         throw new UserInvalidKeyException("Las claves deben coincidir.");
		}
	}

}
