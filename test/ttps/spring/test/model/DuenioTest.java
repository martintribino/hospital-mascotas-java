package ttps.spring.test.model;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import ttps.spring.model.Duenio;
import ttps.spring.model.Mascota;

public class DuenioTest {
	private Duenio d;
	private Duenio due;
	
	public DuenioTest() {
		d = new Duenio(); //para los dinamicos
		due = new Duenio(
                "duenio",
                "Duenio",
                "Duenio",
                "duenio123",
                "duenio@duenio.com",
                223556879,
                123654789,
                "no tiene",
                new ArrayList<Mascota>()); //para los estaticos
	}

	@Test
	public void testAdministrador() {
        assertEquals("Probando constructor de clase Duenio sin parámetros", Duenio.class, d.getClass());
	}

	@Test
	public void testAdministradorStringStringStringStringStringIntIntString() {
        assertEquals("Duenio", due.getApellido());
        assertEquals(223556879, due.getDni());
        assertEquals("no tiene", due.getDomicilio());
        assertEquals("duenio@duenio.com", due.getEmail());
        assertEquals("Duenio", due.getNombre());
        assertEquals(123654789, due.getTelefono());
        assertEquals("Probando constructor de clase Duenio con parámetros", Duenio.class, due.getClass());
	}

	@Test
	public void testSetNombre() {
		String nombreNuevo = "Due";
		d.setNombre(nombreNuevo);
        assertEquals("Probando propiedad nombre de clase Duenio", nombreNuevo, d.getNombre());
	}

	@Test
	public void testSetApellido() {
		String apeNuevo = "Due";
		d.setApellido(apeNuevo);
        assertEquals("Probando propiedad apellido de clase Duenio", apeNuevo, d.getApellido());
	}

	@Test
	public void testSetEmail() {
		String mailNuevo = "Duenio@Duenio.com";
		d.setEmail(mailNuevo);
        assertEquals("Probando propiedad mail de clase Duenio", mailNuevo, d.getEmail());
	}

	@Test
	public void testSetDni() {
		int dniNuevo = 223556854;
		d.setDni(dniNuevo);
        assertEquals("Probando propiedad dni de clase Duenio", dniNuevo, d.getDni());
	}

	@Test
	public void testSetTelefono() {
		int telNuevo = 123654765;
		d.setTelefono(telNuevo);
        assertEquals("Probando propiedad teléfono de clase Duenio", telNuevo, d.getTelefono());
	}

	@Test
	public void testSetDomicilio() {
		String domNuevo = "no sabe";
		d.setDomicilio(domNuevo);
        assertEquals("Probando propiedad domicilio de clase Duenio", domNuevo, d.getDomicilio());
	}

	@Test
	public void testSetId() {
		Long idNuevo = (long) 1;
		d.setId(idNuevo);
        assertEquals("Probando propiedad id de clase Duenio", idNuevo, d.getId());
	}

}
