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

import ttps.spring.exceptions.BadRequestException;
import ttps.spring.exceptions.MascotaNotFoundException;
import ttps.spring.exceptions.UserNotFoundException;
import ttps.spring.model.Duenio;
import ttps.spring.model.FichaPublica;
import ttps.spring.model.Mascota;
import ttps.spring.model.Persona;
import ttps.spring.model.QRCodeGenerator;
import ttps.spring.model.Usuario;
import ttps.spring.model.Veterinario;
import ttps.spring.requests.MascotaReqBody;
import ttps.spring.responses.ImageResponse;
import ttps.spring.rest.services.DuenioService;
import ttps.spring.rest.services.MascotaService;
import ttps.spring.rest.services.UsuarioService;
import ttps.spring.rest.services.VeterinarioService;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.codec.Base64;

@RestController
@RequestMapping
public class MascotaController {

	@Autowired
	private MascotaService mascotaService;
	@Autowired
	private UsuarioService usuService;
	@Autowired
	private DuenioService duenioServ;
	@Autowired
	private VeterinarioService vetServ;
	
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
			FichaPublica f = mascota.getFicha();
			Mascota aux = new Mascota();
			aux.setSlug(mascota.getSlug());
			if(f.getNombre())
				aux.setNombre(mascota.getNombre());
			if(f.getEspecie())
				aux.setEspecie(mascota.getEspecie());
			if(f.getRaza())
				aux.setRaza(mascota.getRaza());
			if(f.getSexo())
				aux.setSexo(mascota.getSexo());
			if(f.getColor())
				aux.setColor(mascota.getColor());
			if(f.getSenias())
				aux.setSenias(mascota.getSenias());
			if(f.getFechaNacimiento())
				aux.setFechaNacimiento(mascota.getFechaNacimiento());
			else
				aux.setFechaNacimiento(null);
			if(f.getImagen())
				aux.setImagen(mascota.getImagen());
			if(mascota.getDuenio() != null && f.getDuenio())
				aux.setDuenio(mascota.getDuenio());
			if(mascota.getVeterinario() != null && f.getVeterinario())
				aux.setVeterinario(mascota.getVeterinario());
			mascotasReturn.add(aux);
		}
		return new ResponseEntity<List<Mascota>>(mascotasReturn, HttpStatus.OK);
	}

	 //Recupero todas los mascotas pero solo las propiedades publicas
	@GetMapping(value="/mascotas", produces={MediaType.APPLICATION_JSON_VALUE}, params="filtro")
	public ResponseEntity<List<Mascota>> listarPublicasFiltro(@RequestParam("filtro") String filtro) {
		List<Mascota> mascotas = mascotaService.listarFiltro(filtro);
		if(mascotas.isEmpty()){
			return new ResponseEntity<List<Mascota>>(mascotas, HttpStatus.OK);
		}
		List<Mascota> mascotasReturn = new ArrayList<Mascota>();
		for (Mascota mascota: mascotas) {
			FichaPublica f = mascota.getFicha();
			Mascota aux = new Mascota();
			aux.setSlug(mascota.getSlug());
			if(f.getNombre())
				aux.setNombre(mascota.getNombre());
			if(f.getEspecie())
				aux.setEspecie(mascota.getEspecie());
			if(f.getRaza())
				aux.setRaza(mascota.getRaza());
			if(f.getSexo())
				aux.setSexo(mascota.getSexo());
			if(f.getColor())
				aux.setColor(mascota.getColor());
			if(f.getSenias())
				aux.setSenias(mascota.getSenias());
			if(f.getFechaNacimiento())
				aux.setFechaNacimiento(mascota.getFechaNacimiento());
			else
				aux.setFechaNacimiento(null);
			if(f.getImagen())
				aux.setImagen(mascota.getImagen());
			if(mascota.getDuenio() != null && f.getDuenio())
				aux.setDuenio(mascota.getDuenio());
			if(mascota.getVeterinario() != null && f.getVeterinario())
				aux.setVeterinario(mascota.getVeterinario());
			mascotasReturn.add(aux);
		}
		return new ResponseEntity<List<Mascota>>(mascotasReturn, HttpStatus.OK);
	}

	 //Recupero todas los mascotas pero solo las propiedades publicas
	@GetMapping(value="/mascotas/qr/", produces={MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<ImageResponse> gettQRCode(
			@RequestParam("slg") String slug
		) throws Exception {
		Mascota mascota = mascotaService.encontrar(slug);
		if (mascota == null) {
	         throw new MascotaNotFoundException("Mascota no encontrada." );
	    }
		try
		{
			int width = 300;
			int height = 300;
			String strQRCode = mascota.getFichaPublicaSTR();
			byte[] base64 = Base64.encode(QRCodeGenerator.getQRCodeImage(strQRCode, width, height));
			String qrstr = new String(base64);
			ImageResponse ir = new ImageResponse();
			ir.setB64str(qrstr);
			ir.setExtension("png");
			ir.setWidth(width);
			ir.setHeight(height);
			return new ResponseEntity<ImageResponse>(ir, HttpStatus.OK);
		}
		catch (Exception e) {
	        throw e;
		}
	}
	
	//retorna mascotas por duenio
	@GetMapping(value="/api/mascotas/usuario/", produces={MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<List<Mascota>> listarPorUsuario(@RequestParam("username") String userName) {
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
			List<Mascota> mascotas = new ArrayList<Mascota>();
			switch (perfil.getRole()) {
				case "administrador":
			        throw new BadRequestException("No role." );
				case "duenio":
					mascotas = mascotaService.listarPorDuenio(perfil.getId());
					break;
				case "veterinario":
					mascotas = mascotaService.listarPorVeterinario(perfil.getId());
					break;
				default:
			        throw new BadRequestException("No role." );
			}
			return new ResponseEntity<List<Mascota>>(mascotas, HttpStatus.OK);
		}
		catch (Exception e) {
	        throw e;
		}
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
	
	//guarda una mascota para un usuario veterinario o duenio
	@PostMapping(value="/api/mascotas", produces={MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Mascota> guardar(@Valid @RequestBody MascotaReqBody mascota) {
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
			masc.setNombre(mascota.getNombre());
			masc.setEspecie(mascota.getEspecie());
			masc.setRaza(mascota.getRaza());
			masc.setSexo(mascota.getSexo());
			masc.setColor(mascota.getColor());
			masc.setSenias(mascota.getSenias());
			masc.setFechaNacimiento(mascota.getFechaNacimiento());
			masc.setImagen(mascota.getImagen());
			masc.setDuenio(null);
			masc.setVeterinario(null);
			switch (perfil.getRole()) {
				case "administrador":
			        throw new BadRequestException("No role." );
				case "duenio":
					Duenio d = duenioServ.encontrar(perfil.getId());
					masc.setDuenio(d);
					break;
				case "veterinario":
					Veterinario v = vetServ.encontrar(perfil.getId());
					masc.setVeterinario(v);
					break;
				default:
			        throw new BadRequestException("No role." );
			}
			Mascota mascotaCreated = (Mascota) mascotaService.guardar(masc);
			return new ResponseEntity<Mascota>(mascotaCreated, HttpStatus.CREATED);
		}
		catch (Exception e) {
	        throw e;
		}
	}
	
	//actualiza una mascota
	@PutMapping(value="/api/mascotas", produces={MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Mascota> actualizar(@Valid @RequestBody MascotaReqBody mascParam) {
		String userName = mascParam.getUsername();
		Usuario usu = usuService.recuperarUsuarioPorNombre(userName);
		if (usu == null) {
	         throw new UserNotFoundException("Usuario no válido: " + userName);
	    }
		Persona perfil = usu.getPersona();
		if (perfil == null) {
	         throw new UserNotFoundException("Perfil no encontrado." );
	    }
		Mascota mascota = mascotaService.encontrar(mascParam.getSlug());
		if (mascota == null) {
	         throw new MascotaNotFoundException("Mascota no encontrada." );
	    }
		if (mascota.getDuenio() == null || mascota.getDuenio().getId() != perfil.getId()) {
			throw new MascotaNotFoundException("Dueño incorrecto." );
		}
		mascota.setNombre(mascParam.getNombre());
		mascota.setFechaNacimiento(mascParam.getFechaNacimiento());
		mascota.setEspecie(mascParam.getEspecie());
		mascota.setRaza(mascParam.getRaza());
		mascota.setSexo(mascParam.getSexo());
		mascota.setColor(mascParam.getColor());
		mascota.setSenias(mascParam.getSenias());
		mascota.setImagen(mascParam.getImagen());
		Mascota mascotaUpdated = mascotaService.actualizar(mascota);
		return new ResponseEntity<Mascota>(mascotaUpdated, HttpStatus.OK);
	}
	
	//borra una mascota
	@DeleteMapping(value="/api/mascotas", produces={MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Mascota> eliminar(
			@RequestParam("username") String userName,
			@RequestParam("slg") String slug
			) {
		Usuario usu = usuService.recuperarUsuarioPorNombre(userName);
		if(usu == null) {
	         throw new UserNotFoundException("Usuario no válido: " + userName);
	    }
		Mascota mascota = mascotaService.encontrar(slug);
		if (mascota == null) {
	         throw new MascotaNotFoundException("No se encontró mascota.");
		}
		Persona perfil = usu.getPersona();
		if (perfil == null) {
	         throw new UserNotFoundException("Perfil no encontrado." );
	    }
		try
		{
			switch (perfil.getRole()) {
			case "administrador":
				mascotaService.eliminar(mascota.getId());
			case "duenio":
				if(mascota.getDuenio().getId() != usu.getPersona().getId()) {
					throw new BadRequestException("Dueño no valido: " + userName);
				}
				mascotaService.eliminar(mascota.getId());
				break;
			case "veterinario":
				//se borra la suscripcion unicamente
				mascotaService.removerVeterinario(mascota);
				break;
			default:
		        throw new BadRequestException("No role." );
		}
			return new ResponseEntity<Mascota>(HttpStatus.NO_CONTENT);
		}
		catch (Exception ex)
		{
	         throw ex;
		}
	}

}
