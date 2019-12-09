package ttps.spring.test.model;

import static org.junit.Assert.*;

import org.junit.Test;

import ttps.spring.model.Persona;

public class PersonaTest {
	private Persona p;
	
	public PersonaTest() {
		p = new Persona();
	}

	@Test
	public void testSetNombre() {
		String nombreNuevo = "Persona";
		p.setNombre(nombreNuevo);
        assertEquals("Probando propiedad nombre de clase Persona", nombreNuevo, p.getNombre());
	}

	@Test
	public void testSetApellido() {
		String apeNuevo = "PersonaApe";
		p.setApellido(apeNuevo);
        assertEquals("Probando propiedad apellido de clase Persona", apeNuevo, p.getApellido());
	}

	@Test
	public void testSetEmail() {
		String mailNuevo = "persona@persona.com";
		p.setEmail(mailNuevo);
        assertEquals("Probando propiedad mail de clase Persona", mailNuevo, p.getEmail());
	}

	@Test
	public void testSetDni() {
		int dniNuevo = 223556854;
		p.setDni(dniNuevo);
        assertEquals("Probando propiedad dni de clase Persona", dniNuevo, p.getDni());
	}

	@Test
	public void testSetTelefono() {
		int telNuevo = 123654765;
		p.setTelefono(telNuevo);
        assertEquals("Probando propiedad teléfono de clase Persona", telNuevo, p.getTelefono());
	}

	@Test
	public void testSetDomicilio() {
		String domNuevo = "no sabe";
		p.setDomicilio(domNuevo);
        assertEquals("Probando propiedad domicilio de clase Persona", domNuevo, p.getDomicilio());
	}

}
