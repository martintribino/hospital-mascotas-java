package ttps.spring.model;

import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Enfermedad")
public class Enfermedad extends Evento {
	/**
	 * Clase Enfermedad
	 */

	public Enfermedad() {
		super();
	}

	public Enfermedad(
			Date fecha,
			String descripcion,
			Boolean concurrio,
			Veterinario veterinario,
			Mascota mascota) {
		super(fecha, descripcion, concurrio, veterinario, mascota);
	}

}
