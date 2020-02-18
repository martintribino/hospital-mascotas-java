package ttps.spring.test.model;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import ttps.spring.model.Solicitud;
import ttps.spring.model.Veterinario;

public class VeterinarioTest {
	private Veterinario v;
	private Veterinario vet;

	public VeterinarioTest() {
		v = new Veterinario(); //para los dinamicos
		vet = new Veterinario(
	            "veterinario",
	            "Veterinario",
	            "Veterinario",
	            "veterinario123",
	            "veterinario@veterinario.com",
	            223556879,
	            123654789,
	            "no tiene",
	            "nomClinica",
	            "domClinica",
	            false ); //para los estaticos
	}

	@Test
	public void testVeterinario() {
        assertEquals("Probando constructor de clase Veterinario sin parámetros", Veterinario.class, v.getClass());
	}

	@Test
	public void testVeterinarioStringStringStringStringStringIntIntString() {
        assertEquals("Veterinario", vet.getApellido());
        assertEquals(223556879, vet.getDni());
        assertEquals("no tiene", vet.getDomicilio());
        assertEquals("veterinario@veterinario.com", vet.getEmail());
        assertEquals("Veterinario", vet.getNombre());
        assertEquals(123654789, vet.getTelefono());
        assertEquals("Probando constructor de clase Veterinario con parámetros", Veterinario.class, vet.getClass());
        assertEquals("Probando propiedad solicitudes de clase Veterinario", new ArrayList<Solicitud>(), vet.getSolicitudes());
	}

	@Test
	public void testSetNombre() {
		String nombreNuevo = "VeterinarioNom";
		v.setNombre(nombreNuevo);
        assertEquals("Probando propiedad nombre de clase Veterinario", nombreNuevo, v.getNombre());
	}

	@Test
	public void testSetApellido() {
		String apeNuevo = "VeterinarioApe";
		v.setApellido(apeNuevo);
        assertEquals("Probando propiedad apellido de clase Veterinario", apeNuevo, v.getApellido());
	}

	@Test
	public void testSetEmail() {
		String mailNuevo = "vet@vet.com";
		v.setEmail(mailNuevo);
        assertEquals("Probando propiedad mail de clase Veterinario", mailNuevo, v.getEmail());
	}

	@Test
	public void testSetDni() {
		int dniNuevo = 223556854;
		v.setDni(dniNuevo);
        assertEquals("Probando propiedad dni de clase Veterinario", dniNuevo, v.getDni());
	}

	@Test
	public void testSetTelefono() {
		int telNuevo = 123654765;
		v.setTelefono(telNuevo);
        assertEquals("Probando propiedad teléfono de clase Veterinario", telNuevo, v.getTelefono());
	}

	@Test
	public void testSetDomicilio() {
		String domNuevo = "no sabe";
		v.setDomicilio(domNuevo);
        assertEquals("Probando propiedad domicilio de clase Veterinario", domNuevo, v.getDomicilio());
	}

	@Test
	public void testSetDomicilioClinica() {
		String domNuevo = "no sabe clinica";
		v.setDomicilioClinica(domNuevo);
        assertEquals("Probando propiedad domicilio clinica de clase Veterinario", domNuevo, v.getDomicilioClinica());
	}

	@Test
	public void testSetValidado() {
		v.setValidado(false);
        assertFalse("Probando propiedad validado de clase Veterinario", v.getValidado());
		v.setValidado(true);
        assertTrue("Probando propiedad validado de clase Veterinario", v.getValidado());
	}

	@Test
	public void testSetNombreClinica() {
		String nomNuevo = "no sabe clinica";
		v.setNombreClinica(nomNuevo);
        assertEquals("Probando propiedad nombre clinica de clase Veterinario", nomNuevo, v.getNombreClinica());
	}

}
