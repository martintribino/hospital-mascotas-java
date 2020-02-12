package ttps.spring.test.model;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.LocalTime;

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
		LocalDate fecha = LocalDate.of(2025, 8, 8);
		LocalTime inicio = LocalTime.of(9, 30);
		LocalTime fin = LocalTime.of(10, 0);
		Intervencion i = new Intervencion(
				fecha,
				inicio,
				fin,
				"Descripcion",
				null,
				null);
		assertEquals("Probando constructor de clase Intervencion con parámetros", Intervencion.class, i.getClass());
	}

}
