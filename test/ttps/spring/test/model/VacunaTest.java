package ttps.spring.test.model;

import static org.junit.Assert.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

import ttps.spring.model.Vacuna;

public class VacunaTest {

	@Test
	public void testVacuna() {
		Vacuna v = new Vacuna();
        assertEquals("Probando constructor de clase Vacuna sin parámetros", Vacuna.class, v.getClass());
	}

	@Test
	public void testVacunaDateStringBooleanVeterinarioMascota() {
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date fecha;
		try {
			fecha = formatter.parse("2025-08-08");
		}
		catch(ParseException pe) {
		    throw new IllegalArgumentException(pe);
		}
		Vacuna v = new Vacuna(
				fecha,
				"Vacuna",
				true,
				null,
				null);
        assertEquals("Probando constructor de clase Vacuna con parámetros", Vacuna.class, v.getClass());
	}

}
