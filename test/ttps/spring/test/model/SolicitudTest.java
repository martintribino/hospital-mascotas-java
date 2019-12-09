package ttps.spring.test.model;

import static org.junit.Assert.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.junit.Test;

import ttps.spring.model.Mascota;
import ttps.spring.model.Solicitud;
import ttps.spring.model.Veterinario;

public class SolicitudTest {
	private Solicitud s;
	
	public SolicitudTest() {
		Solicitud.Estados estado = Solicitud.Estados.ESPERA;
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date fecha;
		try {
			fecha = formatter.parse("2005-08-08");
		}
		catch(ParseException pe) {
		    throw new IllegalArgumentException(pe);
		}
		Mascota mascota = new Mascota();
		Veterinario veterinario = new Veterinario();
		s = new Solicitud(
				fecha,
				estado,
				mascota,
				veterinario);
        assertEquals("Probando propiedad mascota de clase Solicitud", mascota, s.getMascota());
        assertEquals("Probando propiedad veterinario de clase Solicitud", veterinario, s.getVeterinario());
		s = new Solicitud();
	}

	@Test
	public void testSetFecha() {
		Calendar today = Calendar.getInstance();
		today.clear(Calendar.HOUR); today.clear(Calendar.MINUTE); today.clear(Calendar.SECOND);
		Date todayDate = today.getTime();
		s.setFecha(todayDate);
        assertEquals("Probando propiedad fecha de clase Solicitud", today.getTime(), s.getFecha());
	}

	@Test
	public void testSetEstado() {
		Solicitud.Estados estado = Solicitud.Estados.ESPERA;
		s.setEstado(estado);
        assertEquals("Probando propiedad estado ESPERA de clase Solicitud", estado, s.getEstado());
		estado = Solicitud.Estados.RECHAZADO;
		s.setEstado(estado);
        assertEquals("Probando propiedad estado RECHAZADO de clase Solicitud", estado, s.getEstado());
		estado = Solicitud.Estados.APROBADO;
		s.setEstado(estado);
        assertEquals("Probando propiedad estado APROBADO de clase Solicitud", estado, s.getEstado());
	}

}
