package ttps.spring.test.model;

import static org.junit.Assert.*;

import org.junit.Test;

import ttps.spring.model.Enfermedad;

public class EnfermedadTest {
	private Enfermedad e;

	@Test
	public void testEnfermedad() {
		e = new Enfermedad();
        assertEquals("Probando constructor de clase Enfermedad sin parámetros", Enfermedad.class, e.getClass());
	}

}
