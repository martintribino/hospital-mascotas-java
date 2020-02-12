package ttps.spring.model;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Intervencion")
public class Intervencion extends Evento {

	/**
	 * Clase Intervencion
	 */
	private static final long serialVersionUID = -8738807883108116756L;

	public Intervencion() {
		super();
	}

	public Intervencion(
			LocalDate fecha,
			LocalTime inicio,
			LocalTime fin,
			String descripcion,
			Veterinario veterinario,
			Mascota mascota) {
		super(fecha, inicio, fin, descripcion, veterinario, mascota);
	}

}
