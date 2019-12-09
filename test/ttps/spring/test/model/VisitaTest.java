package ttps.spring.test.model;

import static org.junit.Assert.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

import ttps.spring.model.Visita;

public class VisitaTest {
	private Visita v;
	
	public VisitaTest() {
		v = new Visita();
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date fecha;
		try {
			fecha = formatter.parse("2025-08-08");
		}
		catch(ParseException pe) {
		    throw new IllegalArgumentException(pe);
		}
		v = new Visita(
				fecha,
				"Vacuna",
				true,
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
