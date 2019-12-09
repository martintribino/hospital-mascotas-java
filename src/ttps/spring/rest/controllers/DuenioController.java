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

import ttps.spring.model.Duenio;
import ttps.spring.model.Encrypt;
import ttps.spring.model.Usuario;
import ttps.spring.rest.services.DuenioService;

@RestController
@RequestMapping(value="/api/duenios", produces={MediaType.APPLICATION_JSON_VALUE})
public class DuenioController {

	@Autowired
	private DuenioService duenioService;

	 //Recupero todas los Dueños
	@GetMapping
	public ResponseEntity<List<Duenio>> listar(HttpServletRequest request) {
		List<Duenio> duenios = duenioService.listar();
		if(duenios.isEmpty()){
			return new ResponseEntity<List<Duenio>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Duenio>>(duenios, HttpStatus.OK);
	}
	
	//retorna un Duenio por id
	@GetMapping("/{id}")
	public ResponseEntity<Duenio> encontrar(@PathVariable("id") long id, HttpServletRequest request) {
		Duenio duenio = duenioService.encontrar(id);
		if (duenio == null) {
			return new ResponseEntity<Duenio>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Duenio>(duenio, HttpStatus.OK);
	}
	
	//guarda un Duenio
	@PostMapping
	public ResponseEntity<Duenio> guardar(@Valid @RequestBody Duenio d, HttpServletRequest request) {
		if (duenioService.existe(d)) {
			return new ResponseEntity<Duenio>(HttpStatus.CONFLICT); //respuesta 409
		}
		Duenio duenio = new Duenio();
		duenio.setNombre(d.getNombre());
		duenio.setApellido(d.getApellido());
		duenio.setEmail(d.getEmail());
		duenio.setDni(d.getDni());
		duenio.setTelefono(d.getTelefono());
		duenio.setDomicilio(d.getDomicilio());
		Usuario usuario = new Usuario();
		usuario.setNombreUsuario(d.getUsuario().getNombreUsuario());
		String pass = Encrypt.encode(d.getUsuario().getClave());
		usuario.setClave(pass);
		duenio.setUsuario(usuario);
		Duenio duenioCreated = (Duenio) duenioService.guardar(duenio);
		return new ResponseEntity<Duenio>(duenioCreated, HttpStatus.CREATED);
	}
	
	//actualiza un Duenio
	@PutMapping("/{id}")
	public ResponseEntity<Duenio> actualizar(@PathVariable("id") long id, HttpServletRequest request,
											  @Valid @RequestBody Duenio d) {
		Duenio duenio = duenioService.encontrar(id);
		if (duenio == null) {
			return new ResponseEntity<Duenio>(HttpStatus.NOT_FOUND);
		}
		duenio.setNombre(d.getNombre());
		duenio.setApellido(d.getApellido());
		duenio.setEmail(d.getEmail());
		duenio.setDni(d.getDni());
		duenio.setTelefono(d.getTelefono());
		duenio.setDomicilio(d.getDomicilio());
		Usuario usuario = new Usuario();
		usuario.setNombreUsuario(d.getUsuario().getNombreUsuario());
		String pass = Encrypt.encode(d.getUsuario().getClave());
		usuario.setClave(pass);
		duenio.setUsuario(usuario);
		Duenio duenioUpdated = (Duenio) duenioService.actualizar(duenio);
		return new ResponseEntity<Duenio>(duenioUpdated, HttpStatus.OK);
	}
	
	//borra un Duenio
	@DeleteMapping("/{id}")
	public ResponseEntity<Duenio> eliminar(@PathVariable("id") long id, HttpServletRequest request) {
		Duenio duenio = duenioService.encontrar(id);
		if (duenio == null) {
			return new ResponseEntity<Duenio>(HttpStatus.NOT_FOUND);
		}
		duenioService.eliminar(id);
		return new ResponseEntity<Duenio>(HttpStatus.NO_CONTENT);
	}

}
