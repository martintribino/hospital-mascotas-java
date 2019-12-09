package ttps.spring.test.model;

import static org.junit.Assert.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

import ttps.spring.model.Intervencion;

public class IntervencionTest {

	@Test
	public void testIntervencion() {
		Intervencion i = new Intervencion();
        assertEquals("Probando constructor de clase Intervencion sin parámetros", Intervencion.class, i.getClass());
	}

	@Test
	public void testIntervencionDateStringBooleanVeterinarioMascota() {
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date fecha;
		try {
			fecha = formatter.parse("2025-08-08");
		}
		catch(ParseException pe) {
		    throw new IllegalArgumentException(pe);
		}
		Intervencion i = new Intervencion(
				fecha,
				"Descripcion",
				false,
				null,
				null);
		assertEquals("Probando constructor de clase Intervencion con parámetros", Intervencion.class, i.getClass());
	}

}
