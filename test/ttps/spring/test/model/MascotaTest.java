package ttps.spring.test.model;

import static org.junit.Assert.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

import ttps.spring.model.Duenio;
import ttps.spring.model.Mascota;
import ttps.spring.model.Veterinario;

public class MascotaTest {
	private Mascota m;

	public MascotaTest() {
		Veterinario v = new Veterinario();
		v.setNombre("VeterinarioNombre");
		v.setApellido("VeterinarioApe");
		Duenio d = new Duenio();
		d.setNombre("DuenioNombre");
		d.setApellido("DuenioApe");
		m = new Mascota();
		m = new Mascota(
	            "mascota",
	            new Date(),
	            "ninguna",
	            "ninguna",
	            "hembra",
	            "marron",
	            "ninguna",
	            "",
	            false,
	            new Duenio(),
	            v);
	}

	@Test
	public void testSetNombre() {
		String nombreNuevo = "MascotaNom";
		m.setNombre(nombreNuevo);
        assertEquals("Probando propiedad nombre de clase Mascota", nombreNuevo, m.getNombre());
	}

	@Test
	public void testSetFechaNacimiento() {
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date myDate;
		try {
			myDate = formatter.parse("2005-08-08");
		}
		catch(ParseException pe) {
		    throw new IllegalArgumentException(pe);
		}
		m.setFechaNacimiento(myDate);
        assertEquals("Probando propiedad fecha de nacimiento de clase Mascota", myDate, m.getFechaNacimiento());
	}

	@Test
	public void testSetEspecie() {
		String nombreNuevo = "Pointer";
		m.setEspecie(nombreNuevo);
        assertEquals("Probando propiedad especie de clase Mascota", nombreNuevo, m.getEspecie());
	}

	@Test
	public void testSetRaza() {
		String nombreNuevo = "Pointer";
		m.setRaza(nombreNuevo);
        assertEquals("Probando propiedad raza de clase Mascota", nombreNuevo, m.getRaza());
	}

	@Test
	public void testSetSexo() {
		String nombreNuevo = "Macho";
		m.setSexo(nombreNuevo);
        assertEquals("Probando propiedad sexo de clase Mascota", nombreNuevo, m.getSexo());
	}

	@Test
	public void testSetColor() {
		String nombreNuevo = "Blanco";
		m.setColor(nombreNuevo);
        assertEquals("Probando propiedad color de clase Mascota", nombreNuevo, m.getColor());
	}

	@Test
	public void testSetSenias() {
		String nombreNuevo = "Mancha marron en la oreja";
		m.setSenias(nombreNuevo);
        assertEquals("Probando propiedad senias de clase Mascota", nombreNuevo, m.getSenias());
	}

	@Test
	public void testSetImagen() {
		String urlNueva = null;
		m.setImagen(urlNueva);
        assertEquals("Probando propiedad imagen de clase Mascota", urlNueva, m.getImagen());
	}

	@Test
	public void testSetDuenio() {
		Duenio duenioNuevo = new Duenio();
		m.setDuenio(duenioNuevo);
        assertEquals("Probando propiedad duenio de clase Mascota", duenioNuevo, m.getDuenio());
	}

	@Test
	public void testSetVeterinario() {
		Veterinario duenioNuevo = new Veterinario();
		m.setVeterinario(duenioNuevo);
        assertEquals("Probando propiedad veterinario de clase Mascota", duenioNuevo, m.getVeterinario());
	}

	@Test
	public void testHabilitarNombre() {
		m.habilitarNombre();
        assertTrue("Probando habilitar nombre de clase Mascota", m.nombreEstaHabilitado());
		m.deshabilitarNombre();
        assertFalse("Probando deshabilitar nombre de clase Mascota", m.nombreEstaHabilitado());
	}

	@Test
	public void testHabilitarFecha() {
		m.habilitarFecha();
        assertTrue("Probando habilitar Fecha de clase Mascota", m.fechaEstaHabilitado());
		m.deshabilitarFecha();
        assertFalse("Probando deshabilitar Fecha de clase Mascota", m.fechaEstaHabilitado());
	}

	@Test
	public void testHabilitarEspecie() {
		m.habilitarEspecie();
        assertTrue("Probando habilitar Especie de clase Mascota", m.especieEstaHabilitado());
		m.deshabilitarEspecie();
        assertFalse("Probando deshabilitar Especie de clase Mascota", m.especieEstaHabilitado());
	}

	@Test
	public void testHabilitarRaza() {
		m.habilitarRaza();
        assertTrue("Probando habilitar Raza de clase Mascota", m.razaEstaHabilitado());
		m.deshabilitarRaza();
        assertFalse("Probando deshabilitar Raza de clase Mascota", m.razaEstaHabilitado());
	}

	@Test
	public void testHabilitarSexo() {
		m.habilitarSexo();
        assertTrue("Probando habilitar Sexo de clase Mascota", m.sexoEstaHabilitado());
		m.deshabilitarSexo();
        assertFalse("Probando deshabilitar Sexo de clase Mascota", m.sexoEstaHabilitado());
	}

	@Test
	public void testHabilitarColor() {
		m.habilitarColor();
        assertTrue("Probando habilitar Color de clase Mascota", m.colorEstaHabilitado());
		m.deshabilitarColor();
        assertFalse("Probando deshabilitar Color de clase Mascota", m.colorEstaHabilitado());
	}

	@Test
	public void testHabilitarSenias() {
		m.habilitarSenias();
        assertTrue("Probando habilitar Senias de clase Mascota", m.seniasEstaHabilitado());
		m.deshabilitarSenias();
        assertFalse("Probando deshabilitar Senias de clase Mascota", m.seniasEstaHabilitado());
	}

	@Test
	public void testHabilitarImagen() {
		m.habilitarImagen();
        assertTrue("Probando habilitar Imagen de clase Mascota", m.imagenEstaHabilitado());
		m.deshabilitarImagen();
        assertFalse("Probando deshabilitar Imagen de clase Mascota", m.imagenEstaHabilitado());
	}

	@Test
	public void testHabilitarDuenio() {
		m.habilitarDuenio();
        assertTrue("Probando habilitar Duenio de clase Mascota", m.duenioEstaHabilitado());
		m.deshabilitarDuenio();
        assertFalse("Probando deshabilitar Duenio de clase Mascota", m.duenioEstaHabilitado());
	}

	@Test
	public void Veterinario() {
		m.habilitarVeterinario();
        assertTrue("Probando habilitar Veterinario de clase Mascota", m.veterinarioEstaHabilitado());
		m.deshabilitarVeterinario();
        assertFalse("Probando deshabilitar Veterinario de clase Mascota", m.veterinarioEstaHabilitado());
	}

}
