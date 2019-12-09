package ttps.spring.rest.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ttps.spring.model.Encrypt;
import ttps.spring.model.Usuario;
import ttps.spring.model.Veterinario;
import ttps.spring.rest.services.VeterinarioService;

@RestController
@RequestMapping(value="/api/veterinarios", produces={MediaType.APPLICATION_JSON_VALUE})
public class VeterinarioController {

	@Autowired
	private VeterinarioService veterinarioService;

	 //Recupero todas los Veterinarios
	@GetMapping
	public ResponseEntity<List<Veterinario>> listar(HttpServletRequest request) {
		List<Veterinario> veterinarios = veterinarioService.listar();
		if(veterinarios.isEmpty()){
			return new ResponseEntity<List<Veterinario>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Veterinario>>(veterinarios, HttpStatus.OK);
	}
	
	//retorna un Veterinario por id
	@GetMapping("/{id}")
	public ResponseEntity<Veterinario> encontrar(@PathVariable("id") long id, HttpServletRequest request) {
		Veterinario veterinario = veterinarioService.encontrar(id);
		if (veterinario == null) {
			return new ResponseEntity<Veterinario>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Veterinario>(veterinario, HttpStatus.OK);
	}
	
	//guarda un Veterinario
	@PostMapping
	public ResponseEntity<Veterinario> guardar(@Valid @RequestBody Veterinario vet, HttpServletRequest request) {
		if (veterinarioService.existe(vet)) {
			return new ResponseEntity<Veterinario>(HttpStatus.CONFLICT);
		}
		Veterinario veterinario = new Veterinario();
		veterinario.setNombre(vet.getNombre());
		veterinario.setApellido(vet.getApellido());
		veterinario.setEmail(vet.getEmail());
		veterinario.setDni(vet.getDni());
		veterinario.setTelefono(vet.getTelefono());
		veterinario.setDomicilio(vet.getDomicilio());
		veterinario.setNombreClinica(vet.getNombreClinica());
		veterinario.setDomicilioClinica(vet.getDomicilioClinica());
		Usuario usuario = new Usuario();
		usuario.setNombreUsuario(vet.getUsuario().getNombreUsuario());
		String pass = Encrypt.encode(vet.getUsuario().getClave());
		usuario.setClave(pass);
		veterinario.setUsuario(usuario);
		Veterinario veterinarioCreated = (Veterinario) veterinarioService.guardar(veterinario);
		return new ResponseEntity<Veterinario>(veterinarioCreated, HttpStatus.CREATED);
	}
	
	//actualiza un Veterinario
	@PutMapping("/{id}")
	public ResponseEntity<Veterinario> actualizar(@PathVariable("id") long id, HttpServletRequest request,
											  @Valid @RequestBody Veterinario v) {
		Veterinario veterinario = veterinarioService.encontrar(id);
		if (veterinario == null) {
			return new ResponseEntity<Veterinario>(HttpStatus.NOT_FOUND);
		}
		veterinario.setNombre(v.getNombre());
		veterinario.setApellido(v.getApellido());
		veterinario.setEmail(v.getEmail());
		veterinario.setDni(v.getDni());
		veterinario.setTelefono(v.getTelefono());
		veterinario.setDomicilio(v.getDomicilio());
		veterinario.setNombreClinica(v.getNombreClinica());
		veterinario.setDomicilioClinica(v.getDomicilioClinica());
		Usuario usuario = new Usuario();
		usuario.setNombreUsuario(v.getUsuario().getNombreUsuario());
		String pass = Encrypt.encode(v.getUsuario().getClave());
		usuario.setClave(pass);
		veterinario.setUsuario(usuario);
		Veterinario veterinarioUpdated = (Veterinario) veterinarioService.actualizar(veterinario);
		return new ResponseEntity<Veterinario>(veterinarioUpdated, HttpStatus.OK);
	}
	
	//borra un Veterinario
	@DeleteMapping("/{id}")
	public ResponseEntity<Veterinario> eliminar(@PathVariable("id") long id, HttpServletRequest request) {
		Veterinario veterinario = veterinarioService.encontrar(id);
		if (veterinario == null) {
			return new ResponseEntity<Veterinario>(HttpStatus.NOT_FOUND);
		}
		veterinarioService.eliminar(id);
		return new ResponseEntity<Veterinario>(HttpStatus.NO_CONTENT);
	}

}