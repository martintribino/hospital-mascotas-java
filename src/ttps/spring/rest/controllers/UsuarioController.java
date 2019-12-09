package ttps.spring.rest.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import ttps.spring.exceptions.UnauthorizedException;
import ttps.spring.exceptions.UserNotFoundException;
import ttps.spring.model.Usuario;
import ttps.spring.responses.UsuarioResponse;
import ttps.spring.rest.services.UsuarioService;
import ttps.spring.security.JWToken;

@RestController
public class UsuarioController {

	public static final String LOGIN_URL = "/login";
	public static final String TOKEN_NAME = "X-JWToken";
	public static final String EXPOSE_HEADERS = "Access-Control-Expose-Headers";

	@Autowired
	private UsuarioService usuarioService;
	
	@RequestMapping(value=UsuarioController.LOGIN_URL, produces={MediaType.APPLICATION_JSON_VALUE})
	//retorna un Usuario por username
	@PostMapping()
	public @ResponseBody ResponseEntity<UsuarioResponse> login(@Valid @RequestBody Usuario usu) throws UserNotFoundException, UnauthorizedException {
		List<Usuario> listaUsuarios = usuarioService.recuperarUsuario(usu.getNombreUsuario());
		if(listaUsuarios.isEmpty()) {
	         throw new UserNotFoundException("Usuario no válido : " + usu.getNombreUsuario());
	    }
		Usuario usuario = listaUsuarios.get(0);
        if (usuario.verificarClave(usu.getClave())) {
        	String token = JWToken.generar(usuario.getNombreUsuario());
			HttpHeaders headers = new HttpHeaders();
			//headers.add(UsuarioController.TOKEN_NAME, token);
			//headers.add(UsuarioController.EXPOSE_HEADERS, UsuarioController.TOKEN_NAME);
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

}
