package ttps.spring.test.model;

import static org.junit.Assert.*;

import org.junit.Test;

import ttps.spring.model.Encrypt;
import ttps.spring.model.Persona;
import ttps.spring.model.Usuario;

public class UsuarioTest {
	private Persona p;
	private Usuario u;
	
	public UsuarioTest() {
		p = new Persona();
		u = new Usuario();
	}

	@Test
	public void testSetNombreUsuario() {
		String nombreNuevo = "Admin";
		u.setNombreUsuario(nombreNuevo);
        assertEquals("Probando propiedad nombreUsuario de clase Usuario", nombreNuevo, u.getNombreUsuario());
	}

	@Test
	public void testSetClave() {
		String claveNueva = Encrypt.encode("adm123");
		u.setClave("adm123");
        assertEquals("Probando propiedad clave de clase Usuario", claveNueva, u.getClave());
	}

	@Test
	public void testSetPersona() {
		u.setPersona(p);
		assertEquals("Probando propiedad persona de la clase Usuario",p , u.getPersona());
	}

	@Test
	public void testVerificarClave() {
		String claveNueva = Encrypt.encode("adm123");
		u.setClave(claveNueva);
        assertTrue("Probando metodo verificarClave de clase Usuario", u.verificarClave(claveNueva));
	}

}
