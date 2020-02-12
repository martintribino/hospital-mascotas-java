package ttps.spring.rest.controllers;

import java.time.LocalDate;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ttps.spring.responses.HorariosResponse;

@RestController
@RequestMapping(value="/api/turnos", produces={MediaType.APPLICATION_JSON_VALUE})
public class TurnoController {

	 //Obtengo horarios validos para turnos
	@GetMapping(value="/horarios", produces={MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<HorariosResponse> horariosValidos(@RequestParam("fecha") String fechaP) {
		LocalDate fecha = LocalDate.parse(fechaP);
		HorariosResponse hr = new HorariosResponse(fecha);
		return new ResponseEntity<HorariosResponse>(hr, HttpStatus.OK);
	}
}
