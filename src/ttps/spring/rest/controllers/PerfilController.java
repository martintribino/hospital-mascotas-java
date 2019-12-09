package ttps.spring.rest.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ttps.spring.model.Administrador;
import ttps.spring.model.Duenio;
import ttps.spring.model.Veterinario;
import ttps.spring.requests.PersonaReqBody;
import ttps.spring.responses.DefaultResponse;

@RestController
@RequestMapping(value="/profile", produces={MediaType.APPLICATION_JSON_VALUE})
public class PerfilController {

	@Autowired
	private AdministradorController adminCont;
	@Autowired
	private DuenioController duenioCont;
	@Autowired
	private VeterinarioController vetCont;
	
	//guarda un Perfil
	@PostMapping
	public ResponseEntity<?> guardar(@Valid @RequestBody PersonaReqBody p, HttpServletRequest request) {
		System.out.println(p.getRole());
		switch (p.getRole()) {
			case "administrador":
				Administrador a = new Administrador(p);
				return adminCont.guardar(a, request);
			case "duenio":
				Duenio d = new Duenio(p);
				return duenioCont.guardar(d, request);
			case "veterinario":
				Veterinario v = new Veterinario(p);
				return vetCont.guardar(v, request);
			default:
				DefaultResponse resp = new DefaultResponse();
				resp.setStatus(HttpStatus.BAD_REQUEST);
				resp.setStatusCode(HttpStatus.BAD_REQUEST.value());
				resp.setStatusText("No role");
				return new ResponseEntity<DefaultResponse>(resp, HttpStatus.BAD_REQUEST);
		}
	}

}
