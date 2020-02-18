package ttps.spring.test.model;

import static org.junit.Assert.*;

import org.junit.Test;

import ttps.spring.model.Evento;
import ttps.spring.model.Mascota;

public class EventoTest {
	private Evento e;
	
	public EventoTest() {
		e = new Evento();
	}

	@Test
	public void testSetDescripcion() {
		String desc = "Descripcion";
		e.setDescripcion(desc);
        assertEquals("Probando propiedad descripcion de clase Evento", desc, e.getDescripcion());
	}

	@Test
	public void testSetMascota() {
		Mascota m = new Mascota();
		e.setMascota(m);
        assertEquals("Probando propiedad mascota de clase Evento", m, e.getMascota());
	}

}
