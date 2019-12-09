package ttps.spring.test.model;

import static org.junit.Assert.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

import ttps.spring.model.Evento;
import ttps.spring.model.Mascota;
import ttps.spring.model.Veterinario;

public class EventoTest {
	private Evento e;
	private Evento ev;
	
	public EventoTest() {
		e = new Evento();
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date fecha;
		try {
			fecha = formatter.parse("2025-08-08");
		}
		catch(ParseException pe) {
		    throw new IllegalArgumentException(pe);
		}
		ev = new Evento(
				fecha,
				"descripcion",
				false,
				null,
				null);
	}

	@Test
	public void testSetFecha() {
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date fecha;
		try {
			fecha = formatter.parse("2025-08-08");
		}
		catch(ParseException pe) {
		    throw new IllegalArgumentException(pe);
		}
		ev.setFecha(fecha);
        assertEquals("Probando propiedad fecha de clase Evento", fecha, ev.getFecha());
	}

	@Test
	public void testSetDescripcion() {
		String desc = "Descripcion";
		e.setDescripcion(desc);
        assertEquals("Probando propiedad descripcion de clase Evento", desc, e.getDescripcion());
	}

	@Test
	public void testSetConcurrio() {
		Boolean conc = true;
		e.setConcurrio(conc);
        assertEquals("Probando propiedad concurrio de clase Evento", conc, e.getConcurrio());
	}

	@Test
	public void testSetVeterinario() {
		Veterinario v = new Veterinario();
		e.setVeterinario(v);
        assertEquals("Probando propiedad veterinario de clase Evento", v, e.getVeterinario());
	}

	@Test
	public void testSetMascota() {
		Mascota m = new Mascota();
		e.setMascota(m);
        assertEquals("Probando propiedad mascota de clase Evento", m, e.getMascota());
	}

}
