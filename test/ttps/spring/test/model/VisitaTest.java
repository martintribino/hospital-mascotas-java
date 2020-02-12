package ttps.spring.test.model;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.Test;

import ttps.spring.model.Visita;

public class VisitaTest {
	private Visita v;
	
	public VisitaTest() {
		LocalDate fecha = LocalDate.of(2025, 8, 8);
		LocalTime inicio = LocalTime.of(9, 30);
		LocalTime fin = LocalTime.of(10, 0);
		v = new Visita(
				fecha,
				inicio,
				fin,
				"Vacuna",
				null,
				null,
				"droga",
				"peso",
				"motivo",
				"diagnostico",
				"indicaciones");
	}

	@Test
	public void testSetPeso() {
		String peso = "Peso";
		v.setPeso(peso);
        assertEquals("Probando propiedad peso de clase Visita", peso, v.getPeso());
	}

	@Test
	public void testSetMotivo() {
		String motivo = "Motivo";
		v.setMotivo(motivo);
        assertEquals("Probando propiedad motivo de clase Visita", motivo, v.getMotivo());
	}

	@Test
	public void testSetDiagnostico() {
		String diagnostico = "Diagnostico";
		v.setDiagnostico(diagnostico);
        assertEquals("Probando propiedad diagnostico de clase Visita", diagnostico, v.getDiagnostico());
	}

	@Test
	public void testSetIndicaciones() {
		String indicaciones = "Indicaciones";
		v.setIndicaciones(indicaciones);
        assertEquals("Probando propiedad indicaciones de clase Visita", indicaciones, v.getIndicaciones());
	}

}
