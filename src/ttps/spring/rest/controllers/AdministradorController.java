package ttps.spring.rest.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ttps.spring.model.Administrador;
import ttps.spring.model.Encrypt;
import ttps.spring.model.Usuario;
import ttps.spring.rest.services.AdministradorService;
import ttps.spring.rest.services.UsuarioService;

@RestController
@RequestMapping(value="/api/administradores", produces={MediaType.APPLICATION_JSON_VALUE})
public class AdministradorController {

	@Autowired
	private AdministradorService adminService;
	@Autowired
	private UsuarioService usuarioService;

	 //Recupero todas los Administradores
	@GetMapping
	public ResponseEntity<List<Administrador>> listar() {
		List<Administrador> administradores = adminService.listar();
		if(administradores.isEmpty()){
			return new ResponseEntity<List<Administrador>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Administrador>>(administradores, HttpStatus.OK);
	}
	
	//guarda un Administrador
	//@PostMapping
	public ResponseEntity<Administrador> guardar(@Valid @RequestBody Administrador admin) {
		if (adminService.existe(admin)) {
			return new ResponseEntity<Administrador>(admin, HttpStatus.CONFLICT);
		}
		if (usuarioService.existe(admin.getUsuario())) {
			return new ResponseEntity<Administrador>(admin, HttpStatus.CONFLICT);
		}
		Administrador administrador = new Administrador();
		administrador.setNombre(admin.getNombre());
		administrador.setApellido(admin.getApellido());
		administrador.setEmail(admin.getEmail());
		administrador.setDni(admin.getDni());
		administrador.setTelefono(admin.getTelefono());
		administrador.setDomicilio(admin.getDomicilio());
		Usuario usuario = new Usuario();
		usuario.setNombreUsuario(admin.getUsuario().getNombreUsuario());
		String pass = Encrypt.encode(admin.getUsuario().getClave());
		usuario.setClave(pass);
		administrador.setUsuario(usuario);
		Administrador adminCreated = (Administrador) adminService.guardar(administrador);
		return new ResponseEntity<Administrador>(adminCreated, HttpStatus.CREATED);
	}
	
	//actualiza un Administrador
	//@PutMapping("/{id}")
	public ResponseEntity<Administrador> actualizar(/*@PathVariable("id")*/ long id,
											  @Valid @RequestBody Administrador admin) {
		Administrador administrador = adminService.encontrar(id);
		if (administrador == null || administrador.getUsuario() == null) {
			return new ResponseEntity<Administrador>(admin, HttpStatus.NOT_FOUND);
		}
		administrador.setNombre(admin.getNombre());
		administrador.setApellido(admin.getApellido());
		administrador.setEmail(admin.getEmail());
		administrador.setDni(admin.getDni());
		administrador.setTelefono(admin.getTelefono());
		administrador.setDomicilio(admin.getDomicilio());
		Administrador adminUpdated = (Administrador) adminService.actualizar(administrador);
		return new ResponseEntity<Administrador>(adminUpdated, HttpStatus.OK);
	}

}
