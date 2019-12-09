package ttps.spring.test.model;

import static org.junit.Assert.*;

import org.junit.Test;

import ttps.spring.model.Desparasitacion;

public class DesparasitacionTest {
	private Desparasitacion d;
	
	public DesparasitacionTest() {
		d = new Desparasitacion(); 
	}

	@Test
	public void testSetDroga() {
		String droga = "droga";
		d.setDroga(droga);
        assertEquals("Probando propiedad droga de clase Desparasitacion", droga, d.getDroga());
	}

	@Test
	public void testSetResultado() {
		String resul = "resul";
		d.setResultado(resul);
        assertEquals("Probando propiedad resultado de clase Desparasitacion", resul, d.getResultado());
	}

}
