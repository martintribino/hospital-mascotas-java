package ttps.spring.test.model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import ttps.spring.model.Administrador;

public class AdministradorTest {
	 private Administrador a;
	 private Administrador ad;

	public AdministradorTest() {
		ad = new Administrador(); //para los dinamicos
		a = new Administrador(
                "admin",
                "Administrador",
                "Administrador",
                "admin123",
                "administrador@admin.com",
                223556879,
                123654789,
                "no tiene"); //para los estaticos
	}

	@Test
	public void testAdministrador() {
        assertEquals("Probando constructor de clase Administrador sin parámetros", Administrador.class, ad.getClass());
	}

	@Test
	public void testAdministradorStringStringStringStringStringIntIntString() {
        assertEquals("Administrador", a.getApellido());
        assertEquals(223556879, a.getDni());
        assertEquals("no tiene", a.getDomicilio());
        assertEquals("administrador@admin.com", a.getEmail());
        assertEquals("Administrador", a.getNombre());
        assertEquals(123654789, a.getTelefono());
        assertEquals("Probando constructor de clase Administrador con parámetros", Administrador.class, a.getClass());
	}

	@Test
	public void testSetNombre() {
		String nombreNuevo = "Admin";
		ad.setNombre(nombreNuevo);
        assertEquals("Probando propiedad nombre de clase Administrador", nombreNuevo, ad.getNombre());
	}

	@Test
	public void testSetApellido() {
		String apeNuevo = "Admin";
		ad.setApellido(apeNuevo);
        assertEquals("Probando propiedad apellido de clase Administrador", apeNuevo, ad.getApellido());
	}

	@Test
	public void testSetEmail() {
		String mailNuevo = "admin@admin.com";
		ad.setEmail(mailNuevo);
        assertEquals("Probando propiedad mail de clase Administrador", mailNuevo, ad.getEmail());
	}

	@Test
	public void testSetDni() {
		int dniNuevo = 223556854;
		ad.setDni(dniNuevo);
        assertEquals("Probando propiedad dni de clase Administrador", dniNuevo, ad.getDni());
	}

	@Test
	public void testSetTelefono() {
		int telNuevo = 123654765;
		ad.setTelefono(telNuevo);
        assertEquals("Probando propiedad teléfono de clase Administrador", telNuevo, ad.getTelefono());
	}

	@Test
	public void testSetDomicilio() {
		String domNuevo = "no sabe";
		ad.setDomicilio(domNuevo);
        assertEquals("Probando propiedad domicilio de clase Administrador", domNuevo, ad.getDomicilio());
	}

	@Test
	public void testSetId() {
		Long idNuevo = (long) 1;
		ad.setId(idNuevo);
        assertEquals("Probando propiedad id de clase Administrador", idNuevo, ad.getId());
	}

}
