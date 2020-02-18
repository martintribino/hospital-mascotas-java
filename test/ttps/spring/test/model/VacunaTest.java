package ttps.spring.test.model;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.LocalTime;

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
		LocalDate fecha = LocalDate.of(2025, 8, 8);
		LocalTime inicio = LocalTime.of(9, 30);
		LocalTime fin = LocalTime.of(10, 0);
		Vacuna v = new Vacuna(
				fecha,
				inicio,
				fin,
				"Vacuna",
				null);
        assertEquals("Probando constructor de clase Vacuna con parámetros", Vacuna.class, v.getClass());
	}

}
