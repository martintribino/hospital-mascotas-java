package ttps.spring.test.model;

import static org.junit.Assert.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

import ttps.spring.model.Reproduccion;

public class ReproduccionTest {
	private Reproduccion r;
	
	public ReproduccionTest() {
		r = new Reproduccion();
	}

	@Test
	public void testSetFechaParto() {
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date fecha;
		try {
			fecha = formatter.parse("2025-08-08");
		}
		catch(ParseException pe) {
		    throw new IllegalArgumentException(pe);
		}
		r.setFechaParto(fecha);
        assertEquals("Probando propiedad fecha de clase Reproduccion", fecha, r.getFechaParto());
	}

	@Test
	public void testSetNroCachorros() {
		int nro = 2;
		r.setNroCachorros(nro);
        assertEquals("Probando propiedad nroCachorros de clase Reproduccion", nro, r.getNroCachorros());
	}

}
