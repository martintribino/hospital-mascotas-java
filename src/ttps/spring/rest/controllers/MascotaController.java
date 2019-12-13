package ttps.spring.rest.controllers;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ttps.spring.exceptions.UserNotFoundException;
import ttps.spring.model.Duenio;
import ttps.spring.model.Mascota;
import ttps.spring.model.Persona;
import ttps.spring.model.Usuario;
import ttps.spring.requests.MascotaReqBody;
import ttps.spring.rest.services.DuenioService;
import ttps.spring.rest.services.MascotaService;
import ttps.spring.rest.services.UsuarioService;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping
public class MascotaController {

	@Autowired
	private MascotaService mascotaService;
	@Autowired
	private UsuarioService usuService;
	@Autowired
	private DuenioService duenioServ;
	
	 //Recupero todas los mascotas
	@GetMapping(value="/api/mascotas", produces={MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<List<Mascota>> listar() {
		List<Mascota> mascotas = mascotaService.listar();
		if(mascotas.isEmpty()){
			return new ResponseEntity<List<Mascota>>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<Mascota>>(mascotas, HttpStatus.OK);
	}

	 //Recupero todas los mascotas pero solo las propiedades publicas
	@GetMapping(value="/mascotas", produces={MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<List<Mascota>> listarPublicas() {
		List<Mascota> mascotas = mascotaService.listar();
		if(mascotas.isEmpty()){
			return new ResponseEntity<List<Mascota>>(mascotas, HttpStatus.OK);
		}
		List<Mascota> mascotasReturn = new ArrayList<Mascota>();
		for (Mascota mascota: mascotas) {
			Mascota aux = new Mascota();
			aux.setNombre(mascota.getNombre());
			if(mascota.especieEstaHabilitado())
				aux.setEspecie(mascota.getEspecie());
			if(mascota.razaEstaHabilitado())
				aux.setRaza(mascota.getRaza());
			if(mascota.sexoEstaHabilitado())
				aux.setSexo(mascota.getSexo());
			if(mascota.colorEstaHabilitado())
				aux.setColor(mascota.getColor());
			if(mascota.seniaEstaHabilitado())
				aux.setSenias(mascota.getSenias());
			if(mascota.fechaEstaHabilitado())
				aux.setFechaNacimiento(mascota.getFechaNacimiento());
			if(mascota.imagenEstaHabilitado())
				aux.setImagen(mascota.getImagen());
			/*if(mascota.duenioEstaHabilitado())
				aux.setDuenio(mascota.getDuenio());
			if(mascota.veterinarioEstaHabilitado())
				aux.setVeterinario(mascota.getVeterinario());*/
			mascotasReturn.add(aux);
		}
		return new ResponseEntity<List<Mascota>>(mascotasReturn, HttpStatus.OK);
	}
	
	//retorna mascotas por dueno
	@GetMapping(value="/api/mascotas/duenio/", produces={MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<List<Mascota>> listarPorDuenio(@RequestParam("username") String userName) {
		Usuario usu = usuService.recuperarUsuarioPorNombre(userName);
		if(usu == null) {
	         throw new UserNotFoundException("Usuario no válido: " + userName);
	    }
		Persona perfil = usu.getPersona();
		if(perfil == null) {
	         throw new UserNotFoundException("Perfil no encontrado." );
	    }
		List<Mascota> mascotas = mascotaService.listarPorDuenio(perfil.getId());
		if (mascotas.isEmpty()) {
			return new ResponseEntity<List<Mascota>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Mascota>>(mascotas, HttpStatus.OK);
	}
	
	//retorna una mascota por id
	@GetMapping(value="/api/mascotas/{id}", produces={MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Mascota> encontrar(@PathVariable("id") long id) {
		Mascota mascota = mascotaService.encontrar(id);
		if (mascota == null) {
			return new ResponseEntity<Mascota>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Mascota>(mascota, HttpStatus.OK);
	}
	
	//guarda una mascota
	@PostMapping(value="/api/mascotas", produces={MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Mascota> guardar(@RequestBody MascotaReqBody mascota) {
		String userName = mascota.getUsername();
		Usuario usu = usuService.recuperarUsuarioPorNombre(userName);
		if(usu == null) {
	         throw new UserNotFoundException("Usuario no válido: " + userName);
	    }
		Persona perfil = usu.getPersona();
		if(perfil == null) {
	         throw new UserNotFoundException("Perfil no encontrado." );
	    }
		try {
			Mascota masc = new Mascota();
			Duenio d = duenioServ.encontrar(perfil.getId());
			masc.setNombre(mascota.getNombre());
			masc.setEspecie(mascota.getEspecie());
			masc.setRaza(mascota.getRaza());
			masc.setSexo(mascota.getSexo());
			masc.setColor(mascota.getColor());
			masc.setSenias(mascota.getSenias());
			masc.setFechaNacimiento(mascota.getFechaNacimiento());
			masc.setImagen(mascota.getImagen());
			masc.setDuenio(d);
			Mascota mascotaCreated = (Mascota) mascotaService.guardar(masc);
			return new ResponseEntity<Mascota>(mascotaCreated, HttpStatus.CREATED);
		}
		catch (Exception e) {
			System.out.println(e);
			return new ResponseEntity<Mascota>(HttpStatus.BAD_REQUEST);
		}
	}
	
	//actualiza una mascota
	@PutMapping(value="/api/mascotas/{id}", produces={MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Mascota> actualizar(@PathVariable("id") long id,
											  @Valid @RequestBody Mascota mascParam) {
		Mascota mascota = mascotaService.encontrar(id);
		if (mascota == null) {
			return new ResponseEntity<Mascota>(HttpStatus.NOT_FOUND); //Código de respuesta 404
		}
		mascota.setNombre(mascParam.getNombre());
		mascota.setFechaNacimiento(mascParam.getFechaNacimiento());
		mascota.setEspecie(mascParam.getEspecie());
		mascota.setRaza(mascParam.getRaza());
		mascota.setSexo(mascParam.getSexo());
		mascota.setColor(mascParam.getColor());
		mascota.setSenias(mascParam.getSenias());
		mascota.setImagen(mascParam.getImagen());
		Mascota mascotaUpdated = (Mascota) mascotaService.actualizar(mascota);
		return new ResponseEntity<Mascota>(mascotaUpdated, HttpStatus.OK);
	}
	
	//borra una mascota
	@DeleteMapping(value="/api/mascotas/{id}", produces={MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Mascota> eliminar(@PathVariable("id") long id) {
		Mascota mascota = mascotaService.encontrar(id);
		if (mascota == null) {
			return new ResponseEntity<Mascota>(HttpStatus.NOT_FOUND);
		}
		mascotaService.eliminar(id);
		return new ResponseEntity<Mascota>(HttpStatus.NO_CONTENT);
	}

}
