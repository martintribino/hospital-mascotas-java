package ttps.spring.model;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Enfermedad")
public class Enfermedad extends Evento {

	/**
	 * Clase Enfermedad
	 */
	private static final long serialVersionUID = -2596005362855999285L;

	public Enfermedad() {
		super();
	}

	public Enfermedad(
			LocalDate fecha,
			LocalTime inicio,
			LocalTime fin,
			String descripcion,
			Mascota mascota) {
		super(fecha, inicio, fin, descripcion, mascota);
	}

}
