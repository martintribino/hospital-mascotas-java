package ttps.spring.rest.controllers;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ttps.spring.model.FichaPublica;
import ttps.spring.rest.services.FichaPublicaService;

@RestController
@RequestMapping(value="/api/ficha/", produces={MediaType.APPLICATION_JSON_VALUE})
public class FichaPublicaController {

	@Autowired
	private FichaPublicaService fichaService;

	//actualiza una Solicitud de una mascota de un duenio para un veterinario
	@PutMapping
	public ResponseEntity<FichaPublica> actualizar(
			@Valid @RequestBody FichaPublica fichaParam
	) {
		FichaPublica ficha = fichaService.encontrar(fichaParam.getSlug());
		if (ficha == null) {
	         throw new EntityNotFoundException("No se encontró ficha.");
		}
		try
		{
			ficha.setNombre(fichaParam.getNombre());
			ficha.setEspecie(fichaParam.getEspecie());
			ficha.setRaza(fichaParam.getRaza());
			ficha.setSexo(fichaParam.getSexo());
			ficha.setColor(fichaParam.getColor());
			ficha.setSenias(fichaParam.getSenias());
			ficha.setFechaNacimiento(fichaParam.getFechaNacimiento());
			ficha.setImagen(fichaParam.getImagen());
			ficha.setExtraviada(fichaParam.getExtraviada());
			ficha.setDuenio(fichaParam.getDuenio());
			ficha.setVeterinario(fichaParam.getVeterinario());
			fichaService.actualizar(ficha);
			return new ResponseEntity<FichaPublica>(ficha, HttpStatus.OK);
		}
		catch(Exception ex)
		{
			throw ex;
		}
	}

}
