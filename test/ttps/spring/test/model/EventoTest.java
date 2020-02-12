package ttps.spring.test.model;

import static org.junit.Assert.*;

import org.junit.Test;

import ttps.spring.model.Evento;
import ttps.spring.model.Mascota;
import ttps.spring.model.Veterinario;

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
